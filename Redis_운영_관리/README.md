# 레디스 운영관리 정리

## 레디스란?

데이터의 안정적 저장, 빠른 처리를 위한 기술이다. 캐시 솔루션 혹은 NoSQL의 Key-Value 스토어라고도 불린다.

기본 명령어는 [공식 문서](http://redis.io/commands)에서 확인 할 수 있다.

## 레디스의 주요 특성

- Key-Value 스토어 구조를 지원한다.
- 컬렉션을 지원한다.
  - List, Set, Sorted Set, Hash 등..
- Publish / Subscribe 모델을 지원한다.
- 디스크에 저장할 수 있다.
  - Memcached와의 차별점! 영속화 할 수 있다.
  - 현재까지의 업데이트 관련 명령을 저장할 수 있다. (AOF)
- Master / Slave 구조를 지원한다.
- 초당 100,000개의 요청을 처리할 수 있는 빠른 성능을 자랑한다.

### Redis VS Memcached

Redis = Memcached **+ 영속성**

레디스는 **디스크에 저장**할 수 있지만 멤케시는 그렇지 못하다.

다만, **안정성은 레디스가 더 떨어진다.** 레디스는 사용하지 말아야 하는 API가 공개적으로 노출이 되어 있기 때문이다. (ex. JPA의 `findAll()`과 같은.)

응답 속도의 균일성에서도 레디스가 더 떨어지는데 이는 극단적인 상황에서 발생한다. 그리 걱정하지 않아도 될 것 같다.

## 레디스 운영과 관리

### 레디스는 싱글 스레드이다.

싱글 스레드의 특성상 하나의 무거운 작업을 수행하기에는 적합하지 않은 구조이다. 이러한 특징을 이해하지 않고 사용하면 장애가 발생할 수 있다.

#### `keys` 명령어 사용하지 않기

`keys` 명령어는 정규표현식과 함께 사용해서 일치하는 key를 전부 가져오는 작업이다.

실제로 레디스 공식 문서에서 살펴보면 시간 복잡도가 O(N)이다. 굉장히 위험하다. 그래서 공식 문서에서도 프로덕션 환경에서 사용하지 말라고 적혀있다.

#### `flushall` / `flushdb` 명령어 조심하기

레디스의 모든 데이터를 삭제하는 명령어이다.

특정 DB를 지워버리는 명령어가 `flushdb`이고 모든 DB를 지워버리는게 `flushall`이다.

멤케시는 순식간에 모든 데이터를 지워버렸을지 몰라도 레디스는 매우 느리게 지우기 때문에 조심해야 한다.

#### 레디스 영속성 (Persistent)

##### RDB

RDBMS가 아니라 레디스 메모리에 있는 데이터를 덤프뜨는 기능을 의미한다.

기본적으로 덤프를 뜨는 옵션은 true 이다.

덤프를 뜨는 행위는 싱글 스레드 기반의 레디스에게 위험한 작업이다. 그래서 덤프를 뜨는 작업은 fork를 이용해서 자식 프로세스가 처리하도록 한다. 그래서 스냅샷의 개념으로 생각할 수 있다.

`SAVE`와 `BGSAVE` 가 있는데 명령에서 볼수 있듯 `BGSAVE` 가 fork 를 이용해서 덤프를 뜬다. `SAVE`하면 모든 작업이 멈추고 덤프 뜨기를 시작한다.

redis.conf에 다음과 같이 설정해 주면 덤프를 뜰 수 있다.

```
dbfilename dump.rdb
```

명령어로도 가능하다.

```
// 현재 덤프 설정 값 보기
config get dbfilename 

// 덤프 이름 바꾸기
config set dbfilename test.rdb
```

이 명령어는 스케쥴링이 가능한 옵션이다.

BGSAVE [초] [변화량] 을 입력해서 스케쥴을 걸어둘 수 있다.

예를 들어 `BGSAVE 60 1000`로 스케쥴을 걸어두면 60초마다 검사를 하는데 1000번 이상의 변화가 감지되면 그때 스냅샷, 덤프를 뜨기 시작한다.

##### AOF (Append Only File)

데이터를 저장하기 전에 AOF 파일에 현재 수행해야 할 명령어를 저장해 두고 장애가 나면 AOF 기반 복구를 한다.

AOF 파일과 RDB 파일이 동시에 존재한다면 가장 최근의 스냅샷을 가지고 있는 것은 AOF이기 때문에 AOF를 먼저 읽는다.

그런데 **AOF는 기본 설정이 no**로 되어있기 때문에 **appendonly를 yes로 바꿔서 사용**해야 한다.

AOF 파일 저장 시점은 appendfsync 설정 값에 따라 다르다. 속도는 `no > everysec > always`이다.

#### 메모리를 두배로 사용하는 문제

**fork를 이용해서 덤프를 뜨는 순간** 프로세스가 생성된다. 그리고 그 순간 **메모리도 2배로 뻥튀기** 된다. 따라서 10GB 메모리가 20GB로 뻥튀기 될 수 있다.  COW(Copy on Write) 기술로 극복을 하려고 했지만 **쓰기 작업이 많아서 예전처럼 메모리 뻥튀기 문제는 여전히 남아있다.**

#### Read는 하는데 Write는 못하는 문제

ping 체크는 문제 없는데 write만 못하는 경우가 발생하기도 한다.

이러한 문제는 보통 RDB 저장이 실패하면 발생한다. RDB 저장에 실패하면 write 명령을 더 이상 처리하지 않는다.

그렇다면 RDB 저장 실패의 원인은?

- RDB를 저장할 용량이 부족
- 물리적 디스크 고장
- 메모리 부족으로 fork 실패
- 누군가 강제로 자식 프로세스 종료
- 등등..

위와 같은 이유로 RDB 저장이 실패하면 lastbgsave_status의 변수가 `REDIS_ERR`로 설정되면서 write가 실패한다.

위와 같은 이유로 wrtie가 실패하는 상황이라면 `set` 명령어를 통해 `... currently not able to persist on disk. Commands that may modify the data set are disabled. ...` 문구가 포함된 에러 메세지를 확인할 수 있다.

아니면 info 명령어를 통해 `rdb_last_bgsave_status`가 `ok` 인지 확인하는 방법도 있다.

만약 에러가 발생해도 write를 가능하게 하고 싶다면 아래와 같이 행동하자.

1. config set stop-writes-on-bgsave-error no
2. redis.conf :arrow_right: stop-writes-on-bgsave-error no

## 레디스 복제

레디스는 복제기능이 있어서 장애, 서버-장비 교체에 사용할 수 있다.

### 복제 모델 (Master / Slave)

변경은 마스터에서만 일어나고 변경된 부분은 Slave에게 전파되는 형식이다. 한 대의 Slave는 한 대의 Master만 가진다.

꼬리물기 식으로 하나의 Slave는 다른 Slave의 마스터로 동작할 수 있다. 하지만 Root에 위치한 Master는 오직 한 대이다.

`slaveof [ip] [port]` 명령어를 통해 현재 장비를 다른 장비의 Slave로 등록할 수 있다. 혹은 redis.conf에서 다음과 같이 설정하도록 하자.

```
slaveof [masterip] [masterport]
```

Master쪽에서 redis.conf의 Replication쪽 설정을 보면 자신의 Slave를 확인 할 수 있다.

### 주의사항

#### `slaveof no one`

Slave는 Master의 Health Check를 지속적으로 하면서 복제를 수행하는데 한 번 마스터와의 연결이 끊어진 경우 Slave의 모든 데이터가 사라질 수 있는 경우를 의미한다.

Slave는 Master와의 연결이 한 번 끊어졌다 다시 연결 될 때 Master와 동기화를 위해 Master의 모든 정보를 그대로 복제하는데 이 때 Master에 데이터가 비어있다면 Slave도 마찬가지로 비어있게 되버린다.

따라서 장애 판단은 다른 부분에서 한다고 가정하고 일단 장애가 발생한다면 Slave 노드에게 `slaveof no one` 명령어를 이용해서 Slave 노드라는 것을 명시적으로 끊어버리면서 위와 같은 상황에서 데이터를 보존할 수 있다.

#### 복제는 무조건 RDB를 사용한다.

RDB 옵션을 꺼놔도 Slave로 데이터를 전파하는 작업은 fork를 이용해서 RDB를 생성-전파하는 방식이다. 이 때 메모리 뻥튀기가 발생 할 수 있으므로 하나의 레디스가 너무 많은 메모리를 사용하지 않도록 주의하도록 하자.

### 실시간 마이그레이션하기

1. 새로운 장비에서 기존 인스턴스의 레디스를 `slaveof`를 이용해서 마스터로 설정한다.
2. 새로운 장비의 slave-read-only 설정은 끈다.
   - 새로운 장비가 슬레이브로 등록되었지만 데이터의 업데이트가 가능하도록 하자.
3. 클라이언트가 새로운 장비를 Master로 인식하도록 설정한다.
4. 새로운 장비에서 `slaveof no one`을 이용해서 기존 Master와의 연결을 종료한다.

## Redis HA와 Sentinel

Redis 서비스를 운영할 때 기본적으로 Master/Slave 구조를 취한다. 마스터 장애시 슬레이브가 마스터 역할을 할 수 있도록 변경할 수 있게 해야한다. `slave-read-only` 가 `yes` 로 되어있으면 쓰기 요청이 모두 실패한다.

장애 발생시 다음과 같은 흐름으로 작업을 한다.

1. **마스터의 장애를** 정확히 **판별**한다.
2. **슬레이브를 마스터로** 승격시킨다.
3. 해당 작업 내용을 **클라이언트에게 통지**한다.

위 작업이 힘들어서 Sentinel 이라는 데몬을 이용해서 처리한다.

Sentinel에서 장애가 발생한 마스터에 접속한 Client를 알 방법이 없기 때문에 해당 알림을 원하는 클라이언트는 Redis Pub/Sub으로 Sentinel에 등록해야 한다.

### Redis Ha와 Sentinel 구성

Redis Master 의 장애 발생 :arrow_right: Sentinel이 슬레이브를 한 대 선택하여 마스터로 승격 :arrow_right: Pub/Sub 으로 변경을 통지 :arrow_right: Client가 Master로 접속 변경

### Sentinel은 어떻게 장애를 판별할까?

기본적으로 마스터/슬레이브에 PING 명령의 응답을 보고 판단하는데 응답이 없다고 바로 장애로 판단하지는 않는다.

`SDOWN`, `ODOWN` 두 가지 상태로 판별한다.

SDOWN: Subjectively Down, Sentinel 하나가 해당 서버를 장애로 인식하는 주관적 상태

ODOWN: Objectively Down, 여러 대의 Sentinel이 해당 서버를 장애로 인식한 객관적 상태

장애를 판단하는 정족수인 Failover 설정을 잘 설정해야하는데, 정족수가 3인데 Sentinel이 2라면 정족수를 채울수 없어서 장애가 발생해도 슬레이브를 마스터로 승격시킬수 없다. 따라서 장비를 홀수로, 장비의 과반수를 정족수로 두는 것이 안전하다.

### Sentinel은 마스터로 승격할 슬레이브를 어떻게 선택할까?

새로운 마스터는 ODOWN 상태일 때 선출한다. 선출 플로우는 다음과 같다.

1. SDOWN, ODOWN, DISCONNECT 슬레이브 제외
2. 'last_avail_time' < 'info_validity_time' 제외
3. 'info_refresh' < 'info_validity_time' 제외
4. 'master_link_down_time' > 'max_master_down_time' 제외
5. 후보 중 slave_priority가 높은 순, runid가 큰 순으로 선택

### Sentinel 설정과 사용

```
# sentinel.conf

	sentinel moniter resque 127.0.0.1 2001 2					# -- 1
	sentinel down-after-milliseconds resque 3000			# -- 2
	sentinel failover-timeout resque 900000						# -- 3
	sentinel can-failover resque yes									# -- 4
sentinel parallel-syncs resque 1										# -- 5
```

1. sentinel monitor <클러스터 명> <마스터 IP> <마스터 Port> <정족수>
   - 모니터링 할 마스터 서버의 주소, 클러스터 이름, 정족수
2. sentinel down-after-milliseconds <클러스터 명> <시간 milliseconds>
   - 다운으로 인식하는 시간
3. sentinel failover-timeout <클러스터 명> <시간 milliseconds>
   - Failover시 사용되는 timeout
4. sentinel can-failover rescue yes
   - Failover 여부
5. sentinel parallel-syncs <클러스터 명> <sync 할 slave 숫자>
   - 새로운 마스터 승격 후 몇 개의 슬레이브가 싱크해야 클라이언트에게 알려줄 것인지

```
+switch-master
<클러스터 명> <이전 마스터 IP> <Port> <새 마스터 IP> <Port>
```

위 메세지를 받은 클라이언트는 새 마스터 IP와 Port를 이용해서 서버 목록을 바꿔서 다시 접속하면 Failover가 완료된다.

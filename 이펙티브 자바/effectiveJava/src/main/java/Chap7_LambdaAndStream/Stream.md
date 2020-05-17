### 스트림 매핑 - 모던 자바 인 액션

#### 매핑이란?
특정 객체에서 특정 데이터를 선택한다.


#### 스트림의 각 요소에 함수 적용하기
함수를 적용한 결과는 고치는 것보다 새로운 버전을 만드는 개념에 가깝기 때문에 "매핑"이라고 칭한다.

```java
    void mapping(){
        List<String> words = Arrays.asList("Java", "Bom", "Study");
        List<Integer> lengths = words.stream()
                .map(String::length)
                .collect(Collectors.toList());
    }
```


#### 스트림 평면화

Q. 주어진 스트링리스트의 각 요소 중 고유문자만 뽑아내서 하나의 리스트로 반환하기
```java
    @DisplayName("잘못된 map의 사용")
    @Test
    void wrongCase() {
        //given
        List<String> wrongCase = Arrays.asList("HELLO", "WORLD");

        //when
        List<String[]> collect = wrongCase.stream()
                .map(word -> word.split(""))
                .distinct()
                .collect(Collectors.toList());

        //then // 하나도 안걸러짐
        // Why -> ["H","E", "L", "L", "O] ["W","O","R", "L", "D] 는 다르니까 distinct 로 걸러질 수 없음
        for (String[] str : collect){
            for (int i = 0; i <str.length ; i++) {
                System.out.printf("%s",str[i]);
            }
            System.out.println();
        }
    }
```

```java
     @DisplayName("두번째 올바르지 않은 map의 사용")
    @Test
    void rightCase() {
        //given
        List<String> wrongCase = Arrays.asList("HELLO", "WORLD");

        //when
        List<Stream<String>> result = wrongCase.stream()
                .map(word -> word.split("")) // Stream<String[]>
                .map(Arrays::stream) // Stream<List<String>>, 스트림 시퀀스에 대한 스트림을 변환해준다
                .distinct()
                .collect(Collectors.toList());//List<Stream<String>>, 그래서 여기에서 Stream List로 수집된다
        
    }
```

map을 사용했을 때 원하는 답이 안나온다.
해결책은 스트림의 시퀀스가 스트림이어서 스트림 리스트로 수집되는 것을 하나의 스트림안으로 "평면화"시키는데 있다.

flatMap은 각 배열을 스트림의 컨텐츠로 매핑한다.

```java
    @DisplayName("flatMap으로 사용하여 평면화한다")
    @Test
    void flatMapTest(){
        //given
        List<String> words = Arrays.asList("HELLO", "WORLD");

        // 두개의 시퀀스를 하나로 평면화해야한다.
        //when
        List<String> goodResult = words.stream()
                .map(word -> word.split(""))
                .flatMap(Arrays::stream) // 하나의 스트림으로 반환되고
                .distinct() // 그제서야 원하는 distinct가 수행된다
                .collect(Collectors.toList());

        assertThat(goodResult).isEqualTo(Arrays.asList("H","E","L","O","W","R","D"));
    }

```



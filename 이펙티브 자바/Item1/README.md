#### 서비스 제공자 프레임워크와 JDBC 



**서비스 인터페이스** 

구현체의 동작을 정의한다.  Connection 클래스가 그 예이다.



**서비스 접근 API**

클라이언트가 서비스의 인스턴스를 얻을 때 사용하는 API. DriverManager.getConnection() 이 해당한다.

```java
Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/jdbc?serverTimezone=UTC", "root", "as242526"); // 서비스 인터페이스
```

DriverManager가 등록된 JDBC 드라이버가 제공하는 Connection 을 반환한다. 여기서는 com.mysql.cj.jdbc.Driver 를 사용하였다.

이 DriverManager.getConnection 은 등록된 Driver 에 따른 Connection 구현체를 반환하기 때문에 "유연한 정적팩터리"의 실체라고 표현된다.(p.11)



**제공자 등록 API**

제공자가 구현체를 등록할 때 사용하는 API.

본 예에서는 registerDriver API에 해당한다. java.sql.Driver 를 구현한 com.mysql.cj.jdbc.Driver 이다. 

```java
DriverManager.registerDriver(driver); // 제공자 등록 API
```



추가로 등장하는 개념 **서비스 제공자 인터페이스(SPI)**  는 서비스 인터페이스의 인스턴스를 생성하는 팩터리 객체를 말한다. java.sql.Driver 가 해당한다.  
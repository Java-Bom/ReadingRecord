package item1.jdbc;

import com.mysql.cj.jdbc.Driver;

import java.sql.*;

public class JdbcSample {

    public static void main(String[] args) throws SQLException {

        /**
         *  implements java.sql.Driver
         *  java.sql.Driver: 서비스 제공자 인터페이스
         *  com.mysql.cj.jdbc.Driver 는 이 인터페이스를 구현하여 제공자 등록 API 를 통해 등록되고 갈아끼워진다.
         */
        Driver driver = new Driver();
        DriverManager.registerDriver(driver); // 제공자 등록 API

        /** Class.forName("com.mysql.cj.jdbc.Driver") 을 통해 Driver 등록도 가능하다.
         *  DriverManager 는 static 필드에서 Driver 를 등록받기 때문에
         *  ClassLoader 수행 시 mysql Driver 를 주입받는다. 본 예제에서는 registerDriver API 를 사용한다.
         */

        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/jdbc?serverTimezone=UTC", "root", "as242526"); // 서비스 인터페이스

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM test");


        while(resultSet.next()){
            System.out.println(resultSet.getString(1));
            System.out.println(resultSet.getString(2));
        }


    }
}

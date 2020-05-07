package Chap2_GenerateObjectAndDestroy.item7;

import Chap2_GenerateObjectAndDestroy.item7.vo.DBConnection;
import Chap2_GenerateObjectAndDestroy.item7.vo.ResultSet;
import lombok.Getter;

@Getter
public class CustomDatabaseManager implements DatabaseManager {

    // 이 인터페이스는 외부에서 정의되어서 참조된다. 그래서 정의한 클라이언트가 더이상 이 인터페이스를 사용하지 않아도 gc 대상이 될 수 없다.
    private Connection connection;

    public CustomDatabaseManager(Connection connection) {
        System.out.println("Setting Connection");
        this.connection = connection;
    }

    // 필드에 참조된 콜백이 gc 대상이 되는 방법: 명시적으로 null 처리하는 로직이 있어야하고 Client 는 이 메서드를 요청해야함.
    public void deconnect() {
        this.connection = null;
    }

    @Override
    public ResultSet execute(final String query) {
        System.out.println(query + " 실행...");
        DBConnection dbConnection = connection.getDBConnection();
        return ResultSet.createResultSet(dbConnection);
    }

}


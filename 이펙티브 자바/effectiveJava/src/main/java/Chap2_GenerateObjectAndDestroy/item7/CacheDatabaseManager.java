package Chap2_GenerateObjectAndDestroy.item7;

import Chap2_GenerateObjectAndDestroy.item7.vo.DBConnection;
import Chap2_GenerateObjectAndDestroy.item7.vo.ResultSet;

import java.util.WeakHashMap;

public class CacheDatabaseManager implements DatabaseManager{

    private static final String CACHE_KEY = "Connection Interface";

    // WeakHashMap 같이 시간이 지남에 따라 더이상 참조되지 않으면 gc 대상이 될 수 있도록 하는 클래스 사용.
    private WeakHashMap<Connection, Object> connections = new WeakHashMap<>();

    public CacheDatabaseManager(Connection connections) {
        this.connections.put(connections, CACHE_KEY);
    }

    public Connection getConnection() {
        if(connections.size() <= 0){
            throw new RuntimeException();
        }
        return (Connection) connections.get(CACHE_KEY);
    }

    @Override
    public ResultSet execute(final String query) {
        System.out.println("Cache 에서 꺼낸 Connection 으로 쿼리 실행");
        Connection connection = (Connection) this.connections.get(CACHE_KEY);
        DBConnection dbConnection = connection.getDBConnection();
        /** Connection 을 사용하여 Result 를 얻어오는 로직 ~ **/
        return new ResultSet();
    }
}

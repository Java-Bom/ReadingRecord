package item7;

import item7.vo.DBConnection;
import item7.vo.ResultSet;

import java.util.WeakHashMap;

public class CustomDatabaseManager {

    // 이 인터페이스는 외부에서 정의되어서 참조된다. 그래서 정의한 클라이언트가 더이상 이 인터페이스를 사용하지 않아도 gc 대상이 될 수 없다.
    private ConnectionInterface connectionInterface;

    // 1. 필드에 참조된 콜백이 gc 대상이 되는 방법: 명시적으로 null 처리하는 로직이 있어야하고 Client 는 이 메서드를 요청해야함.
    public void clearConnectionInterface(){
        this.connectionInterface = null;
    }

    // 2. WeakHashMap 같이 시간이 지남에 따라 더이상 참조되지 않으면 gc 대상이 될 수 있도록 하는 클래스 사용.
    private WeakHashMap<ConnectionInterface, Object> connectionInterfaceObjectWeakHashMap = new WeakHashMap<>();


    public ConnectionInterface getConnectionInterface() {
        return connectionInterface;
    }

    public WeakHashMap<ConnectionInterface, Object> getConnectionInterfaceObjectWeakHashMap() {
        return connectionInterfaceObjectWeakHashMap;
    }

    public void setConnectionInterface(ConnectionInterface connectionInterface) {
        System.out.println("register connectionInterface...");
        this.connectionInterface = connectionInterface;
    }
    public void setConnectionInterfaceObjectWeakHashMap(ConnectionInterface connectionInterface) {
        this.connectionInterfaceObjectWeakHashMap.put(connectionInterface, "Connection Interface");
    }

    public ResultSet execute(final String query){
        System.out.println(query+" 실행...");
        DBConnection dbConnection = connectionInterface.getDBConnection();
        return ResultSet.createResultSet(dbConnection);
    }

    interface ConnectionInterface{
        DBConnection getDBConnection();
    }

}

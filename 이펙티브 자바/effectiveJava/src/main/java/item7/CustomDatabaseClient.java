package item7;

import item7.vo.DBConnection;

public class CustomDatabaseClient {
    public static void main(String[] args) throws InterruptedException {

        // 필드에 참조될 콜백
        CustomDatabaseManager.ConnectionInterface connectionInterface = new CustomDatabaseManager.ConnectionInterface() {
            @Override
            public DBConnection getDBConnection() {
                System.out.println("Client 에서 정의한 connectionInterface");
                System.out.println("load Connection...");
                return new DBConnection();
            }
        };

        // WeakHashMap 에 참조될 콜백
        CustomDatabaseManager.ConnectionInterface connectionInterfaceForWeakHashMap = new CustomDatabaseManager.ConnectionInterface() {
            @Override
            public DBConnection getDBConnection() {
                System.out.println("Client 에서 정의한 connectionInterfaceForWeakGashMap");
                System.out.println("load Connection...");
                return new DBConnection();
            }
        };


        CustomDatabaseManager customDatabaseManager = new CustomDatabaseManager();

        customDatabaseManager.setConnectionInterface(connectionInterface); // 필드저장
        customDatabaseManager.setConnectionInterfaceObjectWeakHashMap(connectionInterfaceForWeakHashMap); // WeakHashMap 저장

//        customDatabaseManager.execute("SELECT ID FROM TABLE");


        // 참조해제 시도
        connectionInterface = null;
//        customDatabaseManager.clearConnectionInterface();
        connectionInterfaceForWeakHashMap = null;

        System.gc();
        System.runFinalization();

        Thread.sleep(3000);


        if(customDatabaseManager.getConnectionInterface() != null){
            System.out.println("필드에 저장된 ConnectionInterface 는 gc 대상이 아닙니다."); // 출력
        }
        if(customDatabaseManager.getConnectionInterfaceObjectWeakHashMap().size() > 0 ){
            System.out.println("WeakHashMap 에 저장된 ConnectionInterface 는 gc 대상이 아니었습니다."); // 미출력
        }


    }
}

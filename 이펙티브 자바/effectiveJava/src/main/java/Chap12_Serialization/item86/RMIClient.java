package Chap12_Serialization.item86;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIClient {
    public static void main(String[] args) {
        String host = (args.length < 1) ? null : args[0];
        try {
            // Rmi registry에 서버 IP, port를 설정한다.
            Registry registry = LocateRegistry.getRegistry(host);

            // Rmi registry.lookup을 통해 gongyumin이름을 찾아 stub을 가져온다.
            Sample stub = (Sample) registry.lookup("sampleRmi");

            // server의 함수를 호출한다.
            String ResultEcho = stub.Echo("Hello World");
            int ResultAdd = stub.Add(125, 175);

            System.out.println("ResultEcho  : " + ResultEcho);
            System.out.println("ResultAdd   : " + ResultAdd);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}

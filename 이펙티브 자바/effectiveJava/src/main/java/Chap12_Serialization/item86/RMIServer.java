package Chap12_Serialization.item86;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class RMIServer implements Sample {
    public static void main(String[] args) {
        try {
            RMIServer rmiServer = new RMIServer();
            Sample stub = (Sample) UnicastRemoteObject.exportObject(rmiServer, 0);

            Registry registry = LocateRegistry.getRegistry();
            registry.bind("sampleRmi", stub);

            System.out.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }

    @Override
    public int Add(final int num1, final int num2) throws RemoteException {
        return num1 + num2;
    }

    @Override
    public String Echo(final String msg) throws RemoteException {
        return msg;
    }
}

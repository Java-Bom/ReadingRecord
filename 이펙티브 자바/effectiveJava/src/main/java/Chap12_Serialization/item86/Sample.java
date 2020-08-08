package Chap12_Serialization.item86;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Sample extends Remote {
    int Add(int num1, int num2) throws RemoteException;

    String Echo(String msg) throws RemoteException;
}

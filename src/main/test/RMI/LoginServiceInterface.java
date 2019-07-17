package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface LoginServiceInterface extends Remote {
    public String login(String username, String password) throws RemoteException;
}

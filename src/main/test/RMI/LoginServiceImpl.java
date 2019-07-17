package RMI;

/**
 * @version 1.0
 * @description: //TODO description
 * @author: 林经翀（jingchong.lin@ucarinc.com）
 * @date 2019/7/15 17:38
 */

import java.rmi.RemoteException;

public class LoginServiceImpl implements LoginServiceInterface {

    @Override
    public String login(String username, String password) throws RemoteException {
        if ("GYD".equals(username) && "123456".equals(password)) {
            return "Login successfully";
        } else {
            return "Error!";
        }
    }

}
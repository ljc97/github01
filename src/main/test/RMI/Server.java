package RMI;

/**
 * @version 1.0
 * @description: //TODO description
 * @author: 林经翀（jingchong.lin@ucarinc.com）
 * @date 2019/7/15 17:38
 */

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

    public static void main(String[] args) {
        //ע创建注册管理器
        Registry registry = null;
        try {
            // 注册9999端口
            registry = LocateRegistry.createRegistry(9999);

        } catch (RemoteException e) {

        }
        try {
            // 创建实例
            LoginServiceInterface server = new LoginServiceImpl();
            // 绑定
            registry.rebind("loginService", server);

            System.out.println("==========bind server successfully===========");

            while (true) {

            }

        } catch (RemoteException e) {
            System.out.println("==========Error:Bind server===========");
        }

    }

}

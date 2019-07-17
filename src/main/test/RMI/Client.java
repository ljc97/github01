package RMI;

/**
 * @version 1.0
 * @description: //TODO description
 * @author: 林经翀（jingchong.lin@ucarinc.com）
 * @date 2019/7/15 17:41
 */

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Client {

    public static void main(String[] args) {
        Client client = new Client();
        client.loginTest();
    }

    public void loginTest() {
        Registry registry = null;

        try {
            // 获取服务注册管理器
            registry = LocateRegistry.getRegistry("127.0.0.1", 9999);


            // 列出所有注册的服务
            String[] list = registry.list();
            for (String s : list) {
                System.out.println(s);
            }

            // 根据命名获取服务
            LoginServiceInterface server = (LoginServiceInterface) registry.lookup("loginService");
            // 调用远程方法
            String result = server.login("GYD", "123456");
            // 输出调用结果
            System.out.println("登录结果： " + result);
        } catch (AccessException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }

}
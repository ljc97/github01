package 网络编程;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * @version 1.0
 * @description: //TODO description
 * @author: 林经翀（jingchong.lin@ucarinc.com）
 * @date 2019/7/15 16:18
 */
public class Serve implements Runnable {
    //服务端
    private static final int PORT = 80;


    @Override
    public void run() {
        try {
            //新建抽象地址对象
            SocketAddress socketAddress = new InetSocketAddress(PORT);
            //新建服务端Socket资源
            ServerSocket serverSocket = new ServerSocket();
            //绑定新的Socket资源至目标地址和端口
            serverSocket.bind(socketAddress);
            //等待连接建立
            Socket socket = serverSocket.accept();
            //获取输入流
            DataInputStream in = new DataInputStream(socket.getInputStream());
            //获取输入字符串
            String express = in.readUTF();
            System.out.println("服务端收到消息：" + express);
            //计算
            String result = calc(express);
            //获取输出流
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            //输出结果字符串
            out.writeUTF(result);
            //关闭连接
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String calc(String express) {
        if (express.indexOf("+") == -1) {
            try {
                throw new Exception("没有正确的加法算式！");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String[] split = express.split("\\+");

        int sum = Integer.parseInt(split[0]) + Integer.parseInt(split[1]);
        return sum + "";
    }
}

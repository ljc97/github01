package 网络编程;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @version 1.0
 * @description: //TODO description
 * @author: 林经翀（jingchong.lin@ucarinc.com）
 * @date 2019/7/15 16:22
 */
public class Client implements Runnable {

    //客户端
    private static final int PORT = 80;

    @Override
    public void run() {

        try {
            // 向本地的80端口建立TCP连接
            Socket socket = new Socket("127.0.0.1", PORT);
            System.out.println("传输表达式：");
            String express = new Scanner(System.in).nextLine();
            // 获取输出流
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            // 输出请求字符串
            dos.writeUTF(express);
            //获取输入流
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            // 读取响应字符串
            String result = dis.readUTF();
            System.out.println("客户端接收到的消息：" + result);
            socket.close();
        } catch (Exception e) {

        }
    }
}

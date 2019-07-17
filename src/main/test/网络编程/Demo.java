package 网络编程;

/**
 * @version 1.0
 * @description: //TODO description
 * @author: 林经翀（jingchong.lin@ucarinc.com）
 * @date 2019/7/15 16:25
 */
public class Demo {
    public static void main(String[] args) throws InterruptedException {
        Thread serve = new Thread(new Serve());
        Thread client = new Thread(new Client());
        serve.start();
        Thread.sleep(500);
        client.start();
    }
}

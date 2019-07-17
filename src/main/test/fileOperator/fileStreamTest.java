package fileOperator;

import java.io.*;

/**
 * @version 1.0
 * @description: //TODO description
 * @author: 林经翀（jingchong.lin@ucarinc.com）
 * @date 2019/7/13 15:37
 */
public class fileStreamTest {
    public static void main(String[] args) throws IOException {
        String path = "E:/a.txt";

        File file = new File(path);
        FileOutputStream fos = new FileOutputStream(file);
        // 构建FileOutputStream对象,文件不存在会自动新建

        OutputStreamWriter writer = new OutputStreamWriter(fos, "UTF-8");
        // 构建OutputStreamWriter对象,参数可以指定编码,默认为操作系统默认编码,windows上是gbk

        writer.append("中文输入");
        // 写入到缓冲区

        writer.append("\r\n");
        // 换行

        writer.append("English");
        // 刷新缓存冲,写入到文件,如果下面已经没有写入的内容了,直接close也会写入

        writer.close();
        // 关闭写入流,同时会把缓冲区内容写入文件,所以上面的注释掉

        fos.close();
        // 关闭输出流,释放系统资源

        FileInputStream fis = new FileInputStream(file);
        // 构建FileInputStream对象

        InputStreamReader reader = new InputStreamReader(fis, "UTF-8");
        // 构建InputStreamReader对象,编码与写入相同

        StringBuffer sb = new StringBuffer();
        while (reader.ready()) {
            sb.append((char) reader.read());
            // 转成char加到StringBuffer对象中
        }
        System.out.println(sb.toString());
        reader.close();
        // 关闭读取流

        fis.close();
        // 关闭输入流,释放系统资源

    }
}

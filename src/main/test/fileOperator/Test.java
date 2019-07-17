package fileOperator;

import java.io.*;

/**
 * @version 1.0
 * @description: //TODO description
 * @author: 林经翀（jingchong.lin@ucarinc.com）
 * @date 2019/7/13 16:03
 */
public class Test {
    //编码集
    private static final String CHARSET_ENCODING = "UTF-8";

    //    文件地址
    private static final String PATH = "e:/a.txt";

    public static void main(String[] args) throws Exception {
        Test test = new Test();
        File file = new File(PATH);
//        test.writeFile("zzz", file, true);

        long start1 = System.currentTimeMillis();
        test.readFile(file);
        long end1 = System.currentTimeMillis();

        long start2 = System.currentTimeMillis();
        test.bufferedReader(file);
        long end2 = System.currentTimeMillis();

        System.out.println(end1 - start1);
        System.out.println(end2 - start2);


    }

    private boolean isDirectory(File file) {
        return file.isDirectory();
    }

    private void readFile(File file) throws Exception {
        if (isDirectory(file)) {
            throw new Exception(file.getName() + "is not a file");
        }

        try (FileInputStream fis = new FileInputStream(file);
             InputStreamReader reader = new InputStreamReader(fis, CHARSET_ENCODING);) {
            StringBuffer sb = new StringBuffer();
            while (reader.ready()) {
                sb.append(((char) reader.read()));
            }
            System.out.println(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void writeFile(String msg, File file, boolean append) throws Exception {
        if (isDirectory(file)) {
            throw new Exception(file.getName() + "is not a file");
        }

        try (FileOutputStream fos = new FileOutputStream(file, append);
             OutputStreamWriter os = new OutputStreamWriter(fos, CHARSET_ENCODING)) {
            os.write(msg);
            os.write("\n");
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void bufferedReader(File file) throws IOException {
        try (FileInputStream fis = new FileInputStream(file);
             InputStreamReader fReader = new InputStreamReader(fis, CHARSET_ENCODING);
             BufferedReader br = new BufferedReader(fReader);) {
            while (br.ready()) {
                System.out.println(br.readLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}

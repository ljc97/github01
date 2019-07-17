package 序列化;

import lombok.Data;

import java.io.*;

/**
 * @version 1.0
 * @description: //TODO description
 * @author: 林经翀（jingchong.lin@ucarinc.com）
 * @date 2019/7/15 10:07
 */
public class SerializeDemo {

    public static void main(String[] args) {
        String filePath = "E:/person.ser";
        Person person = new Person();
        person.setId(1);
        person.setName("zzz");
        SerializeDemo.writeObject(person, filePath);
        Person p = SerializeDemo.readObject(filePath, Person.class);
        System.out.println(p.toString());
    }

    /**
     * @param t
     * @param path
     * @return void
     * @description //将一个对象序列化到指定路径
     * @author 林经翀（jingchong.lin@ucarinc.com）
     * @date 2019/7/15 11:16
     **/
    public static <T extends Serializable> void writeObject(T t, String path) {
        File file = new File(path);
        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos);) {
            oos.writeObject(t);
            oos.flush();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public static <T extends Serializable> T readObject(String path, Class<T> clazz) {
        File file = new File(path);
        Object obj = null;
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis);) {
            obj = ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ((T) obj);
    }
}


@Data
class Person implements Serializable {
    private int id;
    private String name;
    private char sex;
    private transient String passWod;

}


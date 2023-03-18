package $_015_io;

import javax.swing.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author William
 * @date 2020/9/25 6:10 PM
 * @description
 */
public class Demo {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

//        File file = new File("demo_files/file_test.txt");
//        System.out.println(file.getParentFile().getAbsolutePath()); // /Users/delores/Hubs/Art/A_Projects/demo-modules/files
//        if (!file.getParentFile().exists()) {
//            file.getParentFile().mkdirs();
//        }
//
////        if (!file.exists()) {
////            file.createNewFile();
////        }
////        不用创建文件，下面的流操作会自动创建文件
//
//        OutputStream fos1 = new FileOutputStream(file);
//
//        String msg = "Delores 中国";
//        // 字节流
//        fos1.write(msg.getBytes());
//        fos1.close();
//
//        // Autocloseable 不用手动关闭流
//        try (OutputStream fos2 = new FileOutputStream(file, true)) {
//            fos2.write(msg.getBytes());
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }


//        InputStream input = new FileInputStream(file);
//        byte[] data = new byte[1024];
//        int len = input.read(data);
//        System.out.println("[" + new String(data, 0, len) + "]");
//        input.close();


//        Writer writer = new FileWriter(file);
//        writer.write("Hello World");
//        // 可以追加输出
//        writer.append("Delores  中国");
//        writer.close();
//
//
//
//
//        Reader reader = new FileReader(file);
//        // 1Kb == 1024byte
//        // 128 char == 128 * 8 == 1024byte == 1kb
//        char[] data = new char[128];
//        int len = reader.read(data);
//        System.out.println("【" + new String(data, 0 ,len) + "】");
//        reader.close();

//        OutputStream output = new FileOutputStream(file);
//        Writer out = new OutputStreamWriter(output);
//        out.write("Delores");


//        InputStream input = new FileInputStream(new File("files/demo.txt"));
//        OutputStream output = new FileOutputStream(new File("demo_files/demo.txt"));
//        input.transferTo(output);
//        input.close();
//        output.close();

//        FileUtil fu = new FileUtil(new File("/Users/delores/Hubs/Art/A_Projects/demo-modules/files"), new File("/Users/delores/Hubs/Art/A_Projects/demo-modules/demo_files"));
//        try {
//            fu.copy();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        File file = new File("demo_files/file_test.txt");

        // public static final PrintStream out = null;
//        OutputStream os = System.out;
//
//        PrintWriter out = new PrintWriter(file);
//        out.println("haha");
//        out.close();
//
//        PrintStream ps = new PrintStream(file);
//        ps.println("中国");
//        ps.close();
//
//
//        // public static final InputStream in = null;
//        InputStream in = System.in;
//
//        System.out.print("请输入信息：");
//        byte[] data = new byte[1024];
//        int len = in.read(data);
//        System.out.println("内容为：" + new String(data, 0, len));
//
//
//        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
//        String msg = input.readLine();
//        System.out.println("MSG: " + msg);

//        BufferedReader bf = new BufferedReader(new FileReader(file));
//        String ms = null;
//        while ((ms = bf.readLine()) != null) {
//            System.out.println(ms);
//        }
//        bf.close();
//        中国
//        世界
//        No.1


//        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
//        bw.write("delores");
//        bw.append("china");
//        bw.close();
//
//        FileWriter fw = new FileWriter(file);
//        fw.write("william");
//        fw.append("Wow");
//        fw.close();
//
//
//        Scanner scanner = new Scanner(System.in);
//        while (scanner.hasNext()) {
//            System.out.println(scanner.next());
//        }


        IMessage message1 = Factory.getInstance("$_015_io.NetMessage", IMessage.class);
        message1.send();
    }
}

class Factory {
    private Factory() {};

    @SuppressWarnings("unchecked")
    public static <T> T getInstance(String className, Class<T> clazz) {
        T instance = null;
        try {
            instance = (T) Class.forName(className).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }
}



interface IMessage {
    public void send();
}

class NetMessage implements IMessage {
    @Override
    public void send() {
        System.out.println("send network message");
    }
}


class Person implements Serializable {

    private static final long serialVersionUID = 5861254288888150983L;
    private transient String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

class FileUtil {

    private File src;
    private File des;

    public FileUtil(File src, File des) {
        this.src = src;
        this.des = des;
    }

    public void copy() throws IOException {
        if (this.src.isFile()) {
            copyFile(this.src, this.des);
        } else {
            copyDir(this.src);
        }
    }

    private void copyDir(File file) throws IOException {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (int i = 0; i < files.length; i++) {
                    copyDir(files[i]);
                }
            }
        } else {
            String newFilePath = file.getPath().replace(this.src.getPath() + File.separator, "");
            File newFile = new File(this.des, newFilePath);
            copyFile(file, newFile);
        }
    }

    private void copyFile(File srcFile, File desFile) throws IOException {
        if (!srcFile.exists()) {
            System.out.println("Source File doesn't exist.");
        }
        if (!desFile.getParentFile().exists()) {
            desFile.getParentFile().mkdirs();
        }
        try (InputStream input = new FileInputStream(srcFile); OutputStream output = new FileOutputStream(desFile)) {
//            input.transferTo(output);
        }
    }
}

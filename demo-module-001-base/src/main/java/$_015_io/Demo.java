package $_015_io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author William
 * @date 2020/9/25 6:10 PM
 * @description
 */
public class Demo {

    public static void main(String[] args) throws IOException {

        File file = new File("files/file_test.txt");
        if (!file.exists()) {
            file.createNewFile();
        }

        FileInputStream fio = new FileInputStream(file);

    }
}

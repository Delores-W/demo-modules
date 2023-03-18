package test;

/**
 * @author William
 * @date 9/8/21 7:52 PM
 * @description
 */
public class Main {


    public static void main(String[] argv) {
        for (int i = 0; i < 10; i++) {
            if (i == 7) {
                continue;
            }
            System.out.print(i + " ");
        }
    }

}

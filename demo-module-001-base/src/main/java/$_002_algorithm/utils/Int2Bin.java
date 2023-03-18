package $_002_algorithm.utils;

/**
 * @author William
 * @date 2022/3/29 11:26 AM
 * @description
 */
public class Int2Bin {

    public static void main(String[] args) {
        System.out.println(toBinary(8));
        System.out.println(toBinary(67));

        StringBuilder sb = new StringBuilder();
        sb.append("Delores");
        sb.insert(0, 'A');
        // ADelores
        sb.insert(0, 'B');
        // BADelores
        System.out.println(sb);
    }

    public static String toBinary(int num) {
        StringBuilder str = new StringBuilder();
        while (num != 0) {
            str.insert(0, num % 2);
            num = num / 2;
        }
        return str.toString();
    }
}

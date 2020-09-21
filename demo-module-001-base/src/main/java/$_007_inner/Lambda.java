package $_007_inner;

/**
 * @author William
 * @date 2020/9/8 4:52 PM
 * @description
 */
public class Lambda {

    public static void main(String[] args) {

        IMsg message = msg -> System.out.println(msg);
        message.send("delores");

        IMsg message1 = msg -> {
            System.out.println(msg);
        };
        message1.send("delores");


        IMath math = (a, b) -> {
            return a + b;
        };
        math.add(10, 20);

        IMath math1 = (a, b) -> a + b;
        math1.add(10, 40);
    }
}

@FunctionalInterface
interface IMsg {
    void send(String message);
}

@FunctionalInterface
interface IMath {
    int add(int a, int b);
}
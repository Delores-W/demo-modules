package $_007_inner;

/**
 * @author William
 * @date 2020/9/8 4:15 PM
 * @description
 */
public class TestAnonymousClass {

    public static void main(String[] args) {
        IMessage.getInstance().send("Delores");
    }
}

interface IMessage {
    void send(String message);

    static IMessage getInstance() {
        IMessage message = new IMessage() {
            @Override
            public void send(String message) {
                System.out.println(message);
            }
        };
        return message;
    }
}


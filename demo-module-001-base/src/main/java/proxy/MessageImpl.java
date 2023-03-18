package proxy;

/**
 * @author William
 * @date 4/3/21 10:55 PM
 * @description
 */
public class MessageImpl implements Message {
    @Override
    public void send() {
        System.out.println("send message");
    }
}

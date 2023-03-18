package proxy;

/**
 * @author William
 * @date 4/3/21 11:07 PM
 * @description
 */
public class Test {

    public static void main(String[] args) {
        MessageProxy messageProxy = new MessageProxy(new MessageImpl());
        Message message = (Message) messageProxy.getProxyInstance();
        message.send();
    }
}

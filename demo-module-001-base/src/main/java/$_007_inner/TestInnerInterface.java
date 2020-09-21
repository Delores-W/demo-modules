package $_007_inner;

/**
 * @author William
 * @date 2020/9/8 11:53 AM
 * @description
 */
public class TestInnerInterface {

    public static void main(String[] args) {
        IMessageWrap.send(new DefaultMessage(), new NetChannel());
    }
}


interface IMessageWrap { // 消息包装

    static interface IMessage {
        public String getContent();
    }

    interface IChannel {
        public boolean connect();
    }

    public static void send(IMessage message, IChannel channel) {
        if (channel.connect()) {
           String str = message.getContent();
            System.out.println(str);
        } else {
            System.out.println("消息通道无法建立");
        }
    }

}

class DefaultMessage implements IMessageWrap.IMessage {
    @Override
    public String getContent() {
        return "Delores";
    }
}

class NetChannel implements IMessageWrap.IChannel {
    @Override
    public boolean connect() {
        return true;
    }
}



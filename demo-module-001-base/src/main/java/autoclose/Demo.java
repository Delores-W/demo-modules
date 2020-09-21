package autoclose;

/**
 * @author William
 * @date 2020/9/15 12:26 PM
 * @description
 */
public class Demo {
    public static void main(String[] args) {

        try(NetMessage message = new NetMessage("delores")) {
            message.send();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        message.close();

        //【OPEN】连接成功
        //【SEND】发送消息delores
        //【CLOSE】关闭连接
    }
}

interface IMessage extends AutoCloseable {
    void send();
}

class NetMessage implements IMessage {
    private String message;

    public NetMessage(String message) {
        this.message = message;
    }

    public boolean open() { // 获取资源连接
        System.out.println("【OPEN】连接成功");
        return true;
    }

    @Override
    public void send() {
        if (this.open())
        System.out.println("【SEND】发送消息" + this.message);
    }

    @Override
    public void close() {
        System.out.println("【CLOSE】关闭连接");
    }
}

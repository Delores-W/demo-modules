package $_010_generics;

/**
 * @author William
 * @date 2020/8/31 11:00 AM
 * @description
 */
public class GenericsInterface {
    public static void main(String[] args) {

    }
}


interface IMessage<T> {
    public String echo(T t);
}

class MessageImpl<S> implements IMessage<S> {
    public String echo(S s) {
        return "delores";
    }
}

class MessageImpl222 implements IMessage<String> {
    public String echo(String s) {
        return "delores";
    }
}
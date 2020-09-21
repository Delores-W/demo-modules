package $_010_generics;

/**
 * @author William
 * @date 2020/8/31 10:12 AM
 * @description
 */
public class Demo {

    public static void main(String[] args) {
//        Message<String> message = new Message<String>();
//        message.setContent("Delores");
        Message<Integer> message1 = new Message<Integer>();
        message1.setContent(77);
//        fun(message);
        fun(message1);
    }

    // ? extends Number, 泛型通配下限 只能传入String 或者父类
    public static void fun(Message<?> message) {
        System.out.println(message.getContent());
    }
}


// 也可以在类定义时，就限制上下限
class Message<T extends Number> {
    private T content;

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}

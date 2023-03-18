package $_001_demo;

import java.awt.im.spi.InputMethod;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.*;

/**
 * @author William
 * @date 2020/11/13 5:12 PM
 * @description
 */
public class Test {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Method method = Person.class.getMethod("msg", String.class);
        DeloresAnnotation deloresAnnotation = method.getAnnotation(DeloresAnnotation.class);
        String msg = deloresAnnotation.title() + " --- " +  deloresAnnotation.url();
        method.invoke(Person.class.getDeclaredConstructor().newInstance(), msg);
        // @Delores Delores --- www.delores.com

    }
}

class Person {
    @DeloresAnnotation(title = "Delores")
    public void msg(String msg) {
        System.out.println("@Delores " + msg);
    }
}

@Retention(RetentionPolicy.RUNTIME)
@interface DeloresAnnotation {
    public String title(); // 如果不加默认值，则为必须字段
    public String url() default "www.delores.com";

}

@FunctionalInterface
//@Deprecated(since = "1.8")
interface IMessage {
    void send();

}
@SuppressWarnings("serial")
class MessageReal implements IMessage, Serializable {
    @Override
    public void send() {
        System.out.println("Send message: Delores");
    }

}

class ProxyFactory {
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    public Object getProxyInstance() {
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object returnValue = null;
                        if (connect()) {
                            returnValue = method.invoke(target, args);
                            close();
                        }
                        return returnValue;
                    }
                }
        );
    }

    public boolean connect() {
        System.out.println("消息通道的连接");
        return true;
    }

    public void close() {
        System.out.println("关闭消息通道");
    }
}


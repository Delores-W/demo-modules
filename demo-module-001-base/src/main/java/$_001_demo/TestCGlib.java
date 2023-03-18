package $_001_demo;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.w3c.dom.ls.LSOutput;

import java.lang.reflect.Method;

/**
 * @author William
 * @date 11/17/20 11:23 PM
 * @description
 */
public class TestCGlib {

    public static void main(String[] args) {
        Message proxy = (Message) new ProxyFactory2(new Message()).getProxyInstance();
        proxy.send();
    }
}

class ProxyFactory2 {
    private Object target;

    public ProxyFactory2(Object target) {
        this.target = target;
    }

    public Object getProxyInstance() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                Object returnData = null;
                if (connect()) {
                    returnData = method.invoke(target, objects);
                    close();
                }
                return returnData;
            }
        });
        return enhancer.create();
    }

    public boolean connect() {
        System.out.println("消息通道的连接");
        return true;
    }
    public void close() {
        System.out.println("关闭消息通道");
    }
}

class Message {
    void send() {
        System.out.println("Send message: Delores");
    }
}
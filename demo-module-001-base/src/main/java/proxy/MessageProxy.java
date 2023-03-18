package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author William
 * @date 4/3/21 10:56 PM
 * @description
 */
public class MessageProxy implements InvocationHandler {
    private Object target;

    public MessageProxy(Object target) {
        this.target = target;
    }

    public Object getProxyInstance() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    /**
     *
     * @param proxy 方法的执行需要其实例对象来执行
     * @param method 要执行的方法
     * @param args 方法参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before");
        method.invoke(target, args);
        System.out.println("after");
        return null;
    }

}

package com.delores.dynamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author William
 * @date 3/25/21 11:18 PM
 * @description
 */
public class ProxyFactory {

    // 维护一个目标对象，被代理的接口
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    // 给目标对象生成代理对象，通过InvocationHandler，运用反射执行目标对象方法
    public Object getProxyInstance() {
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("开始事务");
                        System.out.println("执行了方法 " + method.getName());
                        Object result = method.invoke(target, args);
                        System.out.println("提交事务");
                        return result;
                    }
                });
    }
}

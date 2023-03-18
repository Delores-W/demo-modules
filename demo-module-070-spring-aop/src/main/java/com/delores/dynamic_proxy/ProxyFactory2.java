package com.delores.dynamic_proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author William
 * @date 3/26/21 12:20 AM
 * @description
 */
public class ProxyFactory2 {

    // 维护一个目标对象，被代理的接口
    private Object target;

    public ProxyFactory2(Object target) {
        this.target = target;
    }

    public Object getProxyInstance() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(new MethodInterceptor() {
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("开始事务");
                System.out.println("执行了方法 " + method.getName());
                Object returnData = method.invoke(target, objects);
                System.out.println("提交事务");
                return returnData;
            }
        });
        return enhancer.create();
    }
}

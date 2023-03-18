package com.delores.plugin;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.util.Properties;

/**
 * @author William
 * @date 4/4/21 12:38 AM
 * @description
 */

// 完成插件签名：告诉mybatis要拦截哪个对象的哪个方法
@Intercepts(
        @Signature(type = StatementHandler.class, method = "parameterize", args = java.sql.Statement.class)
)
public class MyFirstPlugin implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("MyFirstPlugin......intercept: " + invocation.getMethod());

        Object target = invocation.getTarget();
        MetaObject metaObject = SystemMetaObject.forObject(target);
        // 通过反射赋值，更改sql参数
        Object value = metaObject.getValue("parameterHandler.parameterObject");
        System.out.println("sql的参数是: " + value);
        metaObject.setValue("parameterHandler.parameterObject", 2L);

        // 执行目标方法，相当于Filter.doChain()
        Object proceed = invocation.proceed();
        return proceed;
    }

    @Override
    public Object plugin(Object target) {
        System.out.println("MyFirstPlugin......plugin: 包装的对象 -- " + target);
        // 借助Plugin的wrap方法，来使用当前的Interceptor包装目标对象
        Object wrap = Plugin.wrap(target, this);
        return wrap;
    }

    @Override
    public void setProperties(Properties properties) {

    }
}

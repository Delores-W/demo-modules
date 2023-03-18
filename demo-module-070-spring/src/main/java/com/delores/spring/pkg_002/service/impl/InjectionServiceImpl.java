package com.delores.spring.pkg_002.service.impl;

import com.delores.spring.pkg_002.dao.InjectionDao;
import com.delores.spring.pkg_002.service.InjectionService;

/**
 * @author William
 * @date 2020/1/7 4:40 PM
 * @description
 */
public class InjectionServiceImpl implements InjectionService {

    private InjectionDao injectionDao;

    // Set 方法注入
    public void setInjectionDao(InjectionDao injectionDao) {
        this.injectionDao = injectionDao;
    }


//    // 构造器注入
    public InjectionServiceImpl(InjectionDao injectionDao) {
        this.injectionDao = injectionDao;
    }

    @Override
    public void save(String args) {
        // 模拟service 业务逻辑操作
        System.out.println("Service 接收参数: " + args);

        args = args + ": " + this.hashCode();

        injectionDao.save(args);
    }

    public void init() {

    }
}

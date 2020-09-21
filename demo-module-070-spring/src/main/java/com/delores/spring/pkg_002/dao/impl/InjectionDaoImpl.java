package com.delores.spring.pkg_002.dao.impl;

import com.delores.spring.pkg_002.dao.InjectionDao;

/**
 * @author William
 * @date 2020/1/7 4:39 PM
 * @description
 */
public class InjectionDaoImpl implements InjectionDao {
    @Override
    public void save(String args) {
        System.out.println("Save : " + args);
    }
}

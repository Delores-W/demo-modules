package com.delores.before_ioc.dao;

/**
 * @author William
 * @date 3/23/21 11:16 PM
 * @description
 */
public class UserDaoOracleImpl implements UserDao {
    public void getUser() {
        System.out.println("从Oracle获得对象");
    }
}

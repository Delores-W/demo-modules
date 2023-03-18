package com.delores.before_ioc.service;

import com.delores.before_ioc.dao.UserDao;
import com.delores.before_ioc.dao.UserDaoOracleImpl;

/**
 * @author William
 * @date 3/23/21 10:17 PM
 * @description
 */
public class UserServiceImpl implements UserService {

//     代码写死来控制
//     UserDao userDao = new UserDaoImpl();
//     UserDao userDao = new UserDaoOracleImpl();

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void getUser() {
        userDao.getUser();
    }
}

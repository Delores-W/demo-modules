package com.delores.aop.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author William
 * @date 3/26/21 10:53 AM
 * @description
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    public void add() {
        System.out.println("添加一个用户");
    }

    public void delete() {
        System.out.println("删除一个用户");
    }

    public void update() {
        System.out.println("更新一个用户");
    }

    public void select() {
        System.out.println("查询一个用户");
    }
}

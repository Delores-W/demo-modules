package com.delores.acl_service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.delores.acl_service.entity.User;

/**
 * @author William
 * @date 5/14/21 3:20 AM
 * @description
 */
public interface UserService extends IService<User> {

    // 从数据库中取出用户信息
    User selectByUsername(String username);
}

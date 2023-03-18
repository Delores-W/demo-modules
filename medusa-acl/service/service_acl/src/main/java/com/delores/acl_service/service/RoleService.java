package com.delores.acl_service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.delores.acl_service.entity.Role;

import java.util.List;
import java.util.Map;

/**
 * @author William
 * @date 5/14/21 4:40 PM
 * @description
 */
public interface RoleService extends IService<Role> {

    //根据用户获取角色数据
    Map<String, Object> findRoleByUserId(String userId);

    //根据用户分配角色
    void saveUserRoleRealtionShip(String userId, String[] roleId);

    List<Role> selectRoleByUserId(String id);
}

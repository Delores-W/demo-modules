package com.delores.acl_service.service;

import com.alibaba.fastjson.JSONObject;
import com.delores.acl_service.entity.Permission;

import java.util.List;

/**
 * @author William
 * @date 5/14/21 5:49 PM
 * @description
 */
public interface PermissionService {
    //获取全部菜单
    List<Permission> queryAllMenu();

    //根据角色获取菜单
    List<Permission> selectAllMenu(String roleId);

    //给角色分配权限
    void saveRolePermissionRealtionShip(String roleId, String[] permissionId);

    //递归删除菜单
    void removeChildById(String id);

    //根据用户id获取用户菜单
    List<String> selectPermissionValueByUserId(String id);

    List<JSONObject> selectPermissionByUserId(String id);

    //获取全部菜单
    List<Permission> queryAllMenuGuli();

    //递归删除菜单
    void removeChildByIdGuli(String id);

    //给角色分配权限
    void saveRolePermissionRealtionShipGuli(String roleId, String[] permissionId);
}

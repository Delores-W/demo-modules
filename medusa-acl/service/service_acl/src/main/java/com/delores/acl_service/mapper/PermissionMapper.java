package com.delores.acl_service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.delores.acl_service.entity.Permission;

import java.util.List;

/**
 * @author William
 * @date 5/14/21 1:29 PM
 * @description
 */
public interface PermissionMapper extends BaseMapper<Permission> {


    List<String> selectPermissionValueByUserId(String id);

    List<String> selectAllPermissionValue();

    List<Permission> selectPermissionByUserId(String userId);
}

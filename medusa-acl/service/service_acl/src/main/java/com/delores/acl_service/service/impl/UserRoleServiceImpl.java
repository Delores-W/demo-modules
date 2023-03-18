package com.delores.acl_service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.delores.acl_service.entity.UserRole;
import com.delores.acl_service.mapper.UserRoleMapper;
import com.delores.acl_service.service.UserRoleService;
import org.springframework.stereotype.Service;

/**
 * @author William
 * @date 5/14/21 7:32 PM
 * @description
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}

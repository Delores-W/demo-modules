package com.delores.acl_service.service.impl;

import com.delores.acl_service.entity.User;
import com.delores.acl_service.service.PermissionService;
import com.delores.acl_service.service.UserService;
import com.delores.security.entity.SecurityUser;
import com.delores.security.entity.TokenUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author William
 * @date 5/14/21 3:14 AM
 * @description
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询数据
        User user = userService.selectByUsername(username);
        //判断
        if(user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        TokenUser tokenUser = new TokenUser();
        BeanUtils.copyProperties(user, tokenUser);
        //根据用户查询用户权限列表
        List<String> permissionValueList = permissionService.selectPermissionValueByUserId(user.getId());
        SecurityUser securityUser = new SecurityUser();
        securityUser.setCurrentUserInfo(tokenUser);
        securityUser.setPermissionValueList(permissionValueList);
        return securityUser;
    }
}

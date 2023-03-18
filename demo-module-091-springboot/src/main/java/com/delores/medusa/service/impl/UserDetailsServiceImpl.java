package com.delores.medusa.service.impl;

import com.delores.medusa.dao.mapper.UserMapper;
import com.delores.medusa.model.enums.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author William
 * @date 5/6/21 9:55 PM
 * @description
 */
@Slf4j
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // html中 的name属性必须指定为username!!!!!!

        log.info("Try to login: " + username);

        // password 123456
        // encoder $2a$10$BTWy2CThMmI5vys/59m7fOILOVb9kLA6dEPsxYCkyOFzx6.shEdD2

        // 查询数据库，判断用户是否存在
        com.delores.medusa.model.User user = userMapper.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(StatusCode.UserNotExists.getMsg());
        }

        // 这样可以在测试环境下，数据库可以存储不加密的明文密码！！！
        return new User(username, new BCryptPasswordEncoder().encode(user.getPassword()), AuthorityUtils.commaSeparatedStringToAuthorityList("admin,common,ROLE_abc"));
        // For Prod
        // return new User(username, user.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList("admin,common,ROLE_abc"));
    }
}

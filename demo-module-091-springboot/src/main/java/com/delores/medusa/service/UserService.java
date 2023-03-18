package com.delores.medusa.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.delores.medusa.exception.LogicalException;
import com.delores.medusa.model.User;
import com.delores.medusa.exception.CloudException;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * @author William
 * @date 4/12/21 3:33 PM
 * @description
 */
public interface UserService {

    // mybatis
    User getOneUser(Long id);

    // for mybatis-plus
    IPage<User> getAll(User user, Page<User> page);

    // mybatis-plus 分页插件执行自定义sql
    IPage<User> getUsers(User user, Page<User> page);

    // PageHelper plugin
    List<User> getUsers2(User user);

    // Spring Data Jpa
    Optional<User> getUser(Long id);

    org.springframework.data.domain.Page<User> findAllWithPage(Pageable pageable);

    User findUserByUsername(String username);

    int save(User user) throws CloudException, LogicalException;
}

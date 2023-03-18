package com.delores.medusa.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.delores.medusa.dao.mapper.UserMapper;
import com.delores.medusa.dao.repository.UserRepository;
import com.delores.medusa.exception.LogicalException;
import com.delores.medusa.model.User;
import com.delores.medusa.model.enums.StatusCode;
import com.delores.medusa.model.enums.UserState;
import com.delores.medusa.exception.CloudException;
import com.delores.medusa.service.UserService;
import com.delores.medusa.utils.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author William
 * @date 4/12/21 3:34 PM
 * @description
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getOneUser(Long id) {
        User user = userMapper.getOneUser(id);
        // mybatis-plus
        // User user = userMapper.selectById(id);
        return user;
    }

    /**
     * queryWrapper 包装参数
     * selectPage mybatis-plus 内置查询接口 实现分页
     *
     * @param user
     * @param page
     * @return
     */
    @Override
    public IPage<User> getAll(User user, Page<User> page) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", user.getName());
        return userMapper.selectPage(page, queryWrapper);
    }

    /**
     * mybatis-plus 分页插件 执行自定义sql
     *
     * @param user
     * @return
     */
    @Override
    public IPage<User> getUsers(User user, Page<User> page) {
        return userMapper.getUsers(user, page);
    }

    /**
     * PageHelper plugin
     * @param user
     * @return
     */
    @Override
    public List<User> getUsers2(User user) {
        return userMapper.getUsers2(user);
    }


    /**
     * test 4 spring data jpa
     * @param id
     * @return
     */
    @Override
    public Optional<User> getUser(Long id) {
//        return userRepository.getOne(id);
        return userRepository.findById(id);
    }

    @Override
    public org.springframework.data.domain.Page<User> findAllWithPage(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User findUserByUsername(String username) {
        return userMapper.findUserByUsername(username);
    }

    @Override
    public int save(User user) throws LogicalException {
        User u = userMapper.findUserByUsername(user.getName());
        if (u != null) {
            throw new LogicalException(StatusCode.UserAlreadyExists);
        }
        // name mobile
        user.setState(UserState.ACTIVE);
        user.setCreated(new Date());
        user.setPassword(EncryptUtil.encryptWithSalt(user.getPassword()));
        return userMapper.save(user);
    }
}

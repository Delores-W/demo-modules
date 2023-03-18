package com.delores.medusa.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.delores.medusa.model.User;
import com.delores.medusa.model.enums.StatusCode;
import com.delores.medusa.model.response.BaseResponse;
import com.delores.medusa.model.response.PageResponse;
import com.delores.medusa.service.UserService;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author William
 * @date 4/12/21 3:31 PM
 * @description
 */
@Slf4j
@RestController
@RequestMapping("/mybatis")
public class MybatisController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public BaseResponse<User> getUser() {
        User user = userService.getOneUser(1L);
        BaseResponse<User> response;
        if (user != null) {
            response = new BaseResponse<>(StatusCode.SUCCESS, user);
        } else {
            response = new BaseResponse<>(StatusCode.FAIL);
        }
        log.info(response.toString());
        return response;
    }

    // 把查出的内容封装在Page对象（Mybatis Plus 内置模板类）
    // 分页插件执行内置接口查询
    @GetMapping("/userPage")
    public PageResponse<User> getUserByPage() {
        User user = new User();
        user.setName("Delores");
        IPage<User> page = userService.getAll(user, new Page<>(1, 3));
        PageResponse<User> response;
        if (page != null) {
            response = new PageResponse<>();
            response.setPageStatsMybatisPlus(page, true);
        } else {
            response = new PageResponse<>(StatusCode.FAIL);
        }
        return response;
    }

    // 把查出的内容封装在Page对象（Mybatis Plus 内置模板类）
    // 分页插件执行自定义sql
    @GetMapping("/userPage2")
    public PageResponse<User> getUserByPage2() {
        User user = new User();
        user.setName("Delores");
        IPage<User> page = userService.getUsers(user, new Page<>(2, 2));
        PageResponse<User> response;
        if (page != null) {
            response = new PageResponse<>();
            response.setPageStatsMybatisPlus(page, true);
        } else {
            response = new PageResponse<>(StatusCode.FAIL);
        }
        return response;
    }

    // 把查出的内容封装在Page对象（PageHelper 插件 内置模板类）
    // 分页插件执行自定义sql
    @GetMapping("/userPage3")
    public PageResponse<User> getUserByPage3() {
        User user = new User();
        user.setName("Delores");

        com.github.pagehelper.Page<User> page = PageHelper.startPage(1, 3);
        List<User> list = userService.getUsers2(user);
        PageResponse<User> response;
        if (list != null) {
            response = new PageResponse<>();
            response.setPageStatsPageHelper(page, true);
        } else {
            response = new PageResponse<>(StatusCode.FAIL);
        }
        return response;
    }

}

package com.delores.acl_service.controller;

import com.alibaba.fastjson.JSONObject;
import com.delores.acl_service.service.IndexService;
import com.delores.base.response.BaseResponse;
import com.delores.base.utils.enums.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author William
 * @date 5/15/21 1:47 PM
 * @description
 */
@RestController
@RequestMapping("/admin/acl/index")
//@CrossOrigin
public class IndexController {

    @Autowired
    private IndexService indexService;

    /**
     * 根据token获取用户信息
     */
    @GetMapping("info")
    public BaseResponse<Map<String, Object>> info(){
        //获取当前登录用户用户名
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Map<String, Object> userInfo = indexService.getUserInfo(username);
        return new BaseResponse<>(StatusCode.SUCCESS, userInfo);
    }

    /**
     * 获取菜单
     * @return
     */
    @GetMapping("menu")
    public BaseResponse<List<JSONObject>> getMenu(){
        //获取当前登录用户用户名
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<JSONObject> permissionList = indexService.getMenu(username);
        return new BaseResponse<>(StatusCode.SUCCESS, permissionList);
    }

    @PostMapping("logout")
    public BaseResponse<String> logout(){
        return new BaseResponse<>(StatusCode.SUCCESS);
    }

}

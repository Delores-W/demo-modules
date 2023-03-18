package com.delores.acl_service.service;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * @author William
 * @date 5/14/21 7:35 PM
 * @description
 */
public interface IndexService {

    /**
     * 根据用户名获取用户登录信息
     * @param username
     * @return
     */
    Map<String, Object> getUserInfo(String username);

    /**
     * 根据用户名获取动态菜单
     * @param username
     * @return
     */
    List<JSONObject> getMenu(String username);

}

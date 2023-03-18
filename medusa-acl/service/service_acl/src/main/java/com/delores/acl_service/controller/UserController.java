package com.delores.acl_service.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.delores.acl_service.entity.User;
import com.delores.acl_service.service.RoleService;
import com.delores.acl_service.service.UserService;
import com.delores.base.response.BaseResponse;
import com.delores.base.response.PageResponse;
import com.delores.base.utils.EncryptUtil;
import com.delores.base.utils.enums.StatusCode;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
@RestController
@RequestMapping("/admin/acl/user")
//@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "获取管理用户分页列表")
    @GetMapping("{page}/{limit}")
    public PageResponse<User> index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "courseQuery", value = "查询对象", required = false)
                    User userQueryVo) {
        Page<User> pageParam = new Page<>(page, limit);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(userQueryVo.getUsername())) {
            wrapper.like("username",userQueryVo.getUsername());
        }

        IPage<User> pageModel = userService.page(pageParam, wrapper);
        // return R.ok().data("items", pageModel.getRecords()).data("total", pageModel.getTotal());
        return new PageResponse<>(pageModel, true);
    }

    @ApiOperation(value = "新增管理用户")
    @PostMapping("save")
    public BaseResponse<String> save(@RequestBody User user) {
        user.setPassword(EncryptUtil.encrypt(user.getPassword()));
        userService.save(user);
        return new BaseResponse<>(StatusCode.SUCCESS);
    }

    @ApiOperation(value = "根据用户获取角色数据")
    @GetMapping("/toAssign/{userId}")
    public BaseResponse<Map<String, Object>> toAssign(@PathVariable String userId) {
        Map<String, Object> roleMap = roleService.findRoleByUserId(userId);
        return new BaseResponse<>(StatusCode.SUCCESS, roleMap);
    }

    @ApiOperation(value = "根据用户分配角色")
    @PostMapping("/doAssign")
    public BaseResponse<String> doAssign(@RequestParam String userId,@RequestParam String[] roleId) {
        roleService.saveUserRoleRealtionShip(userId,roleId);
        return new BaseResponse<>(StatusCode.SUCCESS);
    }
}

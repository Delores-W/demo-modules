package com.delores.acl_service.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.delores.acl_service.entity.Role;
import com.delores.acl_service.service.RoleService;
import com.delores.base.response.BaseResponse;
import com.delores.base.response.PageResponse;
import com.delores.base.utils.enums.StatusCode;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author William
 * @date 5/15/21 12:30 PM
 * @description
 */
@RestController
@RequestMapping("/admin/acl/role")
//@CrossOrigin
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "获取角色分页列表")
    @GetMapping("{page}/{limit}")
    public PageResponse<Role> index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            Role role) {
        Page<Role> pageParam = new Page<>(page, limit);
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(role.getRoleName())) {
            wrapper.like("role_name",role.getRoleName());
        }
        roleService.page(pageParam,wrapper);
        return new PageResponse<>(pageParam, true);
    }

    @ApiOperation(value = "新增角色")
    @PostMapping("save")
    public BaseResponse<Role> save(@RequestBody Role role) {
        roleService.save(role);
        return new BaseResponse<>(StatusCode.SUCCESS);
    }
}

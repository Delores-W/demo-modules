package com.delores.acl_service.controller;

import com.delores.acl_service.entity.Permission;
import com.delores.acl_service.service.PermissionService;
import com.delores.base.response.BaseResponse;
import com.delores.base.utils.enums.StatusCode;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author William
 * @date 5/15/21 2:29 PM
 * @description
 */
@RestController
@RequestMapping("/admin/acl/permission")
//@CrossOrigin
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    //获取全部菜单
    @ApiOperation(value = "查询所有菜单")
    @GetMapping
    public BaseResponse<List<Permission>> indexAllPermission() {
        List<Permission> list =  permissionService.queryAllMenu();
        return new BaseResponse<>(StatusCode.SUCCESS, list);
    }

    @ApiOperation(value = "给角色分配权限")
    @PostMapping("/doAssign")
    public BaseResponse<String> doAssign(String roleId,String[] permissionId) {
        permissionService.saveRolePermissionRealtionShipGuli(roleId,permissionId);
        return new BaseResponse<>(StatusCode.SUCCESS);
    }

    @ApiOperation(value = "根据角色获取菜单")
    @GetMapping("toAssign/{roleId}")
    public BaseResponse<List<Permission>> toAssign(@PathVariable String roleId) {
        List<Permission> list = permissionService.selectAllMenu(roleId);
        return new BaseResponse<>(StatusCode.SUCCESS, list);
    }

}

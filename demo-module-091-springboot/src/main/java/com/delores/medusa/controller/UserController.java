package com.delores.medusa.controller;

import com.delores.medusa.exception.BaseMedusaException;
import com.delores.medusa.exception.LogicalException;
import com.delores.medusa.model.User;
import com.delores.medusa.model.enums.StatusCode;
import com.delores.medusa.model.response.BaseResponse;
import com.delores.medusa.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author William
 * @date 4/29/21 3:25 PM
 * @description
 */
@Api("User Controller")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    @ApiOperation(value = "save user to DB", notes = "save user, 返回新增的user")
    public BaseResponse<User> addUser(@RequestBody User user) throws BaseMedusaException {

        int result = userService.save(user);
        if (result < 1) {
            throw new LogicalException(StatusCode.SYSTEM_ERROR);
        }
        user = userService.findUserByUsername(user.getName());
        return new BaseResponse<>(StatusCode.SUCCESS, user);
    }
}

package com.delores.medusa.controller;

import com.delores.medusa.controller.request.AuthenticationRequest;
import com.delores.medusa.exception.BaseMedusaException;
import com.delores.medusa.model.AuthTokenModel;
import com.delores.medusa.model.enums.StatusCode;
import com.delores.medusa.model.response.BaseResponse;
import com.delores.medusa.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author William
 * @date 4/29/21 10:15 AM
 * @description
 */
@Slf4j
@RestController
@Api(tags = {"tags -- Authentication Controller"}, value = "用户认证")
public class AuthenticationController {

    @Autowired
    private AuthService authService;

    /**
     *  database 保存 token
     */
    @ApiOperation(value = "Api Operation Value -- 用户认证登录 DB", notes = "notes -- 返回Token信息")
    @PostMapping(value = "/db/login")
    public BaseResponse<AuthTokenModel> dbLogin(@RequestBody AuthenticationRequest authenticationRequest){
        log.info(authenticationRequest.getUsername());
        log.info(authenticationRequest.getPassword());

        AuthTokenModel authTokenModel = authService.authAndCreateTokenDB(authenticationRequest);

        return new BaseResponse<>(StatusCode.SUCCESS, authTokenModel);
    }

    /**
     *  redis 保存 token
     */
    @ApiOperation(value = "Api Operation Value -- 用户认证登录 Redis", notes = "notes -- 返回Token信息")
    @PostMapping(value = "/redis/login")
    public BaseResponse<AuthTokenModel> redisLogin(@RequestBody AuthenticationRequest authenticationRequest){
        log.info(authenticationRequest.getUsername());
        log.info(authenticationRequest.getPassword());

        AuthTokenModel authTokenModel = authService.authAndCreateTokenRedis(authenticationRequest);

        return new BaseResponse<>(StatusCode.SUCCESS, authTokenModel);
    }

    /**
     *  JWT
     */
    @ApiOperation(value = "Api Operation Value -- 用户认证登录 JWT", notes = "notes -- 返回Token信息")
    @PostMapping(value = "/jwt/login")
    public BaseResponse<AuthTokenModel> jwtLogin(@RequestBody AuthenticationRequest authenticationRequest){
        log.info(authenticationRequest.getUsername());
        log.info(authenticationRequest.getPassword());

        AuthTokenModel authTokenModel = authService.authAndCreateTokenJwt(authenticationRequest);

        return new BaseResponse<>(StatusCode.SUCCESS, authTokenModel);
    }

    /**
     *  JWT + Redis
     */
    @ApiOperation(value = "Api Operation Value -- 用户认证登录 JWT + Redis", notes = "notes -- 返回Token信息")
    @PostMapping(value = "/jwtRedis/login")
    public BaseResponse<AuthTokenModel> jwtRedisLogin(@RequestBody AuthenticationRequest authenticationRequest){
        log.info(authenticationRequest.getUsername());
        log.info(authenticationRequest.getPassword());

        AuthTokenModel authTokenModel = authService.authAndCreateTokenJwtRedis(authenticationRequest);

        return new BaseResponse<>(StatusCode.SUCCESS, authTokenModel);
    }
}

package com.delores.medusa.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.delores.medusa.controller.request.AuthenticationRequest;
import com.delores.medusa.dao.mapper.AuthTokenMapper;
import com.delores.medusa.dto.AccessTokenDto;
import com.delores.medusa.exception.BaseMedusaException;
import com.delores.medusa.exception.CloudException;
import com.delores.medusa.exception.LogicalException;
import com.delores.medusa.exception.TechnicalException;
import com.delores.medusa.model.AuthToken;
import com.delores.medusa.model.AuthTokenModel;
import com.delores.medusa.model.Constant;
import com.delores.medusa.model.User;
import com.delores.medusa.model.enums.StatusCode;
import com.delores.medusa.model.response.BaseResponse;
import com.delores.medusa.service.AuthService;
import com.delores.medusa.service.RedisService;
import com.delores.medusa.service.UserService;
import com.delores.medusa.utils.EncryptUtil;
import com.delores.medusa.utils.JwtUtil;
import com.google.common.base.Strings;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author William
 * @date 4/29/21 12:33 PM
 * @description
 */
@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthTokenMapper authTokenMapper;

    @Autowired
    private RedisService redisService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public AuthTokenModel authAndCreateTokenDB(AuthenticationRequest authenticationRequest) throws BaseMedusaException {

        User user = authUser(authenticationRequest);

        //失效以前那些仍然在使用的access token
        authTokenMapper.invalidateTokenByUser(user.getId());

        // Generate Token
        Long timestamp = System.currentTimeMillis();
        // AccessTokenDto tokenDto = new AccessTokenDto(user.getId(),userName,timestamp, RandomStringUtils.randomAlphanumeric(18));
        AccessTokenDto tokenDto = new AccessTokenDto(user.getId(), user.getName(), timestamp, Constant.snowFlake.nextId().toString(), Constant.ACCESS_TOKEN_EXPIRE);
        String jsonToken = JSON.toJSONString(tokenDto);
        log.info("----json格式的access token字符串：{}", jsonToken);
        // encrypt token
        String accessToken = EncryptUtil.aesEncrypt(jsonToken, Constant.TOKEN_AUTH_KEY);
        log.info("--数据库用户认证成功，成功生成accessToken--" + accessToken);

        // 保存Token到数据库
        AuthToken authToken = new AuthToken();
        authToken.setUserId(user.getId());
        authToken.setAccessToken(accessToken);
        authToken.setAccessExpire(Constant.ACCESS_TOKEN_EXPIRE);
        authToken.setTokenTimestamp(timestamp);
        authToken.setCreateTime(new Date());
        authTokenMapper.save(authToken);

        return new AuthTokenModel(accessToken, Constant.ACCESS_TOKEN_EXPIRE);
    }

    private User authUser(AuthenticationRequest request) throws LogicalException {
        // Perform the security
        if (Strings.isNullOrEmpty(request.getUsername()) || Strings.isNullOrEmpty(request.getPassword())) {
            throw new LogicalException(StatusCode.UserNamePasswordNotBlank);
        }
        User user = userService.findUserByUsername(request.getUsername());
        if (user == null || !user.getPassword().equals(EncryptUtil.encryptWithSalt(request.getPassword()))) {
            throw new LogicalException(StatusCode.UsernamePasswordNotMatch);
        }
        return user;
    }

    //验证-解析token
    @Override
    public BaseResponse<String> validateTokenDB(final String accessToken) throws BaseMedusaException {
        BaseResponse<String> response = new BaseResponse<>(StatusCode.SUCCESS);

        if (Strings.isNullOrEmpty(accessToken)) {
            // return new BaseResponse<>(StatusCode.AccessTokenNotBlank);
            throw new LogicalException(StatusCode.AccessTokenNotBlank);
        }

        //验证这个token是否存在
        AuthToken authToken = authTokenMapper.selectByAccessToken(accessToken);
        if (authToken == null) {
            // return new BaseResponse<>(StatusCode.AccessTokenNotExist);
            throw new LogicalException(StatusCode.AccessTokenNotExist);
        }

        AccessTokenDto dto;
        //为了防止token的伪造，会主动额外做异步操作-解析token
        try {
            dto = parseAccessToken(accessToken);
            // ************************************************************************************************************
            // 如果比较底层的异常只给程序员调试，而不是给客户端信息，则捕获底层异常后，返回更友好的异常，通过controller层捕获，返回给客户端
            // ************************************************************************************************************
        } catch (Exception e) {
            throw new LogicalException(StatusCode.AccessTokenInvalidate);
        }

        if (dto != null) {
            //验证token是否过期
            if (System.currentTimeMillis() - dto.getTimestamp() > dto.getExpireTime()) {
                invalidateByAccessToken(accessToken);
                // return new BaseResponse<>(StatusCode.TokenValidateExpireToken);
                throw new LogicalException(StatusCode.TokenValidateExpireToken);
            }
        }

        return response;
    }

    //解密accessToken
    public AccessTokenDto parseAccessToken(final String accessToken) throws BaseMedusaException {
        String jsonStr = EncryptUtil.aesDecrypt(accessToken, Constant.TOKEN_AUTH_KEY);
        return JSONObject.parseObject(jsonStr, AccessTokenDto.class);
    }

    //失效access token
    public void invalidateByAccessToken(final String accessToken) {
        if (Strings.isNullOrEmpty(accessToken)) {
            authTokenMapper.invalidateByToken(accessToken);
        }
    }

    @Override
    public AuthTokenModel authAndCreateTokenRedis(AuthenticationRequest authenticationRequest) throws BaseMedusaException {
        User user = authUser(authenticationRequest);

        // Generate Token
        Long timestamp = System.currentTimeMillis();
        // AccessTokenDto tokenDto = new AccessTokenDto(user.getId(),userName,timestamp, RandomStringUtils.randomAlphanumeric(18));
        AccessTokenDto tokenDto = new AccessTokenDto(user.getId(), user.getName(), timestamp, Constant.snowFlake.nextId().toString(), Constant.ACCESS_TOKEN_EXPIRE);
        String jsonToken = JSON.toJSONString(tokenDto);
        log.info("----json格式的access token字符串：{}", jsonToken);
        // encrypt token
        String accessToken = EncryptUtil.aesEncrypt(jsonToken, Constant.TOKEN_AUTH_KEY);
        log.info("--用户认证成功，成功生成accessToken--" + accessToken);

        // 保存Token到Redis
        redisService.set(Constant.TOKEN_REDIS_KEY_PREFIX + user.getName(), accessToken, Constant.ACCESS_TOKEN_EXPIRE, TimeUnit.MILLISECONDS);

        return new AuthTokenModel(accessToken, Constant.ACCESS_TOKEN_EXPIRE);
    }

    @Override
    public BaseResponse<String> validateTokenRedis(String accessToken) throws BaseMedusaException {
        log.info("--redis+token用户验证-开始进行--");
        BaseResponse<String> response = new BaseResponse<>(StatusCode.SUCCESS);
        if (Strings.isNullOrEmpty(accessToken)) {
            throw new LogicalException(StatusCode.AccessTokenNotBlank);
        }

        //解析token(为了防止别人伪造token)
        AccessTokenDto accessTokenDto = parseAccessToken(accessToken);
        if (Strings.isNullOrEmpty(accessTokenDto.getUserName())) {
            throw new LogicalException(StatusCode.AccessTokenInvalidate);
        }

        //定义缓存的key
        final String key = Constant.TOKEN_REDIS_KEY_PREFIX + accessTokenDto.getUserName();

        //判断key是否存在，如果存在的话，那就意味着accessToken是存在(不一定)
        // 如果此时在缓存动手脚 随便为这个key设置一个值 那么这个key将会永不过期
        boolean exist = redisService.exists(key);

        if (!exist) {
            throw new LogicalException(StatusCode.AccessTokenNotExist);
        }

        // 所以必须要判断 前端传过来的token 与 缓存中的值是否相同
        // 可能性很小 因为在登录时会刷新缓存的Token
        if (!Objects.equals(accessToken, redisService.get(key))) {
            // return new BaseResponse<>(StatusCode.AccessTokenInvalidate);
            throw new LogicalException(StatusCode.AccessTokenInvalidate);
        }
        return response;
    }

    @Override
    public AuthTokenModel authAndCreateTokenJwt(AuthenticationRequest authenticationRequest) throws BaseMedusaException {
        User user = authUser(authenticationRequest);
        // Generate Token
        String accessToken = JwtUtil.generateJWT(user.getId().toString(), user.getName(), Constant.ACCESS_TOKEN_EXPIRE);

        log.info("--JWT 用户认证成功，成功生成accessToken--");

        return new AuthTokenModel(accessToken, Constant.ACCESS_TOKEN_EXPIRE);
    }

    @Override
    public BaseResponse<String> validateTokenJwt(String accessToken) throws BaseMedusaException {
        log.info("--jwt+token用户验证-开始进行--");
        if (Strings.isNullOrEmpty(accessToken)) {
            throw new LogicalException(StatusCode.AccessTokenNotBlank);
        }
        JwtUtil.validateJWT(accessToken);
        return new BaseResponse<>(StatusCode.SUCCESS);
    }

    @Override
    public AuthTokenModel authAndCreateTokenJwtRedis(AuthenticationRequest authenticationRequest) throws BaseMedusaException {
        User user = authUser(authenticationRequest);
        // Generate Token
        String accessToken = JwtUtil.generateJWTWithoutExp(user.getId().toString(), user.getName());

        log.info("--JWT Redis 用户认证成功，成功生成accessToken--");
        // 保存Token到Redis
        redisService.set(Constant.TOKEN_REDIS_KEY_PREFIX + user.getName(), accessToken, Constant.ACCESS_TOKEN_EXPIRE, TimeUnit.MILLISECONDS);

        return new AuthTokenModel(accessToken, Constant.ACCESS_TOKEN_EXPIRE);
    }

    @Override
    public BaseResponse<String> validateTokenJwtRedis(String accessToken) throws BaseMedusaException {
        log.info("--JWT + Redis 用户验证-开始进行--");
        BaseResponse<String> response = new BaseResponse<>(StatusCode.SUCCESS);
        if (Strings.isNullOrEmpty(accessToken)) {
            throw new LogicalException(StatusCode.AccessTokenNotBlank);
        }

        Claims claims = JwtUtil.validateJWT(accessToken);

        //定义缓存的key
        final String key = Constant.TOKEN_REDIS_KEY_PREFIX + claims.getSubject();
        //判断key是否存在，如果存在的话，那就意味着accessToken是存在(不一定)
        // 如果此时在缓存动手脚 随便为这个key设置一个值 那么这个key将会永不过期
        boolean exist = redisService.exists(key);

        if (!exist) {
            throw new LogicalException(StatusCode.AccessTokenNotExist);
        }

        // 所以必须要判断 前端传过来的token 与 缓存中的值是否相同
        // 可能性很小 因为在登录时会刷新缓存的Token
        if (!Objects.equals(accessToken, redisService.get(key))) {
            // return new BaseResponse<>(StatusCode.AccessTokenInvalidate);
            throw new LogicalException(StatusCode.AccessTokenInvalidate);
        }

        return response;
    }
}

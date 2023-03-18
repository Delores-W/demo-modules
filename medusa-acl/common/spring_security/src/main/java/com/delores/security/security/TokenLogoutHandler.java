package com.delores.security.security;

import com.delores.base.response.BaseResponse;
import com.delores.base.response.ResponseUtil;
import com.delores.base.service.RedisService;
import com.delores.base.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author William
 * @date 5/13/21 2:53 AM
 * @description 退出处理器
 */
public class TokenLogoutHandler implements LogoutHandler {

    private TokenUtil tokenUtil;

    private RedisService redisService;

    public TokenLogoutHandler(TokenUtil tokenUtil, RedisService redisService) {
        this.tokenUtil = tokenUtil;
        this.redisService = redisService;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String username = tokenUtil.verifyToken(request);

        redisService.remove(Constant.JWT_TOKEN_REDIS_KEY_PREFIX + username);

        ResponseUtil.out(response, BaseResponse.SUCCESS());
    }
}

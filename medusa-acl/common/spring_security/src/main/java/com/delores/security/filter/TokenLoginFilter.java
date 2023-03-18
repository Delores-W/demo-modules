package com.delores.security.filter;

import com.delores.base.exception.LogicalException;
import com.delores.base.response.BaseResponse;
import com.delores.base.response.ResponseUtil;
import com.delores.security.entity.AuthToken;
import com.delores.security.entity.SecurityUser;
import com.delores.security.entity.TokenUser;
import com.delores.security.security.TokenUtil;
import com.delores.base.service.RedisService;
import com.delores.base.utils.Constant;
import com.delores.base.utils.enums.StatusCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author William
 * @date 5/13/21 5:58 PM
 * @description
 */
public class TokenLoginFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private RedisService redisService;

    private AuthenticationManager authenticationManager;

    public TokenLoginFilter(AuthenticationManager authenticationManager, TokenUtil tokenUtil, RedisService redisService) {
        this.authenticationManager = authenticationManager;
        this.tokenUtil = tokenUtil;
        this.redisService = redisService;
        this.setPostOnly(true);
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/admin/acl/login", "POST"));
    }

    public TokenLoginFilter(AuthenticationManager authenticationManager, RedisService redisService) {
        this.authenticationManager = authenticationManager;
        this.redisService = redisService;
        this.setPostOnly(true);
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/admin/acl/login", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        //获取表单提交数据
        try {
            String jsonString = IOUtils.toString(request.getInputStream(), "UTF-8");
            TokenUser user = new ObjectMapper().readValue(jsonString, TokenUser.class);

            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(),
                    new ArrayList<>()));
        } catch (IOException e) {
            e.printStackTrace();
            throw new LogicalException("认证请求解析error");
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        // 得到认证之后的用户信息
        SecurityUser user = (SecurityUser) authResult.getPrincipal();

        // 根据用户名生成token
        String token = TokenUtil.generateJWT(user.getUsername(), Constant.ACCESS_TOKEN_EXPIRE);

        // 把用户名和用户权限列表放在redis中
        redisService.set(Constant.JWT_TOKEN_REDIS_KEY_PREFIX + user.getUsername(), user.getPermissionValueList());

        // 返回token
        ResponseUtil.out(response, new BaseResponse<>(StatusCode.SUCCESS, new AuthToken(token)));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        ResponseUtil.out(response, new BaseResponse<>(failed));
    }
}

package com.delores.security.filter;

import com.delores.base.response.BaseResponse;
import com.delores.base.response.ResponseUtil;
import com.delores.base.utils.enums.StatusCode;
import com.delores.security.security.TokenUtil;
import com.delores.base.service.RedisService;
import com.delores.base.utils.Constant;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author William
 * @date 5/13/21 5:58 PM
 * @description
 */
public class TokenAuthenticationFilter extends BasicAuthenticationFilter {

    private RedisService redisService;

    public TokenAuthenticationFilter(AuthenticationManager authenticationManager, RedisService redisService) {
        super(authenticationManager);
        this.redisService = redisService;
    }

    public TokenAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException{

        try {
            //获取当前认证成功用户权限信息
            UsernamePasswordAuthenticationToken authRequest = getAuthentication(request);
            //判断如果有权限信息，放到权限上下文中
            SecurityContextHolder.getContext().setAuthentication(authRequest);
        } catch (Exception exception) {
            exception.printStackTrace();
            ResponseUtil.out(response,new BaseResponse<>(StatusCode.TokenValidateCheckFail));
        }
        chain.doFilter(request,response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request){
        final String token = request.getHeader(Constant.AUTH_HEADER_NAME);
        if (!Strings.isNullOrEmpty(token)) {
            String username = TokenUtil.parseUserFromToken(token);

            List<String> permissionValueList = (List<String>) redisService.get(Constant.JWT_TOKEN_REDIS_KEY_PREFIX + username);

            return new UsernamePasswordAuthenticationToken(username, token, mapToGrantedAuthorities(permissionValueList));
        }
        return null;
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<String> authorities) {
        return authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

}

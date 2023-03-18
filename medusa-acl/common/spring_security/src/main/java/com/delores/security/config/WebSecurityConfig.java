package com.delores.security.config;

import com.delores.base.service.RedisService;
import com.delores.security.filter.TokenAuthenticationFilter;
import com.delores.security.filter.TokenLoginFilter;
import com.delores.security.handler.MyAccessDeniedHandler;
import com.delores.security.security.CustomPasswordEncoder;
import com.delores.security.security.TokenLogoutHandler;
import com.delores.security.security.TokenUtil;
import com.delores.security.security.UnauthEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author William
 * @date 5/14/21 2:12 AM
 * @description
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private CustomPasswordEncoder customPasswordEncoder;

    // 等价于上面的autowired
//    @Autowired
//    public WebSecurityConfig(UserDetailsService userDetailsService, CustomPasswordEncoder customPasswordEncoder, TokenUtil tokenUtil) {
//        this.userDetailsService = userDetailsService;
//        this.tokenUtil = tokenUtil;
//        this.customPasswordEncoder = customPasswordEncoder;
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/orderService/**").hasRole("delores")
                .anyRequest().authenticated().and()
                .exceptionHandling().authenticationEntryPoint(new UnauthEntryPoint()).and() //没有权限访问
                .addFilter(new TokenLoginFilter(authenticationManager(), redisService))
                .addFilter(new TokenAuthenticationFilter(authenticationManager(), redisService))
                .logout().logoutUrl("/admin/acl/index/logout")
                .addLogoutHandler(new TokenLogoutHandler(tokenUtil, redisService));

        // 403 错误
        http.exceptionHandling()
                .accessDeniedHandler(new MyAccessDeniedHandler());

    }

    // 调用userDetailsService和密码处理
    // 自定义 AuthenticationProvider UserDetailsService PasswordEncoder
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(customPasswordEncoder);
    }

    //不进行认证的路径，可以直接访问
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/api-docs", "/swagger-resources/**", "/swagger-ui.html**", "/webjars/**", "/css/**");
    }

}

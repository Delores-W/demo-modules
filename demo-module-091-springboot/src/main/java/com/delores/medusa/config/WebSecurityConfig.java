package com.delores.medusa.config;

import com.delores.medusa.config.handler.MyAccessDeniedHandler;
import com.delores.medusa.config.handler.MyAuthenticationFailureHandler;
import com.delores.medusa.config.handler.MyAuthenticationSuccessHandler;
import com.delores.medusa.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author William
 * @date 5/6/21 11:36 AM
 * @description
 */
@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 没有权限默认回到登录页面
        http.formLogin()
                // 自定义登录页面
                .loginPage("/login.html")

                // 当发现、login时，被认为是登录，去执行UserDetailsServiceImpl
                .loginProcessingUrl("/login")
                .permitAll()

                // 如果没有配置controller请求 会404
                // 配置了 会405 Request method 'POST' not supported] 因为是转发，请求路径没有变化，还是登录处理请求路径(/login),所以相应的请求类型为post
                //.successForwardUrl("/index");

                // 重定向 不需要controller做页面跳转
                 .defaultSuccessUrl("/");

                // 自定义AuthenticationSuccessHandler
                // .successHandler(new MyAuthenticationSuccessHandler())

                // 登录失败处理
                // .failureHandler(new MyAuthenticationFailureHandler());

        // 请求授权规则
        http.authorizeRequests()
                // .antMatchers("/db/login", "/redis/login", "/jwt/login", "/jwtRedis/login").permitAll()
                // .antMatchers("/").permitAll()
                .antMatchers("/hello").hasAuthority("admin")
                .anyRequest().authenticated();

        // 403 错误
        http.exceptionHandling()
                 .accessDeniedPage("/noAccess");
//                .accessDeniedHandler(new MyAccessDeniedHandler());

        http.csrf().disable();

////        // 开启HttpBasic认证
////        http.httpBasic()
////                .and()
////                // 认证所有请求
////                .authorizeRequests()
////                // 所有请求都必须认证成功
////                .anyRequest().authenticated();
//

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/api-docs", "/swagger-resources/**", "/swagger-ui.html**", "/webjars/**", "/css/**");
    }

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private RedisService redisService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // auth.inMemoryAuthentication().withUser("william").password(passwordEncoder().encode("123456")).roles("admin");
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}

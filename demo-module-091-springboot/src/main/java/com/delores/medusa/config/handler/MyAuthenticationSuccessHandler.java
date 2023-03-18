package com.delores.medusa.config.handler;

import com.delores.medusa.model.enums.StatusCode;
import com.delores.medusa.model.response.BaseResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author William
 * @date 5/7/21 7:14 PM
 * @description
 */
@Slf4j
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info(request.getParameter("username"));
        log.info(request.getParameter("password"));

        response.setContentType("text/json;charset=utf-8");
        BaseResponse<String> successResponse = new BaseResponse<>(StatusCode.SUCCESS, "Login Success");

        // com.fasterxml.jackson.databind.ObjectMapper;
        // jackson 提供的将对象转化为字符串
        response.getWriter().write(new ObjectMapper().writeValueAsString(successResponse));

    }
}

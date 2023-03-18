package com.delores.security.handler;

import com.delores.base.response.BaseResponse;
import com.delores.base.response.ResponseUtil;
import com.delores.base.utils.enums.StatusCode;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author William
 * @date 5/19/21 11:29 AM
 * @description
 */
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
//        ResponseUtil.out(httpServletResponse, new BaseResponse<>(e));
        ResponseUtil.out(httpServletResponse, new BaseResponse<>(HttpStatus.FORBIDDEN.value(), StatusCode.CurrUserHasNotPermission.getMsg()));
    }
}

package com.delores.medusa.config.interceptor;

import com.delores.medusa.model.enums.StatusCode;
import com.delores.medusa.model.response.BaseResponse;
import com.delores.medusa.service.AuthService;
import com.delores.medusa.service.CommonService;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @author William
 * @date 5/4/21 12:59 PM
 * @description
 */
@Slf4j
public class DataBaseInterceptor implements HandlerInterceptor {

    @Autowired
    private AuthService authService;

    @Autowired
    private CommonService commonService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            final String accessToken = request.getHeader("Authorization");
            if (Strings.isNullOrEmpty(accessToken)) {
                log.error("--database+token用户验证失败--");
                BaseResponse<String> baseResponse = new BaseResponse<>(StatusCode.FAIL, "accessToken必填~请先进行登录,并将生成的accessToken塞入http请求头的accessToken字段中");
                commonService.print(response, baseResponse);
            } else {
                log.info("--database+token用户验证-开始进行--");
                BaseResponse<String> result = authService.validateTokenDB(accessToken);
                if (Objects.equals(result.getCode(), StatusCode.SUCCESS.getCode())) {
                    return true;
                } else {
                    commonService.print(response, result);
                    return false;
                }
            }
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (response.getStatus()==500){
            modelAndView.setViewName("/error/500");
        }else if (response.getStatus()==404){
            modelAndView.setViewName("/error/404");
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

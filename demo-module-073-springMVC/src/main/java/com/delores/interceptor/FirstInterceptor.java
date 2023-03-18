package com.delores.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author William
 * @date 4/1/21 1:41 PM
 * @description
 */
public class FirstInterceptor implements HandlerInterceptor {
    /**
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     * fasdfajsdjfjlasjl
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("[FirstInterceptor] preHandle");
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("[FirstInterceptor] postHandle");

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("[FirstInterceptor] afterCompletion");
    }
}

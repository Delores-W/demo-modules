package com.delores.Filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author William
 * @date 3/31/21 4:12 PM
 * @description
 */
public class AdminFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpServletRequest.getSession();
        String username = (String) session.getAttribute("username");
//        username = "Delores";

        // 检查用户是否登录，权限是否正确
        if (username == null) {
            System.out.println("you don't have the permission");
            // 跳转到登录页面，或给出没有权限提示
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.sendRedirect("http://localhost:8080/demo_module_090_webapp_war/index.jsp");

            // 如果转发的话，没有设置<base>, 请求过来的路径是 http://localhost:8080/demo_module_090_webapp_war/admin/stop.jsp
//            RequestDispatcher dispatcher = servletRequest.getRequestDispatcher("../index.jsp");
//            dispatcher.forward(httpServletRequest, servletResponse);
        } else {
            // 继续执行后面的操作
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    public void destroy() {

    }
}

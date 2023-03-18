package com.delores.servlet;

import com.delores.service.Carbon;
import com.delores.utils.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * @author William
 * @date 3/30/21 3:10 PM
 * @description
 */
public class HelloServlet implements Servlet {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Carbon carbon;

    public void init(ServletConfig servletConfig) throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, servletConfig.getServletContext());

        logger.info("------- tomcat instantiate servlet -------");

        ServletContext context = servletConfig.getServletContext();
        Enumeration initParameterNames = context.getInitParameterNames();
        String contextPath = context.getContextPath();
        String realPath = context.getRealPath("/");
        logger.info("initParameterNames: " + initParameterNames.toString());
        logger.info("Context Path: " + contextPath);
        logger.info("real path: " + realPath);


    }

    public ServletConfig getServletConfig() {
        return null;
    }

    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        logger.info("------- servlet execute service method -------");


        Carbon carbon1 = SpringUtil.getBean(Carbon.class);

        Carbon carbon2 = SpringUtil.getBean(Carbon.class);

        System.out.println(carbon1);
        System.out.println(carbon2);

        System.out.println(carbon);

        PrintWriter out = servletResponse.getWriter();
        out.println("success servlet service!!!");
        out.println("Delores");
    }

    public String getServletInfo() {
        return null;
    }

    public void destroy() {
        logger.info("------- servlet execute destroy method -------");
    }
}

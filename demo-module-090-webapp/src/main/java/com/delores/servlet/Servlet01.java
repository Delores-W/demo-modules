package com.delores.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @author William
 * @date 3/30/21 11:10 PM
 * @description
 */
public class Servlet01 extends GenericServlet {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        logger.info("Extend GenericServlet -- Service Method");
    }
}

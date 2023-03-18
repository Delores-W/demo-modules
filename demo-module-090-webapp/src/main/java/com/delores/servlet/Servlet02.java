package com.delores.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * @author William
 * @date 3/30/21 11:15 PM
 * @description
 */
public class Servlet02 extends HttpServlet {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        logger.info(config.getServletName());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.service(req, resp);

        String servletName = getServletConfig().getServletName();
        logger.info(servletName); //    Servlet02

        ServletContext context = getServletContext();
        logger.info(context.getContextPath()); //    /webapp

        // 再次请求时， 会把SessionID 以cookie的形式发送给服务器
        HttpSession session = req.getSession();
        System.out.println(session.getId()); //    7D0A105821E0C02DFFC307DCD5B56F15

        logger.info("HttpServletResponse Delores");
        resp.getWriter().println("HttpServletResponse Delores");


//        RequestDispatcher dispatcher = req.getRequestDispatcher("/hello");
//        dispatcher.forward(req, resp);


    }

//    @Override
//    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
////        super.service(req, res);
//        res.getWriter().println("ServletResponse Delores");
//        logger.info("ServletResponse Delores");
//    }
}

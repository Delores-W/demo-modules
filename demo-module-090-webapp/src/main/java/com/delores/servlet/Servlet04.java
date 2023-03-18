package com.delores.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author William
 * @date 3/31/21 5:48 PM
 * @description
 */
//@WebServlet(name = "Servlet04", urlPatterns = {"/04"})
public class Servlet04 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 再次请求时， 会把SessionID 以cookie的形式发送给服务器, 通过id获得 Session对象，
        // 如果不存在，则重新创建
        HttpSession session = request.getSession();
        System.out.println(session.getId());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}

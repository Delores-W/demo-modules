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
 * @date 3/31/21 5:19 PM
 * @description
 */
//@WebServlet(urlPatterns = {"/03"})
public class Servlet03 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 第一次调用request.getSession() 会创建一个Session对象，
        // 响应的时候将 JSESSIONID：AF9AB91E9A2013F6E35A37F63EC1C1F3 放入cookie 中
        HttpSession session = request.getSession();
        session.setAttribute("name", "Delores");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}

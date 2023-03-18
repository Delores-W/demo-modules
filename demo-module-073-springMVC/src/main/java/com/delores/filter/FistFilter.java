package com.delores.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author William
 * @date 9/1/21 2:02 PM
 * @description
 */
public class FistFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("First Filter Init");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("First Filter doFilter!!!");
        chain.doFilter(request, response);
    }

    public void destroy() {
        System.out.println("First Filter Destroy");
    }
}

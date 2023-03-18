//package com.delores.medusa.config.filter;
//
//import com.delores.medusa.config.Config;
//import com.delores.medusa.utils.JwtUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * @author William
// * @date 5/6/21 12:51 PM
// * @description
// */
//public class AuthenticationTokenFilter extends OncePerRequestFilter {
//
//    private static final String TOKEN_PREFIX = "Bearer ";
//
//    @Autowired
//    private Config.Jwt jwt;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String authToken = request.getHeader(jwt.getHeader());
//
//        if (authToken != null && authToken.startsWith(TOKEN_PREFIX)) {
//            authToken = authToken.substring(7);
//        }
//
//        String username = JwtUtil.parseUserFromToken(authToken);
//
//        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null, null);
//            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//        }
//
//        filterChain.doFilter(request, response);
//    }
//}

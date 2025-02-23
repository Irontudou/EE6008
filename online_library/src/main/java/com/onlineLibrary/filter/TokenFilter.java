package com.onlineLibrary.filter;

import com.onlineLibrary.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * 令牌校验过滤器
 * 不使用时注释掉@WebFilter(urlPatterns = "/*")
 */
//@WebFilter(urlPatterns = "/*")
public class TokenFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //获取请求路径
        String requestURI = request.getRequestURI();
        //判断是否为login
        if (requestURI.contains("/login01")|| requestURI.contains("/register01")) {
            filterChain.doFilter(request, response);
            return;
        }
        //获取请求头 token
        String token = request.getHeader("token");
        //判断token是否存在 不存在说明用户未登录 响应401(或者响应超时，请重新登录等)
        if (token == null || token.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        //如果纯在，校验token，校验失败，响应401
        try {
            JwtUtils.parseJwt(token);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        //校验通过
        filterChain.doFilter(request, response);
    }
}

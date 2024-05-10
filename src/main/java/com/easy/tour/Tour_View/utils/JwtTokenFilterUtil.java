package com.easy.tour.Tour_View.utils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

public class JwtTokenFilterUtil extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        Cookie[] cookies = httpRequest.getCookies();

        // Check Token
        boolean tokenFound = false;
        boolean isLoginPage = httpRequest.getRequestURI().equals("/admin/v1/login");

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("jwtToken".equals(cookie.getName())) {
                    tokenFound = true;
                    break;
                }
            }
        }

        if (!isLoginPage && !tokenFound) {
            if (!httpRequest.getRequestURI().equals("/admin/v1/forgot-password")) {
                httpResponse.sendRedirect("/admin/v1/login");
                return;
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}

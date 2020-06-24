//package com.minhvu.fruit.controller;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebFilter("/user/*")
//public class AuthFilter implements Filter {
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest req = (HttpServletRequest) servletRequest;
//        HttpServletResponse resp = (HttpServletResponse) servletResponse;
//        if (req.getSession().getAttribute("id_user") != null){
//            filterChain.doFilter(req,resp);
//        }else {
//            resp.sendRedirect(req.getContextPath() + "/login");
//        }
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}

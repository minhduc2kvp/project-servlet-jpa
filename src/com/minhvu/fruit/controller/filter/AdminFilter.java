//package com.minhvu.fruit.controller.filter;
//
//import com.minhvu.fruit.service.implement.UserServiceImpl;
//import com.minhvu.fruit.service.interfaces.UserService;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebFilter("/admin/*")
//public class AdminFilter implements Filter {
//    private UserService userService = new UserServiceImpl();
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
//        if (req.getSession().getAttribute("id_user") != null && userService.getById((Integer) req.getSession().getAttribute("id_user")).getRole().equalsIgnoreCase("role_admin")){
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

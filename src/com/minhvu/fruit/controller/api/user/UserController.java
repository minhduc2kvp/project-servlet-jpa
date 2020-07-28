package com.minhvu.fruit.controller.api.user;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.minhvu.fruit.common.AppConfig;
import com.minhvu.fruit.dto.UserDTO;
import com.minhvu.fruit.model.User;
import com.minhvu.fruit.service.implement.UserServiceImpl;
import com.minhvu.fruit.service.interfaces.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/user")
public class UserController extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    private Gson gson = AppConfig.GSON;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDTO result = null;
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            result = userService.getById(id);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        if (result != null){
            resp.getWriter().write(gson.toJson(result));
            resp.getWriter().flush();
        }else {
            resp.getWriter().write("Don't have any result !!!");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDTO result = null;
        try {
            UserDTO userDTO = gson.fromJson(req.getReader(), UserDTO.class);
            result = userService.insert(userDTO);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        if (result != null){
            resp.getWriter().write(gson.toJson(result));
        }else {
            resp.getWriter().write("{\"error\" : \"Didn't insert user\"}");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDTO result = null;
        try {
            UserDTO userDTO = gson.fromJson(req.getReader(), UserDTO.class);
            result = userService.update(userDTO);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        if (result != null){
            resp.getWriter().write(gson.toJson(result));
        }else {
            resp.getWriter().write("{\"error\" : \"Didn't update user\"}");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean check = false;
        try{
            int id = Integer.parseInt(req.getParameter("id"));
            userService.delete(id);
            check = true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        if(check){
            resp.getWriter().write("{\"report\" : \"success\"}");
        }else {
            resp.getWriter().write("{\"report\" : \"faild\"}");
        }
    }
}

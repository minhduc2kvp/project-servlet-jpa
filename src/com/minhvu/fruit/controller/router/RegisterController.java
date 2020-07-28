package com.minhvu.fruit.controller.router;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.minhvu.fruit.common.AppConfig;
import com.minhvu.fruit.dto.UserDTO;
import com.minhvu.fruit.model.User;
import com.minhvu.fruit.service.implement.UserServiceImpl;
import com.minhvu.fruit.service.interfaces.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterController extends HttpServlet {
    private Gson gson = AppConfig.GSON;
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("register.html");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        UserDTO result = null;
        try {
            UserDTO user = gson.fromJson(req.getReader(),UserDTO.class);
            result = userService.insert(user);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        if (result != null){
            resp.getWriter().println("success");
        }else {
            resp.getWriter().println("fail");
        }
    }
}

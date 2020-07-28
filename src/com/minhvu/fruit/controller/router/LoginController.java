package com.minhvu.fruit.controller.router;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.minhvu.fruit.common.AppConfig;
import com.minhvu.fruit.dto.UserDTO;
import com.minhvu.fruit.service.implement.UserServiceImpl;
import com.minhvu.fruit.service.interfaces.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    private Gson gson = AppConfig.GSON;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("login.html");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean check = false;
        try {
            JsonObject jsonObject = gson.fromJson(req.getReader(),JsonObject.class);
            String email = jsonObject.get("email").getAsString();
            String password = jsonObject.get("password").getAsString();
            UserDTO user = userService.login(email,password);
            if (user != null){
                req.getSession().setAttribute("id_user",user.getId());
                check = true;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        if (check){
            resp.getWriter().println("success");
        }else {
            resp.getWriter().println("fail");
        }
    }
}

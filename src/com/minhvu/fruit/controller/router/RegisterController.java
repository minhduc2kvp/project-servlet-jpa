package com.minhvu.fruit.controller.router;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.minhvu.fruit.common.AppConfig;
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
    private Gson gson = new Gson();
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("register.html");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        boolean check = false;
        try {
            JsonObject jsonObject = gson.fromJson(req.getReader(),JsonObject.class).getAsJsonObject();
            String firstname = AppConfig.encodeUTF8(jsonObject.get("firstname").getAsString());
            String lastname = AppConfig.encodeUTF8(jsonObject.get("lastname").getAsString());
            String email = jsonObject.get("email").getAsString();
            String password = jsonObject.get("password").getAsString();
            String birthday = jsonObject.get("birthday").getAsString();
            String avatar = jsonObject.get("avatar").getAsString();
            int id_ward = jsonObject.get("id_ward").getAsInt();
            String address_detail = AppConfig.encodeUTF8(jsonObject.get("address_detail").getAsString());
            if (userService.insert(firstname,lastname,email,password,birthday,avatar,id_ward,address_detail)){
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

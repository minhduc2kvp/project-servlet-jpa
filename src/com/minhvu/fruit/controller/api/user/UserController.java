package com.minhvu.fruit.controller.api.user;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.minhvu.fruit.model.User;
import com.minhvu.fruit.service.implement.UserServiceImpl;
import com.minhvu.fruit.service.interfaces.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/my-info")
public class UserController extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = userService.getById((Integer) req.getSession().getAttribute("id_user"));
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        JsonObject object = new JsonObject();
        object.addProperty("id",user.getId());
        object.addProperty("firstname",user.getFirstname());
        object.addProperty("lastname",user.getLastname());
        object.addProperty("email",user.getEmail());
        object.addProperty("birthday",user.getBirthday().toString());
        object.addProperty("avatar",user.getAvatar());
        resp.getWriter().println(gson.toJson(object));
    }
}

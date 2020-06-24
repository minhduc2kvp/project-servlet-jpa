package com.minhvu.fruit.controller.api.comment;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.minhvu.fruit.common.AppConfig;
import com.minhvu.fruit.model.Comment;
import com.minhvu.fruit.service.implement.CommentServiceImpl;
import com.minhvu.fruit.service.interfaces.CommentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/api/comment")
public class CommentController extends HttpServlet {
    private Gson gson = AppConfig.GSON;
    private CommentService commentService = new CommentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int idProduct = Integer.parseInt(req.getParameter("id_product"));
            List<Comment> comments = commentService.getByProductId(idProduct);
            List<JsonObject> jsonObjectList = new ArrayList<>();
            for (Comment comment : comments){
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("id", comment.getId());
                jsonObject.addProperty("id_user", comment.getIdUser());
                jsonObject.addProperty("name_user", comment.getUserByIdUser().getFirstname() + " " + comment.getUserByIdUser().getLastname());
                jsonObject.addProperty("create_time", comment.getCreateTime().toLocaleString());
                jsonObjectList.add(jsonObject);
            }
            resp.getWriter().println(jsonObjectList);
            resp.getWriter().flush();
        }catch (Exception e){
            System.out.println(e.getMessage());
            resp.getWriter().println("Don't exist id_product paramater");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonObject jsonObject = gson.fromJson(req.getReader(), JsonObject.class);
        boolean check = false;
        try {
            int id_product = jsonObject.get("id_product").getAsInt();
            int id_user = jsonObject.get("id_user").getAsInt();
            String comment = AppConfig.encodeUTF8(jsonObject.get("comment").getAsString());
            check = commentService.insert(id_product,id_user,comment);
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

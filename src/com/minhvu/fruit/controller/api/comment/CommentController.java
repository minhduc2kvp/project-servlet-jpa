package com.minhvu.fruit.controller.api.comment;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.minhvu.fruit.common.AppConfig;
import com.minhvu.fruit.dto.CommentDTO;
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
        List<CommentDTO> comments = new ArrayList<>();
        try {
            int idProduct = Integer.parseInt(req.getParameter("idProduct"));
            comments = commentService.getByProductId(idProduct);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        resp.getWriter().write(gson.toJson(comments));
        resp.getWriter().flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CommentDTO result = null;

        try {
            CommentDTO commentDTO = gson.fromJson(req.getReader(), CommentDTO.class);
            result = commentService.insert(commentDTO);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        if (result != null){
            resp.getWriter().write(gson.toJson(result));
        }else {
            resp.getWriter().write("{\"error\" : \"Didn't insert comment\"}");
        }
        resp.getWriter().flush();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CommentDTO result = null;

        try {
            CommentDTO commentDTO = gson.fromJson(req.getReader(), CommentDTO.class);
            result = commentService.update(commentDTO);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        if (result != null){
            resp.getWriter().write(gson.toJson(result));
        }else {
            resp.getWriter().write("{\"error\" : \"Didn't update comment\"}");
        }
        resp.getWriter().flush();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean check = false;
        try{
            int id = Integer.parseInt(req.getParameter("id"));
            commentService.delete(id);
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

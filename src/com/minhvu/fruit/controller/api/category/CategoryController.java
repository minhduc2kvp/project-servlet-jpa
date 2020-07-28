package com.minhvu.fruit.controller.api.category;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.minhvu.fruit.common.AppConfig;
import com.minhvu.fruit.dto.CategoryDTO;
import com.minhvu.fruit.model.Category;
import com.minhvu.fruit.service.implement.CategoryServiceImpl;
import com.minhvu.fruit.service.interfaces.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/api/category")
public class CategoryController extends HttpServlet {
    private CategoryService categoryService = new CategoryServiceImpl();
    private Gson gson = AppConfig.GSON;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CategoryDTO> categories = new ArrayList<>();
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            categories.add(categoryService.getById(id));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        if (categories.size() == 0){
            categories.addAll(categoryService.getAll());
        }
        resp.getWriter().write(gson.toJson(categories));
        resp.getWriter().flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CategoryDTO result = null;
        try {
            CategoryDTO categoryDTO = gson.fromJson(req.getReader(), CategoryDTO.class);
            result = categoryService.insert(categoryDTO);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        if (result != null){
            resp.getWriter().write(gson.toJson(result));
        }else {
            resp.getWriter().write("{\"error\" : \"Didn't insert category\"}");
        }
        resp.getWriter().flush();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CategoryDTO result = null;
        try {
            CategoryDTO categoryDTO = gson.fromJson(req.getReader(), CategoryDTO.class);
            result = categoryService.update(categoryDTO);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        if (result != null){
            resp.getWriter().write(gson.toJson(result));
        }else {
            resp.getWriter().write("{\" error \" : \" Didn't insert category \"}");
        }
        resp.getWriter().flush();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean check = false;
        try{
            int id = Integer.parseInt(req.getParameter("id"));
            categoryService.delete(id);
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

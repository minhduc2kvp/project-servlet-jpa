package com.minhvu.fruit.controller.api.category;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.minhvu.fruit.common.AppConfig;
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
        List<Category> categories = categoryService.getAll();
        List<JsonObject> jsonObjectList = new ArrayList<>();
        for (Category category : categories){
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("id", category.getId());
            jsonObject.addProperty("name", category.getName());
            jsonObjectList.add(jsonObject);
        }
        resp.getWriter().println(gson.toJson(jsonObjectList));
        resp.getWriter().flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonObject jsonObject = gson.fromJson(req.getReader(),JsonObject.class);
        boolean check = false;
        try {
            String name = AppConfig.encodeUTF8(jsonObject.get("name").getAsString());
            check = categoryService.insert(name);
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

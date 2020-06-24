package com.minhvu.fruit.controller.api.product;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.minhvu.fruit.common.AppConfig;
import com.minhvu.fruit.service.implement.ProductServiceImpl;
import com.minhvu.fruit.service.interfaces.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@WebServlet("/admin/add-product")
public class AddProduct extends HttpServlet {
    private ProductService productService = new ProductServiceImpl();
    private Gson gson = AppConfig.GSON;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("add_product.html");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean check = false;
        try {
            JsonObject jsonObject = gson.fromJson(req.getReader(),JsonObject.class);
            List<String> images = new LinkedList<>();
            for (JsonElement obj : jsonObject.get("avatar").getAsJsonArray()){
                images.add(obj.getAsString());
            }
            String name = AppConfig.encodeUTF8(jsonObject.get("name").getAsString());
            String description = AppConfig.encodeUTF8(jsonObject.get("description").getAsString());
            double price = jsonObject.get("price").getAsDouble();
            String origin = AppConfig.encodeUTF8(jsonObject.get("origin").getAsString());
            double amount = jsonObject.get("amount").getAsDouble();
            int id_category = jsonObject.get("id_category").getAsInt();
            check = productService.insert(name,description,price,origin,amount,images,id_category);
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

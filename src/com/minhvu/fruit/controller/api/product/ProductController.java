package com.minhvu.fruit.controller.api.product;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.minhvu.fruit.common.AppConfig;
import com.minhvu.fruit.model.Product;
import com.minhvu.fruit.service.implement.ProductServiceImpl;
import com.minhvu.fruit.service.interfaces.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/api/product")
public class ProductController extends HttpServlet {
    private ProductService productService = new ProductServiceImpl();
    private Gson gson = AppConfig.GSON;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int idCategory = Integer.parseInt(req.getParameter("id_category"));
            List<Product> products = productService.getByCategory(idCategory);
            if (products != null){
                List<JsonObject> jsonObjectList = new ArrayList<>();
                for (Product product : products){
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("id", product.getId());
                    jsonObject.addProperty("name", product.getName());
                    jsonObject.addProperty("description", product.getDescription());
                    jsonObject.addProperty("price", DecimalFormat.getInstance().format(product.getPrice()));
                    jsonObject.addProperty("origin", product.getOrigin());
                    jsonObject.addProperty("amount", product.getAmount());
                    jsonObject.addProperty("sale", product.getSale());
                    jsonObject.addProperty("create_time", product.getCreateDate().toLocaleString());
                    jsonObject.addProperty("image1", product.getImage1());
                    jsonObject.addProperty("image2", product.getImage2());
                    jsonObject.addProperty("image3", product.getImage3());
                    jsonObjectList.add(jsonObject);
                }
                resp.getWriter().println(gson.toJson(jsonObjectList));
                resp.getWriter().flush();
            }else {
                resp.getWriter().println("Something wrong!!!");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            resp.getWriter().println("Don't exist category");
        }
    }
}

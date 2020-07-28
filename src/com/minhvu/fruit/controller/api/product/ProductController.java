package com.minhvu.fruit.controller.api.product;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.minhvu.fruit.common.AppConfig;
import com.minhvu.fruit.dto.ProductDTO;
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
        List<ProductDTO> products = new ArrayList<>();
        try {

            try {
                int idCategory = Integer.parseInt(req.getParameter("id_category"));
                products.addAll(productService.getByCategory(idCategory));
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

            try {
                int id = Integer.parseInt(req.getParameter("id"));
                products.add(productService.getById(id));
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

            if (products.size() == 0){
                products.addAll(productService.getAll());
            }

            resp.getWriter().write(gson.toJson(products));
            resp.getWriter().flush();
        }catch (Exception e){
            System.out.println(e.getMessage());
            resp.getWriter().println("Don't have any product !!!");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDTO result = null;
        try {
            ProductDTO productDTO = gson.fromJson(req.getReader(), ProductDTO.class);
            result = productService.insert(productDTO);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        if (result != null){
            resp.getWriter().write(gson.toJson(result));
        }else {
            resp.getWriter().write("{\"error\" : \"Didn't insert product\"}");
        }
        resp.getWriter().flush();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDTO result = null;
        try {
            ProductDTO productDTO = gson.fromJson(req.getReader(), ProductDTO.class);
            result = productService.update(productDTO);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        if (result != null){
            resp.getWriter().write(gson.toJson(result));
        }else {
            resp.getWriter().write("{\"error\" : \"Didn't insert product\"}");
        }
        resp.getWriter().flush();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean check = false;
        try{
            int id = Integer.parseInt(req.getParameter("id"));
            productService.delete(id);
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

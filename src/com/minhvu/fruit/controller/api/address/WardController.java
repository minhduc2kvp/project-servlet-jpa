package com.minhvu.fruit.controller.api.address;

import com.google.gson.Gson;
import com.minhvu.fruit.common.AppConfig;
import com.minhvu.fruit.dto.AddressDTO;
import com.minhvu.fruit.service.implement.AddressServiceImpl;
import com.minhvu.fruit.service.interfaces.AddressService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@WebServlet("/api/address/ward")
public class WardController extends HttpServlet {
    private AddressService addressService = new AddressServiceImpl();
    private Gson gson = AppConfig.GSON;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<AddressDTO> wards = new ArrayList<>();
        try {
            int id_district = Integer.parseInt(req.getParameter("idDistrict"));
            wards = addressService.getAllWardsInDistrict(id_district);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        String resultJson = gson.toJson(wards);
        resp.getWriter().println(resultJson);
        resp.getWriter().flush();
    }
}

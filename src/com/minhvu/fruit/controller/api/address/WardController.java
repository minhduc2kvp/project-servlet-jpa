package com.minhvu.fruit.controller.api.address;

import com.google.gson.Gson;
import com.minhvu.fruit.model.AddressJson;
import com.minhvu.fruit.model.Ward;
import com.minhvu.fruit.service.implement.AddressServiceImpl;
import com.minhvu.fruit.service.interfaces.AddressService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@WebServlet("/api/address/ward")
public class WardController extends HttpServlet {
    private AddressService addressService = new AddressServiceImpl();
    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id_district = Integer.parseInt(req.getParameter("id"));
        List<AddressJson> wards = new LinkedList<>();
        for(Ward ward : addressService.getAllWardsInDistrict(id_district)){
            wards.add(new AddressJson(ward.getId(),ward.getName()));
        }
        String resultJson = gson.toJson(wards);
        resp.getWriter().println(resultJson);
        resp.getWriter().flush();
    }
}

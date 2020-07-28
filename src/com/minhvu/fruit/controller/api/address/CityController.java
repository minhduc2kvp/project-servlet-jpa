package com.minhvu.fruit.controller.api.address;

import com.google.gson.Gson;
import com.minhvu.fruit.dto.AddressDTO;
import com.minhvu.fruit.model.City;
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

@WebServlet("/api/address/city")
public class CityController extends HttpServlet {
    private AddressService addressService = new AddressServiceImpl();
    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<AddressDTO> cities = addressService.getAllCities();
        String resultJson = gson.toJson(cities);
        resp.getWriter().println(resultJson);
        resp.getWriter().flush();
    }
}

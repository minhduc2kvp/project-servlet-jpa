package com.minhvu.fruit.controller.api.address;

import com.google.gson.Gson;
import com.minhvu.fruit.dto.AddressDTO;
import com.minhvu.fruit.model.District;
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

@WebServlet("/api/address/district")
public class DistrictController extends HttpServlet {
    private AddressService addressService = new AddressServiceImpl();
    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<AddressDTO> districts = new ArrayList<>();
        try {
            int id_city = Integer.parseInt(req.getParameter("idCity"));
            districts = addressService.getAllDistrictsInCity(id_city);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        String resultJson = gson.toJson(districts);
        resp.getWriter().println(resultJson);
        resp.getWriter().flush();
    }
}

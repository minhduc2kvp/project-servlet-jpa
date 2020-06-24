package com.minhvu.fruit.controller.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.minhvu.fruit.common.AppConfig;
import com.minhvu.fruit.model.City;
import com.minhvu.fruit.model.District;
import com.minhvu.fruit.model.User;
import com.minhvu.fruit.model.Ward;
import com.minhvu.fruit.service.implement.AddressServiceImpl;
import com.minhvu.fruit.service.implement.UserServiceImpl;
import com.minhvu.fruit.service.interfaces.AddressService;
import com.minhvu.fruit.service.interfaces.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@WebServlet("/api/address")
public class AddressController extends HttpServlet {
    private AddressService addressService = new AddressServiceImpl();
    private UserService userService = new UserServiceImpl();
    private Gson gson = AppConfig.GSON;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> resultJson = new LinkedList<>();
//        for (Address address : addressService.getAllAddresses()){
//            resultJson.add("{ id_user: '" + address.getIdUser() + "', id_address : '" + address.getWardByIdAddress().getId() + "'," +
//                    " detail_address : '" + address.getDetailAddress() + "', ward : '" + address.getWardByIdAddress().getName() +"', " +
//                    "district : '" + address.getWardByIdAddress().getDistrictByIdDistrict().getName() + "'," +
//                    " city : '" + address.getWardByIdAddress().getDistrictByIdDistrict().getCityByIdCity().getName() + "' }");
//        }
//        for (City city : addressService.getAllCities()) {
//            resultJson.add("{ id : " + city.getId() + ", name : " + city.getName() + "}");
//        }
//        for (City city : addressService.getAllCities()) {
//            for (District district : addressService.getAllDistrictsInCity(city)){
//                resultJson.add("{ id : " + district.getId() + ", name : " + district.getName() + "}");
//            }
//            break;
//        }
        for (City city : addressService.getAllCities()) {
            for (District district : addressService.getAllDistrictsInCity(city.getId())){
                for (Ward ward : addressService.getAllWardsInDistrict(district.getId())){
                    resultJson.add("{ id : '" + ward.getId() + "', name : '" + ward.getName() + "', name-district : '" + ward.getDistrictByIdDistrict().getName() + "', name-city : '" + ward.getDistrictByIdDistrict().getCityByIdCity().getName() + "'}");
                }
                break;
            }
            break;
        }
        resp.getWriter().println(resultJson.toString());
//        List<JsonObject> jsonObjects = new ArrayList<>();
//        for (User user : userService.getAllUsersAccepted()){
//            JsonObject jsonObject = new JsonObject();
//            jsonObject.addProperty("firstname", user.getFirstname());
//            jsonObject.addProperty("lastname",user.getLastname());
//            jsonObject.addProperty("email",user.getEmail());
//
//            jsonObjects.add(jsonObject);
//        }
//
//        String result = gson.toJson(jsonObjects);
//        resp.getWriter().println(result);
    }
}

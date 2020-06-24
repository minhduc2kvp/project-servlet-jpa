package com.minhvu.fruit.controller.api.invoice;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.minhvu.fruit.common.AppConfig;
import com.minhvu.fruit.model.Address;
import com.minhvu.fruit.model.Invoice;
import com.minhvu.fruit.model.Item;
import com.minhvu.fruit.service.implement.InvoiceServiceImpl;
import com.minhvu.fruit.service.interfaces.InvoiceService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@WebServlet("/api/user/invoice")
public class InvoiceController extends HttpServlet {
    private InvoiceService invoiceService = new InvoiceServiceImpl();
    private Gson gson = AppConfig.GSON;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idUser = -1;
        try {
            idUser = Integer.parseInt(req.getParameter("id_user"));
        }catch (Exception e){
            System.out.println("Don't exist id user for load invoice");
        }
        if (idUser != -1){
            List<Invoice> invoices = invoiceService.getByUserId(idUser);
            resp.getWriter().println(toInvoiceJson(invoices));
            resp.getWriter().flush();
        }else {
            List<Invoice> invoices = invoiceService.getAll();
            resp.getWriter().println(toInvoiceJson(invoices));
            resp.getWriter().flush();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean check = false;
        try {
            JsonObject jsonObject = gson.fromJson(req.getReader(), JsonObject.class);
//            int idUser = (int) req.getSession().getAttribute("id_user");
            int idUser = jsonObject.get("id_user").getAsInt();
            String nameUser = AppConfig.encodeUTF8(jsonObject.get("name_user").getAsString());
            String email = jsonObject.get("email").getAsString();
            String numberphone = jsonObject.get("numberphone").getAsString();
            String addressDetail = AppConfig.encodeUTF8(jsonObject.get("address_detail").getAsString());
            int idWard = jsonObject.get("id_ward").getAsInt();
            HashMap<Integer, Double> items = new HashMap<>();
            JsonArray listItem = jsonObject.get("list_item").getAsJsonArray();
            for(JsonElement jsonElement : listItem){
                JsonObject jObj = jsonElement.getAsJsonObject();
                items.put(jObj.get("id_product").getAsInt(), jObj.get("quantity").getAsDouble());
            }
            invoiceService.insert(nameUser,email,numberphone,idUser,idWard,addressDetail,items);
            check = true;
        }catch (Exception e){
            AppConfig.logError(e);
        }
        if (check){
            resp.getWriter().println("success");
        }else {
            resp.getWriter().println("fail");
        }
    }

    private List<JsonObject> toInvoiceJson(List<Invoice> invoices){
        List<JsonObject> jsonObjects = new ArrayList<>();
        for (Invoice invoice : invoices){
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("id", invoice.getId());
            jsonObject.addProperty("id_user", invoice.getIdUser());
            jsonObject.addProperty("name_user", invoice.getNameUser());
            jsonObject.addProperty("email", invoice.getEmail());
            jsonObject.addProperty("numberphone", invoice.getNumberphone());
            jsonObject.addProperty("date", invoice.getDate().toLocaleString());
            Address address = invoice.getAddressByIdAddress();
            String addressDetail = address.getDetailAddress() + ", " + address.getWardByIdWard().getName() + ", " + address.getWardByIdWard().getDistrictByIdDistrict().getName() + ", " + address.getWardByIdWard().getDistrictByIdDistrict().getCityByIdCity().getName();
            jsonObject.addProperty("address", addressDetail);
            JsonArray jsonElements = new JsonArray();
            List<Item> itemList = (List<Item>) invoice.getItemsById();
            for (Item item : itemList){
                JsonObject jObj = new JsonObject();
                jObj.addProperty("id_product", item.getIdProduct());
                jObj.addProperty("name_product", item.getProduct().getName());
                jObj.addProperty("avatar_product", item.getProduct().getImage1());
                jObj.addProperty("quantity", item.getQuantity());
                jObj.addProperty("product_price", item.getIdProduct());
                jsonElements.add(jObj);
            }
            jsonObject.addProperty("list_item", gson.toJson(jsonElements));
            jsonObjects.add(jsonObject);
        }
        return jsonObjects;
    }
}

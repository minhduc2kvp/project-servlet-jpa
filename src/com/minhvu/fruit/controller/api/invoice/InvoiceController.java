package com.minhvu.fruit.controller.api.invoice;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.minhvu.fruit.common.AppConfig;
import com.minhvu.fruit.dto.InvoiceDTO;
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

@WebServlet("/api/invoice")
public class InvoiceController extends HttpServlet {
    private InvoiceService invoiceService = new InvoiceServiceImpl();
    private Gson gson = AppConfig.GSON;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<InvoiceDTO> invoiceDTOList = new ArrayList<>();
        Integer id = null;
        try {
            id = Integer.parseInt(req.getParameter("id"));
            invoiceDTOList.add(invoiceService.getById(id));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        if (id == null){
            try {
                int idUser = Integer.parseInt(req.getParameter("idUser"));
                invoiceDTOList.addAll(invoiceService.getByUserId(idUser));
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

        if (invoiceDTOList.size() != 0){
            resp.getWriter().write(gson.toJson(invoiceDTOList));
        }else {
            resp.getWriter().write("{\"error\":\"Don't have any result\"}");
        }
        resp.getWriter().flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        InvoiceDTO result = null;
        try {
            InvoiceDTO invoiceDTO = gson.fromJson(req.getReader(), InvoiceDTO.class);
            result = invoiceService.insert(invoiceDTO);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        if (result != null){
            resp.getWriter().write(gson.toJson(result));
        }else {
            resp.getWriter().write("{\"error\":\"Didn't insert invoice\"}");
        }
        resp.getWriter().flush();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        InvoiceDTO result = null;
        try {
            InvoiceDTO invoiceDTO = gson.fromJson(req.getReader(), InvoiceDTO.class);
            result = invoiceService.update(invoiceDTO);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        if (result != null){
            resp.getWriter().write(gson.toJson(result));
        }else {
            resp.getWriter().write("{\"error\":\"Didn't update invoice\"}");
        }
        resp.getWriter().flush();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean check = false;
        try{
            int id = Integer.parseInt(req.getParameter("id"));
            invoiceService.delete(id);
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

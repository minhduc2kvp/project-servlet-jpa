package com.minhvu.fruit.service.interfaces;

import com.minhvu.fruit.model.Invoice;

import java.util.HashMap;
import java.util.List;

public interface InvoiceService extends BaseService<Invoice> {
    List<Invoice> getByUserId(int id_user);
    boolean insert(String name_user, String email, String numberphone, int id_user,int id_ward, String address_detail, HashMap<Integer, Double> items);
}

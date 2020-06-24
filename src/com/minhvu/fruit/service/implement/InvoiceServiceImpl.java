package com.minhvu.fruit.service.implement;

import com.minhvu.fruit.dao.implement.AddressDaoImpl;
import com.minhvu.fruit.dao.implement.InvoiceDaoImpl;
import com.minhvu.fruit.dao.implement.ProductDaoImpl;
import com.minhvu.fruit.dao.implement.UserDaoImpl;
import com.minhvu.fruit.dao.interfaces.AddressDao;
import com.minhvu.fruit.dao.interfaces.InvoiceDao;
import com.minhvu.fruit.dao.interfaces.ProductDao;
import com.minhvu.fruit.dao.interfaces.UserDao;
import com.minhvu.fruit.model.*;
import com.minhvu.fruit.service.interfaces.InvoiceService;
import com.minhvu.fruit.model.Item;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

public class InvoiceServiceImpl implements InvoiceService {
    private InvoiceDao invoiceDao = new InvoiceDaoImpl();
    private AddressDao addressDao = new AddressDaoImpl();
    private ProductDao productDao = new ProductDaoImpl();
    private UserDao userDao = new UserDaoImpl();

    @Override
    public List<Invoice> getByUserId(int id_user) {
        List<Invoice> invoices = null;
        try {
            invoices = invoiceDao.getByUserId(id_user);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return invoices;
    }

    @Override
    public boolean insert(String name_user, String email, String numberphone, int id_user, int id_ward, String address_detail, HashMap<Integer, Double> items) {
        boolean check = false;
        try {
            Ward ward = addressDao.getWardById(id_ward);
            Address address = new Address(address_detail,ward);
            User user = userDao.getById(id_user);
            List<Item> itemList = new ArrayList<>();
            for (Map.Entry<Integer,Double> item : items.entrySet()){
                Product product = productDao.getById(item.getKey());
                double quantity = item.getValue();
                Item i = new Item(quantity, product.getPrice(), product);
                itemList.add(i);
            }
            Invoice invoice = new Invoice(email,numberphone,name_user,user,address,itemList);
            invoice.setDate(new Timestamp(System.currentTimeMillis()));
            invoiceDao.insert(invoice);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return check;
    }

    @Override
    public boolean update(Invoice invoice) {
        boolean check = false;
        try {
            invoiceDao.update(invoice);
            check = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return check;
    }

    @Override
    public boolean delete(int id) {
        boolean check = false;
        try {
            invoiceDao.delete(id);
            check = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return check;
    }

    @Override
    public Invoice getById(int id) {
        Invoice invoice = null;
        try {
            invoice = invoiceDao.getById(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return invoice;
    }

    @Override
    public List<Invoice> getAll() {
        List<Invoice> invoices = null;
        try {
            invoices = invoiceDao.getAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return invoices;
    }
}

package com.minhvu.fruit.service.implement;

import com.minhvu.fruit.common.AppConfig;
import com.minhvu.fruit.dao.implement.CategoryDaoImpl;
import com.minhvu.fruit.dao.implement.ProductDaoImpl;
import com.minhvu.fruit.dao.interfaces.CategoryDao;
import com.minhvu.fruit.dao.interfaces.ProductDao;
import com.minhvu.fruit.model.Product;
import com.minhvu.fruit.service.interfaces.ProductService;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    private ProductDao productDao = new ProductDaoImpl();
    private CategoryDao categoryDao = new CategoryDaoImpl();


    @Override
    public boolean insert(String name, String description, double price, String origin, double amount, List<String> images, int id_category) {
        boolean check = false;
        try {
            Product product = new Product(name,description,BigDecimal.valueOf(price),origin,amount, AppConfig.uploadFileImage(images.get(0)),AppConfig.uploadFileImage(images.get(1)),AppConfig.uploadFileImage(images.get(2)),categoryDao.getById(id_category));
            product.setCreateDate(new Timestamp(System.currentTimeMillis()));
            productDao.insert(product);
            check = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return check;
    }

    @Override
    public List<Product> getByCategory(int idCategory) {
        List<Product> products = null;
        try {
            products = productDao.getByCategory(idCategory);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return products;
    }

    @Override
    public boolean update(Product product) {
        boolean check = false;
        try{
            productDao.update(product);
            check = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return check;
    }

    @Override
    public boolean delete(int id) {
        boolean check = false;
        try{
            productDao.delete(id);
            check = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return check;
    }

    @Override
    public Product getById(int id) {
        Product product = null;
        try {
            product = productDao.getById(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return product;
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = null;
        try {
            products = productDao.getAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return products;
    }
}

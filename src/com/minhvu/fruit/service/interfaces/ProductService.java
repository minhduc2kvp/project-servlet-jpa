package com.minhvu.fruit.service.interfaces;

import com.minhvu.fruit.model.Product;

import java.util.List;

public interface ProductService extends BaseService<Product>{
    boolean insert(String name, String description, double price, String origin, double amount,List<String> images, int id_category);
    List<Product> getByCategory(int idCategory);
}

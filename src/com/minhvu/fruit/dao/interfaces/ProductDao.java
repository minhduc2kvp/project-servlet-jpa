package com.minhvu.fruit.dao.interfaces;

import com.minhvu.fruit.model.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao extends BaseDao<Product>{
    List<Product> getByCategory(int idCategory) throws SQLException;
}

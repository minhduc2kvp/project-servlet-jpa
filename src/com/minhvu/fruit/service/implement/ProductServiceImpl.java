package com.minhvu.fruit.service.implement;

import com.minhvu.fruit.common.converter.ProductConverter;
import com.minhvu.fruit.dao.implement.CategoryDaoImpl;
import com.minhvu.fruit.dao.implement.ProductDaoImpl;
import com.minhvu.fruit.dao.interfaces.CategoryDao;
import com.minhvu.fruit.dao.interfaces.ProductDao;
import com.minhvu.fruit.dto.ProductDTO;
import com.minhvu.fruit.model.Product;
import com.minhvu.fruit.service.interfaces.ProductService;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    private ProductDao productDao = new ProductDaoImpl();
    private CategoryDao categoryDao = new CategoryDaoImpl();
    private ProductConverter productConverter = new ProductConverter();


    @Override
    public ProductDTO insert(ProductDTO productDTO) {
        ProductDTO result = null;
        try {
            Product product = productConverter.toEntity(productDTO);
            product.setCreateDate(new Timestamp(System.currentTimeMillis()));
            product.setCategoryByIdCategory(categoryDao.getById(productDTO.getIdCategory()));
            result = productConverter.toDTO(productDao.insert(product));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public List<ProductDTO> getByCategory(int idCategory) {
        List<ProductDTO> products = new ArrayList<>();
        try {
            List<Product> productList = productDao.getByCategory(idCategory);
            for (Product product : productList){
                products.add(productConverter.toDTO(product));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return products;
    }

    @Override
    public ProductDTO update(ProductDTO productDTO) {
        ProductDTO result = null;
        try {
            Product product = productDao.getById(productDTO.getId());
            product.setName(productDTO.getName());
            product.setDescription(productDTO.getDescription());
            product.setPrice(BigDecimal.valueOf(productDTO.getPrice()));
            product.setOrigin(productDTO.getOrigin());
            product.setSale(productDTO.getSale());
            product.setAmount(productDTO.getAmount());
            product.setImage1(productDTO.getImage1());
            product.setImage2(productDTO.getImage2());
            product.setImage3(productDTO.getImage3());
            result = productConverter.toDTO(productDao.update(product));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
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
    public ProductDTO getById(int id) {
        ProductDTO result = null;
        try {
            result = productConverter.toDTO(productDao.getById(id));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public List<ProductDTO> getAll() {
        List<ProductDTO> products = new ArrayList<>();
        try {
            List<Product> productList = productDao.getAll();
            for (Product product : productList){
                products.add(productConverter.toDTO(product));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return products;
    }
}

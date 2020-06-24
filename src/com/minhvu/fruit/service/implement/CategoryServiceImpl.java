package com.minhvu.fruit.service.implement;

import com.minhvu.fruit.dao.implement.CategoryDaoImpl;
import com.minhvu.fruit.dao.interfaces.CategoryDao;
import com.minhvu.fruit.model.Category;
import com.minhvu.fruit.service.interfaces.CategoryService;

import java.sql.SQLException;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public boolean insert(String name) {
        boolean check = false;
        try {
            Category category = new Category();
            category.setName(name);
            categoryDao.insert(category);
            check = true;
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return check;
    }

    @Override
    public boolean update(Category category) {
        boolean check = false;
        try {
            categoryDao.update(category);
            check = true;
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return check;
    }

    @Override
    public boolean delete(int id) {
        boolean check = false;
        try {
            categoryDao.delete(id);
            check = true;
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return check;
    }

    @Override
    public Category getById(int id) {
        Category category = null;
        try {
            category = categoryDao.getById(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return category;
    }

    @Override
    public List<Category> getAll() {
        List<Category> categories = null;
        try{
            categories = categoryDao.getAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return categories;
    }
}

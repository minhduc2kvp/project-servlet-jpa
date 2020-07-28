package com.minhvu.fruit.service.implement;

import com.minhvu.fruit.common.converter.CategoryConverter;
import com.minhvu.fruit.dao.implement.CategoryDaoImpl;
import com.minhvu.fruit.dao.interfaces.CategoryDao;
import com.minhvu.fruit.dto.CategoryDTO;
import com.minhvu.fruit.model.Category;
import com.minhvu.fruit.service.interfaces.CategoryService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao = new CategoryDaoImpl();
    private CategoryConverter categoryConverter = new CategoryConverter();

    @Override
    public CategoryDTO insert(CategoryDTO categoryDTO) {
        CategoryDTO result = null;
        try {
            Category category = categoryConverter.toEntity(categoryDTO);
            result = categoryConverter.toDTO(categoryDao.insert(category));
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public CategoryDTO update(CategoryDTO categoryDTO) {
        CategoryDTO result = null;
        try {
            Category category = categoryConverter.toEntity(categoryDTO);
            category.setId(categoryDTO.getId());
            result = categoryConverter.toDTO(categoryDao.update(category));
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
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
    public CategoryDTO getById(int id) {
        CategoryDTO result = null;
        try {
            result = categoryConverter.toDTO(categoryDao.getById(id));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public List<CategoryDTO> getAll() {
        List<CategoryDTO> categories = new ArrayList<>();
        try{
            List<Category> categoryList = categoryDao.getAll();
            for (Category category : categoryList){
                categories.add(categoryConverter.toDTO(category));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return categories;
    }
}

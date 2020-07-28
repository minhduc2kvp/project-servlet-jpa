package com.minhvu.fruit.common.converter;

import com.minhvu.fruit.dto.CategoryDTO;
import com.minhvu.fruit.model.Category;

public class CategoryConverter implements Converter<CategoryDTO, Category> {


    @Override
    public CategoryDTO toDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO(category.getId(), category.getName());
        return categoryDTO;
    }

    @Override
    public Category toEntity(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        return category;
    }
}

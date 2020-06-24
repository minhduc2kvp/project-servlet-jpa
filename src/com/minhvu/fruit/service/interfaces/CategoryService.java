package com.minhvu.fruit.service.interfaces;

import com.minhvu.fruit.model.Category;

public interface CategoryService extends BaseService<Category> {
    boolean insert(String name);
}

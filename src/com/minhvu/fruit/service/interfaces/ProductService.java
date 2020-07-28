package com.minhvu.fruit.service.interfaces;

import com.minhvu.fruit.dto.ProductDTO;

import java.util.List;

public interface ProductService extends BaseService<ProductDTO>{
    List<ProductDTO> getByCategory(int idCategory);
}

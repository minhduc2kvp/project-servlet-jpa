package com.minhvu.fruit.common.converter;

import com.minhvu.fruit.dto.ProductDTO;
import com.minhvu.fruit.model.Product;

import java.math.BigDecimal;

public class ProductConverter implements Converter<ProductDTO, Product> {
    @Override
    public ProductDTO toDTO(Product product) {
        ProductDTO productDTO = new ProductDTO(product.getId(),product.getName(),product.getDescription(),product.getPrice().doubleValue(),product.getOrigin(),product.getAmount(),product.getSale(),product.getCreateDate(),product.getBought(),product.getImage1(),product.getImage2(),product.getImage3(),product.getIdCategory());
        return productDTO;
    }

    @Override
    public Product toEntity(ProductDTO productDTO) {
        Product product = new Product(productDTO.getName(),productDTO.getDescription(), BigDecimal.valueOf(productDTO.getPrice()),productDTO.getOrigin(),productDTO.getAmount(),productDTO.getImage1(),productDTO.getImage2(),productDTO.getImage3(),null);
        return product;
    }
}

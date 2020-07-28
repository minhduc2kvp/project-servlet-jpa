package com.minhvu.fruit.common.converter;

import com.minhvu.fruit.dto.ItemDTO;
import com.minhvu.fruit.model.Item;

import java.math.BigDecimal;

public class ItemConverter implements Converter<ItemDTO, Item>{
    private ProductConverter productConverter = new ProductConverter();

    @Override
    public ItemDTO toDTO(Item item) {
        ItemDTO itemDTO = new ItemDTO(item.getId(),productConverter.toDTO(item.getProduct()),item.getQuantity(),item.getProductPrice().doubleValue());
        return itemDTO;
    }

    @Override
    public Item toEntity(ItemDTO itemDTO) {
//        Item item = new Item(itemDTO.getQuantity(), BigDecimal.valueOf(itemDTO.getProductPrice()),productConverter.toEntity(itemDTO.getProduct()));
        return null;
    }
}

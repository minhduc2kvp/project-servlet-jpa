package com.minhvu.fruit.common.converter;

public interface Converter<DTO,ENTITY> {
    DTO toDTO(ENTITY entity);
    ENTITY toEntity(DTO dto);
}

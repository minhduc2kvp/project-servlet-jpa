package com.minhvu.fruit.service.interfaces;

import com.minhvu.fruit.dto.CommentDTO;

import java.util.List;

public interface CommentService extends BaseService<CommentDTO> {
    List<CommentDTO> getByProductId(int id_product);
}

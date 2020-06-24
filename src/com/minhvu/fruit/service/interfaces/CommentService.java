package com.minhvu.fruit.service.interfaces;

import com.minhvu.fruit.model.Comment;

import java.util.List;

public interface CommentService extends BaseService<Comment> {
    boolean insert(int id_product, int id_user, String comment);
    List<Comment> getByProductId(int id_product);
}

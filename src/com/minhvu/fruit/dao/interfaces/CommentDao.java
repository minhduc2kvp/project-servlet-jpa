package com.minhvu.fruit.dao.interfaces;

import com.minhvu.fruit.model.Comment;

import java.sql.SQLException;
import java.util.List;

public interface CommentDao extends BaseDao<Comment> {
    List<Comment> getByProductId(int id_product) throws SQLException;
}

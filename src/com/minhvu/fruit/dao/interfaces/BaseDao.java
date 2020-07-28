package com.minhvu.fruit.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

public interface BaseDao<T> {
    T insert(T t) throws SQLException;
    T update(T t)throws SQLException;
    void delete(int id) throws SQLException;
    T getById(int id) throws SQLException;
    List<T> getAll() throws SQLException;
}

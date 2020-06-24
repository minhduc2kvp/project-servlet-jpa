package com.minhvu.fruit.service.interfaces;

import java.util.List;

public interface BaseService<T> {
    boolean update(T t);
    boolean delete(int id);
    T getById(int id);
    List<T> getAll();
}

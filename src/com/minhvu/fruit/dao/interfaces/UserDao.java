package com.minhvu.fruit.dao.interfaces;

import com.minhvu.fruit.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao extends BaseDao<User>{
    List<User> getAllUsersIsActive() throws SQLException;
}

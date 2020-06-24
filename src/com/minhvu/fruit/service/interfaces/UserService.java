package com.minhvu.fruit.service.interfaces;

import com.minhvu.fruit.model.User;

import java.util.List;

public interface UserService extends BaseService<User>{
    boolean insert(String firstname, String lastname, String email, String password, String birthday, String avatar, int id_ward, String address_detail);
    List<User> getAllUsersIsActive();
    User login(String username, String password);
}

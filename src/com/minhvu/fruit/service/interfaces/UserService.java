package com.minhvu.fruit.service.interfaces;

import com.minhvu.fruit.dto.UserDTO;

import java.util.List;

public interface UserService extends BaseService<UserDTO>{
    List<UserDTO> getAllUsersIsActive();
    UserDTO login(String username, String password);
}

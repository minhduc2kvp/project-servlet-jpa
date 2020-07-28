package com.minhvu.fruit.common.converter;

import com.minhvu.fruit.dto.UserDTO;
import com.minhvu.fruit.model.Address;
import com.minhvu.fruit.model.User;

public class UserConverter implements Converter<UserDTO,User> {

    @Override
    public UserDTO toDTO(User user){
        Address addressUser = user.getAddressByIdAddress();
        String address = addressUser.getDetailAddress() + ", " + addressUser.getWardByIdWard().getName()
                + ", " + addressUser.getWardByIdWard().getDistrictByIdDistrict().getName()
                + ", " + addressUser.getWardByIdWard().getDistrictByIdDistrict().getCityByIdCity().getName();
        UserDTO userDTO = new UserDTO(user.getId(), user.getFirstname(), user.getLastname(), user.getEmail(), "********",user.getBirthday(), user.getAvatar(), address, addressUser.getWardByIdWard().getId(), addressUser.getDetailAddress());
        return userDTO;
    }

    @Override
    public User toEntity(UserDTO userDTO){
        User user = new User(userDTO.getFirstname(),userDTO.getLastname(),userDTO.getEmail(),userDTO.getPassword(),userDTO.getBirthday(),userDTO.getAvatar(),null);
        if (userDTO.getId() != null){
            user.setId(userDTO.getId());
        }
        return user;
    }

}

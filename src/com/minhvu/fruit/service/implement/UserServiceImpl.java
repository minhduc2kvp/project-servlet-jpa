package com.minhvu.fruit.service.implement;

import com.minhvu.fruit.common.converter.UserConverter;
import com.minhvu.fruit.dao.implement.AddressDaoImpl;
import com.minhvu.fruit.dao.implement.UserDaoImpl;
import com.minhvu.fruit.dao.interfaces.AddressDao;
import com.minhvu.fruit.dao.interfaces.UserDao;
import com.minhvu.fruit.dto.UserDTO;
import com.minhvu.fruit.model.Address;
import com.minhvu.fruit.model.User;
import com.minhvu.fruit.service.interfaces.UserService;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    private AddressDao addressDao = new AddressDaoImpl();
    private UserConverter userConverter = new UserConverter();

    @Override
    public UserDTO insert(UserDTO userDTO) {
        UserDTO resultUserDTO = null;
        if (checkAccountAvailable(userDTO.getEmail())){
            return resultUserDTO;
        }
        try{
            String hashPassword = BCrypt.hashpw(userDTO.getPassword(),BCrypt.gensalt(7));
            Address address = new Address(userDTO.getAddressDetail(),addressDao.getWardById(userDTO.getIdWard()));
            User user = userConverter.toEntity(userDTO);
            user.setAddressByIdAddress(address);
            user.setPassword(hashPassword);
            user.setRole("role_user");
            resultUserDTO = userConverter.toDTO(userDao.insert(user));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultUserDTO;
    }

    @Override
    public List<UserDTO> getAllUsersIsActive() {
        List<UserDTO> users = new ArrayList<>();
        try {
            List<User> userList = userDao.getAllUsersIsActive();
            for (User user : userList){
                users.add(userConverter.toDTO(user));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }

    @Override
    public UserDTO login(String email, String password) {
        UserDTO result = null;
        try {
            List<User> userList = userDao.getAllUsersIsActive();
            for (User user : userList){
                if (user.getEmail().equals(email) && BCrypt.checkpw(password,user.getPassword())){
                    result = userConverter.toDTO(user);
                    break;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    private boolean checkAccountAvailable(String email){
        boolean check = false;
        for (UserDTO user : getAllUsersIsActive()){
            if (user.getEmail().equals(email)){
                check = true;
                break;
            }
        }
        return check;
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        UserDTO resultUserDTO = null;
        try {
            User user = userDao.getById(userDTO.getId());
            user.setAddressByIdAddress(new Address(userDTO.getAddressDetail(),addressDao.getWardById(userDTO.getIdWard())));
            user.setFirstname(userDTO.getFirstname());
            user.setLastname(userDTO.getLastname());
            user.setBirthday(userDTO.getBirthday());
            user.setEmail(userDTO.getEmail());
            user.setAvatar(userDTO.getAvatar());
            resultUserDTO = userConverter.toDTO(userDao.update(user));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultUserDTO;
    }

    @Override
    public boolean delete(int id) {
        boolean check = false;
        try {
            userDao.delete(id);
            check = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return check;
    }

    @Override
    public UserDTO getById(int id) {
        UserDTO userDTO = null;
        try {
            userDTO = userConverter.toDTO(userDao.getById(id));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userDTO;
    }

    @Override
    public List<UserDTO> getAll() {
        List<UserDTO> users = new ArrayList<>();
        try {
            List<User> userList = userDao.getAll();
            for (User user : userList){
                users.add(userConverter.toDTO(user));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }
}

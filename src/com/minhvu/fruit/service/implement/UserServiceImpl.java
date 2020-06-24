package com.minhvu.fruit.service.implement;

import com.minhvu.fruit.common.AppConfig;
import com.minhvu.fruit.dao.implement.AddressDaoImpl;
import com.minhvu.fruit.dao.implement.UserDaoImpl;
import com.minhvu.fruit.dao.interfaces.AddressDao;
import com.minhvu.fruit.dao.interfaces.UserDao;
import com.minhvu.fruit.model.Address;
import com.minhvu.fruit.model.User;
import com.minhvu.fruit.service.interfaces.UserService;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    private AddressDao addressDao = new AddressDaoImpl();

    @Override
    public boolean insert(String firstname, String lastname, String email, String password, String birthday, String avatar, int id_ward, String address_detail) {
        boolean check = false;
        if (checkAccountAvailable(email)){
            return check;
        }
        try{
            String hashPassword = BCrypt.hashpw(password,BCrypt.gensalt(7));
            Address address = new Address(address_detail,addressDao.getWardById(id_ward));
            User user = new User(firstname,lastname,email,hashPassword,Date.valueOf(birthday),AppConfig.uploadFileImage(avatar),address);
            user.setRole("role_user");
            userDao.insert(user);
            check = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return check;
    }

    @Override
    public List<User> getAllUsersIsActive() {
        List<User> users = null;
        try {
            users = userDao.getAllUsersIsActive();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }

    @Override
    public User login(String email, String password) {
        User result = null;
        for (User user : getAllUsersIsActive()){
            if (user.getEmail().equals(email) && BCrypt.checkpw(password,user.getPassword())){
                result = user;
                break;
            }
        }
        return result;
    }

    private boolean checkAccountAvailable(String email){
        boolean check = false;
        for (User user : getAllUsersIsActive()){
            if (user.getEmail().equals(email)){
                check = true;
                break;
            }
        }
        return check;
    }

    @Override
    public boolean update(User user) {
        boolean check = false;
        try {
            userDao.update(user);
            check = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return check;
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
    public User getById(int id) {
        User user = null;
        try {
            user = userDao.getById(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> users = null;
        try{
            users = userDao.getAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }
}

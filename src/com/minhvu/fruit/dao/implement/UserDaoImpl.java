package com.minhvu.fruit.dao.implement;

import com.minhvu.fruit.common.AppConfig;
import com.minhvu.fruit.dao.interfaces.UserDao;
import com.minhvu.fruit.model.User;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private EntityManager entityManager = AppConfig.ENTITY_MANAGER;

    @Override
    public List<User> getAllUsersIsActive() {
        List<User> users = new ArrayList<>();
        try {
            users = entityManager.createQuery("select u from User as u where u.status = 0").getResultList();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        entityManager.clear();
        return users;
    }

    @Override
    public User insert(User user) {
        User resultUser = null;
        entityManager.getTransaction().begin();
        try {
            entityManager.persist(user);
        }catch (Exception e){
            System.out.println(e.getMessage());
            entityManager.getTransaction().rollback();
            entityManager.clear();
            return resultUser;
        }
        entityManager.flush();
        resultUser = user;
        entityManager.getTransaction().commit();
        entityManager.clear();
        return resultUser;
    }

    @Override
    public User update(User user) {
        User resultUser = null;
        entityManager.getTransaction().begin();
        try {
            entityManager.merge(user);
        }catch (Exception e){
            System.out.println(e.getMessage());
            entityManager.getTransaction().rollback();
            entityManager.clear();
            return resultUser;
        }
        entityManager.flush();
        resultUser = user;
        entityManager.getTransaction().commit();
        entityManager.clear();
        return resultUser;
    }

    @Override
    public void delete(int id) {
        User user = getById(id);
        if (user != null){
            entityManager.getTransaction().begin();
            user.setStatus((byte) 2);
            try {
                entityManager.merge(user);
            }catch (Exception e){
                System.out.println(e.getMessage());
                entityManager.getTransaction().rollback();
                entityManager.clear();
                return;
            }
            entityManager.getTransaction().commit();
            entityManager.clear();
        }
    }

    @Override
    public User getById(int id) {
        User user = null;
        try {
            user = entityManager.find(User.class,id);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        entityManager.clear();
        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try {
            users = entityManager.createQuery("select u from User as u where u.status != 2").getResultList();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        entityManager.clear();
        return users;
    }
}

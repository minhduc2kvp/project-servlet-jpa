package com.minhvu.fruit.dao.implement;

import com.minhvu.fruit.common.AppConfig;
import com.minhvu.fruit.dao.interfaces.UserDao;
import com.minhvu.fruit.model.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private EntityManager entityManager = AppConfig.ENTITY_MANAGER;

    @Override
    public List<User> getAllUsersIsActive() {
        List<User> users = entityManager.createQuery("select u from User as u where u.status = 0").getResultList();
        entityManager.clear();
        return users;
    }

    @Override
    public void insert(User user) {
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        entityManager.clear();
    }

    @Override
    public void update(User user) {
        entityManager.getTransaction().begin();
        entityManager.merge(user);
        entityManager.getTransaction().commit();
        entityManager.clear();
    }

    @Override
    public void delete(int id) {
        entityManager.getTransaction().begin();
        User user = getById(id);
        user.setStatus((byte) 2);
        entityManager.merge(user);
        entityManager.getTransaction().commit();
        entityManager.clear();
    }

    @Override
    public User getById(int id) {
        User user = entityManager.find(User.class,id);
        entityManager.clear();
        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> users = entityManager.createQuery("select u from User as u where u.status != 2").getResultList();
        entityManager.clear();
        return users;
    }
}

package com.minhvu.fruit.dao.implement;

import com.minhvu.fruit.common.AppConfig;
import com.minhvu.fruit.dao.interfaces.CategoryDao;
import com.minhvu.fruit.model.Category;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    private EntityManager entityManager = AppConfig.ENTITY_MANAGER;

    @Override
    public void insert(Category category) {
        entityManager.getTransaction().begin();
        entityManager.persist(category);
        entityManager.getTransaction().commit();
        entityManager.clear();
    }

    @Override
    public void update(Category category) {
        entityManager.getTransaction().begin();
        entityManager.merge(category);
        entityManager.getTransaction().commit();
        entityManager.clear();
    }

    @Override
    public void delete(int id) {
        entityManager.getTransaction().begin();
        Category category = getById(id);
        category.setDeleted(true);
        entityManager.merge(category);
        entityManager.getTransaction().commit();
        entityManager.clear();
    }

    @Override
    public Category getById(int id) {
        Query query = entityManager.createQuery("select ct from Category as ct where ct.deleted = false and ct.id = :id");
        query.setParameter("id",id);
        Category category = (Category) query.getSingleResult();
        entityManager.clear();
        return category;
    }

    @Override
    public List<Category> getAll() {
        List<Category> categories = entityManager.createQuery("select ct from Category as ct where ct.deleted = false").getResultList();
        entityManager.clear();
        return categories;
    }
}

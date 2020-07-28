package com.minhvu.fruit.dao.implement;

import com.minhvu.fruit.common.AppConfig;
import com.minhvu.fruit.dao.interfaces.CategoryDao;
import com.minhvu.fruit.model.Category;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    private EntityManager entityManager = AppConfig.ENTITY_MANAGER;

    @Override
    public Category insert(Category category) {
        Category resultCategory = null;
        entityManager.getTransaction().begin();
        try{
            entityManager.persist(category);
        }catch (Exception e){
            System.out.println(e.getMessage());
            entityManager.getTransaction().rollback();
            entityManager.clear();
            return resultCategory;
        }
        entityManager.flush();
        resultCategory = category;
        entityManager.getTransaction().commit();
        entityManager.clear();
        return resultCategory;
    }

    @Override
    public Category update(Category category) {
        Category resultCategory = null;
        entityManager.getTransaction().begin();
        try{
            entityManager.merge(category);
        }catch (Exception e){
            System.out.println(e.getMessage());
            entityManager.getTransaction().rollback();
            entityManager.clear();
            return resultCategory;
        }
        entityManager.flush();
        resultCategory = category;
        entityManager.getTransaction().commit();
        entityManager.clear();
        return resultCategory;
    }

    @Override
    public void delete(int id) {
        Category category = getById(id);
        if (category != null){
            entityManager.getTransaction().begin();
            category.setDeleted(true);
            try{
                entityManager.merge(category);
            }catch (Exception e){
                System.out.println(e.getMessage());
                entityManager.getTransaction().rollback();
                entityManager.clear();
                return ;
            }
            entityManager.getTransaction().commit();
            entityManager.clear();
        }
    }

    @Override
    public Category getById(int id) {
        Query query = entityManager.createQuery("select ct from Category as ct where ct.deleted = false and ct.id = :id");
        query.setParameter("id",id);
        Category category = null;
        try{
            category = (Category) query.getSingleResult();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        entityManager.clear();
        return category;
    }

    @Override
    public List<Category> getAll() {
        List<Category> categories = new ArrayList<>();
        try{
            categories = entityManager.createQuery("select ct from Category as ct where ct.deleted = false").getResultList();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        entityManager.clear();
        return categories;
    }
}

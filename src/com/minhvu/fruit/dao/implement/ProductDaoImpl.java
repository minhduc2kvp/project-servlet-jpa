package com.minhvu.fruit.dao.implement;

import com.minhvu.fruit.common.AppConfig;
import com.minhvu.fruit.dao.interfaces.ProductDao;
import com.minhvu.fruit.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    private EntityManager entityManager = AppConfig.ENTITY_MANAGER;

    @Override
    public Product insert(Product product) {
        Product resultProduct = null;
        entityManager.getTransaction().begin();
        try {
            entityManager.persist(product);
        }catch (Exception e){
            System.out.println(e.getMessage());
            entityManager.getTransaction().rollback();
            entityManager.clear();
            return resultProduct;
        }
        entityManager.flush();
        resultProduct = product;
        entityManager.getTransaction().commit();
        entityManager.clear();
        return resultProduct;
    }

    @Override
    public Product update(Product product) {
        Product resultProduct = null;
        entityManager.getTransaction().begin();
        try {
            entityManager.merge(product);
        }catch (Exception e){
            System.out.println(e.getMessage());
            entityManager.getTransaction().rollback();
            entityManager.clear();
            return resultProduct;
        }
        entityManager.flush();
        resultProduct = product;
        entityManager.getTransaction().commit();
        entityManager.clear();
        return resultProduct;
    }

    @Override
    public void delete(int id) {
        Product product = getById(id);
        if (product != null){
            entityManager.getTransaction().begin();
            product.setDeleted(true);
            try {
                entityManager.merge(product);
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
    public Product getById(int id) {
        Product product = null;
        Query query = entityManager.createQuery("select p from Product as p where p.deleted = false and p.id = :id");
        query.setParameter("id",id);
        try {
            product = (Product) query.getSingleResult();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        entityManager.clear();
        return product;
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        try {
            products = entityManager.createQuery("select p from Product as p where p.deleted = false ").getResultList();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        entityManager.clear();
        return products;
    }

    @Override
    public List<Product> getByCategory(int idCategory) throws SQLException {
        List<Product> products = new ArrayList<>();
        try {
            products = entityManager.createQuery("select p from Product as p where p.deleted = false and idCategory = :id_category").setParameter("id_category", idCategory).getResultList();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        entityManager.clear();
        return products;
    }
}

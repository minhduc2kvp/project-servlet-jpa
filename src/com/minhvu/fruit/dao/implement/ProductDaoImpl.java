package com.minhvu.fruit.dao.implement;

import com.minhvu.fruit.common.AppConfig;
import com.minhvu.fruit.dao.interfaces.ProductDao;
import com.minhvu.fruit.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.SQLException;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    private EntityManager entityManager = AppConfig.ENTITY_MANAGER;

    @Override
    public void insert(Product product) {
        entityManager.getTransaction().begin();
        entityManager.persist(product);
        entityManager.getTransaction().commit();
        entityManager.clear();
    }

    @Override
    public void update(Product product) {
        entityManager.getTransaction().begin();
        entityManager.merge(product);
        entityManager.getTransaction().commit();
        entityManager.clear();
    }

    @Override
    public void delete(int id) {
        entityManager.getTransaction().begin();
        Product product = getById(id);
        product.setDeleted(true);
        entityManager.merge(product);
        entityManager.getTransaction().commit();
        entityManager.clear();
    }

    @Override
    public Product getById(int id) {
        Query query = entityManager.createQuery("select p from Product as p where p.deleted = false and p.id = :id");
        query.setParameter("id",id);
        Product product = (Product) query.getSingleResult();
        entityManager.clear();
        return product;
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = entityManager.createQuery("select p from Product as p where p.deleted = false ").getResultList();
        entityManager.clear();
        return products;
    }

    @Override
    public List<Product> getByCategory(int idCategory) throws SQLException {
        List<Product> products = entityManager.createQuery("select p from Product as p where p.deleted = false and idCategory = :id_category").setParameter("id_category", idCategory).getResultList();
        entityManager.clear();
        return products;
    }
}

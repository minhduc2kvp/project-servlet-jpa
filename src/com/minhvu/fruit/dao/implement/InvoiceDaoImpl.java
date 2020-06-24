package com.minhvu.fruit.dao.implement;

import com.minhvu.fruit.common.AppConfig;
import com.minhvu.fruit.dao.interfaces.InvoiceDao;
import com.minhvu.fruit.model.Invoice;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.SQLException;
import java.util.List;

public class InvoiceDaoImpl implements InvoiceDao {
    private EntityManager entityManager = AppConfig.ENTITY_MANAGER;

    @Override
    public List<Invoice> getByUserId(int id_user) throws SQLException {
        Query query = entityManager.createQuery("select iv from Invoice as iv where iv.deleted = false and iv.idUser = :id");
        query.setParameter("id", id_user);
        List<Invoice> invoices = query.getResultList();
        entityManager.clear();
        return invoices;
    }

    @Override
    public void insert(Invoice invoice) throws SQLException {
        entityManager.getTransaction().begin();
        entityManager.persist(invoice);
        entityManager.getTransaction().commit();
        entityManager.clear();
    }

    @Override
    public void update(Invoice invoice) throws SQLException {
        entityManager.getTransaction().begin();
        entityManager.merge(invoice);
        entityManager.getTransaction().commit();
        entityManager.clear();
    }

    @Override
    public void delete(int id) throws SQLException {
        entityManager.getTransaction().begin();
        Invoice invoice = getById(id);
        invoice.setDeleted(true);
        entityManager.merge(invoice);
        entityManager.getTransaction().commit();
        entityManager.clear();
    }

    @Override
    public Invoice getById(int id) throws SQLException {
        Invoice invoice = (Invoice) entityManager.createQuery("select iv from Invoice as iv where iv.deleted = false and iv.id = :id").setParameter("id",id).getSingleResult();
        entityManager.clear();
        return invoice;
    }

    @Override
    public List<Invoice> getAll() throws SQLException {
        List<Invoice> invoices = entityManager.createQuery("select iv from Invoice as iv where iv.deleted = false ").getResultList();
        entityManager.clear();
        return invoices;
    }
}

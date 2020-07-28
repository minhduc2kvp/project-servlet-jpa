package com.minhvu.fruit.dao.implement;

import com.minhvu.fruit.common.AppConfig;
import com.minhvu.fruit.dao.interfaces.InvoiceDao;
import com.minhvu.fruit.model.Invoice;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDaoImpl implements InvoiceDao {
    private EntityManager entityManager = AppConfig.ENTITY_MANAGER;

    @Override
    public List<Invoice> getByUserId(int id_user) throws SQLException {
        List<Invoice> invoices = new ArrayList<>();
        Query query = entityManager.createQuery("select iv from Invoice as iv where iv.deleted = false and iv.idUser = :id");
        query.setParameter("id", id_user);
        try {
            invoices = query.getResultList();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        entityManager.clear();
        return invoices;
    }

    @Override
    public Invoice insert(Invoice invoice) throws SQLException {
        Invoice resultInvoice = null;
        entityManager.getTransaction().begin();
        try {
            entityManager.persist(invoice);
        }catch (Exception e){
            System.out.println(e.getMessage());
            entityManager.getTransaction().rollback();
            entityManager.clear();
            return resultInvoice;
        }
        entityManager.flush();
        resultInvoice = invoice;
        entityManager.getTransaction().commit();
        entityManager.clear();
        return resultInvoice;
    }

    @Override
    public Invoice update(Invoice invoice) throws SQLException {
        Invoice resultInvoice = null;
        entityManager.getTransaction().begin();
        try {
            entityManager.merge(invoice);
        }catch (Exception e){
            System.out.println(e.getMessage());
            entityManager.getTransaction().rollback();
            entityManager.clear();
            return resultInvoice;
        }
        entityManager.flush();
        resultInvoice = invoice;
        entityManager.getTransaction().commit();
        entityManager.clear();
        return resultInvoice;
    }

    @Override
    public void delete(int id) throws SQLException {
        Invoice invoice = getById(id);
        if (invoice != null){
            entityManager.getTransaction().begin();
            invoice.setDeleted(true);
            try {
                entityManager.merge(invoice);
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
    public Invoice getById(int id) throws SQLException {
        Invoice invoice = null;
        try {
            invoice = (Invoice) entityManager.createQuery("select iv from Invoice as iv where iv.deleted = false and iv.id = :id").setParameter("id",id).getSingleResult();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        entityManager.clear();
        return invoice;
    }

    @Override
    public List<Invoice> getAll() throws SQLException {
        List<Invoice> invoices = new ArrayList<>();
        try {
            invoices = entityManager.createQuery("select iv from Invoice as iv where iv.deleted = false ").getResultList();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        entityManager.clear();
        return invoices;
    }
}

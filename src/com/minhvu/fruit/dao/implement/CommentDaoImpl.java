package com.minhvu.fruit.dao.implement;

import com.minhvu.fruit.common.AppConfig;
import com.minhvu.fruit.dao.interfaces.CommentDao;
import com.minhvu.fruit.model.Comment;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.SQLException;
import java.util.List;

public class CommentDaoImpl implements CommentDao {
    private EntityManager entityManager = AppConfig.ENTITY_MANAGER;

    @Override
    public Comment insert(Comment comment) throws SQLException {
        Comment resultComment = null;
        entityManager.getTransaction().begin();
        try{
            entityManager.persist(comment);
        }catch (Exception e){
            System.out.println(e.getMessage());
            entityManager.getTransaction().rollback();
            entityManager.clear();
            return resultComment;
        }
        entityManager.flush();
        resultComment = comment;
        entityManager.getTransaction().commit();
        entityManager.clear();
        return resultComment;
    }

    @Override
    public Comment update(Comment comment) throws SQLException {
        Comment resultComment = null;
        entityManager.getTransaction().begin();
        try{
            entityManager.merge(comment);
        }catch (Exception e){
            System.out.println(e.getMessage());
            entityManager.getTransaction().rollback();
            entityManager.clear();
            return resultComment;
        }
        entityManager.flush();
        resultComment = comment;
        entityManager.getTransaction().commit();
        entityManager.clear();
        return resultComment;
    }

    @Override
    public void delete(int id) throws SQLException {
        Comment comment = getById(id);
        if (comment != null){
            entityManager.getTransaction().begin();
            comment.setDeleted(true);
            try {
                entityManager.merge(comment);
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
    public Comment getById(int id) throws SQLException {
        Comment comment = null;
        Query query = entityManager.createQuery("select cmt from Comment as cmt where cmt.deleted = false and cmt.id = :id");
        query.setParameter("id",id);
        try {
            comment = (Comment) query.getSingleResult();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        entityManager.clear();
        return comment;
    }

    @Override
    public List<Comment> getAll() throws SQLException {
        List<Comment> comments = null;
        try {
            comments = entityManager.createQuery("select cmt from Comment as cmt where deleted = false").getResultList();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        entityManager.clear();
        return comments;
    }

    @Override
    public List<Comment> getByProductId(int id_product) throws SQLException{
        List<Comment> comments = null;
        Query query = entityManager.createQuery("select cmt from Comment as cmt where cmt.deleted = false and cmt.idProduct = :id_product");
        query.setParameter("id_product",id_product);
        try{
            comments = query.getResultList();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        entityManager.clear();
        return comments;
    }
}

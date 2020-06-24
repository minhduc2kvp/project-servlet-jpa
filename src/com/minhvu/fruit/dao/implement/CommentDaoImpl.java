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
    public void insert(Comment comment) throws SQLException {
        entityManager.getTransaction().begin();
        entityManager.persist(comment);
        entityManager.getTransaction().commit();
        entityManager.clear();
    }

    @Override
    public void update(Comment comment) throws SQLException {
        entityManager.getTransaction().begin();
        entityManager.merge(comment);
        entityManager.getTransaction().commit();
        entityManager.clear();
    }

    @Override
    public void delete(int id) throws SQLException {
        entityManager.getTransaction().begin();
        Comment comment = getById(id);
        comment.setDeleted(true);
        entityManager.merge(comment);
        entityManager.getTransaction().commit();
        entityManager.clear();
    }

    @Override
    public Comment getById(int id) throws SQLException {
        Comment comment = null;
        Query query = entityManager.createQuery("select cmt from Comment as cmt where cmt.deleted = false and cmt.id = :id");
        query.setParameter("id",id);
        comment = (Comment) query.getSingleResult();
        entityManager.clear();
        return comment;
    }

    @Override
    public List<Comment> getAll() throws SQLException {
        List<Comment> comments = null;
        comments = entityManager.createQuery("select cmt from Comment as cmt where deleted = false").getResultList();
        entityManager.clear();
        return comments;
    }

    @Override
    public List<Comment> getByProductId(int id_product) throws SQLException{
        List<Comment> comments = null;
        Query query = entityManager.createQuery("select cmt from Comment as cmt where cmt.deleted = false and cmt.idProduct = :id_product");
        query.setParameter("id_product",id_product);
        comments = query.getResultList();
        entityManager.clear();
        return comments;
    }
}

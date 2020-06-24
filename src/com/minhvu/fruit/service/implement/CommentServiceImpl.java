package com.minhvu.fruit.service.implement;

import com.minhvu.fruit.dao.implement.CommentDaoImpl;
import com.minhvu.fruit.dao.implement.ProductDaoImpl;
import com.minhvu.fruit.dao.implement.UserDaoImpl;
import com.minhvu.fruit.dao.interfaces.CommentDao;
import com.minhvu.fruit.dao.interfaces.ProductDao;
import com.minhvu.fruit.dao.interfaces.UserDao;
import com.minhvu.fruit.model.Comment;
import com.minhvu.fruit.model.Product;
import com.minhvu.fruit.model.User;
import com.minhvu.fruit.service.interfaces.CommentService;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class CommentServiceImpl implements CommentService {
    private CommentDao commentDao = new CommentDaoImpl();
    private UserDao userDao = new UserDaoImpl();
    private ProductDao productDao = new ProductDaoImpl();

    @Override
    public boolean insert(int id_product, int id_user, String comment) {
        boolean check = false;
        try {
            Product product = productDao.getById(id_product);
            User user = userDao.getById(id_user);
            Comment cmt = new Comment(comment,user,product);
            cmt.setCreateTime(new Timestamp(System.currentTimeMillis()));
            commentDao.insert(cmt);
            check = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return check;
    }

    @Override
    public List<Comment> getByProductId(int id_product) {
        List<Comment> comments = null;
        try {
            comments = commentDao.getByProductId(id_product);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return comments;
    }

    @Override
    public boolean update(Comment comment) {
        boolean check = false;
        try {
            commentDao.update(comment);
            check = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return check;
    }

    @Override
    public boolean delete(int id) {
        boolean check = false;
        try {
            commentDao.delete(id);
            check = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return check;
    }

    @Override
    public Comment getById(int id) {
        Comment comment = null;
        try {
            comment = commentDao.getById(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return comment;
    }

    @Override
    public List<Comment> getAll() {
        List<Comment> comments = null;
        try {
            comments = commentDao.getAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return comments;
    }
}

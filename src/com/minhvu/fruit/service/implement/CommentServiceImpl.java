package com.minhvu.fruit.service.implement;

import com.minhvu.fruit.common.converter.CommentConverter;
import com.minhvu.fruit.dao.implement.CommentDaoImpl;
import com.minhvu.fruit.dao.implement.ProductDaoImpl;
import com.minhvu.fruit.dao.implement.UserDaoImpl;
import com.minhvu.fruit.dao.interfaces.CommentDao;
import com.minhvu.fruit.dao.interfaces.ProductDao;
import com.minhvu.fruit.dao.interfaces.UserDao;
import com.minhvu.fruit.dto.CommentDTO;
import com.minhvu.fruit.model.Comment;
import com.minhvu.fruit.service.interfaces.CommentService;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CommentServiceImpl implements CommentService {
    private CommentDao commentDao = new CommentDaoImpl();
    private UserDao userDao = new UserDaoImpl();
    private ProductDao productDao = new ProductDaoImpl();
    private CommentConverter commentConverter = new CommentConverter();

    @Override
    public CommentDTO insert(CommentDTO commentDTO) {
        CommentDTO result = null;
        try {
            Comment comment = commentConverter.toEntity(commentDTO);
            comment.setUserByIdUser(userDao.getById(commentDTO.getIdUser()));
            comment.setProductByIdProduct(productDao.getById(comment.getIdProduct()));
            comment.setCreateTime(new Timestamp(System.currentTimeMillis()));
            result = commentConverter.toDTO(commentDao.insert(comment));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public List<CommentDTO> getByProductId(int id_product) {
        List<CommentDTO> comments = new ArrayList<>();
        try {
            List<Comment> commentList = commentDao.getByProductId(id_product);
            for (Comment comment : commentList){
                comments.add(commentConverter.toDTO(comment));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return comments;
    }

    @Override
    public CommentDTO update(CommentDTO commentDTO) {
        CommentDTO result = null;
        try {
            Comment comment = commentDao.getById(commentDTO.getId());
            comment.setComment(commentDTO.getComment());
            result = commentConverter.toDTO(commentDao.update(comment));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
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
    public CommentDTO getById(int id) {
        CommentDTO result = null;
        try {
            result = commentConverter.toDTO(commentDao.getById(id));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public List<CommentDTO> getAll() {
        List<CommentDTO> comments = new ArrayList<>();
        try {
            List<Comment> commentList = commentDao.getAll();
            for (Comment comment : commentList){
                comments.add(commentConverter.toDTO(comment));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return comments;
    }
}

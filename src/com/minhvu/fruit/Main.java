package com.minhvu.fruit;

import com.minhvu.fruit.common.AppConfig;
import com.minhvu.fruit.dao.implement.CommentDaoImpl;
import com.minhvu.fruit.dao.interfaces.CommentDao;
import com.minhvu.fruit.dto.CommentDTO;
import com.minhvu.fruit.model.*;
import com.minhvu.fruit.service.implement.*;
import com.minhvu.fruit.service.interfaces.*;
import com.sun.xml.bind.v2.runtime.output.Encoded;

import javax.persistence.EntityManager;
import java.beans.Encoder;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args) throws UnsupportedEncodingException, SQLException {
        CommentService commentService = new CommentServiceImpl();
//        CommentDao commentDao = new CommentDaoImpl();
//        List<Comment> comments = commentDao.getByProductId(17);
//        for (Comment comment : comments){
//            System.out.println(comment.getId() + " - " + comment.getUserByIdUser().getEmail() + " - " + comment.getProductByIdProduct().getName());
//        }
        List<CommentDTO> commentDTOS = commentService.getByProductId(17);
        for (CommentDTO commentDTO : commentDTOS){
            System.out.println(commentDTO.getComment() + " - " + commentDTO.getId());
        }
    }
}

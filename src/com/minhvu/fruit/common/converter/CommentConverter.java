package com.minhvu.fruit.common.converter;

import com.minhvu.fruit.dto.CommentDTO;
import com.minhvu.fruit.model.Comment;
import com.minhvu.fruit.model.User;

import java.sql.Timestamp;

public class CommentConverter implements Converter<CommentDTO, Comment> {

    private UserConverter userConverter = new UserConverter();

    @Override
    public CommentDTO toDTO(Comment comment) {
        User user = comment.getUserByIdUser();
        CommentDTO commentDTO = new CommentDTO(comment.getId(), comment.getComment(), comment.getCreateTime(), user.getId(), comment.getIdProduct(), user.getFirstname() + " " + user.getLastname(), user.getAvatar());
        return commentDTO;
    }

    @Override
    public Comment toEntity(CommentDTO commentDTO) {
//        Comment comment = new Comment();
//        comment.setComment(commentDTO.getComment());
//        comment.setCreateTime(new Timestamp(comment.getCreateTime().getTime()));
//        comment.setIdProduct(commentDTO.getIdProduct());
        return null;
    }
}

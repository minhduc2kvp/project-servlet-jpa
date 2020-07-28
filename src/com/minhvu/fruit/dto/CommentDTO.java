package com.minhvu.fruit.dto;

import java.util.Date;

public class CommentDTO {
    private Integer id;
    private String comment;
    private Date createTime;
    private int idUser;
    private int idProduct;
    private String nameUser;
    private String avatar;

    public CommentDTO() {
    }

    public CommentDTO(Integer id, String comment, Date createTime, int idUser, int idProduct, String nameUser, String avatar) {
        this.id = id;
        this.comment = comment;
        this.createTime = createTime;
        this.idUser = idUser;
        this.idProduct = idProduct;
        this.nameUser = nameUser;
        this.avatar = avatar;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }
}

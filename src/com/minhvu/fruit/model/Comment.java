package com.minhvu.fruit.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Comment {
    private int id;
    private int idUser;
    private int idProduct;
    private String comment;
    private Timestamp createTime;
    private boolean deleted;
    private User userByIdUser;
    private Product productByIdProduct;


    public Comment() {
    }

    public Comment(String comment, User userByIdUser, Product productByIdProduct) {
        this.comment = comment;
        this.userByIdUser = userByIdUser;
        this.productByIdProduct = productByIdProduct;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "id_user",insertable = false,updatable = false)
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Basic
    @Column(name = "id_product",insertable = false,updatable = false)
    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    @Basic
    @Column(name = "comment")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "deleted")
    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment1 = (Comment) o;
        return id == comment1.id &&
                idUser == comment1.idUser &&
                idProduct == comment1.idProduct &&
                deleted == comment1.deleted &&
                Objects.equals(comment, comment1.comment) &&
                Objects.equals(createTime, comment1.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idUser, idProduct, comment, createTime, deleted);
    }

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id", nullable = false)
    public User getUserByIdUser() {
        return userByIdUser;
    }

    public void setUserByIdUser(User userByIdUser) {
        this.userByIdUser = userByIdUser;
    }

    @ManyToOne
    @JoinColumn(name = "id_product", referencedColumnName = "id", nullable = false)
    public Product getProductByIdProduct() {
        return productByIdProduct;
    }

    public void setProductByIdProduct(Product productByIdProduct) {
        this.productByIdProduct = productByIdProduct;
    }
}

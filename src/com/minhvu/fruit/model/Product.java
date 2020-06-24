package com.minhvu.fruit.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Product {
    private int id;
    private String name;
    private String description;
    private BigDecimal price;
    private String origin;
    private double amount;
    private int sale;
    private boolean deleted;
    private Timestamp createDate;
    private double bought;
    private int idCategory;
    private String image1;
    private String image2;
    private String image3;
    private Collection<Comment> commentsById;
//    private Collection<Item> itemsById;
    private Category categoryByIdCategory;

    public Product() {
    }

    public Product(String name, String description, BigDecimal price, String origin, double amount, String image1, String image2, String image3, Category categoryByIdCategory) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.origin = origin;
        this.amount = amount;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.categoryByIdCategory = categoryByIdCategory;
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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Basic
    @Column(name = "origin")
    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @Basic
    @Column(name = "amount")
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "sale")
    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

    @Basic
    @Column(name = "deleted")
    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }


    @Basic
    @Column(name = "create_date")
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "bought")
    public double getBought() {
        return bought;
    }

    public void setBought(double bought) {
        this.bought = bought;
    }

    @Basic
    @Column(name = "id_category",insertable = false,updatable = false)
    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    @Basic
    @Column(name = "image1")
    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    @Basic
    @Column(name = "image2")
    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    @Basic
    @Column(name = "image3")
    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id &&
                Double.compare(product.amount, amount) == 0 &&
                sale == product.sale &&
                deleted == product.deleted &&
                Double.compare(product.bought, bought) == 0 &&
                Objects.equals(name, product.name) &&
                Objects.equals(description, product.description) &&
                Objects.equals(price, product.price) &&
                Objects.equals(origin, product.origin) &&
                Objects.equals(createDate, product.createDate) &&
                Objects.equals(image1, product.image1) &&
                Objects.equals(image2, product.image2) &&
                Objects.equals(image3, product.image3);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price, origin, amount, sale, deleted, createDate, bought, image1, image2, image3);
    }

    @OneToMany(mappedBy = "productByIdProduct", fetch = FetchType.EAGER)
    public Collection<Comment> getCommentsById() {
        return commentsById;
    }

    public void setCommentsById(Collection<Comment> commentsById) {
        this.commentsById = commentsById;
    }

//    @OneToMany(mappedBy = "product")
//    public Collection<Item> getItemsById() {
//        return itemsById;
//    }
//
//    public void setItemsById(Collection<Item> itemsById) {
//        this.itemsById = itemsById;
//    }

    @ManyToOne
    @JoinColumn(name = "id_category", referencedColumnName = "id", nullable = false)
    public Category getCategoryByIdCategory() {
        return categoryByIdCategory;
    }

    public void setCategoryByIdCategory(Category categoryByIdCategory) {
        this.categoryByIdCategory = categoryByIdCategory;
    }
}

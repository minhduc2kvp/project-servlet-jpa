package com.minhvu.fruit.dto;

import java.util.Date;

public class ProductDTO {
    private Integer id;
    private String name;
    private String description;
    private double price;
    private String origin;
    private double amount;
    private int sale;
    private Date createDate;
    private double bought;
    private String image1;
    private String image2;
    private String image3;
    private int idCategory;

    public ProductDTO() {
    }

    public ProductDTO(Integer id, String name, String description, double price, String origin, double amount, int sale, Date createDate, double bought, String image1, String image2, String image3, int idCategory) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.origin = origin;
        this.amount = amount;
        this.sale = sale;
        this.createDate = createDate;
        this.bought = bought;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.idCategory = idCategory;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public double getBought() {
        return bought;
    }

    public void setBought(double bought) {
        this.bought = bought;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }
}

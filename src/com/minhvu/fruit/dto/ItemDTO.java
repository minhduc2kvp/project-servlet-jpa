package com.minhvu.fruit.dto;

public class ItemDTO {
    private Integer id;
    private ProductDTO product;
    private double quantity;
    private double productPrice;

    public ItemDTO() {
    }

    public ItemDTO(Integer id, ProductDTO product, double quantity, double productPrice) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.productPrice = productPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
}

package com.minhvu.fruit.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Item {
    private int idInvoice;
    private int idProduct;
    private double quantity;
    private BigDecimal productPrice;
    private int id;
    private Product product;

    public Item() {
    }

    public Item(double quantity, BigDecimal productPrice, Product product) {
        this.quantity = quantity;
        this.productPrice = productPrice;
        this.product = product;
    }

    @ManyToOne
    @JoinColumn(name = "id_product", nullable = false)
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Basic
    @Column(name = "id_invoice",insertable = false,updatable = false)
    public int getIdInvoice() {
        return idInvoice;
    }

    public void setIdInvoice(int idInvoice) {
        this.idInvoice = idInvoice;
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
    @Column(name = "quantity")
    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    @Basic
    @Column(name = "product_price")
    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return idInvoice == item.idInvoice &&
                idProduct == item.idProduct &&
                Double.compare(item.quantity, quantity) == 0 &&
                id == item.id &&
                Objects.equals(productPrice, item.productPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idInvoice, idProduct, quantity, productPrice, id);
    }
}

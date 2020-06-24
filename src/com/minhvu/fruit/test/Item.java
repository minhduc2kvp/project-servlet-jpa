//package com.minhvu.fruit.test;
//
//import com.minhvu.fruit.model.Product;
//
//import javax.persistence.*;
//import java.math.BigDecimal;
//import java.util.Objects;
//
//@Entity
//@Table(name = "item")
//public class Item {
//    @Id
//    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
////    private int idInvoice;
////    private int idProduct;
//    private double quantity;
//    private BigDecimal productPrice;
////    private Invoice invoiceByIdInvoice;
//    private Product product;
//
//    public Item() {
//    }
//
//    public Item(double quantity, BigDecimal productPrice, Product product) {
//        this.quantity = quantity;
//        this.productPrice = productPrice;
//        this.product = product;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
////    @Basic
////    @Column(name = "id_invoice",insertable = false,updatable = false)
////    public int getIdInvoice() {
////        return idInvoice;
////    }
////
////    public void setIdInvoice(int idInvoice) {
////        this.idInvoice = idInvoice;
////    }
////
////    @Basic
////    @Column(name = "id_product",insertable = false,updatable = false)
////    public int getIdProduct() {
////        return idProduct;
////    }
////
////    public void setIdProduct(int idProduct) {
////        this.idProduct = idProduct;
////    }
//
//    @Basic
//    @Column(name = "quantity")
//    public double getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(double quantity) {
//        this.quantity = quantity;
//    }
//
//    @Basic
//    @Column(name = "product_price")
//    public BigDecimal getProductPrice() {
//        return productPrice;
//    }
//
//    public void setProductPrice(BigDecimal productPrice) {
//        this.productPrice = productPrice;
//    }
//
////    @Override
////    public boolean equals(Object o) {
////        if (this == o) return true;
////        if (o == null || getClass() != o.getClass()) return false;
////        Item item = (Item) o;
////        return idInvoice == item.idInvoice &&
////                idProduct == item.idProduct &&
////                Double.compare(item.quantity, quantity) == 0 &&
////                Objects.equals(productPrice, item.productPrice);
////    }
////
////    @Override
////    public int hashCode() {
////        return Objects.hash(idInvoice, idProduct, quantity, productPrice);
////    }
//
////    @ManyToOne
////    @JoinColumn(name = "id_invoice", referencedColumnName = "id", nullable = false)
////    public Invoice getInvoiceByIdInvoice() {
////        return invoiceByIdInvoice;
////    }
////
////    public void setInvoiceByIdInvoice(Invoice invoiceByIdInvoice) {
////        this.invoiceByIdInvoice = invoiceByIdInvoice;
////    }
//
//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "id_product", referencedColumnName = "id",nullable = false)
//    public Product getProduct() {
//        return product;
//    }
//
//    public void setProduct(Product product) {
//        this.product = product;
//    }
//}

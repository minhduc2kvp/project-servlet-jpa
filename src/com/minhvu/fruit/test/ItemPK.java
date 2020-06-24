//package com.minhvu.fruit.model;
//
//import javax.persistence.Column;
//import javax.persistence.Id;
//import java.io.Serializable;
//import java.util.Objects;
//
//public class ItemPK implements Serializable {
//    private int idInvoice;
//    private int idProduct;
//
//    @Id
//    @Column(name = "id_invoice",insertable = false,updatable = false)
//    public int getIdInvoice() {
//        return idInvoice;
//    }
//
//    public void setIdInvoice(int idInvoice) {
//        this.idInvoice = idInvoice;
//    }
//
//    @Id
//    @Column(name = "id_product",insertable = false,updatable = false)
//    public int getIdProduct() {
//        return idProduct;
//    }
//
//    public void setIdProduct(int idProduct) {
//        this.idProduct = idProduct;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        ItemPK itemPK = (ItemPK) o;
//        return idInvoice == itemPK.idInvoice &&
//                idProduct == itemPK.idProduct;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(idInvoice, idProduct);
//    }
//}

package com.minhvu.fruit.dto;

import java.util.Date;
import java.util.List;

public class InvoiceDTO {
    private Integer id;
    private int idUser;
    private Date date;
    private String address;
    private String email;
    private String numberphone;
    private String nameUser;
    private List<ItemDTO> items;
    private Integer idWard;
    private String addressDetail;

    public InvoiceDTO() {
    }

    public InvoiceDTO(Integer id, int idUser, Date date, String address, String email, String numberphone, String nameUser, List<ItemDTO> items, Integer idWard, String addressDetail) {
        this.id = id;
        this.idUser = idUser;
        this.date = date;
        this.address = address;
        this.email = email;
        this.numberphone = numberphone;
        this.nameUser = nameUser;
        this.items = items;
        this.idWard = idWard;
        this.addressDetail = addressDetail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumberphone() {
        return numberphone;
    }

    public void setNumberphone(String numberphone) {
        this.numberphone = numberphone;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public List<ItemDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemDTO> items) {
        this.items = items;
    }

    public Integer getIdWard() {
        return idWard;
    }

    public void setIdWard(Integer idWard) {
        this.idWard = idWard;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }
}

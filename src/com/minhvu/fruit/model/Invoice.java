package com.minhvu.fruit.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Invoice {
    private int id;
    private int idUser;
    private Timestamp date;
    private int idAddress;
    private byte status;
    private String email;
    private String numberphone;
    private String nameUser;
    private boolean deleted;
    private User userByIdUser;
    private Address addressByIdAddress;
    private Collection<Item> itemsById;

    public Invoice() {
    }

    public Invoice(String email, String numberphone, String nameUser, User userByIdUser, Address addressByIdAddress, Collection<Item> itemsById) {
        this.email = email;
        this.numberphone = numberphone;
        this.nameUser = nameUser;
        this.userByIdUser = userByIdUser;
        this.addressByIdAddress = addressByIdAddress;
        this.itemsById = itemsById;
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
    @Column(name = "date")
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Basic
    @Column(name = "id_address",insertable = false,updatable = false)
    public int getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(int idAddress) {
        this.idAddress = idAddress;
    }

    @Basic
    @Column(name = "status")
    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "numberphone")
    public String getNumberphone() {
        return numberphone;
    }

    public void setNumberphone(String numberphone) {
        this.numberphone = numberphone;
    }

    @Basic
    @Column(name = "name_user")
    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
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
        Invoice invoice = (Invoice) o;
        return id == invoice.id &&
                idUser == invoice.idUser &&
                idAddress == invoice.idAddress &&
                status == invoice.status &&
                Objects.equals(date, invoice.date) &&
                Objects.equals(email, invoice.email) &&
                Objects.equals(numberphone, invoice.numberphone) &&
                Objects.equals(nameUser, invoice.nameUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idUser, date, idAddress, status, email, numberphone, nameUser);
    }

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id", nullable = false)
    public User getUserByIdUser() {
        return userByIdUser;
    }

    public void setUserByIdUser(User userByIdUser) {
        this.userByIdUser = userByIdUser;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_address", referencedColumnName = "id", nullable = false)
    public Address getAddressByIdAddress() {
        return addressByIdAddress;
    }

    public void setAddressByIdAddress(Address addressByIdAddress) {
        this.addressByIdAddress = addressByIdAddress;
    }

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_invoice", referencedColumnName = "id", nullable = false)
    public Collection<Item> getItemsById() {
        return itemsById;
    }

    public void setItemsById(Collection<Item> itemsById) {
        this.itemsById = itemsById;
    }
}

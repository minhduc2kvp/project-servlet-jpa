package com.minhvu.fruit.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
public class User {
    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Date birthday;
    private String avatar;
    private byte status;
    private String role;
    private int idAddress;
//    private Collection<Comment> commentsById;
    private Collection<Invoice> invoicesById;
    private Address addressByIdAddress;

    public User() {
    }

    public User(String firstname, String lastname, String email, String password, Date birthday, String avatar, Address addressByIdAddress) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.avatar = avatar;
        this.addressByIdAddress = addressByIdAddress;
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "firstname")
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Basic
    @Column(name = "lastname")
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "birthday")
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "avatar")
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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
    @Column(name = "role")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Basic
    @Column(name = "id_address",insertable = false,updatable = false)
    public int getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(int idAddress) {
        this.idAddress = idAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                status == user.status &&
                idAddress == user.idAddress &&
                Objects.equals(firstname, user.firstname) &&
                Objects.equals(lastname, user.lastname) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(birthday, user.birthday) &&
                Objects.equals(avatar, user.avatar) &&
                Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, email, password, birthday, avatar, status, role, idAddress);
    }

//    @OneToMany(mappedBy = "userByIdUser")
//    public Collection<Comment> getCommentsById() {
//        return commentsById;
//    }
//
//    public void setCommentsById(Collection<Comment> commentsById) {
//        this.commentsById = commentsById;
//    }

    @OneToMany(mappedBy = "userByIdUser")
    public Collection<Invoice> getInvoicesById() {
        return invoicesById;
    }

    public void setInvoicesById(Collection<Invoice> invoicesById) {
        this.invoicesById = invoicesById;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_address", referencedColumnName = "id", nullable = false)
    public Address getAddressByIdAddress() {
        return addressByIdAddress;
    }

    public void setAddressByIdAddress(Address addressByIdAddress) {
        this.addressByIdAddress = addressByIdAddress;
    }
}

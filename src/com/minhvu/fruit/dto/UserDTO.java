package com.minhvu.fruit.dto;

import java.sql.Date;

public class UserDTO {
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Date birthday;
    private String avatar;
    private String address;
    private Integer idWard;
    private String addressDetail;

    public UserDTO() {
    }

    public UserDTO(Integer id, String firstname, String lastname, String email, String password, Date birthday, String avatar, String address, Integer idWard, String addressDetail) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.avatar = avatar;
        this.address = address;
        this.idWard = idWard;
        this.addressDetail = addressDetail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

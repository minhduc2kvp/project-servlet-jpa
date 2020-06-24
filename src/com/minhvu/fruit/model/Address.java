package com.minhvu.fruit.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "address")
public class Address {
    private int id;
    private int idWard;
    private String detailAddress;
    private Ward wardByIdWard;
//    private Collection<Invoice> invoicesById;
//    private Collection<User> usersById;


    public Address(String detailAddress, Ward wardByIdWard) {
        this.detailAddress = detailAddress;
        this.wardByIdWard = wardByIdWard;
    }

    public Address() {
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
    @Column(name = "id_ward",insertable = false,updatable = false)
    public int getIdWard() {
        return idWard;
    }

    public void setIdWard(int idWard) {
        this.idWard = idWard;
    }

    @Basic
    @Column(name = "detail_address")
    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return id == address.id &&
                idWard == address.idWard &&
                Objects.equals(detailAddress, address.detailAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idWard, detailAddress);
    }

    @ManyToOne
    @JoinColumn(name = "id_ward", referencedColumnName = "id", nullable = false)
    public Ward getWardByIdWard() {
        return wardByIdWard;
    }

    public void setWardByIdWard(Ward wardByIdWard) {
        this.wardByIdWard = wardByIdWard;
    }

//    @OneToMany(mappedBy = "addressByIdAddress")
//    public Collection<Invoice> getInvoicesById() {
//        return invoicesById;
//    }
//
//    public void setInvoicesById(Collection<Invoice> invoicesById) {
//        this.invoicesById = invoicesById;
//    }

//    @OneToMany(mappedBy = "addressByIdAddress")
//    public Collection<User> getUsersById() {
//        return usersById;
//    }
//
//    public void setUsersById(Collection<User> usersById) {
//        this.usersById = usersById;
//    }
}

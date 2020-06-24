package com.minhvu.fruit.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Ward {
    private int id;
    private int idDistrict;
    private String name;
    private Collection<Address> addressesById;
    private District districtByIdDistrict;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "id_district",insertable = false,updatable = false)
    public int getIdDistrict() {
        return idDistrict;
    }

    public void setIdDistrict(int idDistrict) {
        this.idDistrict = idDistrict;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ward ward = (Ward) o;
        return id == ward.id &&
                idDistrict == ward.idDistrict &&
                Objects.equals(name, ward.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idDistrict, name);
    }

    @OneToMany(mappedBy = "wardByIdWard")
    public Collection<Address> getAddressesById() {
        return addressesById;
    }

    public void setAddressesById(Collection<Address> addressesById) {
        this.addressesById = addressesById;
    }

    @ManyToOne
    @JoinColumn(name = "id_district", referencedColumnName = "id", nullable = false)
    public District getDistrictByIdDistrict() {
        return districtByIdDistrict;
    }

    public void setDistrictByIdDistrict(District districtByIdDistrict) {
        this.districtByIdDistrict = districtByIdDistrict;
    }
}

package com.minhvu.fruit.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class District {
    private int id;
    private int idCity;
    private String name;
    private City cityByIdCity;
    private Collection<Ward> wardsById;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "id_city",insertable = false,updatable = false)
    public int getIdCity() {
        return idCity;
    }

    public void setIdCity(int idCity) {
        this.idCity = idCity;
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
        District district = (District) o;
        return id == district.id &&
                idCity == district.idCity &&
                Objects.equals(name, district.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idCity, name);
    }

    @ManyToOne
    @JoinColumn(name = "id_city", referencedColumnName = "id", nullable = false)
    public City getCityByIdCity() {
        return cityByIdCity;
    }

    public void setCityByIdCity(City cityByIdCity) {
        this.cityByIdCity = cityByIdCity;
    }

    @OneToMany(mappedBy = "districtByIdDistrict")
    public Collection<Ward> getWardsById() {
        return wardsById;
    }

    public void setWardsById(Collection<Ward> wardsById) {
        this.wardsById = wardsById;
    }
}

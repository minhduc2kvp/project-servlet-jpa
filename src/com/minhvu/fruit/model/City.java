package com.minhvu.fruit.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class City {
    private int id;
    private String name;
    private Collection<District> districtsById;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        City city = (City) o;
        return id == city.id &&
                Objects.equals(name, city.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @OneToMany(mappedBy = "cityByIdCity")
    public Collection<District> getDistrictsById() {
        return districtsById;
    }

    public void setDistrictsById(Collection<District> districtsById) {
        this.districtsById = districtsById;
    }
}

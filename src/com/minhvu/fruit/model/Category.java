package com.minhvu.fruit.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Category {
    private int id;
    private String name;
    private boolean deleted;
    private Collection<Product> productsById;

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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        Category category = (Category) o;
        return id == category.id &&
                deleted == category.deleted &&
                Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, deleted);
    }

    @OneToMany(mappedBy = "categoryByIdCategory")
    public Collection<Product> getProductsById() {
        return productsById;
    }

    public void setProductsById(Collection<Product> productsById) {
        this.productsById = productsById;
    }
}

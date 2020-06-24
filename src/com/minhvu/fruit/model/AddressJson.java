package com.minhvu.fruit.model;

public class AddressJson {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AddressJson(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public AddressJson() {
    }
}

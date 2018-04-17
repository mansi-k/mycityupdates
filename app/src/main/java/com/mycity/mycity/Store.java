package com.mycity.mycity;

public class Store {

    private String category;
    private String city;
    private String name;
    private String owner;

    public Store() {

    }

    public Store( String category, String city, String name, String owner ) {
        this.category = category;
        this.city = city;
        this.name = name;
        this.owner = owner;
    }

    public String getCategory() {
        return category;
    }

    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

}
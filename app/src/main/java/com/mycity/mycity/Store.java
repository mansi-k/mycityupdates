package com.mycity.mycity;

public class Store {

    private String category;
    private String city;
    private String name;

    public Store() {

    }

    public Store( String category, String city, String name ) {
        this.category = category;
        this.city = city;
        this.name = name;
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

}
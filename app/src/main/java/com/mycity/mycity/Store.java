package com.mycity.mycity;

import com.google.firebase.database.ServerValue;

import java.io.Serializable;

public class Store implements Serializable{

    public String category, city, name, uqn;

    public Store() {

    }

    public Store(String cat, String city, String name, String uqn) {
        this.category = cat;
        this.city = city;
        this.name = name;
        this.uqn = uqn;
    }

    public String getCat() {
        return category;
    }

    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }

    public String getUqn() {
        return uqn;
    }

}

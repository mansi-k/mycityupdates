package com.mycity.mycity;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.ServerValue;

import java.io.Serializable;
import java.util.HashMap;

public class Post {
    public Object createdat;
    public String title;
    public String desc;
    public String city;
    public String category;
    public String store;

    public Post() {

    }

    public Post(String title, String desc, Object createdat, String city, String category, String store) {
        this.title = title;
        this.desc = desc;
        this.city = city;
        this.category = category;
        this.store = store;
        if(createdat==null)
            this.createdat = ServerValue.TIMESTAMP;
        else
            this.createdat = createdat;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public Object getCreatedat() {
        return createdat;
    }

    public String getCity() {
        return city;
    }

    public String getCategory() {
        return category;
    }

    public String getStore() {
        return store;
    }

}

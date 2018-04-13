package com.mycity.mycity;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.ServerValue;

import java.io.Serializable;
import java.util.HashMap;

public class Post implements Serializable {
    public Object createdat;
    public String title;
    public String desc;

    public Post() {

    }

    public Post(String ptitle, String pdesc) {
        title = ptitle;
        desc = pdesc;
        createdat = ServerValue.TIMESTAMP;
    }

}

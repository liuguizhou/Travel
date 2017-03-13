package com.travel.liuyun.bean;

import java.io.Serializable;

/**
 * Created by liuguizhou on 2016/10/27.
 */

public class Banner implements Serializable {

    private int id;
    private String name;
    private String imgUrl;
    private String description;


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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

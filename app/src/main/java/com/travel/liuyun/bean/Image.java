package com.travel.liuyun.bean;

import java.io.Serializable;

/**
 * Created by joe on 16-7-12.
 */
public class Image implements Serializable {
    private String url;
    private String thumUrl;

    public Image() {
    }

    public Image(String url, String thumUrl) {
        this.url = url;
        this.thumUrl = thumUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumUrl() {
        return thumUrl;
    }

    public void setThumUrl(String thumUrl) {
        this.thumUrl = thumUrl;
    }
}

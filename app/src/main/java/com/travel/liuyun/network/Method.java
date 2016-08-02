package com.travel.liuyun.network;


import com.travel.liuyun.Constants;

/**
 * Created by joe on 16-7-22.
 */
public enum Method {
    modifyUserInfo(Constants.MODIFY_USER_INFO)
    ;

    private String url;

    Method(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}

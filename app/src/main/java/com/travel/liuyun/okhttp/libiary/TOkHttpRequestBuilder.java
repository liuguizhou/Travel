package com.travel.liuyun.okhttp.libiary;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by lpc on 2016/11/4.
 */

public abstract class TOkHttpRequestBuilder<T extends TOkHttpRequestBuilder> {

    protected String url ;
    protected Object tag ;
    protected int id ;
    protected Map<String,String> headers ;
    protected Map<String,String> param ;
    /**
     * 私有参数
     */
    protected Map<String,Object> pri_args;
    /**
     * 公有参数
     */
    protected Map<String,Object> pub_args;


    public T id(int id) {
        this.id = id ;
        return (T)this ;
    }
    public T url(String url) {
        this.url = url ;
        return (T)this;
    }
    public T tag(Object tag) {
        this.tag = tag;
        return (T)this;
    }


    public T headers(Map<String,String> headers) {
        this.headers = headers ;
        return (T)this ;
    }
    public T addHeader(String key, String val) {
        if(this.headers == null) {
            this.headers = new LinkedHashMap();
        }

        this.headers.put(key, val);
        return (T)this;
    }

    public T pri_args(Map<String,Object> pri_args) {
        this.pri_args = pri_args ;
        return (T)this ;
    }

    public T pub_args(Map<String,Object> pub_args) {
        this.pub_args = pub_args ;
        return (T)this ;
    }

}

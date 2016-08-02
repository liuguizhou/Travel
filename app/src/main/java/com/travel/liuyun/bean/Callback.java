package com.travel.liuyun.bean;

/**
 * Created by shengshibotong.com.
 * User: Joe by 13-4-12 上午6:38
 */
public interface Callback<T extends Object> {
    void call(T... values);
}

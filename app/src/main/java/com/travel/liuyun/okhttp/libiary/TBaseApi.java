package com.travel.liuyun.okhttp.libiary;

import java.util.Map;

/**
 * Created by lpc on 2016/11/5.
 */

public class TBaseApi {
    //URL:  http://115.29.206.244:9080/xwy_traveltools/user/createsession/1.0

    public static void postJsonRequest(String url, Map<String, String> headers,
                                       Map<String, Object> pri_param, Map<String, Object> pub_param,
                                       TBaseCallback callback) {
        TOkHttpUtils.postJson().url("http://115.29.206.244:9080/xwy_traveltools/"+url)
                .headers(headers)
                .pri_args(pri_param)
                .pub_args(pub_param)
                .build()
                .execute(callback);

    }

    //最原始的方法，传url，请求头，私参，公参，回调
    public static void createPostRequest(String url, Map<String, String> heads, Map<String, Object> pri_args, Map<String, Object> pub_args, TBaseCallback callback) {
        TOkHttpUtils.postJson()
                .url(url)
                .headers(heads)
                .pri_args(pri_args)
                .pub_args(pub_args)
                .build()
                .execute(callback);
    }

    //封装了公参，使用时不用传公参了。
    public static void createPostRequest(String url, Map<String, String> heads, Map<String, Object> pri_args, TBaseCallback callback) {
        TOkHttpUtils.postJson()
                .url(url)
                .headers(heads)
                .pri_args(pri_args)
                .pub_args(TPublicParam.publicparam)
                .build()
                .execute(callback);
    }

    //使用固定的HOST
    public static void createPostRequest(Map<String, String> heads, Map<String, Object> pri_args, TBaseCallback callback) {
        TOkHttpUtils.postJson()
                .url(THost.HOST)
                .headers(heads)
                .pri_args(pri_args)
                .pub_args(TPublicParam.publicparam)
                .build()
                .execute(callback);
    }

    //封装请求头里面的s字段，只需传m字段就行了。
    public static void createPostRequest(String headM, Map<String, Object> pri_args, TBaseCallback callback) {
        TOkHttpUtils.postJson()
                .url(THost.HOST)
                .headers(TPublicParam.addHeadM(headM))
                .pri_args(pri_args)
                .pub_args(TPublicParam.publicparam)
                .build()
                .execute(callback);

    }
}

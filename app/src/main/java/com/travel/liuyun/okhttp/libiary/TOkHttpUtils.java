package com.travel.liuyun.okhttp.libiary;

import com.zhy.http.okhttp.OkHttpUtils;

import java.util.Map;

import okhttp3.OkHttpClient;

/**
 * Created by lpc on 2016/11/5.
 */

public class TOkHttpUtils extends OkHttpUtils {

    public TOkHttpUtils(OkHttpClient okHttpClient) {
        super(okHttpClient);
    }

    public static void initPubParams(Map<String, Object> pub_args) {
        TPublicParam.publicparam = pub_args ;

    }

    /**
     * 封装好了s字段
     * @param s
     */
    public static void initHeadS(String s) {
        TPublicParam.headparam.put("s",s) ;
    }

    /**
     * 这里增加自己写的返回值为LPostJsonBuilder的方法，postJson，作为调用的入口
     * @return
     */
    public static TPostJsonBuilder postJson() {
        return new TPostJsonBuilder();
    }

}

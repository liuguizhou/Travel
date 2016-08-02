package com.travel.liuyun.retrofit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 手机号相关的API
 * Created by Asia on 2016/3/24 0024.
 */
public class PhoneApi {

    /**
     * HOST地址
     */
    public static final String BASE_URL = "http://api.7mlzg.com/Business/";
    private static final OkHttpClient mOkHttpClient = new OkHttpClient();
    /**
     * 获取PhoneApi实例
     * @return
     */
    public static PhoneApi getApi(){
        return ApiHolder.phoneApi;
    }

    static class ApiHolder{
        private static PhoneApi phoneApi = new PhoneApi();
    }

    private LoginService service;

    private PhoneApi(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).client(mOkHttpClient)
                .build();
        service = retrofit.create(LoginService.class);
    }

    /**
     * 获取PhoneService实例
     * @return
     */
    public LoginService getService(){
        return service;
    }
}

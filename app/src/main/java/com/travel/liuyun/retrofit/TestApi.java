package com.travel.liuyun.retrofit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by liuguizhou on 2016/8/29.
 */

public class TestApi {

    /**
     * Base_Url
     */
    public static final String BASE_URL = "http://api.7mlzg.com/Business/";
    
    private static final OkHttpClient mOkHttpClient = new OkHttpClient();
    /**
     * 获取TestApi实例
     * @return
     */
    public static TestApi getApi(){
        return TestApi.ApiHolder.phoneApi;
    }

    static class ApiHolder{
        private static TestApi phoneApi = new TestApi();
    }

    private CommonService service;

    private TestApi(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).client(mOkHttpClient)
                .build();
        service = retrofit.create(CommonService.class);
    }

    /**
     * 获取CommonService实例
     * @return
     */
    public CommonService getService(){
        return service;
    }
}

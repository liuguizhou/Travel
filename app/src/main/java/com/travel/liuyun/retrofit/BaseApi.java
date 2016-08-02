package com.travel.liuyun.retrofit;

import android.content.Context;

import com.travel.liuyun.TravelApplication;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by liuguizhou on 2016/7/27.
 */
public abstract class BaseApi {
    public static final String API_SERVER = "http://api.7mlzg.com/Business/";
    private static final OkHttpClient mOkHttpClient = new OkHttpClient();
    private static Retrofit mRetrofit;

    public static Retrofit getRetrofit() {
        if (mRetrofit == null) {
            Context context = TravelApplication.getInstance();
            //设定30秒超时
//            mOkHttpClient.setConnectTimeout(30, TimeUnit.SECONDS);
            //设置拦截器，以用于自定义Cookies的设置
//            mOkHttpClient.networkInterceptors()
//                    .add(new CookiesInterceptor(context));
            //设置缓存目录
//            File cacheDirectory = new File(context.getCacheDir()
//                    .getAbsolutePath(), "HttpCache");
//            Cache cache = new Cache(cacheDirectory, 20 * 1024 * 1024);
//            mOkHttpClient.setCache(cache);
            //构建Retrofit
            mRetrofit = new Retrofit.Builder()
                    //配置服务器路径
                    .baseUrl(API_SERVER)
                    //设置日期解析格式，这样可以直接解析Date类型
//                    .setDateFormat("yyyy-MM-dd HH:mm:ss")
                    //配置转化库，默认是Gson
                    .addConverterFactory(GsonConverterFactory.create())
                    //配置回调库，采用RxJava
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    //设置OKHttpClient为网络客户端
                    .client(mOkHttpClient)
                    .build();
        }
        return mRetrofit;
    }
}

package com.travel.liuyun.retrofit;

import android.content.Context;

import com.travel.liuyun.TravelApplication;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by liuguizhou on 2016/7/27.
 */
public abstract class BaseApi {
    public static final String API_SERVER = "http://api.7mlzg.com/Business/";
//    private static final OkHttpClient mOkHttpClient = new OkHttpClient();
//    options.put("platform", "android");
//    options.put("version", "1.0");
//    options.put("key", "123456");
    private static final OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
            .addInterceptor(new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();
                    HttpUrl originalHttpUrl = original.url();
                    /*HttpUrl url = originalHttpUrl
                            .newBuilder()
                            .addQueryParameter("platform", "android")
                            .addQueryParameter("version", "1.0")
                            .addQueryParameter("key", "123456")
                            .build();*/

                    Request request = original.newBuilder()
                            .addHeader("platform", "android")
                            .addHeader("version", "1.0")
                            .addHeader("key", "123456")
                            .method(original.method(), original.body())
                            .build();

                  /*  Request request = original
                            .newBuilder()
                            .url(url)
                            .build();*/
                    okhttp3.Response response = chain.proceed(request);
                    return response;
                }
            })
            .build();
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

package com.travel.liuyun.retrofit;

import android.util.Log;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created on 16/8/4.
 *
 * @author ice
 */
public class BaseApi2 {

    public static final String API_SERVER = "http://api.7mlzg.com/Business/";

    private Retrofit getRetrofit(final String baseUrl, final boolean isWithToken) {
        final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                Request.Builder builder = original.newBuilder()
                        .method(original.method(), original.body())
                //添加请求头部
//                        .header("Content-Type", "application/json")
//                        .header("Accept", "application/json")
                        .header("key", "123456");
//                Log.e("lgz", "key = 123456");
//                        .header("key2", "value2");
//                if (isWithToken)
//                    builder.header("token", App.get().getPreferencesHelper().getToken());

                Request finalRequest = builder.build();

                HttpUrl url = finalRequest.url().newBuilder()
                        // 在原链接上添加后缀，相当于在url上添加了 &platform=android&v=1.0
                        .addQueryParameter("platform", "android")
                        .addQueryParameter("version", "1.0")
//                        .addQueryParameter("key", "123456")
                        .build();
                Log.e("lgz", "platform = android");
//                Logger.i(url.toString());
                finalRequest = finalRequest.newBuilder().url(url).build();
                return chain.proceed(builder.build());
            }
        });

        OkHttpClient okHttpClient = httpClient.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit;
    }

    public Retrofit getRetrofit() {
        return getRetrofit(API_SERVER, false);
    }

    public Retrofit getRetrofitWithToken() {
        return getRetrofit(API_SERVER, true);
    }

}

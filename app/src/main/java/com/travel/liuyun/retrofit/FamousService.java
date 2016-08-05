package com.travel.liuyun.retrofit;

import com.travel.liuyun.bean.Result;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by liuguizhou on 2016/8/5.
 */
public interface FamousService {
    @POST("Login")
    Call<Result> getFamousList(@Query("platform") String platform,
                               @Query("key") String key,
                               @Query("version") String version,
                               @Query("Mobile") String phone,
                               @Query("PassWord") String password);

    @FormUrlEncoded
    @POST("Login")
    Call<Result> getFamousList(@FieldMap Map<String, String> map);

    @Headers({"platform:android", "key:123456", "version:1.0"})
    @POST("Login")
    Call<Result> getFamousList2(@Query("Mobile") String phone, @Query("PassWord") String password);
}

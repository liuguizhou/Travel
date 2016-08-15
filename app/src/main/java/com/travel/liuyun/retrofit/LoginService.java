package com.travel.liuyun.retrofit;

import com.travel.liuyun.bean.Result;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by liuguizhou on 2016/7/28.
 */
public interface LoginService {
    @FormUrlEncoded
    @POST("Login")
    Observable<Result> getFamousList(@Field("platform") String platform, @Field("key") String key, @Field("version") String version,
                                     @Field("Mobile") String phone, @Field("PassWord") String password);

    @FormUrlEncoded
    @POST("Login")
    Observable<Result> getFamousList(@FieldMap Map<String, String> map);
    @FormUrlEncoded
    @POST("Login")
    Observable<Result> getUser(@Field("Mobile") String phone, @Field("PassWord") String password);

    @POST("Login")
    Call<Result> getUser2(@Query("Mobile") String phone, @Query("PassWord") String password);
}

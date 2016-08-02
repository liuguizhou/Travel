package com.travel.liuyun.retrofit;

import com.travel.liuyun.bean.Result;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by liuguizhou on 2016/7/28.
 */
public interface LoginService {
    /*@POST("/Business/Login")
    Observable<Result> getFamousList(@Header("platform") String platform, @Header("key") String key, @Header("version") String version,
                                     @Query("Mobile") String phone,
                                     @Query("PassWord") String password);*/
    @FormUrlEncoded
    @POST("Login")
    Observable<Result> getFamousList(@FieldMap Map<String,String> map);
}

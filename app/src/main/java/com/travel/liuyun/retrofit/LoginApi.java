package com.travel.liuyun.retrofit;

import com.travel.liuyun.bean.Result;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by liuguizhou on 2016/7/27.
 */
public class LoginApi extends BaseApi {
    //定义接口
    public interface FamousService {
        @POST("Login")
        Call<Result> getFamousList(@Query("platform") String platform, @Query("key") String key, @Query("version") String version,
                                   @Query("Mobile") String phone,
                                   @Query("PassWord") String password);
        @FormUrlEncoded
        @POST("Login")
        Call<Result> getFamousList(@FieldMap Map<String,String> map);
    }

    protected static final FamousService service = getRetrofit().create(FamousService.class);


 /*   public static Observable<Result> getFamousList(int userId){
        return service.getFamousList(userId);
    }*/
    /*params.put("platform", "android");
    params.put("ID", "6488");
    params.put("version", "1.0");
    params.put("key", "123456");
    params.put("HeadImg", file);*/

}

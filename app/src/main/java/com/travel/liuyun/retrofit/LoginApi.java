package com.travel.liuyun.retrofit;

import com.travel.liuyun.bean.Result;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.Query;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by liuguizhou on 2016/8/15.
 */

public class LoginApi extends BaseApi2 implements LoginService{

    private LoginService mService = getRetrofit().create(LoginService.class);

    @Override
    public Observable<Result> getFamousList(@Field("platform") String platform, @Field("key") String key, @Field("version") String version, @Field("Mobile") String phone, @Field("PassWord") String password) {
        return null;
    }

    @Override
    public Observable<Result> getFamousList(@FieldMap Map<String, String> map) {
        return null;
    }

    @Override
    public Observable<Result> getUser(@Field("Mobile") String phone, @Field("PassWord") String password) {
        return mService.getUser(phone, password).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Call<Result> getUser2(@Query("Mobile") String phone, @Query("PassWord") String password) {
        return mService.getUser2(phone, password);
    }
}

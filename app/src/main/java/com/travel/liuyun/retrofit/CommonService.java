package com.travel.liuyun.retrofit;

import com.travel.liuyun.bean.Result;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import rx.Observable;

/**
 * Created by liuguizhou on 2016/8/29.
 */

public interface CommonService {

    @Multipart
    @POST("ModifyGuestInfo")
    Observable<Result> upLoadPicture(@PartMap Map<String, RequestBody> params);
}

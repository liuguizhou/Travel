package com.travel.liuyun.okhttp.libiary;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by lpc on 2016/11/5.
 */

public abstract class TBaseCallback<T> extends Callback<TOkHttpResponse<T>> {

    @Override
    public TOkHttpResponse<T> parseNetworkResponse(Response response, int i) throws Exception {
        String str = response.body().string();
        Log.e("travel","str = "+str);
        TOkHttpResponse<T> res = new Gson().fromJson(str,new TypeToken<TOkHttpResponse<T>>(){}.getType()) ;
        return res;
    }

    @Override
    public void onError(Call call, Exception e, int i) {

    }

}

package com.travel.liuyun.okhttp.libiary;

import com.zhy.http.okhttp.request.OkHttpRequest;
import com.zhy.http.okhttp.utils.Exceptions;

import java.util.Map;

import okhttp3.MediaType;

/**
 * Created by lpc on 2016/11/4.
 */

public class TPostJsonRequest extends OkHttpRequest {

    String picContent ;
    MediaType mediaType ;

    private static MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json;charset-8");

    public TPostJsonRequest(String url, Object tag, Map<String,String> params , String content, Map headers, MediaType mediaType, int id) {
        super(url,tag,params,headers,id);
        this.picContent = content ;
        this.mediaType = mediaType ;
        if (this.picContent == null) {
            Exceptions.illegalArgument("the content can not be null !");
        }
        if (this.mediaType == null) {
            this.mediaType = MEDIA_TYPE_JSON ;
        }
    }



    @Override
    protected okhttp3.RequestBody buildRequestBody() {
        return null;
    }

    @Override
    protected okhttp3.Request buildRequest(okhttp3.RequestBody requestBody) {
        return null;
    }
}

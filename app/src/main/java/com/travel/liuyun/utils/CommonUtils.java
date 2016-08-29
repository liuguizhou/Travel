package com.travel.liuyun.utils;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by liuguizhou on 2016/8/29.
 */

public class CommonUtils {
    public static RequestBody toRequestBody(String value) {
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), value);
        return body;
    }
}

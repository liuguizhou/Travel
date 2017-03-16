package com.travel.liuyun.okhttp;

import android.util.Log;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 上传至github
 * Created by liuguizhou on 2017/3/14.
 * Okhttp设置请求日志过滤器，支持打印Post请求参数
 */

public class LogInterceptor implements Interceptor {
    public static String TAG = "LogInterceptor";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Log.e(TAG, "go>>>>>>>>>>>>>>>>>>>>>>");
        Request request = chain.request();
        long startTime = System.currentTimeMillis();
        Response response = chain.proceed(request);
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        MediaType mediaType = response.body().contentType();
        String content = response.body().string();//开启多个下载任务，无响应一段时间后会出现 OOM ; （此法输出日志的方式 有问题）created by lgz
        Log.e(TAG, "\n");
        Log.e(TAG, "----------Start----------------");
        Log.e(TAG, "| " + request.toString());
        String method = request.method();
        if ("POST".equals(method)) {
            StringBuilder sb = new StringBuilder();
            if (request.body() instanceof FormBody) {
                FormBody body = (FormBody) request.body();
                for (int i = 0; i < body.size(); i++) {
                    sb.append(body.encodedName(i) + "=" + body.encodedValue(i) + ",");
                }
                sb.delete(sb.length() - 1, sb.length());
                Log.d(TAG, "| RequestParams:{" + sb.toString() + "}");
            }
        }
        Log.d(TAG, "| Response:" + content);
        response.body().close();
        Log.d(TAG, "----------End:" + duration + "毫秒----------");
        response.body().close();
        return response.newBuilder()
                .body(okhttp3.ResponseBody.create(mediaType, content))
                .build();
    }
}

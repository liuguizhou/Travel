package com.travel.liuyun.okhttp.libiary;

import com.google.gson.Gson;
import com.zhy.http.okhttp.request.PostStringRequest;
import com.zhy.http.okhttp.request.RequestCall;
import com.zhy.http.okhttp.utils.Exceptions;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;

/**
 * Created by lpc on 2016/11/5.
 */

public class TPostJsonBuilder extends TOkHttpRequestBuilder<TPostJsonBuilder> {
    private String paramContent;
    private MediaType mediaType;
    private Map<String,Object> pri_param ;
    private Map<String,Object> pub_param ;


    public TPostJsonBuilder() {
        this.pri_param = new HashMap<>();
        this.pub_param = new HashMap<>() ;
    }



    public TPostJsonBuilder paramContent(String content) {
        if (content != null) {
            this.paramContent = content;
        } else if (this.pri_param.size()!=0 && this.pub_param.size() != 0) {
            this.paramContent = initParam(this.pri_param,this.pub_param);
        } else {
            Exceptions.illegalArgument("params cannot be null!");
        }
        return this;
    }

    /**
     * 这里将公参和私参拼接起来，拼接后的格式为：
     *
        {
             "args": {
                 "pri_args": {
                     ...
                 },
                 "pub_args": {
                     ...
                 }
            }
         }
     *
     *
     *
     *
     * @param pri_param
     * @param pub_param
     * @return
     */
    private String initParam(Map<String, Object> pri_param, Map<String, Object> pub_param) {

        Map<String,Object> args = new HashMap<>();
//        Map<String,Map<String,Object>> pubParam = new HashMap<>();
        args.put("a",pub_param);
//        Map<String,Map<String,Object>> priParam = new HashMap<>();
        args.put("b",pri_param);
        //将pub_param追加到pri_param里
//        priParam.putAll(pubParam);
        args.put("c","swtest");
        Gson gson = new Gson();
        //转换为json string
        String argsStr = gson.toJson(args);
        return argsStr ;
    }

    public TPostJsonBuilder mediaType(MediaType mediaType) {
        this.mediaType = mediaType;
        return this;
    }

    public RequestCall build() {
        paramContent = initParam(this.pri_param,this.pub_param);
        //以json格式传到服务器
        mediaType = MediaType.parse("application/json;charset=utf-8");
        return (new PostStringRequest(this.url, this.tag, param, this.headers, paramContent, this.mediaType,id)).build();
    }
}

package com.travel.liuyun.okhttp.libiary;

import java.util.Map;

/**
 * Created by lpc on 2016/11/7.
 */

public class TPublicParam {

    public TPublicParam() {
    }

    public static Map<String,Object> publicparam ;
    public static Map<String,String> headparam ;

    public static Map<String,String> addHeadM(String m) {
        headparam.put("m",m);
        return headparam;
    }

    public static Map<String,Object> addPublicParams(Map<String,Object> publicparam) {
        TPublicParam.publicparam = publicparam ;
        return TPublicParam.publicparam;
    }


}

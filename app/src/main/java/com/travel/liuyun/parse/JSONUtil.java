/*
 * Copyright (C) 2015 The Drupe Android Project
 *
 */

package com.travel.liuyun.parse;

import android.text.TextUtils;

import com.travel.liuyun.bean.UserInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONUtil {

    public static Map<String, Object> getLoginResult(String result) {
        if (TextUtils.isEmpty(result)) {
            return null;
        }
        try {
            Map<String, Object> resultMap = new HashMap<String, Object>();
            JSONObject jsonObject = new JSONObject(result);
            int status = jsonObject.getInt("status");
            String message = jsonObject.getString("message");
            resultMap.put("status", status);
            resultMap.put("message", message);

            JSONObject dataObject = jsonObject.getJSONObject("data");
            if (!dataObject.isNull("ID")) {
                UserInfo userInfo = new UserInfo();
                userInfo.setId(dataObject.getString("ID"));
                userInfo.setTrueName(dataObject.getString("TrueName"));
                userInfo.setSex(dataObject.getString("Sex"));
                userInfo.setAge(dataObject.getString("Age"));
                userInfo.setAreaID(dataObject.getString("AreaID"));
                userInfo.setWorkingTime(dataObject.getString("WorkingTime"));
                userInfo.setIntroduce(dataObject.getString("Introduce"));
                userInfo.setState(dataObject.getString("State"));
                userInfo.setPictures(dataObject.getString("Pictures"));
                userInfo.setHeadImg(dataObject.getString("HeadImg"));
                userInfo.setMobile(dataObject.getString("Mobile"));
                userInfo.setIdCard(dataObject.getString("IdCard"));
                userInfo.setPermanentEWM(dataObject.getString("PermanentEWM"));
                if (dataObject.has("morepicture")) {
                    JSONArray jsonArray = dataObject.getJSONArray("morepicture");
                    List<String> morePictures = new ArrayList<String>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        morePictures.add(jsonArray.getString(i));

                    }
                    userInfo.setMorepicture(morePictures);
                }

                resultMap.put("data", userInfo);
            }

            return resultMap;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;

    }

    public static String getVerificationCodeResult(String result) {
        if (TextUtils.isEmpty(result)) {
            return null;
        }
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONObject dataObject = jsonObject.getJSONObject("data");
            String VerificationCode = dataObject.getString("VerificationCode");

            return VerificationCode;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;

    }

    public static String[] getRatingCodeResult(String result) {
        if (TextUtils.isEmpty(result)) {
            return null;
        }
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONObject dataObject = jsonObject.getJSONObject("data");
            String commonts = dataObject.getString("comments");
            String count = dataObject.getString("servicecount");
            String[] str = {commonts, count};
            return str;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;

    }

    public static String getRegisterResult(String result) {
        if (TextUtils.isEmpty(result)) {
            return null;
        }
        try {
            JSONObject jsonObject = new JSONObject(result);
            String status = jsonObject.getString("status");

            return status;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;

    }

    public static Map<String, Object> parserObtainCode(String result) {
        if (TextUtils.isEmpty(result)) {
            return null;
        }
        try {
            Map<String, Object> resultMap = new HashMap<String, Object>();
            JSONObject jsonObject = new JSONObject(result);
            int status = jsonObject.getInt("status");
            String message = jsonObject.getString("message");
            resultMap.put("status", status);
            resultMap.put("message", message);

            // JSONObject dataObject = jsonObject.getJSONObject("data");
            // String VerificationCode =
            // dataObject.getString("VerificationCode");

            // resultMap.put("data", VerificationCode);

            return resultMap;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;

    }

}

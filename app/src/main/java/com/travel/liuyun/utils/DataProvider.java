package com.travel.liuyun.utils;

import com.travel.liuyun.bean.BannerItem;

import java.util.ArrayList;

public class DataProvider {
    public static String[] titles = new String[]{
            "伪装者:胡歌演绎'痞子特工'",
            "无心法师:生死离别!月牙遭虐杀",
            "花千骨:尊上沦为花千骨",
            "综艺饭:胖轩偷看夏天洗澡掀波澜",
            "碟中谍4:阿汤哥高塔命悬一线,超越不可能",
    };
    public static String[] urls = new String[]{//640*360 360/640=0.5625
            "http://g.hiphotos.baidu.com/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=b2d4b8831bd8bc3ed2050e98e3e2cd7b/86d6277f9e2f0708f66d27b9eb24b899a901f21b.jpg",//伪装者:胡歌演绎"痞子特工"
            "http://e.hiphotos.baidu.com/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=f859babc369b033b3885f48874a75db6/6609c93d70cf3bc7b34e6a54d300baa1cd112a3f.jpg",//无心法师:生死离别!月牙遭虐杀
            "http://b.hiphotos.baidu.com/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=e248f878f536afc31a013737d27080a1/c75c10385343fbf25316139bb27eca8065388f54.jpg",//花千骨:尊上沦为花千骨
            "http://c.hiphotos.baidu.com/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=53c85e376e81800a7ae8815cd05c589f/8601a18b87d6277f1cbf95802a381f30e824fcdd.jpg",//综艺饭:胖轩偷看夏天洗澡掀波澜
            "http://e.hiphotos.baidu.com/baike/s%3D500/sign=59860d3d06087bf479ec57e9c2d3575e/1b4c510fd9f9d72a244a226bd02a2834349bbb9d.jpg",//碟中谍4:阿汤哥高塔命悬一线,超越不可能
    };

    public static ArrayList<BannerItem> getList() {
        ArrayList<BannerItem> list = new ArrayList<>();
        for (int i = 0; i < urls.length; i++) {
            BannerItem item = new BannerItem();
            item.imgUrl = urls[i];
            item.title = titles[i];

            list.add(item);
        }

        return list;
    }

    /*public static ArrayList<Integer> geUsertGuides() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(R.mipmap.guide_img_1);
        list.add(R.mipmap.guide_img_2);
        list.add(R.mipmap.guide_img_3);
        list.add(R.mipmap.guide_img_4);

        return list;
    }*/

}
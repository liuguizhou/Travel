package com.travel.liuyun.utils;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by liuguizhou on 2017/4/19.
 */

public class BitmapCache implements ImageLoader.ImageCache {
    //内存缓存
    private static LruCache<String, Bitmap> mMemoryCache;
    //单例
    private static BitmapCache lruImageCache;
    private BitmapCache(){
        // Get the Max available memory
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory/10;
        mMemoryCache = new LruCache<String, Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap bitmap){
                return bitmap.getRowBytes() * bitmap.getHeight();
            }
        };
    }
    //获取单例实例
    public static BitmapCache instance(){
        if(lruImageCache == null){
            lruImageCache = new BitmapCache();
        }
        return lruImageCache;
    }
    //从内存获取图片
    @Override
    public Bitmap getBitmap(String url){
        return mMemoryCache.get(url);
    }
    //图片放入到内存
    @Override
    public void putBitmap(String url, Bitmap bitmap){
        if(getBitmap(url) == null){
            mMemoryCache.put(url, bitmap);
        }
    }
}

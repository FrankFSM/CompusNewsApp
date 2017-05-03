package com.cfxy.compusnewsapp.utils;

import android.content.Context;

/**
 * 缓存
 * Created by ralap on 2017/5/3.
 */
public class CacheUtil {

    /**
     * 设置缓存
     * @param key
     * @param value
     * @param context
     */
    public static void setCache(String key, String value, Context context) {
        PrefUtils.putString(key, value, context);
    }

    /**
     * 获取缓存
     * @param key
     * @param context
     * @return
     */
    public static String getCache(String key, Context context) {
        String value = PrefUtils.getString(key, "", context);
        return value;
    }
}

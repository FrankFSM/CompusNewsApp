package com.cfxy.compusnewsapp.global;

import com.cfxy.compusnewsapp.utils.PrefUtils;

/**
 * Created by ralap on 2017/5/2.
 */
public class Constants {

    public static final String SERVER_URL = "http://192.168.1.104:8080/news";
    public static final String CATEGORIES_URL = SERVER_URL + "/getNewsMenu.action";
    //聚合数据的appkey
    public static final String JUHE_APPKEY = "e472d375cd445125e986f607891861f0";

    public static final String JUHE_URL = "http://v.juhe.cn/toutiao/index?type=";

    public static final String JUHE_SERVER_URL = "http://192.168.1.104:8080/news/jhdata";


    public static final String JUHE_TOP_URL =  JUHE_SERVER_URL+"/top.json";
    public static final String JUHE_SHEHUI_URL =  JUHE_SERVER_URL+"/shehui.json";
    public static final String JUHE_GUONEI_URL =  JUHE_SERVER_URL+"/guonei.json";
    public static final String JUHE_GUOJI_URL =  JUHE_SERVER_URL+"/guoji.json";
    public static final String JUHE_YULE_URL =  JUHE_SERVER_URL+"/yule.json";
    public static final String JUHE_TIYU_URL =  JUHE_SERVER_URL+"/tiyu.json";
    public static final String JUHE_JUNSHI_URL =  JUHE_SERVER_URL+"/junshi.json";
    public static final String JUHE_KEJI_URL =  JUHE_SERVER_URL+"/keji.json";
    public static final String JUHE_CAIJING_URL =  JUHE_SERVER_URL+"/caijing.json";
    public static final String JUHE_SHISHANG_URL = JUHE_SERVER_URL+"/shishang.json";




}

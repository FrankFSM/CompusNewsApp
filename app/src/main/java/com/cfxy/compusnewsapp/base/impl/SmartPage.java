package com.cfxy.compusnewsapp.base.impl;

import android.app.Activity;

import com.cfxy.compusnewsapp.base.BaseMenuDetailPage;
import com.cfxy.compusnewsapp.base.BasePager;
import com.cfxy.compusnewsapp.base.impl.jhdetail.JHNewsMenuPage;
import com.cfxy.compusnewsapp.domain.JHNews;
import com.cfxy.compusnewsapp.global.Constants;
import com.cfxy.compusnewsapp.ui.activity.MainActivity;
import com.google.gson.Gson;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ralap on 2017/5/1.
 */
public class SmartPage extends BasePager {

    private List<BaseMenuDetailPage> menuDetailPages;
    private String result;
    private JHNews news;
    private List<JHNews.ResultData> resultDatas;

    //    private NewsMenuData newsMenuData;
    public SmartPage(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        setSlidingMenuEnable(false);
        resultDatas = new ArrayList<>();
//        result = CacheUtil.getCache(Constants.CATEGORIES_URL, mActivity);
//
//        if (!TextUtils.isEmpty(result)) {
//            processResult(result);
//        }
        getTopDataFromServer();


    }

    private void getTopDataFromServer() {
        HttpUtils utils = new HttpUtils();
        utils.send(HttpRequest.HttpMethod.GET, Constants.JUHE_TOP_URL,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        result = responseInfo.result;
                        processResult(result);
                        getShehuiDataFromServer();
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {

                    }
                });
    }

    private void getShehuiDataFromServer() {
        HttpUtils utils = new HttpUtils();
        utils.send(HttpRequest.HttpMethod.GET, Constants.JUHE_SHEHUI_URL,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        result = responseInfo.result;
                        processResult(result);
                        getGuoneiDataFromServer();
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {

                    }
                });
    }

    private void getGuoneiDataFromServer() {
        HttpUtils utils = new HttpUtils();
        utils.send(HttpRequest.HttpMethod.GET, Constants.JUHE_GUONEI_URL,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        result = responseInfo.result;
                        processResult(result);
                        getGuojiDataFromServer();
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {

                    }
                });
    }

    private void getGuojiDataFromServer() {
        HttpUtils utils = new HttpUtils();
        utils.send(HttpRequest.HttpMethod.GET, Constants.JUHE_GUOJI_URL,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        result = responseInfo.result;
                        processResult(result);
                        getYuleDataFromServer();
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {

                    }
                });
    }

    private void getYuleDataFromServer() {
        HttpUtils utils = new HttpUtils();
        utils.send(HttpRequest.HttpMethod.GET, Constants.JUHE_YULE_URL,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        result = responseInfo.result;
                        processResult(result);
                        getTiyuDataFromServer();
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {

                    }
                });
    }

    private void getTiyuDataFromServer() {
        HttpUtils utils = new HttpUtils();
        utils.send(HttpRequest.HttpMethod.GET, Constants.JUHE_TIYU_URL,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        result = responseInfo.result;
                        processResult(result);
                        getunshiDataFromServer();
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {

                    }
                });
    }

    private void getunshiDataFromServer() {
        HttpUtils utils = new HttpUtils();
        utils.send(HttpRequest.HttpMethod.GET, Constants.JUHE_JUNSHI_URL,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        result = responseInfo.result;
                        processResult(result);
                        getKejiDataFromServer();
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {

                    }
                });
    }

    private void getKejiDataFromServer() {
        HttpUtils utils = new HttpUtils();
        utils.send(HttpRequest.HttpMethod.GET, Constants.JUHE_KEJI_URL,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        result = responseInfo.result;
                        processResult(result);
                        getCaijingDataFromServer();
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {

                    }
                });
    }

    private void getCaijingDataFromServer() {
        HttpUtils utils = new HttpUtils();
        utils.send(HttpRequest.HttpMethod.GET, Constants.JUHE_CAIJING_URL,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        result = responseInfo.result;
                        processResult(result);
                        getShishangDataFromServer();
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {

                    }
                });
    }

    private void getShishangDataFromServer() {
        HttpUtils utils = new HttpUtils();
        utils.send(HttpRequest.HttpMethod.GET, Constants.JUHE_SHISHANG_URL,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        result = responseInfo.result;
                        processResult(result);
                        sendData();
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {

                    }
                });
    }

    private void processResult(String result) {
        Gson gson = new Gson();

        news = gson.fromJson(result,JHNews.class);


        MainActivity mUi = (MainActivity) mActivity;

        resultDatas.add(news.result);



    }

    public void sendData(){
        menuDetailPages = new ArrayList<>();
        menuDetailPages.add(new JHNewsMenuPage(mActivity, resultDatas));
        menuDetailPages.get(0).initData();

        setCurrentMenuPage(0);

    }

    public void setCurrentMenuPage(int currentPos) {
        BaseMenuDetailPage menuDatailPage = menuDetailPages.get(currentPos);
        tvTitle.setText("新闻");
        flBasePage.removeAllViews();
        menuDatailPage.initData();
        flBasePage.addView(menuDatailPage.mRootView);

    }

    private void setSlidingMenuEnable(boolean enable){
        MainActivity mUi = (MainActivity) mActivity;
        SlidingMenu slidingMenu = mUi.getSlidingMenu();
        if(enable){
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        }else{
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        }
    }

}

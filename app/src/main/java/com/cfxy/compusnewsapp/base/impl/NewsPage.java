package com.cfxy.compusnewsapp.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.TextView;

import com.cfxy.compusnewsapp.base.BaseMenuDetailPage;
import com.cfxy.compusnewsapp.base.BasePager;
import com.cfxy.compusnewsapp.base.impl.menudetail.InteractMenuPage;
import com.cfxy.compusnewsapp.base.impl.menudetail.NewsMenuPage;
import com.cfxy.compusnewsapp.base.impl.menudetail.PhotosMenuPage;
import com.cfxy.compusnewsapp.base.impl.menudetail.TopictMenuPage;
import com.cfxy.compusnewsapp.domain.NewsMenuData;
import com.cfxy.compusnewsapp.global.Constants;
import com.cfxy.compusnewsapp.ui.activity.MainActivity;
import com.cfxy.compusnewsapp.ui.fragment.LeftMenuFragment;
import com.cfxy.compusnewsapp.utils.CacheUtil;
import com.google.gson.Gson;
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
public class NewsPage extends BasePager {

    private List<BaseMenuDetailPage> menuDetailPages;
    private String result;
    private NewsMenuData newsMenuData;

    public NewsPage(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        result = CacheUtil.getCache(Constants.CATEGORIES_URL, mActivity);
        getDataFromServer();
        if (!TextUtils.isEmpty(result)) {
            processResult(result);
        }
        getDataFromServer();


    }

    private void getDataFromServer() {
        HttpUtils utils = new HttpUtils();
        utils.send(HttpRequest.HttpMethod.GET, Constants.CATEGORIES_URL,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        result = responseInfo.result;
                        // CacheUtil.setCache(Constants.CATEGORIES_URL,result,mActivity);
                        processResult(result);
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {

                    }
                });
    }

    private void processResult(String result) {
        Gson gson = new Gson();
        newsMenuData = gson.fromJson(result, NewsMenuData.class);
        MainActivity mUi = (MainActivity) mActivity;
        LeftMenuFragment leftMenuFragment = mUi.getLeftMenuFragment();
        leftMenuFragment.setData(newsMenuData.data);


        menuDetailPages = new ArrayList<>();
        menuDetailPages.add(new NewsMenuPage(mActivity,newsMenuData.data.get(0).children));
        menuDetailPages.add(new TopictMenuPage(mActivity));
        menuDetailPages.add(new PhotosMenuPage(mActivity));
        menuDetailPages.add(new InteractMenuPage(mActivity));
        setCurrentMenuPage(0);

    }

    public void setCurrentMenuPage(int currentPos) {
        BaseMenuDetailPage menuDatailPage = menuDetailPages.get(currentPos);
        tvTitle.setText(newsMenuData.data.get(currentPos).title);
        flBasePage.removeAllViews();
        menuDatailPage.initData();
        flBasePage.addView(menuDatailPage.mRootView);

    }
}

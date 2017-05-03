package com.cfxy.compusnewsapp.base;

import android.app.Activity;
import android.view.View;

/**
 * Created by ralap on 2017/5/3.
 */
public abstract class BaseMenuDetailPage {
    public  final Activity mActivity;
    public  final View mRootView;


    public BaseMenuDetailPage(Activity activity) {
        this.mActivity = activity;
        this.mRootView = initView();
    }

    public abstract View initView();

    public void initData(){

    }

}

package com.cfxy.compusnewsapp.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

import com.cfxy.compusnewsapp.base.BasePager;

/**
 * Created by ralap on 2017/5/1.
 */
public class SmartPage extends BasePager {

    public SmartPage(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        tvTitle.setText("智能服务");


        TextView tx = new TextView(mActivity);
        tx.setText("智能服务");
        tx.setTextColor(Color.RED);
        tx.setGravity(Gravity.CENTER);
        flBasePage.addView(tx);
    }
}

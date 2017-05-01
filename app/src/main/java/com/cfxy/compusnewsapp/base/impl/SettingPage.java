package com.cfxy.compusnewsapp.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

import com.cfxy.compusnewsapp.base.BasePager;

/**
 * Created by ralap on 2017/5/1.
 */
public class SettingPage extends BasePager {
    public SettingPage(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        tvTitle.setText("设置");

        TextView tx = new TextView(mActivity);
        tx.setText("设置");
        tx.setTextColor(Color.RED);
        tx.setGravity(Gravity.CENTER);
        flBasePage.addView(tx);
    }
}

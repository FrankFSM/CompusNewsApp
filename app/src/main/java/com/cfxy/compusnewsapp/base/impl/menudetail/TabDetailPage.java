package com.cfxy.compusnewsapp.base.impl.menudetail;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.cfxy.compusnewsapp.base.BaseMenuDetailPage;
import com.cfxy.compusnewsapp.domain.NewsMenuData;

/**
 * Created by ralap on 2017/5/3.
 */
public class TabDetailPage extends BaseMenuDetailPage {
    private NewsMenuData.NewsTabData tabData;
    private TextView tx;

    public TabDetailPage(Activity activity, NewsMenuData.NewsTabData tabData) {
        super(activity);
        this.tabData = tabData;
    }

    @Override
    public View initView() {
        tx = new TextView(mActivity);
        tx.setTextColor(Color.RED);
        tx.setGravity(Gravity.CENTER);
        return tx;
    }

    @Override
    public void initData() {
        tx.setText(tabData.title);
    }
}

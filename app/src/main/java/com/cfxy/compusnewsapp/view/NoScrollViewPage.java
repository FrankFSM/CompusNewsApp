package com.cfxy.compusnewsapp.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by ralap on 2017/5/2.
 */
public class NoScrollViewPage extends ViewPager {
    public NoScrollViewPage(Context context) {
        super(context);
    }

    public NoScrollViewPage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return true;
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }
}

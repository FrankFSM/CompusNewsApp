package com.cfxy.compusnewsapp.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.cfxy.compusnewsapp.R;
import com.cfxy.compusnewsapp.ui.fragment.ContentFragment;
import com.cfxy.compusnewsapp.ui.fragment.LeftMenuFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;


public class MainActivity extends SlidingFragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SlidingMenu slidingMenu = getSlidingMenu();
        setBehindContentView(R.layout.lefg_menu);
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        slidingMenu.setBehindOffset(250);
        initFragment();

    }

    public void initFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fl_content, new ContentFragment());
        fragmentTransaction.replace(R.id.fl_left_menu, new LeftMenuFragment());
        fragmentTransaction.commit();

    }
}

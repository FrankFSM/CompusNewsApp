package com.cfxy.compusnewsapp.base.impl.menudetail;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.cfxy.compusnewsapp.R;
import com.cfxy.compusnewsapp.base.BaseMenuDetailPage;
import com.cfxy.compusnewsapp.domain.NewsMenuData;
import com.cfxy.compusnewsapp.ui.activity.MainActivity;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

/**
 * Created by ralap on 2017/5/3.
 */
public class NewsMenuPage extends BaseMenuDetailPage implements ViewPager.OnPageChangeListener{

    private ArrayList<NewsMenuData.NewsTabData> mTabList;

    private ArrayList<TabDetailPage> tabDetailPages;
    @ViewInject(R.id.vp_news_page)
    ViewPager vpNewsPage;

    public NewsMenuPage(Activity activity, ArrayList<NewsMenuData.NewsTabData> children) {
        super(activity);
        this.mTabList = children;
    }

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.pager_menu_detail_news, null);
        ViewUtils.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        //初始化页签
        tabDetailPages = new ArrayList<>();
        for (NewsMenuData.NewsTabData tabData : mTabList) {
            TabDetailPage tabPage = new TabDetailPage(mActivity,tabData);
            tabDetailPages.add(tabPage);
        }
        vpNewsPage.setAdapter(new NewSMenuAdapter());
        vpNewsPage.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if(position == 0){
            setSlidingMenuEnable(true);
        }else{
            setSlidingMenuEnable(false);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    class NewSMenuAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return tabDetailPages.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TabDetailPage detailPage = tabDetailPages.get(position);
            View view = detailPage.mRootView;
            detailPage.initData();
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
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

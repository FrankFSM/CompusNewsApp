package com.cfxy.compusnewsapp.base.impl.jhdetail;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.cfxy.compusnewsapp.R;
import com.cfxy.compusnewsapp.base.BaseMenuDetailPage;
import com.cfxy.compusnewsapp.domain.JHNews;
import com.cfxy.compusnewsapp.domain.NewsMenuData;
import com.cfxy.compusnewsapp.ui.activity.MainActivity;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ralap on 2017/5/3.
 */
public class JHNewsMenuPage extends BaseMenuDetailPage implements ViewPager.OnPageChangeListener {

    private List<JHNews.ResultData> mTabList;

    private ArrayList<JHTabDetailPage> jHTabDetailPages;
    @ViewInject(R.id.vp_news_page)
    ViewPager vpNewsPage;
    @ViewInject(R.id.indiccator)
    TabPageIndicator indicator;

    public JHNewsMenuPage(Activity activity, List<JHNews.ResultData> children) {
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
        jHTabDetailPages = new ArrayList<>();
        for (JHNews.ResultData tabData : mTabList) {
            JHTabDetailPage tabPage = new JHTabDetailPage(mActivity, tabData);
            jHTabDetailPages.add(tabPage);
        }
        vpNewsPage.setAdapter(new NewSMenuAdapter());
        indicator.setViewPager(vpNewsPage);
        indicator.setOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        setSlidingMenuEnable(false);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    class NewSMenuAdapter extends PagerAdapter {

        @Override
        public CharSequence getPageTitle(int position) {
            String title = mTabList.get(position).data.get(0).category;
            if(TextUtils.isEmpty(title)){
                title="时尚";
            }
            return title;
        }

        @Override
        public int getCount() {
            return jHTabDetailPages.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            JHTabDetailPage detailPage = jHTabDetailPages.get(position);
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

    private void setSlidingMenuEnable(boolean enable) {
        MainActivity mUi = (MainActivity) mActivity;
        SlidingMenu slidingMenu = mUi.getSlidingMenu();
        if (enable) {
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        } else {
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        }
    }
}

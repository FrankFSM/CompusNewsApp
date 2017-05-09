package com.cfxy.compusnewsapp.ui.fragment;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.cfxy.compusnewsapp.R;
import com.cfxy.compusnewsapp.base.BaseFragment;
import com.cfxy.compusnewsapp.base.BasePager;
import com.cfxy.compusnewsapp.base.impl.GovPage;
import com.cfxy.compusnewsapp.base.impl.HomePage;
import com.cfxy.compusnewsapp.base.impl.NewsPage;
import com.cfxy.compusnewsapp.base.impl.SettingPage;
import com.cfxy.compusnewsapp.base.impl.SmartPage;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ContentFragment extends BaseFragment {

    @ViewInject(R.id.vp_content)
    ViewPager vpContent;

    @ViewInject(R.id.rg_group)
    RadioGroup rgGroup;

    private ArrayList<BasePager> mPager;

    @Override
    public View initView() {
        View view = View.inflate(mActiviy, R.layout.fragment_content, null);
        ViewUtils.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        mPager = new ArrayList<>();
        mPager.add(new HomePage(mActiviy));
        mPager.add(new NewsPage(mActiviy));
        mPager.add(new SmartPage(mActiviy));
        mPager.add(new GovPage(mActiviy));
        mPager.add(new SettingPage(mActiviy));
        vpContent.setAdapter(new ContentAdapter());
        mPager.get(0).initData();
    }

    @Override
    public void initListener() {
        rgGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.br_home:
                        mPager.get(0).initData();
                        vpContent.setCurrentItem(0,false);
                        break;
                    case R.id.br_newscenter:
                        mPager.get(1).initData();
                        vpContent.setCurrentItem(1,false);
                        break;
                    case R.id.br_smart:
                        mPager.get(2).initData();
                        vpContent.setCurrentItem(2,false);
                        break;
                    case R.id.br_gov:
                        mPager.get(3).initData();
                        vpContent.setCurrentItem(3,false);
                        break;
                    case R.id.br_setting:
                        mPager.get(4).initData();
                        vpContent.setCurrentItem(4,false);
                        break;
                    default:
                    break;
                }
            }
        });
    }

    class ContentAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mPager.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            BasePager pager = mPager.get(position);
            if (position!=1){
                pager.btnMenu.setVisibility(View.GONE);
            }
            container.addView(pager.mRootView);
            return pager.mRootView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    public NewsPage getNewsPage(){
        return (NewsPage) mPager.get(1);
    }

}

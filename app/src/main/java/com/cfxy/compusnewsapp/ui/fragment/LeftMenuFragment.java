package com.cfxy.compusnewsapp.ui.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.cfxy.compusnewsapp.R;
import com.cfxy.compusnewsapp.base.BaseFragment;
import com.cfxy.compusnewsapp.base.impl.NewsPage;
import com.cfxy.compusnewsapp.domain.NewsMenuData;
import com.cfxy.compusnewsapp.ui.activity.MainActivity;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

public class LeftMenuFragment extends BaseFragment {

    @ViewInject(R.id.lv_left_menu)
    ListView lvLeftMenu;


    private ArrayList<NewsMenuData.NewsData> data;
    private MenuAdapter menuAdapter;
    private int currentPos = 0;

    @Override
    public View initView() {
        View view = View.inflate(mActiviy, R.layout.fragment_left_menu, null);
        ViewUtils.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
    }

    @Override
    public void initListener() {


    }

    class MenuAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(mActiviy, R.layout.list_item_left_menu, null);
            TextView tv = (TextView) view.findViewById(R.id.tv_item_left_menu);
            tv.setText(data.get(position).title);
            if (position == currentPos) {
                tv.setEnabled(true);
            } else {
                tv.setEnabled(false);
            }
            return view;
        }
    }

    public void setData(ArrayList<NewsMenuData.NewsData> data) {
        this.data = data;
        if (data == null) {
            MainActivity mUi = (MainActivity) mActiviy;
            SlidingMenu slidingMenu = mUi.getSlidingMenu();
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        } else {

            menuAdapter = new MenuAdapter();
            lvLeftMenu.setAdapter(menuAdapter);
            lvLeftMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    currentPos = position;
                    setCurrentMenuPage();
                    toggle();
                    menuAdapter.notifyDataSetChanged();

                }
            });
            currentPos = 0;
        }
    }

    private void toggle() {
        MainActivity mUi = (MainActivity) mActiviy;
        mUi.toggle();
    }

    protected void setCurrentMenuPage() {
        MainActivity mUi = (MainActivity) mActiviy;
        ContentFragment contentFragment = mUi.getContentFragment();
        NewsPage newsPage = contentFragment.getNewsPage();
        newsPage.setCurrentMenuPage(currentPos);


    }
}

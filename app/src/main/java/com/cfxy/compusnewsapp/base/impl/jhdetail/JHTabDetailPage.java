package com.cfxy.compusnewsapp.base.impl.jhdetail;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cfxy.compusnewsapp.R;
import com.cfxy.compusnewsapp.base.BaseMenuDetailPage;
import com.cfxy.compusnewsapp.base.impl.detail.NewsDetailActivity;
import com.cfxy.compusnewsapp.domain.JHNews;
import com.cfxy.compusnewsapp.domain.NewsData;
import com.cfxy.compusnewsapp.domain.NewsMenuData;
import com.cfxy.compusnewsapp.global.Constants;
import com.cfxy.compusnewsapp.utils.CacheUtil;
import com.cfxy.compusnewsapp.view.HorizontalScrollViewPager;
import com.cfxy.compusnewsapp.view.RefreshListView;
import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ralap on 2017/5/3.
 */
public class JHTabDetailPage extends BaseMenuDetailPage {

    private JHNews.ResultData tabData;
    private String moreUrl;

    @ViewInject(R.id.lv_tab)
    private RefreshListView lvTab;


    private NewsAdapter newsAdapter;
    private TextView tvTitle;
    private List<JHNews.JHNewsData> newsDatas;

    public JHTabDetailPage(Activity activity, JHNews.ResultData tabData) {
        super(activity);
        this.tabData = tabData;
    }

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.page_tab_deatil, null);
        ViewUtils.inject(this, view);


        lvTab.setOnRefreshListener(new RefreshListView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                lvTab.onRefreshComplete(true);
            }

            @Override
            public void loadMore() {
                if (moreUrl != null) {

                } else {
                    lvTab.onRefreshComplete(true);
                    Toast.makeText(mActivity, "没有更个多数据了", Toast.LENGTH_SHORT).show();
                }
            }
        });


        lvTab.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tvTitle = (TextView) view.findViewById(R.id.tv_title);
                tvTitle.setTextColor(Color.GRAY);
                Intent intent = new Intent(mActivity, JHNewsDetailActivity.class);
                System.out.println(newsDatas.get(position).url);
                intent.putExtra("url", newsDatas.get(position).url);
                mActivity.startActivity(intent);
            }
        });


        return view;
    }

    @Override
    public void initData() {
//        String catchData = CacheUtil.getCache(mUrl, mActivity);
//        if (!TextUtils.isEmpty(catchData)) {
//
//            getDataFromServer();
//        }


        newsDatas = tabData.data;
        getDataFromServer();


    }

    public void getDataFromServer() {


        processResult();

    }

    private void processResult() {

//        ArrayList<NewsData.News> news = data.data.newses;
//        mNews.addAll(news);

//        newsAdapter.notifyDataSetChanged();

        lvTab.setAdapter(new NewsAdapter());
    }


    class NewsAdapter extends BaseAdapter {
        public BitmapUtils bitmapUtils;

        public NewsAdapter() {
            bitmapUtils = new BitmapUtils(mActivity);
            bitmapUtils.configDefaultLoadingImage(R.mipmap.pic_item_list_default);
        }

        @Override
        public int getCount() {
            return newsDatas.size();
        }

        @Override
        public JHNews.JHNewsData getItem(int position) {
            return newsDatas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = View.inflate(mActivity, R.layout.list_item_news, null);
                viewHolder.ivIcon = (ImageView) convertView.findViewById(R.id.iv_icon);
                viewHolder.tvDate = (TextView) convertView.findViewById(R.id.tv_date);
                viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            JHNews.JHNewsData news = getItem(position);
            viewHolder.tvDate.setText(news.date);
            viewHolder.tvTitle.setText(news.title);
            bitmapUtils.display(viewHolder.ivIcon, news.thumbnail_pic_s);
            return convertView;
        }
    }


    static class ViewHolder {
        public TextView tvTitle;
        public TextView tvDate;
        public ImageView ivIcon;
    }
}

package com.cfxy.compusnewsapp.base.impl.menudetail;

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
public class TabDetailPage extends BaseMenuDetailPage {

    private NewsMenuData.NewsTabData tabData;
    private String moreUrl;

    @ViewInject(R.id.vp_tab)
    private HorizontalScrollViewPager vpTab;
    @ViewInject(R.id.lv_tab)
    private RefreshListView lvTab;
    @ViewInject(R.id.ci_indicator)
    private CirclePageIndicator ciIndicator;
    @ViewInject(R.id.tv_tab_title)
    private TextView tvTabTitle;

    private String mUrl;
    public List<NewsData.TopNews> mTopNews;
    private NewsData data;
    private List<NewsData.News> mNews;
    private NewsAdapter newsAdapter;
    private TopPageDetailAdapter topPageDetailAdapter;
    private TextView tvTitle;

    public TabDetailPage(Activity activity, NewsMenuData.NewsTabData tabData) {
        super(activity);
        this.tabData = tabData;
        mUrl = Constants.SERVER_URL + "/getNews.action?id=" + tabData.id;
    }

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.page_tab_deatil, null);
        View headView = View.inflate(mActivity, R.layout.list_header_topnews, null);
        ViewUtils.inject(this, view);
        ViewUtils.inject(this, headView);
        lvTab.addHeaderView(headView);
        lvTab.setOnRefreshListener(new RefreshListView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDataFromServer();
            }

            @Override
            public void loadMore() {
                if (moreUrl != null) {
                    getMoreDataFromServer();
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
                Intent intent = new Intent(mActivity, NewsDetailActivity.class);
                intent.putExtra("url", mNews.get(position).url);
                mActivity.startActivity(intent);
            }
        });


        return view;
    }

    @Override
    public void initData() {
        String catchData = CacheUtil.getCache(mUrl, mActivity);
        if (!TextUtils.isEmpty(catchData)) {

            getDataFromServer();
        }

        getDataFromServer();


    }

    public void getDataFromServer() {
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, mUrl, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                CacheUtil.setCache(mUrl, result, mActivity);
                processResult(result, false);
                lvTab.onRefreshComplete(true);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                lvTab.onRefreshComplete(false);
                Toast.makeText(mActivity, s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void processResult(String result, boolean isLoadMore) {
        Gson gson = new Gson();
        data = gson.fromJson(result, NewsData.class);
        mTopNews = data.data.topnewses;


        if (!TextUtils.isEmpty(data.data.more)) {
            moreUrl = Constants.SERVER_URL + data.data.more;
        } else {
            moreUrl = null;
        }
        if (!isLoadMore) {
            mNews = data.data.newses;
            if (mTopNews != null) {
                topPageDetailAdapter = new TopPageDetailAdapter();
                vpTab.setAdapter(topPageDetailAdapter);
                ciIndicator.setViewPager(vpTab);
                ciIndicator.setSnap(true);
                if (vpTab.getCurrentItem() != 0) {

                    ciIndicator.onPageSelected(0);
                }
                ciIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    }

                    @Override
                    public void onPageSelected(int position) {
                        setTitle(position);

                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
            }


            if (mNews != null) {
                newsAdapter = new NewsAdapter();
                lvTab.setAdapter(newsAdapter);
            }
        } else {
            ArrayList<NewsData.News> news = data.data.newses;
            mNews.addAll(news);
            newsAdapter.notifyDataSetChanged();
        }


    }


    private void getMoreDataFromServer() {
        HttpUtils utils = new HttpUtils();
        utils.send(HttpRequest.HttpMethod.GET, moreUrl, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String moreResult = responseInfo.result;
                processResult(moreResult, true);
                // 收起加载更多布局
                lvTab.onRefreshComplete(true);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                e.printStackTrace();
                Toast.makeText(mActivity, s, Toast.LENGTH_SHORT).show();
                // 收起加载更多布局
                lvTab.onRefreshComplete(false);
            }
        });
    }

    class NewsAdapter extends BaseAdapter {
        public BitmapUtils bitmapUtils;

        public NewsAdapter() {
            bitmapUtils = new BitmapUtils(mActivity);
            bitmapUtils.configDefaultLoadingImage(R.mipmap.pic_item_list_default);
        }

        @Override
        public int getCount() {
            return mNews.size();
        }

        @Override
        public NewsData.News getItem(int position) {
            return mNews.get(position);
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

            NewsData.News news = getItem(position);
            viewHolder.tvDate.setText(news.pubdate);
            viewHolder.tvTitle.setText(news.title);
            bitmapUtils.display(viewHolder.ivIcon, Constants.SERVER_URL + news.listimage);
            return convertView;
        }
    }

    private void setTitle(int position) {
        tvTabTitle.setText(mTopNews.get(position).title);
    }

    class TopPageDetailAdapter extends PagerAdapter {

        private BitmapUtils mBitmapUtils;

        public TopPageDetailAdapter() {
            mBitmapUtils = new BitmapUtils(mActivity);
            mBitmapUtils.configDefaultLoadingImage(R.mipmap.topnews_item_default);
        }

        @Override
        public int getCount() {
            return mTopNews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(mActivity);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            mBitmapUtils.display(imageView, Constants.SERVER_URL + mTopNews.get(position).topimage);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    static class ViewHolder {
        public TextView tvTitle;
        public TextView tvDate;
        public ImageView ivIcon;
    }
}

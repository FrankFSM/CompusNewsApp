package com.cfxy.compusnewsapp.base.impl.jhdetail;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cfxy.compusnewsapp.R;
import com.cfxy.compusnewsapp.global.Constants;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class JHNewsDetailActivity extends Activity implements View.OnClickListener {

    @ViewInject(R.id.tv_title)
    private TextView tvTitle;
    @ViewInject(R.id.btn_menu)
    private ImageButton btnMenu;
    @ViewInject(R.id.btn_back)
    private ImageButton btnBack;
    @ViewInject(R.id.ll_controller)
    private LinearLayout llController;
    @ViewInject(R.id.btn_textsize)
    private ImageButton btnTextSize;
    //    @ViewInject(R.id.btn_share)
//    private ImageButton btnShare;
    @ViewInject(R.id.wv_webview)
    private WebView webView;
    @ViewInject(R.id.pb_loading)
    private ProgressBar pbLoading;
    private String url;
    private int mChooseItem;
    private int mSelectItem = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        url = getIntent().getStringExtra("url");
        ViewUtils.inject(this);
        initView();
        initListener();
    }


    public void initView() {
        btnMenu.setVisibility(View.GONE);
        btnBack.setVisibility(View.VISIBLE);
        tvTitle.setVisibility(View.GONE);
        llController.setVisibility(View.VISIBLE);
        WebSettings setting = webView.getSettings();
        setting.setBuiltInZoomControls(true);
        setting.setUseWideViewPort(true);
        setting.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                pbLoading.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                pbLoading.setVisibility(View.GONE);
            }
        });

        webView.loadUrl(url);
    }

    public void initListener() {
        btnBack.setOnClickListener(this);
        btnTextSize.setOnClickListener(this);
        // btnShare.setOnClickListener(this);
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_textsize:
                System.out.println("123");
                showChooseDialog();
                break;
//            case R.id.btn_share:
//                finish();
//                break;
            default:
                break;
        }
    }

    private void showChooseDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("字体设置");
        String[] items = new String[]{
                "超大号字体",
                "大号字体",
                "正常字体",
                "小号字体",
                "超小号字体"
        };
        builder.setSingleChoiceItems(items, mSelectItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mChooseItem = which;
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                WebSettings webSettings = webView.getSettings();
                switch (mChooseItem) {
                    case 0:
                        webSettings.setTextSize(WebSettings.TextSize.LARGEST);
                        break;
                    case 1:
                        webSettings.setTextSize(WebSettings.TextSize.LARGER);
                        break;
                    case 2:
                        webSettings.setTextSize(WebSettings.TextSize.NORMAL);
                        break;
                    case 3:
                        webSettings.setTextSize(WebSettings.TextSize.SMALLER);
                        break;
                    case 4:
                        webSettings.setTextSize(WebSettings.TextSize.SMALLEST);
                        break;
                    default:
                        break;
                }

            }
        });

        builder.setNegativeButton("取消", null);
        builder.show();
    }


}

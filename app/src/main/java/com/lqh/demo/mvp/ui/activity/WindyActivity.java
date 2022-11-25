package com.lqh.demo.mvp.ui.activity;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.lqh.base.activity.BaseActivity;
import com.lqh.demo.R;
import com.tencent.smtt.export.external.interfaces.IX5WebSettings;
import com.tencent.smtt.export.external.interfaces.SslError;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

public class WindyActivity extends BaseActivity {

    private WebView mWebView;
    private ProgressBar progressbar;

    @Override
    public int getLayoutId() {
        return R.layout.activity_windy;
    }

    @Override
    public void setupActivityComponent() {

    }

    @Override
    public void initView() {
        mWebView = (WebView) findViewById(R.id.news_webview);
        findView(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void initData() {
        //开启js脚本支持
        mWebView.getSettings().setJavaScriptEnabled(true);
        //创建进度条
        progressbar = new ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal);
        //设置加载进度条的高度
        progressbar.setLayoutParams(new AbsoluteLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, 10, 0, 0));
        Drawable drawable = getResources().getDrawable(R.drawable.sp_theme_color_line);
        progressbar.setProgressDrawable(drawable);
        //添加进度到WebView
        mWebView.addView(progressbar);
        //适配手机大小
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setSupportZoom(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.getSettings().setDisplayZoomControls(false);
        mWebView.getSettings().setCacheMode(IX5WebSettings.LOAD_NO_CACHE);
        mWebView.setWebChromeClient(new WVChromeClient());
        mWebView.setWebViewClient(new WClient());
        mWebView.loadUrl("https://embed.windy.com/embed2.html?lat=22.553&lon=114.038&detailLat=22.556&detailLon=114.058&width=1080&height=1920&zoom=5&level=surface&overlay=wind&product=ecmwf&menu=&message=&marker=&calendar=now&pressure=&type=map&location=coordinates&detail=&metricWind=default&metricTemp=default&radarRange=-1");
    }

    //进度显示
    private class WVChromeClient extends WebChromeClient {

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                progressbar.setVisibility(View.GONE);
            } else {
                if (progressbar.getVisibility() == View.GONE)
                    progressbar.setVisibility(View.VISIBLE);
                progressbar.setProgress(newProgress);
            }
            if (mListener != null) {
                mListener.onProgressChange(view, newProgress);
            }
            super.onProgressChanged(view, newProgress);
        }
    }

    private class WClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            //在当前Activity打开
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            //https忽略证书问题
            handler.proceed();
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            progressbar.setVisibility(View.GONE);
            if (mListener != null) {
                mListener.onPageFinish(view);
            }
            super.onPageFinished(view, url);
        }
    }

    private onWebViewListener mListener;

    public void setOnWebViewListener(onWebViewListener listener) {
        this.mListener = listener;
    }

    //进度回调接口
    public interface onWebViewListener {

        void onProgressChange(WebView view, int newProgress);

        void onPageFinish(WebView view);

    }

}

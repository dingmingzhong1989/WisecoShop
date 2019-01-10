package com.wiseco.wisecoshop.activity;

import android.os.Build;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.wiseco.wisecoshop.R;
import com.wiseco.wisecoshop.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wiseco on 2018/11/1.
 */

public class AgreementActivity extends BaseActivity {
    @Bind(R.id.back)
    ImageButton back;
    @Bind(R.id.agreement_web)
    WebView agreementWeb;
    @Bind(R.id.progress_bar_agreement)
    ProgressBar progressBarAgreement;

    @Override
    protected void initViews() {

        setContentView(R.layout.activity_agreement);
        ButterKnife.bind(this);
        initwebView();

        progressBarAgreement.setMax(100);

        agreementWeb.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    progressBarAgreement.setVisibility(View.GONE);
                } else {
                    progressBarAgreement.setVisibility(View.VISIBLE);
                    progressBarAgreement.setProgress(newProgress);
                }
            }
        });


    }
    @Override
    protected void postAgain() {

    }
    @Override
    public boolean getStatusBarColor() {
        return true;
    }

    @Override
    protected void loadData() {
        if (getIntent().getIntExtra("taghtml", 0) == 1) {
            agreementWeb.loadUrl("https://m.wisecofincloud.com/MessageOne");


        } else if (getIntent().getIntExtra("taghtml", 0) == 2) {
            agreementWeb.loadUrl("https://m.wisecofincloud.com/MessageTwo");
        } else if (getIntent().getIntExtra("taghtml", 0) == 3) {
            agreementWeb.loadUrl("https://m.wisecofincloud.com/MessageThree");
        }else if (getIntent().getIntExtra("taghtml", 0) == 4) {
            agreementWeb.loadUrl("https://m.wisecofincloud.com/abount");
        }



    }

    @Override
    public void initListener() {

    }





    @OnClick(R.id.back)
    public void onViewClicked() {

        finish();
    }

   /* @Override
    public void onBackPressed() {
        if (agreementWeb.canGoBack()) { //返回上一个页
            agreementWeb.goBack();
            return;
        }
        super.onBackPressed();
    }*/

    private void initwebView() {
        /**设置可以访问文件*/
        agreementWeb.getSettings().setAllowFileAccess(false);
        /**如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript*/
        agreementWeb.getSettings().setJavaScriptEnabled(true);

        /**设置适应Html5的一些方法*/
        agreementWeb.getSettings().setDomStorageEnabled(true);
        /**控制大小--设置缓存最大容量，默认为Max Integer*/
        agreementWeb.getSettings().setAppCacheMaxSize(1024 * 1024 * 8);// 实现8倍缓存
        /**支持通过JS打开新窗口*/
        agreementWeb.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        /**H5缓存*/
        agreementWeb.getSettings().setAppCacheEnabled(true);
        String appCachePath = getApplication().getCacheDir().getAbsolutePath();
        agreementWeb.getSettings().setAppCachePath(appCachePath);
        agreementWeb.getSettings().setDatabaseEnabled(true);
        /**缩放操作*/
        agreementWeb.setInitialScale(100);// 初始显示比例100%
        agreementWeb.getSettings().setSupportZoom(true);//支持缩放，默认为true。是下面那个的前提
        agreementWeb.getSettings().setBuiltInZoomControls(true);//设置内置的缩放控件。若为false，则该WebView不可缩放
        /**设置自适应屏幕，两者合用*/
        agreementWeb.getSettings().setUseWideViewPort(true);//将图片调整到适合webview的大小--关键点
        agreementWeb.getSettings().setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
            agreementWeb.getSettings().setPluginState(WebSettings.PluginState.ON);
        } else {
            // IMPORTANT!! this method is no longer available since Android 4.3
            // so the code doesn't compile anymore
            // webView.getSettings().setPluginsEnabled(true);
        }
        if (Build.VERSION.SDK_INT >= 11) {
            /**缩放操作*/
            agreementWeb.getSettings().setDisplayZoomControls(false);//隐藏原生的缩放控件
        }
        /**
         * 启用mixed content    android 5.0以上默认不支持Mixed Content
         *
         * 5.0以上允许加载http和https混合的页面(5.0以下默认允许，5.0+默认禁止)
         * */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            agreementWeb.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        agreementWeb.setWebViewClient(new WebViewClient() {
            //覆盖shouldOverrideUrlLoading 方法
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        // webView.setWebViewClient(new CustomWebViewClient());

       /* webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onShowCustomView(View arg0, CustomViewCallback arg1) {
                super.onShowCustomView(arg0, arg1);
            }

            @Override
            public void onShowCustomView(View arg0, int arg1,
                                         CustomViewCallback arg2) {
                super.onShowCustomView(arg0, arg1, arg2);
            }

            @Override
            public void onProgressChanged(WebView view, int progress) {

            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
            }

            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {
            }

            // For Android 3.0+
            public void openFileChooser(ValueCallback<Uri> uploadMsg) {
                openFileChooserImpl(uploadMsg);
            }

            // For Android 3.0+
            public void openFileChooser(ValueCallback uploadMsg,
                                        String acceptType) {
                openFileChooserImpl(uploadMsg);
            }

            // For Android 4.1
            public void openFileChooser(ValueCallback<Uri> uploadMsg,
                                        String acceptType, String capture) {
                openFileChooserImpl(uploadMsg);
            }

            @Override
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
                openFileChooserImplForAndroid5(filePathCallback);
                return true;
            }

        });*/

        agreementWeb.requestFocus();
    }
}

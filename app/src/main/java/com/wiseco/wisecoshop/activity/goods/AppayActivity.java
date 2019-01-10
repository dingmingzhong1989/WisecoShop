package com.wiseco.wisecoshop.activity.goods;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.wiseco.wisecoshop.R;
import com.wiseco.wisecoshop.base.BaseActivity;
import com.wiseco.wisecoshop.bean.goods.ApplyBean;
import com.wiseco.wisecoshop.okhttp.CallBackUtil;
import com.wiseco.wisecoshop.okhttp.OkhttpUtil;
import com.wiseco.wisecoshop.utils.ToastUtils;
import com.wiseco.wisecoshop.view.LoadingView;

import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

import static com.wiseco.wisecoshop.MyApplication.gson;
import static com.wiseco.wisecoshop.MyApplication.i;
import static com.wiseco.wisecoshop.utils.HttpPostUtils.getParamsMap;
import static com.wiseco.wisecoshop.utils.UrlUtil.GOOSDSAPPLY;

/**
 * Created by wiseco on 201/12/27.
 */

public class AppayActivity extends BaseActivity implements LoadingView.OnRetryListener, LoadingView.OnErroyClickListener{

    @Bind(R.id.back)
    ImageButton back;
    @Bind(R.id.bar)
    RelativeLayout bar;
    @Bind(R.id.web)
    WebView webView;
    @Bind(R.id.mLoadingView)
    LoadingView mLoadingView;
    private String productcode;
    private int tag;
    @Override
    public boolean getStatusBarColor() {
        return true;
    }

    @Override
    protected void loadData() {
        getData();

    }
    @Override
    protected void postAgain() {
        getData();
    }
    private void getData() {

        i=4;
        Map<String, String> paramsMap = getParamsMap();
        paramsMap.put("productcode", productcode);
        OkhttpUtil.okHttpPost(GOOSDSAPPLY, paramsMap, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                ToastUtils.showToast("数据加载失败");
                mLoadingView.notifyDataChanged(LoadingView.State.error);
            }

            @Override
            public void onResponse(String response) {

                try {
                    Log.d("TAG", "Moneyvity====" + response);
                    ApplyBean applyBean = gson.fromJson(response, ApplyBean.class);
                    if (applyBean.getCode().equals("S")) {
                        mLoadingView.notifyDataChanged(LoadingView.State.done);
                        webView.loadUrl(applyBean.getApplyurl());
                    } else {

                        mLoadingView.notifyDataChanged(LoadingView.State.error);
                    }
                } catch (Exception e) {

                    mLoadingView.notifyDataChanged(LoadingView.State.error);
                }


            }
        });
    }
    @Override
    public void initListener() {

    }


    @OnClick(R.id.back)
    public void onViewClicked(View view) {

        finish();
    }

    private void initwebView() {


        webView.getSettings().setAllowUniversalAccessFromFileURLs(true);
        /**设置可以访问文件*/
        webView.getSettings().setAllowFileAccess(false);
        /**如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript*/
        webView.getSettings().setJavaScriptEnabled(true);

        /**设置适应Html5的一些方法*/
        webView.getSettings().setDomStorageEnabled(true);
        /**控制大小--设置缓存最大容量，默认为Max Integer*/
        webView.getSettings().setAppCacheMaxSize(1024 * 1024 * 8);// 实现8倍缓存
        /**支持通过JS打开新窗口*/
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(false);
        /**H5缓存*/
        webView.getSettings().setAppCacheEnabled(true);
        String appCachePath = getApplication().getCacheDir().getAbsolutePath();
        webView.getSettings().setAppCachePath(appCachePath);
        webView.getSettings().setDatabaseEnabled(true);
        /**缩放操作*/
        webView.setInitialScale(150);// 初始显示比例100%
        webView.getSettings().setSupportZoom(true);//支持缩放，默认为true。是下面那个的前提
        webView.getSettings().setBuiltInZoomControls(true);//设置内置的缩放控件。若为false，则该WebView不可缩放
        /**设置自适应屏幕，两者合用*/
        webView.getSettings().setUseWideViewPort(true);//将图片调整到适合webview的大小--关键点
        webView.getSettings().setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
            webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        } else {
            // IMPORTANT!! this method is no longer available since Android 4.3
            // so the code doesn't compile anymore
            // webView.getSettings().setPluginsEnabled(true);
        }
        if (Build.VERSION.SDK_INT >= 11) {
            /**缩放操作*/
            webView.getSettings().setDisplayZoomControls(false);//隐藏原生的缩放控件
        }
        /**
         * 启用mixed content    android 5.0以上默认不支持Mixed Content
         *
         * 5.0以上允许加载http和https混合的页面(5.0以下默认允许，5.0+默认禁止)
         * */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webView.setWebViewClient(new WebViewClient() {
            //覆盖shouldOverrideUrlLoading 方法
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        // webView.setWebViewClient(new CustomWebViewClient());

        webView.setWebChromeClient(new WebChromeClient() {
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
                Log.d("TAG",title);
            }

            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {
            }

            // For Android 3.0+
            public void openFileChooser(ValueCallback<Uri> uploadMsg) {
                //openFileChooserImpl(uploadMsg);
            }

            // For Android 3.0+
            public void openFileChooser(ValueCallback uploadMsg,
                                        String acceptType) {
                //openFileChooserImpl(uploadMsg);
            }

            // For Android 4.1
            public void openFileChooser(ValueCallback<Uri> uploadMsg,
                                        String acceptType, String capture) {
                //openFileChooserImpl(uploadMsg);
            }

            @Override
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
                // openFileChooserImplForAndroid5(filePathCallback);
                return true;
            }

        });

        webView.requestFocus();
    }

    @Override
    public void onRetry() {
        mLoadingView.notifyDataChanged(LoadingView.State.ing);
    }

    @Override
    public void erroyClick() {
        getData();
    }
    @Override
    protected void initViews() {
        setContentView(R.layout.actvity_appay);

        ButterKnife.bind(this);
        // back.setVisibility(View.INVISIBLE);
        productcode = getIntent().getExtras().getString("productcode");
        tag = getIntent().getExtras().getInt("tag");

        //HttpPostUtils.putEventTag(getUserId(), "", productcode, "CC", CONTENT_EVENT_PRODUCT_DETAIL, COMMENT_EVENT_PRODUCT_DETAIL);
        // ToastUtils.showToast("数据加载中.....");
        mLoadingView.setOnRetryListener(this);
        mLoadingView.notifyDataChanged(LoadingView.State.ing);
        mLoadingView.setOnErroyClickListener(this);
        initwebView();

    }
}

package com.wiseco.wisecoshop;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tamic.statInterface.statsdk.core.TcStatInterface;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;
import com.wiseco.wisecoshop.utils.CacheUtil;

/**
 * Created by wiseco on 2018/10/27.
 */

public class MyApplication extends Application {
    public static Context sContext;
    public static Gson gson;
    public static int i = 0;
    // 是否应用第一次打开的key
    public static String IS_APP_FIRST_OPEN = "IS_APP_FIRST_OPEN";
    // 是否注册过
    public static String IS_USER_REGIEST = "IS_USER_REGIEST";
    // 是否实名认证
    public static String IS_TRUE_NAME = "IS_TRUE_NAME";


    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
        gson = new GsonBuilder().create();
       // AutoLayoutConifg.getInstance().useDeviceSize();

        UMConfigure.init(this,"5c1a1ce7f1f5562579000233"
                ,"umeng",UMConfigure.DEVICE_TYPE_PHONE,"");


        String fileName = "stat_id.json";
        String url = "http://www.baidu.com";
        // init statSdk


        TcStatInterface.initialize(this, CacheUtil.getString(sContext, "USERID", ""), fileName);
        //TcStatInterface.setUrl(BASE);
        TcStatInterface.setUploadPolicy(TcStatInterface.UploadPolicy.UPLOAD_POLICY_INTERVA, TcStatInterface.UPLOAD_TIME_ONE);

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (newConfig.fontScale != 1) {
            getResources();
        }
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        if (res.getConfiguration().fontScale != 1) {//非默认值
            Configuration newConfig = new Configuration();
            newConfig.setToDefaults();//设置默认
            res.updateConfiguration(newConfig, res.getDisplayMetrics());
        }
        return res;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //MultiDex.install(base);
    }

    {
        PlatformConfig.setWeixin("wx6cf3d4028bfbbaac", "41f22961247710ecf563a0f4fa5f892b");

    }
}
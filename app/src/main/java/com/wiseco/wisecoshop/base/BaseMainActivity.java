package com.wiseco.wisecoshop.base;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.view.WindowManager;

import com.tamic.statInterface.statsdk.core.TcStatInterface;
import com.zhy.autolayout.AutoLayoutActivity;

import java.lang.reflect.Field;

/**
 * Created by wiseco on 2018/12/3.
 */

public abstract class BaseMainActivity extends AutoLayoutActivity  {


    private CountTimer countTimerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBarinit();
        initViews();
        initListener();
        loadData();
        initStatusColor();
        countTimerView = new CountTimer(1800*1000, 1000, this);
        countTimerView.start();
    }

    protected  void ActionBarinit(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            try {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                Class decorViewClazz = Class.forName("com.android.internal.policy.DecorView");
                Field field = decorViewClazz.getDeclaredField("mSemiTransparentStatusBarColor");
                field.setAccessible(true);
                field.setInt(getWindow().getDecorView(), Color.TRANSPARENT);  //改为透明
            } catch (Exception e) {}
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


    }


    public class CountTimer extends CountDownTimer {
        private Context context;

        /**
         * 参数 millisInFuture       倒计时总时间（如60S，120s等）
         * 参数 countDownInterval    渐变时间（每次倒计1s）
         */
        public CountTimer(long millisInFuture, long countDownInterval, Context context) {
            super(millisInFuture, countDownInterval);
            this.context = context;
        }

        // 计时完毕时触发
        @Override
        public void onFinish() {
           postAgain();
        }

        // 计时过程显示
        @Override
        public void onTick(long millisUntilFinished) {

        }
    }
    private void initStatusColor() {
        changeStatusBarTextColor(getStatusBarColor());
    }

    public abstract boolean getStatusBarColor();

    protected abstract void loadData();
    protected abstract void postAgain();
    protected abstract void initListener();


    protected abstract void initViews();

    public void open(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

    public void open(Class<?> cls, int code) {
        Intent intent = new Intent(this, cls);
        startActivityForResult(intent, code);
    }

    public void open(Class<?> cls, Bundle b) {
        Intent intent = new Intent(this, cls);
        intent.putExtras(b);
        startActivity(intent);
    }

    public void open(Class<?> cls, Bundle b, int code) {
        Intent intent = new Intent(this, cls);
        intent.putExtras(b);
        startActivityForResult(intent, code);
    }
    private void changeStatusBarTextColor(boolean isBlack) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            if (isBlack) {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//设置状态栏黑色字体


            } else {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);//恢复状态栏白色字体
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        TcStatInterface.recordPageStart(BaseMainActivity.this);

    }

    @Override
    protected void onPause() {
        super.onPause();
        TcStatInterface.recordPageEnd();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // APP退出
       // TcStatInterface.recordAppEnd();
        countTimerView.cancel();
    }


}

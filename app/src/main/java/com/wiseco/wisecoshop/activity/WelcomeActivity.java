package com.wiseco.wisecoshop.activity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.wiseco.wisecoshop.R;
import com.wiseco.wisecoshop.adapter.VpAdapter;
import com.wiseco.wisecoshop.base.BaseMainActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wiseco on 2018/10/9.
 */

public class WelcomeActivity extends BaseMainActivity {
    @Bind(R.id.present_bv)
    ViewPager mBannerView;
    @Bind(R.id.jump)ImageButton jump;
    @Bind(R.id.jumptime)
    TextView jumptime;

    //设置图片加载集合

    private List<String> images;

    //存放图片的id
    private int[] imageIds = new int[]{
            R.drawable.welcome_01,
            R.drawable.welcome_02,
            R.drawable.welcome_03
    };
//
    private static final int MY_PERMISSION_REQUEST_CODE = 10000;
    private String mCurrentVersion;
    private ArrayList<ImageView> imageViews;

    @Override
    protected void postAgain() {

    }
    @Override
    protected void initViews() {
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        ViewPager.LayoutParams mParams = new ViewPager.LayoutParams();
        imageViews = new ArrayList<ImageView>();
        for(int i=0; i<imageIds.length; i++)
        {
            ImageView iv = new ImageView(this);
            iv.setLayoutParams(mParams);//设置布局
            iv.setImageResource(imageIds[i]);//为ImageView添加图片资源
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);//这里也是一个图片的适配


            imageViews.add(iv);
            if (i == imageIds.length -1 ){
                //为最后一张图片添加点击事件
                iv.setOnTouchListener(new View.OnTouchListener(){
                    @Override
                    public boolean onTouch(View v, MotionEvent event){
                       open(MainActivity.class);
                        finish();
                        return true;

                    }
                });
            }

        }

        mBannerView.setAdapter(new VpAdapter(imageViews));



    }

    @Override
    public boolean getStatusBarColor() {
        return true;
    }

    @Override
    protected void loadData() {
        /**
         * 第 1 步: 检查是否有相应的权限
         */
        boolean isAllGranted = checkPermissionAllGranted(
                new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.ACCESS_WIFI_STATE,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.READ_PHONE_STATE
                });


        if (isAllGranted) {
            //countTimerView.start();
            return;
        }
        //第 2 步: 请求权限

        // 一次请求多个权限, 如果其他有权限是已经授予的将会自动忽略掉
        ActivityCompat.requestPermissions(this,
                new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE,

                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                        , Manifest.permission.ACCESS_WIFI_STATE,
                        Manifest.permission.READ_PHONE_STATE
        },
                MY_PERMISSION_REQUEST_CODE);


    }

    /**
     * 第 3 步: 申请权限结果返回处理
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSION_REQUEST_CODE) {
            boolean isAllGranted = true; // 判断是否所有的权限都已经授予了
            for (int grant : grantResults) {
                if (grant != PackageManager.PERMISSION_GRANTED) {
                    isAllGranted = false;
                    break;
                }
            }
            if (isAllGranted) {
               //  countTimerView.start();

                return;
            } else {
                // 弹出对话框告诉用户需要权限的原因, 并引导用户去应用权限管理中手动打开权限按钮
                openAppDetails();
            }
        }
    }

    /**
     * 检查是否拥有指定的所有权限
     */
    private boolean checkPermissionAllGranted(String[] permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                // 只要有一个权限没有被授予, 则直接返回 false
                return false;
            }
        }
        return true;
    }

    /**
     * 打开 APP 的详情设置
     */
    private void openAppDetails() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("请到 “应用信息 -> 权限” 中授予！");
        builder.setPositiveButton("去手动授权", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.setData(Uri.parse("package:" + getPackageName()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("取消", null);
        builder.show();
    }

    @Override
    public void initListener() {

    }

    @OnClick(R.id.jump)
    public void onViewClicked() {
        //isFirstOpen();
        open(MainActivity.class);
        finish();
        //countTimerView.cancel();

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
            open(MainActivity.class);
            finish();
        }

        // 计时过程显示
        @Override
        public void onTick(long millisUntilFinished) {
            jumptime.setText(millisUntilFinished / 1000 + "");
        }
    }





    /**
     * 升级系统
     *
     * @param content
     */
    private void ShowDialog(View content) {
//
        new android.app.AlertDialog.Builder(this)
                .setTitle("版本更新")
                .setView(content)
                .setIcon(R.drawable.logoicon)
                .setPositiveButton("更新", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                        /**/
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        //countTimerView.start();
                    }
                })
                .show();
    }

    //禁止用返回键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }






}

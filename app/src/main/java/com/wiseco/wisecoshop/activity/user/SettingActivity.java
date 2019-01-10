package com.wiseco.wisecoshop.activity.user;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wiseco.wisecoshop.R;
import com.wiseco.wisecoshop.activity.dixconuts.SearchActivity;
import com.wiseco.wisecoshop.base.BaseActivity;
import com.wiseco.wisecoshop.dialog.CallDialog;
import com.wiseco.wisecoshop.dialog.LogoutDialog;
import com.wiseco.wisecoshop.utils.AppUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.wiseco.wisecoshop.utils.UtilsOther.isRegist;

/**
 * Created by wiseco on 2018/12/19.
 */

public class SettingActivity extends BaseActivity {
    @Bind(R.id.back)
    ImageButton back;
    @Bind(R.id.bar_tittle)
    TextView barTittle;
    @Bind(R.id.setting_change_password)
    LinearLayout settingChangePassword;
    @Bind(R.id.setting_aboutus)
    LinearLayout settingAboutus;
    @Bind(R.id.setting_vission)
    LinearLayout settingVission;
    @Bind(R.id.vission_name)
    TextView vission_name;
    @Bind(R.id.setting_phone)
    TextView settingPhone;
    @Bind(R.id.backout)
    TextView backOut;
    @Bind(R.id.setting_opinion)
    LinearLayout settingOpinion;
    @Bind(R.id.user_content)
    LinearLayout userContent;
    private int MY_PERMISSION_REQUEST_CODE = 1001;

    @Override
    public boolean getStatusBarColor() {
        return true;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initListener() {

    }
    @Override
    protected void postAgain() {

    }
    @Override
    protected void initViews() {
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);

        back.setVisibility(View.VISIBLE);
        barTittle.setVisibility(View.VISIBLE);
        barTittle.setText("设置");
        vission_name.setText("V"+AppUtil.getVersionName(this));

        if (isRegist()) {
            backOut.setVisibility(View.VISIBLE);

        } else {
            backOut.setVisibility(View.INVISIBLE);
        }
    }


    @OnClick({R.id.back, R.id.setting_change_password, R.id.setting_aboutus, R.id.setting_phone, R.id.setting_opinion, R.id.backout,R.id.setting_vission})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.setting_change_password:
                open(ChangePasswordActivity.class);
                break;
            case R.id.setting_aboutus:

                open(AboutUsActivity.class);

                break;
            case R.id.setting_phone:
                /**
                 * 第 1 步: 检查是否有相应的权限
                 */
                boolean isAllGranted = checkPermissionAllGranted(
                        new String[]{
                                Manifest.permission.CALL_PHONE
                        });


                if (isAllGranted) {
                    String trim = settingPhone.getText().toString().trim();
                    new CallDialog(this, trim).show();
                    return;
                }
                //第 2 步: 请求权限

                // 一次请求多个权限, 如果其他有权限是已经授予的将会自动忽略掉
                ActivityCompat.requestPermissions(this,
                        new String[]{
                                Manifest.permission.CALL_PHONE},
                        MY_PERMISSION_REQUEST_CODE);


                break;
            case R.id.setting_opinion:
                open(OpinionActivity.class);
                break;

            case R.id.backout:
                new LogoutDialog(this).show();
                break;
            case  R.id.setting_vission:
               open(SearchActivity.class);
                break;

        }
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
                String trim = settingPhone.getText().toString().trim();
                new CallDialog(this, trim).show();

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
}

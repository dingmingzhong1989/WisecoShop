package com.wiseco.wisecoshop.dialog;

import android.app.Activity;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.wiseco.wisecoshop.R;
import com.wiseco.wisecoshop.utils.CacheUtil;
import com.wiseco.wisecoshop.utils.ToastUtils;

import static com.wiseco.wisecoshop.MyApplication.IS_TRUE_NAME;
import static com.wiseco.wisecoshop.MyApplication.IS_USER_REGIEST;
import static com.wiseco.wisecoshop.MyApplication.sContext;

/**
 * Created by wiseco on 2018/12/25.
 */

public class LogoutDialog implements View.OnClickListener {
    private DialogView mDialogView;
    private TextView mCancle;
    private TextView mQuit;
    private Activity mSettinsActivity;

    public LogoutDialog(Activity activity) {
        mSettinsActivity = activity;
        init();
    }

    private void init() {
        View view = LayoutInflater.from(mSettinsActivity).inflate(R.layout.dialog_log_out, null);
        mCancle = (TextView) view.findViewById(R.id.cancel_dialog_log_out);
        mCancle.setOnClickListener(this);
        mQuit = (TextView) view.findViewById(R.id.confirm_dialog_log_out);
        mQuit.setOnClickListener(this);
        mDialogView = new DialogView(mSettinsActivity, view);
        mDialogView.setGravity(Gravity.CENTER);
        mDialogView.setCanceledOnTouchOutside(true);
        mDialogView.setCancelable(true);
        mDialogView.setDimBehind(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel_dialog_log_out:
                dismiss();
                break;
            case R.id.confirm_dialog_log_out:
                logout();
        }
    }

    private void logout() {
        dismiss();
        // FileCacheUtil.cleanSharedPreference(sContext);
        CacheUtil.putBoolean(sContext, IS_USER_REGIEST, false);
        CacheUtil.putBoolean(sContext, IS_TRUE_NAME, false);

        CacheUtil.putString(sContext, "COOKIE", "");

        CacheUtil.putString(sContext, "COOKIETOKTN", "");
        CacheUtil.putString(sContext, "userID", "");
       // userBack.setVisibility(View.INVISIBLE);
        //userName.setText("点击登录/注册");

        Intent intent = new Intent();
        intent.setAction("com.wiseco.wisecoshop.Userfragment");

        mSettinsActivity.sendBroadcast(intent);

       Intent intent1 = new Intent();
        intent1.setAction("com.wiseco.wisecoshop.fragment");
        mSettinsActivity.sendBroadcast(intent1);
        ToastUtils.showToast("退出成功");
        // FileCacheUtil.cleanInternalCache(sContext);
        mSettinsActivity.finish();


    }

    public void show() {
        if (mDialogView != null) {
            mDialogView.showDialog();
        }
    }

    public void dismiss() {
        if (mDialogView != null) {
            mDialogView.dismissDialog();
        }
    }


}

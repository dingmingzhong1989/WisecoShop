package com.wiseco.wisecoshop.dialog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.wiseco.wisecoshop.R;
import com.wiseco.wisecoshop.activity.login.LoginActivity_;


/**
 * Created by 丁明忠 on 2018/11/1.
 */

public class LoginDialog implements View.OnClickListener {
    private DialogView mDialogView;
    private TextView mCancle;
    private TextView mQuit;
    private Activity mSettinsActivity;

    public LoginDialog(Activity activity) {
        mSettinsActivity = activity;
        init();
    }

    private void init() {
        View view = LayoutInflater.from(mSettinsActivity).inflate(R.layout.dialog_login, null);
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


            Bundle bundle = new Bundle();
            bundle.putInt("tag", 0);
            open(LoginActivity_.class, bundle);
            // mSettinsActivity.finish();

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

    public void open(Class<?> cls, Bundle b) {
        Intent intent = new Intent(mSettinsActivity, cls);
        intent.putExtras(b);
        mSettinsActivity.startActivity(intent);
    }
}

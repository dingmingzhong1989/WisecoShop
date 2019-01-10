package com.wiseco.wisecoshop.dialog;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.wiseco.wisecoshop.R;


/**
 * Created by 丁明忠 on 2018/11/1.
 */

public class CallDialog implements View.OnClickListener {
    private String phoneNum;
    private DialogView mDialogView;
    private TextView mCancle;
    private TextView mQuit;
    private Activity mSettinsActivity;
    private TextView phone;

    public CallDialog(Activity activity, String trim) {
        mSettinsActivity = activity;
        phoneNum = trim;
        init();
    }

    private void init() {
        View view = LayoutInflater.from(mSettinsActivity).inflate(R.layout.dialog_call, null);
        phone = (TextView) view.findViewById(R.id.phonenum);
        phone.setText(phoneNum);

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

        Intent myIntentDial=new Intent("android.intent.action.CALL", Uri.parse("tel:"+phoneNum));
        mSettinsActivity.startActivity(myIntentDial);

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

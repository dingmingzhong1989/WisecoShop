package com.wiseco.wisecoshop.dialog;

import android.app.Activity;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.wiseco.wisecoshop.R;
import com.wiseco.wisecoshop.activity.MainActivity;

/**
 * Created by wiseco on 2018/11/5.
 */

public class CancelDialog implements View.OnClickListener {

    private DialogView mDialogView;
    private TextView mCancle;
    private TextView mQuit;
    private Activity mSettinsActivity;



    public  CancelDialog(Activity activity) {
        mSettinsActivity = activity;
        init();
    }



    private void init() {
        View view = LayoutInflater.from(mSettinsActivity).inflate(R.layout.dialog_cancel, null);
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
                out();

        }

    }

    private void out() {
        dismiss();
        //ToastUtils.toastInCenter(mSettinsActivity, R.string.settings_toast_log_out_success);

        open(MainActivity.class);
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
    public void open(Class<?> cls) {
        Intent intent = new Intent(mSettinsActivity, cls);
        mSettinsActivity.startActivity(intent);
    }
}

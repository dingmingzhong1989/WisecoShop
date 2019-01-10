package com.wiseco.wisecoshop.dialog;

import android.app.Activity;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wiseco.wisecoshop.R;
import com.wiseco.wisecoshop.activity.MainActivity;

/**
 * Created by wiseco on 2018/11/27.
 */

public class FinishTrueNameDialog implements View.OnClickListener {

    private DialogView mDialogView;

    private Activity mSettinsActivity;
    private TextView mFinish;
    private ImageView mCancle;


    public FinishTrueNameDialog(Activity activity) {
        mSettinsActivity = activity;
        init();
    }


    private void init() {
        View view = LayoutInflater.from(mSettinsActivity).inflate(R.layout.dialog_finish, null);
        mFinish = (TextView) view.findViewById(R.id.finish);
        mFinish.setOnClickListener(this);
        mCancle = (ImageView) view.findViewById(R.id.cancel);
        mCancle.setOnClickListener(this);
        mDialogView = new DialogView(mSettinsActivity, view);
        mDialogView.setGravity(Gravity.CENTER);
        mDialogView.setCanceledOnTouchOutside(true);
        mDialogView.setCancelable(true);
        mDialogView.setDimBehind(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel:
                dismiss();
                break;
            case R.id.finish:
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

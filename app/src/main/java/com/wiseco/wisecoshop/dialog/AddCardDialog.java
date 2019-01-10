package com.wiseco.wisecoshop.dialog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.wiseco.wisecoshop.R;
import com.wiseco.wisecoshop.activity.user.AddCardActivity;
import com.wiseco.wisecoshop.bean.MyCardBean;
import com.wiseco.wisecoshop.bean.user.BankListBean;
import com.wiseco.wisecoshop.bean.user.CardBrandsListBean;
import com.wiseco.wisecoshop.okhttp.CallBackUtil;
import com.wiseco.wisecoshop.okhttp.OkhttpUtil;

import java.io.Serializable;
import java.util.List;

import okhttp3.Call;

import static com.wiseco.wisecoshop.MyApplication.gson;
import static com.wiseco.wisecoshop.MyApplication.i;
import static com.wiseco.wisecoshop.utils.UrlUtil.QUERYCARDLIST;


/**
 * Created by 丁明忠 on 2018/11/1.
 */

public class AddCardDialog implements View.OnClickListener {
    private DialogView mDialogView;
    private TextView mCancle;
    private TextView mQuit;
    private Activity mSettinsActivity;

    public AddCardDialog(Activity activity) {
        mSettinsActivity = activity;
        init();
    }

    private void init() {
        View view = LayoutInflater.from(mSettinsActivity).inflate(R.layout.dialog_addcard, null);
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


        i=4;
        OkhttpUtil.okHttpPost(QUERYCARDLIST, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                Log.d("TAG", response);
                dismiss();
                try {
                    MyCardBean myCardBean = gson.fromJson(response, MyCardBean.class);
                    if (myCardBean.getCode().equals("S")){
                        List<BankListBean> bankList = myCardBean.getBankList();
                        List<CardBrandsListBean> cardBrandsList = myCardBean.getCardBrandsList();
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("bankList", (Serializable) bankList);
                        bundle.putSerializable("cardBrandsList", (Serializable) cardBrandsList);
                        open(AddCardActivity.class,bundle);
                        mSettinsActivity.finish();
                    }else{

                    }
                }catch (Exception e){


                }






            }
        });

           // open(MyCardActivity.class);



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

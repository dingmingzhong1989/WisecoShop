package com.wiseco.wisecoshop.utils;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wiseco.wisecoshop.R;

import static com.wiseco.wisecoshop.MyApplication.sContext;


/**
 * Created by Administrator on 2018/3/15.
 */

public class ToastUtils {


    private static Toast toast;


    private ToastUtils(Context context) {
        sContext = context;
    }

    public static void showToast(String info) {
        if (toast == null) {
            toast = Toast.makeText(sContext, info, Toast.LENGTH_SHORT);


            LinearLayout layout = (LinearLayout) toast.getView();
            toast.setGravity(Gravity.CENTER, 0, 0);
            layout.setBackgroundColor(Color.parseColor("#00000000"));

            TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
            v.setBackground(sContext.getResources().getDrawable(R.drawable.toastshape));
            v.setAlpha(0.8f);
            v.setPadding(50,30 ,50,30);

            v.setTextColor(Color.WHITE);
            v.setTextSize(14);
        } else {
            toast.setText(info);
        }
        toast.show();
    }

    public static void toastInCenter(Context context, int StringId) {
        Toast toast = Toast.makeText(context.getApplicationContext(), StringId, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
    public static Toast getTransparentToast(Context context, int StringId, int alpha) {
        Toast toast = Toast.makeText(context, StringId, Toast.LENGTH_SHORT);
        toast.getView().getBackground().setAlpha(alpha);//设置背景透明度
        toast.setGravity(Gravity.CENTER, 0, 0);
        return toast;
    }

}

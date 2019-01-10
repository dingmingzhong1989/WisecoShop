package com.wiseco.wisecoshop.utils;

import android.os.Build;
import android.view.View;
import android.view.ViewTreeObserver;

/**
 * Created by wiseco on 2018/11/27.
 */

public class ViewUtils {
    public static void removeOnGlobalLayoutListener(View view, ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
        if (Build.VERSION.SDK_INT < 16) {
            view.getViewTreeObserver().removeGlobalOnLayoutListener(onGlobalLayoutListener);
        } else {
            view.getViewTreeObserver().removeOnGlobalLayoutListener(onGlobalLayoutListener);
        }
    }
}


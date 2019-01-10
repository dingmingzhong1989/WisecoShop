package com.wiseco.wisecoshop.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by wiseco on 2018/12/29.
 */

public class ControlableScrollView extends ScrollView {

    //自定义的监听器，当满足条件时调用
    private OnScrollListener mListener;

    public ControlableScrollView(Context context) {
        super(context);
    }

    public ControlableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ControlableScrollView(Context context, AttributeSet attrs,
                                 int defStyle) {
        super(context, attrs, defStyle);
    }

    //覆盖父类的方法，当scroll时调用，可判断是否已经滑到最后，computeVerticalScrollRange方法用于获取ScrollView的总高度
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mListener != null
                && getHeight() + getScrollY() >= computeVerticalScrollRange()) {
            mListener.onScroll(this);
        }
    }

    //添加监听
    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.mListener = onScrollListener;
    }

    //自定义的监听接口，满足条件是调用其中的方法，执行相应的操作
    public static interface OnScrollListener {
        /**
         * called when the view scrolled to the bottom edge.
         *
         * @param v ControlableScrollView
         */
        public void onScroll(ControlableScrollView v);
    }
}

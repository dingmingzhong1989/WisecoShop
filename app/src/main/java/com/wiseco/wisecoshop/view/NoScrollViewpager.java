package com.wiseco.wisecoshop.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class NoScrollViewpager extends ViewPager {

	public NoScrollViewpager(Context context) {
		super(context);
	}

	public NoScrollViewpager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		return false;// 去掉父类的处理事件的功能，自己不消费事件 
	}

	// 不让拦截子控件的事件
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		return false;//  return false 不拦截子控件的事件
	}
}

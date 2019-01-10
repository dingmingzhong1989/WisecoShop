package com.wiseco.wisecoshop.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by wiseco on 2018/10/17.
 */

public abstract class BaseFragment extends Fragment {
    protected Activity mActivity;// 获取当前Fragment的宿主，其实就是MainUI对象，把他作为Context使用
    private Toast mToast;
    public boolean mIsDataInited;

    // 返回一个控件，让Activity添加到界面上，返回view越快越好，不做更新控件的操作
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mActivity = getActivity();


        return initView();
    }

    // 更新控件
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
       // initData();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (!mIsDataInited) {
            if (getUserVisibleHint()) {
                initData();
              //  mIsDataInited = true;
            }
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser); //防止数据预加载, 只预加载View，不预加载数据
        if (isVisibleToUser && isVisible() && !mIsDataInited) {
            initData();

            Log.d("TAG","setUser==========="+mIsDataInited);
           // mIsDataInited = true;
        }
    }
    /**
     * 当使用show/hide方法时，会触发此回调
     *
     * @param hidden fragment是否被隐藏
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {

        } else {

        }
    }


    /**
     * 让子类实现，返回自己的控件
     *
     * @return
     */

    public abstract View initView();

    /**
     * 子类更新控件，不必须实现
     */
    public void initData() {

    }


    public void open(Class<?> cls) {
        Intent intent = new Intent(mActivity, cls);
        startActivity(intent);
    }

    public void open(Class<?> cls, int code) {
        Intent intent = new Intent(mActivity, cls);
        startActivityForResult(intent, code);
    }

    public void open(Class<?> cls, Bundle b) {
        Intent intent = new Intent(mActivity, cls);
        intent.putExtras(b);
        startActivity(intent);
    }

    public void open(Class<?> cls, Bundle b, int code) {
        Intent intent = new Intent(mActivity, cls);
        intent.putExtras(b);
        startActivityForResult(intent, code);
    }

    /**
     * 显示Toast消息
     *
     * @param msg
     */
    public void showToast(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
        }
        mToast.show();
    }
}

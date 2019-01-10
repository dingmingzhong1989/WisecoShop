package com.wiseco.wisecoshop.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wiseco.wisecoshop.R;
import com.wiseco.wisecoshop.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wiseco on 2018/10/27.
 */

public class MessageFragment extends BaseFragment {
    @Bind(R.id.recyclemessage)
    RecyclerView recyclemessage;

    @Override
    public View initView() {

        View view = View.inflate(mActivity, R.layout.fragment_message, null);
        ButterKnife.bind(this, view);
        GridLayoutManager layoutManager = new GridLayoutManager(mActivity, 1);
        recyclemessage.setLayoutManager(layoutManager);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
       // MessageAdapter messageAdapter = new MessageAdapter();
       // recyclemessage.addItemDecoration(new SpaceItemDecoration(0,10));
       // recyclemessage.setAdapter(messageAdapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

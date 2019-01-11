package com.wiseco.wisecoshop.activity.user;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.wiseco.wisecoshop.R;
import com.wiseco.wisecoshop.adapter.MyCardAdapter;
import com.wiseco.wisecoshop.adapter.OrderCardAdapter;
import com.wiseco.wisecoshop.base.BaseActivity;
import com.wiseco.wisecoshop.bean.MyCardBean;
import com.wiseco.wisecoshop.bean.user.BankListBean;
import com.wiseco.wisecoshop.bean.user.CardBrandsListBean;
import com.wiseco.wisecoshop.bean.user.UserCardListBean;
import com.wiseco.wisecoshop.okhttp.CallBackUtil;
import com.wiseco.wisecoshop.okhttp.OkhttpUtil;
import com.wiseco.wisecoshop.view.LoadingView;
import com.wiseco.wisecoshop.view.SpaceItemDecoration;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

import static com.wiseco.wisecoshop.MyApplication.gson;
import static com.wiseco.wisecoshop.MyApplication.i;
import static com.wiseco.wisecoshop.utils.UrlUtil.QUERYCARDLIST;

/**
 * Created by wiseco on 2018/12/18.
 */

public class MyCardActivity extends BaseActivity implements LoadingView.OnRetryListener, LoadingView.OnErroyClickListener {
    private static final int UODATA_UI = 100;
    @Bind(R.id.back)
    ImageButton back;
    @Bind(R.id.bar_tittle)
    TextView barTittle;
    @Bind(R.id.add_more)
    ImageButton addMore;
    @Bind(R.id.recycle_card)
    RecyclerView recycleCard;
    @Bind(R.id.mLoadingView)
    LoadingView mLoadingView;
    private Handler handler;
    private MyCardBean myCardBean;
    Map<Integer, String> mapCard = new HashMap();
    Map<Integer, String> mapUrl = new HashMap();
    private MyCardAdapter cardtAdapter;

    @Override
    public boolean getStatusBarColor() {
        return true;
    }

    @Override
    protected void loadData() {

        getData();


    }

    @Override
    protected void postAgain() {
        getData();
    }

    private void getData() {
        i = 4;
        OkhttpUtil.okHttpPost(QUERYCARDLIST, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                Log.d("TAG", response);

                try {
                    myCardBean = gson.fromJson(response, MyCardBean.class);
                    if (myCardBean.getUserCardList().size() == 0) {
                        mLoadingView.notifyDataChanged(LoadingView.State.empty);

                    } else {
                        mLoadingView.notifyDataChanged(LoadingView.State.done);
                        handler.sendEmptyMessage(UODATA_UI);
                    }
                } catch (Exception e) {


                }


            }
        });
    }

    @Override
    protected void initListener() {

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                handeResult(msg);


            }
        };


    }

    private void handeResult(Message msg) {

        if (msg != null) {
            switch (msg.what) {

                case UODATA_UI:
                    final List<UserCardListBean> userCardList = myCardBean.getUserCardList();
                    List<BankListBean> bankList = myCardBean.getBankList();

                    for (int i = 0; i < bankList.size(); i++) {

                        mapCard.put(bankList.get(i).getId(), bankList.get(i).getName());

                        mapUrl.put(bankList.get(i).getId(), bankList.get(i).getIcon());
                    }

                    cardtAdapter = new MyCardAdapter(userCardList, mapCard, mapUrl);

                    recycleCard.setAdapter(cardtAdapter);
                    cardtAdapter.setOnItemClickListener(new OrderCardAdapter.OnItemClickListener() {
                        @Override
                        public void onClick(int position) {


                        }

                        @Override
                        public void onLongClick(int position) {


                        }
                    });
                  /*  ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
                        *//**
                         * 设置滑动类型标记，可从两端滑动Item
                         *//*
                        @Override
                        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                            return makeMovementFlags(0, ItemTouchHelper.START );
                        }

                        @Override
                        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {


                            return false;
                        }

                        *//**
                         * 设置Item不支持长按拖动
                         *//*
                        @Override
                        public boolean isLongPressDragEnabled() {
                            return false;
                        }

                        *//**
                         * Item支持滑动
                         *//*
                        @Override
                        public boolean isItemViewSwipeEnabled() {
                            return true;
                        }

                        *//**
                         * 滑动删除Item的操作
                         *//*
                        @Override
                        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                            cardtAdapter.delete(viewHolder.getAdapterPosition());

                        }

                        *//**
                         * Item被选中时候，改变Item的背景
                         *//*
                        @Override
                        public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                            //  item被选中的操作
                            if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
                                viewHolder.itemView.setBackgroundResource(R.color.gray);
                            }
                            super.onSelectedChanged(viewHolder, actionState);
                        }

                        *//**
                         * 移动过程中重新绘制Item，随着滑动的距离，设置Item的透明度
                         *//*
                        @Override
                        public void onChildDraw(Canvas c, RecyclerView recyclerView,
                                                RecyclerView.ViewHolder viewHolder,
                                                float dX, float dY, int actionState, boolean isCurrentlyActive) {
                            float x = Math.abs(dX) + 0.5f;
                            float width = viewHolder.itemView.getWidth();
                            float alpha = 1f - x / width;
                            viewHolder.itemView.setAlpha(alpha);
                            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState,
                                    isCurrentlyActive);
                        }

                        *//**
                         * 用户操作完毕或者动画完毕后调用，恢复item的背景和透明度
                         *//*
                        @Override
                        public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                            // 操作完毕后恢复颜色
                            viewHolder.itemView.setBackgroundResource(R.color.white);
                            viewHolder.itemView.setAlpha(1.0f);
                            super.clearView(recyclerView, viewHolder);
                        }
                    });

                    mItemTouchHelper.attachToRecyclerView(recycleCard);*/
                    break;

            }


        }


    }

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_mycard);
        ButterKnife.bind(this);
        mLoadingView.setOnRetryListener(this);
        mLoadingView.notifyDataChanged(LoadingView.State.ing);
        mLoadingView.setOnErroyClickListener(this);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recycleCard.setLayoutManager(layoutManager);
        recycleCard.addItemDecoration(new SpaceItemDecoration(0, 40));
        MyReceiver receiver = new MyReceiver();
        registerReceiver(receiver, new IntentFilter("com.wiseco.wisecoshop.mycard"));
    }


    @OnClick({R.id.back, R.id.add_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.add_more:
                List<BankListBean> bankList = myCardBean.getBankList();
                List<CardBrandsListBean> cardBrandsList = myCardBean.getCardBrandsList();
                Bundle bundle = new Bundle();
                bundle.putSerializable("bankList", (Serializable) bankList);
                bundle.putSerializable("cardBrandsList", (Serializable) cardBrandsList);
                open(AddCardActivity.class, bundle);
                break;
        }
    }

    @Override
    public void onRetry() {
        mLoadingView.notifyDataChanged(LoadingView.State.ing);
    }

    @Override
    public void erroyClick() {

        getData();

    }

    private class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {


            getData();


            Log.d("TAG-----", "我的卡更新了");
        }
    }

}

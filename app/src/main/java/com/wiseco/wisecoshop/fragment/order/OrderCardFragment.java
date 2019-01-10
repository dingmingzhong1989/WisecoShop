package com.wiseco.wisecoshop.fragment.order;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.wiseco.wisecoshop.R;
import com.wiseco.wisecoshop.activity.order.OrderCardDetailActivity;
import com.wiseco.wisecoshop.adapter.OrderCardAdapter;
import com.wiseco.wisecoshop.base.BaseFragment;
import com.wiseco.wisecoshop.bean.OrderCardBean;
import com.wiseco.wisecoshop.okhttp.CallBackUtil;
import com.wiseco.wisecoshop.okhttp.OkhttpUtil;
import com.wiseco.wisecoshop.utils.CacheUtil;
import com.wiseco.wisecoshop.view.LoadingView;
import com.wiseco.wisecoshop.view.SpaceItemDecoration;

import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

import static com.wiseco.wisecoshop.MyApplication.gson;
import static com.wiseco.wisecoshop.MyApplication.i;
import static com.wiseco.wisecoshop.MyApplication.sContext;
import static com.wiseco.wisecoshop.utils.HttpPostUtils.getParamsMap;
import static com.wiseco.wisecoshop.utils.UrlUtil.ORDER;


/**
 * Created by wiseco on 2018/10/18.
 */

public class OrderCardFragment extends BaseFragment implements LoadingView.OnRetryListener, LoadingView.OnErroyClickListener {
    @Bind(R.id.recycleorder)
    RecyclerView recycleorder;
    @Bind(R.id.mLoadingView)
    LoadingView mLoadingView;
    @Bind(R.id.line_one)
    LinearLayout lineOne;
    @Bind(R.id.line_two)
    LinearLayout lineTwo;
    @Bind(R.id.inde)
    LinearLayout inde;
    private final int MESSAGE_SET_ADAPTER = 100;
    private final int MESSAGE_REFRESH_NEWS = 101;
    private final int MESSAGE_SET_ADAPTERCHA = 102;
    private Handler mHandler;
    private List<OrderCardBean.ItemBean> orderCardBeanItem;
    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_orderall, null);
        ButterKnife.bind(this, view);
        mLoadingView.setOnRetryListener(this);
        mLoadingView.notifyDataChanged(LoadingView.State.ing);
        mLoadingView.setOnErroyClickListener(this);
        GridLayoutManager layoutManager = new GridLayoutManager(mActivity, 1);
        recycleorder.setLayoutManager(layoutManager);
        recycleorder.addItemDecoration(new SpaceItemDecoration(0, 20));
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                handleResult(msg);
            }
        };
        lineOne.setVisibility(View.INVISIBLE);
        lineTwo.setVisibility(View.VISIBLE);
        return view;

    }

    @Override
    public void initData() {
        super.initData();
        getData();


    }

    private void getData() {
        Map<String, String> paramsMap = getParamsMap();
        paramsMap.put("prdClass", "CC");
        paramsMap.put("userId", CacheUtil.getString(sContext, "USERID", ""));
        i = 4;
        //  JSONObject jsonObject = JSONObject.fromObject(paramsMap);
        String s = gson.toJson(paramsMap);
        OkhttpUtil.okHttpPostJson(ORDER, s, new CallBackUtil.CallBackString() {



            @Override
            public void onFailure(Call call, Exception e) {
                mLoadingView.notifyDataChanged(LoadingView.State.error);
            }

            @Override
            public void onResponse(String response) {
                try {
                    OrderCardBean orderCardBean = gson.fromJson(response, OrderCardBean.class);
                    if (orderCardBean.getCode().equals("S")) {
                        mIsDataInited = true;
                        mLoadingView.notifyDataChanged(LoadingView.State.done);
                        orderCardBeanItem = orderCardBean.getItem();
                        if (orderCardBeanItem.size()==0){

                            mLoadingView.notifyDataChanged(LoadingView.State.empty);
                            return;
                        }
                        mHandler.sendEmptyMessage(MESSAGE_SET_ADAPTER);

                    } else {

                        mLoadingView.notifyDataChanged(LoadingView.State.error);
                    }
                }catch (Exception e){
                    mLoadingView.notifyDataChanged(LoadingView.State.error);
                }


                Log.d("TAG", response);

            }
        });


    }

    private void handleResult(Message msg) {

        if (msg != null) {
            switch (msg.what) {
                case MESSAGE_SET_ADAPTER:


                    OrderCardAdapter orderCardAdapter = new OrderCardAdapter(orderCardBeanItem);
                    recycleorder.setAdapter(orderCardAdapter);
                    orderCardAdapter.setOnItemClickListener(new OrderCardAdapter.OnItemClickListener() {
                        @Override
                        public void onClick(int position) {

                            if (position==orderCardBeanItem.size()){
                                recycleorder.scrollToPosition(0);
                            }else{

                                Log.d("TAG", "点击了" + position);

                                int id = orderCardBeanItem.get(position).getId();
                                Bundle bundle = new Bundle();
                                bundle.putInt("orderId",id);
                                open(OrderCardDetailActivity.class,bundle);
                            }

                        }

                        @Override
                        public void onLongClick(int position) {

                        }
                    });
                    break;
                case MESSAGE_REFRESH_NEWS:

                    break;
                case MESSAGE_SET_ADAPTERCHA:

                    break;
                default:
            }
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onRetry() {
        mLoadingView.notifyDataChanged(LoadingView.State.ing);
    }

    @Override
    public void erroyClick() {
        getData();
    }
}

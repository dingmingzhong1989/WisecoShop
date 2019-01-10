package com.wiseco.wisecoshop.fragment.ticket;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.wiseco.wisecoshop.R;
import com.wiseco.wisecoshop.adapter.EndLessOnScrollListener;
import com.wiseco.wisecoshop.adapter.ticket.TicketUserAdapter;
import com.wiseco.wisecoshop.base.BaseFragment;
import com.wiseco.wisecoshop.bean.ticket.TicketNoUsedBean;
import com.wiseco.wisecoshop.bean.ticket.WalletListBean;
import com.wiseco.wisecoshop.okhttp.CallBackUtil;
import com.wiseco.wisecoshop.okhttp.OkhttpUtil;
import com.wiseco.wisecoshop.view.LoadingView;
import com.wiseco.wisecoshop.view.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

import static com.wiseco.wisecoshop.MyApplication.gson;
import static com.wiseco.wisecoshop.MyApplication.i;
import static com.wiseco.wisecoshop.utils.HttpPostUtils.getParamsMap;
import static com.wiseco.wisecoshop.utils.UrlUtil.QUERYWALLET;

/**
 * Created by wiseco on 2018/12/18.
 */

public class TicketUsedFragment extends BaseFragment implements LoadingView.OnRetryListener, LoadingView.OnErroyClickListener {
    private static final int MESSAGE_SET_ADAPTER = 100;
    @Bind(R.id.line_one)
    LinearLayout lineOne;
    @Bind(R.id.line_two)
    LinearLayout lineTwo;
    @Bind(R.id.line_three)
    LinearLayout lineThree;
    @Bind(R.id.recycle_ticket)
    RecyclerView recycleTicket;
    @Bind(R.id.mLoadingView)
    LoadingView mLoadingView;
    private Handler mHandler;
    private Object data;
    private TicketNoUsedBean ticketNoUsedBean;
    private List<WalletListBean> allTicket= new ArrayList<>();
    private int pageNum;
    private int walletNum;
    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_ticketused, null);

        ButterKnife.bind(this, view);

        mLoadingView.setOnRetryListener(this);
        mLoadingView.notifyDataChanged(LoadingView.State.ing);
        mLoadingView.setOnErroyClickListener(this);
        recycleTicket.addItemDecoration(new SpaceItemDecoration(0, 40));

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                handleResult(msg);
            }
        };
        lineOne.setVisibility(View.INVISIBLE);
        lineTwo.setVisibility(View.VISIBLE);
        lineThree.setVisibility(View.INVISIBLE);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        allTicket.clear();
          getData();

       // mHandler.sendEmptyMessage(MESSAGE_SET_ADAPTER);

    }

    private void handleResult(Message msg) {

        if (msg != null) {
            switch (msg.what) {
                case MESSAGE_SET_ADAPTER:

                    GridLayoutManager layoutManager = new GridLayoutManager(mActivity, 1);
                    recycleTicket.setLayoutManager(layoutManager);

                    final TicketUserAdapter ticketAdapter = new TicketUserAdapter(allTicket, "used");
                    recycleTicket.setAdapter(ticketAdapter);
                    recycleTicket.addOnScrollListener(new EndLessOnScrollListener(layoutManager) {
                        @Override
                        public void onLoadMore(final int currentPage) {
                            Log.d("TAG", "加载了" + currentPage+"pagenum==="+walletNum);

                            if (currentPage < pageNum && allTicket.size()<walletNum) {
                                ticketAdapter.setMoreTar(true);

                                Map<String, String> paramsMap = getParamsMap();
                                paramsMap.put("pageNo", (currentPage + 1) + "");
                                paramsMap.put("status", "used");

                                i = 4;
                                OkhttpUtil.okHttpPostJson(QUERYWALLET, gson.toJson(paramsMap), new CallBackUtil.CallBackString() {
                                    @Override
                                    public void onFailure(Call call, Exception e) {
                                        Log.d("TAG", "加载了" + e);
                                    }

                                    @Override
                                    public void onResponse(String response) {
                                        try {
                                            TicketNoUsedBean  ticketNoUsedBean = gson.fromJson(response, TicketNoUsedBean.class);

                                            allTicket.addAll(ticketNoUsedBean.getWalletList());
                                            ticketAdapter.notifyDataSetChanged();

                                        } catch (Exception e) {
                                            Log.d("TAG", "加载了" + e);


                                        }

                                    }
                                });
                            } else if (currentPage == pageNum ||allTicket.size()==walletNum) {

                                ticketAdapter.setMoreTar(false);
                                //ticketAdapter.notifyDataSetChanged();


                            } else {
                                ticketAdapter.setMoreTar(false);

                                return;
                            }


                        }
                    });
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

    }

    @Override
    public void erroyClick() {

    }

    public void getData() {


        i=4;

        Map<String, String> paramsMap = getParamsMap();

        paramsMap.put("status", "used");


        OkhttpUtil.okHttpPostJson(QUERYWALLET, gson.toJson(paramsMap), new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                Log.d("TAG", response);
                try {
                    ticketNoUsedBean = gson.fromJson(response, TicketNoUsedBean.class);
                    pageNum = ticketNoUsedBean.getPageNum();
                    walletNum = ticketNoUsedBean.getWalletNum();

                    if (ticketNoUsedBean.getCode().equals("S")){
                        List<WalletListBean> walletList = ticketNoUsedBean.getWalletList();
                        allTicket.addAll(walletList);
                        mLoadingView.notifyDataChanged(LoadingView.State.done);
                      //  mIsDataInited = true;

                        mHandler.sendEmptyMessage(MESSAGE_SET_ADAPTER);
                    }else if (ticketNoUsedBean.getCode().equals("N")){

                        mLoadingView.notifyDataChanged(LoadingView.State.empty);
                    }else{

                        mLoadingView.notifyDataChanged(LoadingView.State.error);
                    }


                } catch (Exception e) {
                }


            }
        });
    }
}

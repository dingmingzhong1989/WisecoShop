package com.wiseco.wisecoshop.fragment.goods;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wiseco.wisecoshop.R;
import com.wiseco.wisecoshop.activity.goods.MoneyDetailActivity;
import com.wiseco.wisecoshop.adapter.EndLessOnScrollListener;
import com.wiseco.wisecoshop.adapter.MoneyFindAdapter;
import com.wiseco.wisecoshop.adapter.MoneyListAdapter;
import com.wiseco.wisecoshop.base.BaseFragment;
import com.wiseco.wisecoshop.bean.MoneyFindBean;
import com.wiseco.wisecoshop.bean.MoneyListBean;
import com.wiseco.wisecoshop.bean.MoneyListItemBean;
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
import static com.wiseco.wisecoshop.utils.UrlUtil.GOOSDS;
import static com.wiseco.wisecoshop.utils.UrlUtil.GOOSDSPAGEFIND;


/**
 * Created by wiseco on 2018/10/16.
 */

public class MoneyListFragment extends BaseFragment implements LoadingView.OnRetryListener, LoadingView.OnErroyClickListener {
    List<String> list = new ArrayList<>();
    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.recyclemoney)
    RecyclerView recyclemoney;
    @Bind(R.id.mLoadingView)
    LoadingView mLoadingView;
    @Bind(R.id.line_one)
    LinearLayout lineOne;
    @Bind(R.id.line_two)
    LinearLayout lineTwo;

    private MoneyListAdapter moneyListAdapter;
    private List<MoneyListBean.ProductmsgBean.CategorylistBean> categorylist;


    private Handler mHandler;
    private static final int MESSAGE_SET_ADAPTER = 1001;
    private static final int MESSAGE_REFRESH_NEWS = 1002;
    private static final int MESSAGE_SET_ADAPTERCHA = 1003;

    private int pagenum;
    private List<MoneyListItemBean.ProductlistBean> productlist;
    //private List<MoneyFindBean.ProductListBean> moneyFindBeanProductList;

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_money, null);
        ButterKnife.bind(this, view);
        recyclemoney.addItemDecoration(new SpaceItemDecoration(0, 10));
        mLoadingView.setOnRetryListener(this);
        mLoadingView.notifyDataChanged(LoadingView.State.ing);
        mLoadingView.setOnErroyClickListener(this);
        lineOne.setVisibility(View.INVISIBLE);
        lineTwo.setVisibility(View.VISIBLE);
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                handleResult(msg);
            }
        };


        return view;
    }

    @Override
    public void initData() {
        super.initData();
       // HttpPostUtils.putEventTag(getUserId(), "", "", "CL", CONTENT_EVENT_PRODUCT_SELECT, COMMENT_EVENT_PRODUCT_SELECT_MONEY);
        // mLoadingDialog.show();
        // mLoadingView.notifyDataChanged(LoadingView.State.ing);

        getMoneyData();


    }
    public List<String> getCoadList(List<MoneyListItemBean.ProductlistBean> bankList) {
        List<String> stringList=null;

        if (stringList==null){
            stringList = new ArrayList<>();
        }
        for (int i = 0; i < bankList.size(); i++) {

            stringList.add(bankList.get(i).getCode());
        }


        return stringList;
    }

    private void getMoneyData() {
        Map<String, String> paramsMap = getParamsMap();
        paramsMap.put("product", "CL");
        i = 4;
        OkhttpUtil.okHttpPost(GOOSDS, paramsMap, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                //  mLoadingDialog.dissmiss();
                // ToastUtils.toastInCenter(sContext, R.string.network_not_available);
                Log.d("TAG", "ExceptionExceptionExceptionException=====" + e);
                // getMoneyData();
                mLoadingView.notifyDataChanged(LoadingView.State.error);
            }

            @Override
            public void onResponse(String response) {
                try {
                    Log.d("TAG", "AAAAAAAAAAAAAAAAAAAAAAAAA=====" + response);
                    MoneyListBean moneyListBean = gson.fromJson(response, MoneyListBean.class);

                    if (moneyListBean.getCode().equals("S")) {

                        //   mLoadingDialog.dissmiss();
                        mLoadingView.notifyDataChanged(LoadingView.State.done);

                        mIsDataInited = true;

                        pagenum = moneyListBean.getProductmsg().getPagenum();
                        categorylist = moneyListBean.getProductmsg().getCategorylist();
                        MoneyListBean.ProductmsgBean.CategorylistBean categorylistBean = new MoneyListBean.ProductmsgBean.CategorylistBean();

                        //**动态添加值**//*
                        for (int i = 0; i < (categorylist.size() + 1); i++) {
                            tabLayout.addTab(tabLayout.newTab());
                            TabLayout.Tab tab = tabLayout.getTabAt(i);
                            if (tab != null) {
                                tab.setCustomView(R.layout.tab_layout);
                                View view = tab.getCustomView();
                                TextView textView = (TextView) view.findViewById(R.id.tv_txt);
                                view.setTag(i);
                                view.setOnClickListener(mTabOnClickListener);
                                if (i == 0) {
                                    textView.setText("推荐");
                                    // textView.setBackgroundResource(R.drawable.recommendbg);
                                    // textView.setTextColor(getResources().getColor(R.color.white));
                                } else {
                                    textView.setText(categorylist.get(i - 1).getName());
                                }
                            }

                        }
                        String s = gson.toJson(moneyListBean.getProductmsg().getProductlist());

                        MoneyListItemBean moneyListItemBean = gson.fromJson("{" + "productlist :" + s + "}", MoneyListItemBean.class);

                        productlist =moneyListItemBean.getProductlist();
                        getCoadList(productlist);
                        mHandler.sendEmptyMessage(MESSAGE_SET_ADAPTER);

                    }


                } catch (Exception e) {
                    mLoadingView.notifyDataChanged(LoadingView.State.error);
                }


            }
        });
    }

    public void handleResult(Message msg) {
        if (msg != null) {
            switch (msg.what) {
                case MESSAGE_SET_ADAPTER:
                    GridLayoutManager layoutManager = new GridLayoutManager(mActivity, 1);
                    recyclemoney.setLayoutManager(layoutManager);
                    moneyListAdapter = new MoneyListAdapter(productlist);
                    recyclemoney.setAdapter(moneyListAdapter);
                    moneyListAdapter.setOnItemClickListener(new MoneyListAdapter.OnItemClickListener() {
                        @Override
                        public void onClick(int position) {
                            if (position == productlist.size()) {
                                recyclemoney.scrollToPosition(0);

                            } else {
                                Log.d("TAG", "点击了" + position);

                                Bundle bundle = new Bundle();
                                bundle.putString("productcode", productlist.get(position).getCode());
                                open(MoneyDetailActivity.class, bundle);
                            }


                        }

                        @Override
                        public void onLongClick(int position) {

                        }
                    });
                    recyclemoney.addOnScrollListener(new EndLessOnScrollListener(layoutManager) {
                        @Override
                        public void onLoadMore(int currentPage) {

                            if (currentPage < pagenum) {

                                Map<String, String> paramsMap = getParamsMap();
                                paramsMap.put("curpageNo", (currentPage + 1) + "");
                                paramsMap.put("prdclass", "CL");
                                i = 4;

                                OkhttpUtil.okHttpPost(GOOSDSPAGEFIND, paramsMap, new CallBackUtil.CallBackString() {
                                    @Override
                                    public void onFailure(Call call, Exception e) {

                                    }

                                    @Override
                                    public void onResponse(String response) {

                                        try {
                                            MoneyFindBean moneyFindBean = gson.fromJson(response, MoneyFindBean.class);


                                            String json = gson.toJson(moneyFindBean.getProductList());
                                            MoneyListItemBean moneyListItemBean = gson.fromJson("{" + "productlist :" + json + "}", MoneyListItemBean.class);

                                            productlist.addAll(moneyListItemBean.getProductlist());

                                            moneyListAdapter.notifyDataSetChanged();


                                         /*   final List<MoneyFindBean.ProductListBean> moneyFindBeanProductList = moneyFindBean.getProductList();
                                            GridLayoutManager layoutManager1 = new GridLayoutManager(mActivity, 1);
                                            recyclemoney.setLayoutManager(layoutManager1);
                                            MoneyFindAdapter moneyFindAdapter = new MoneyFindAdapter(moneyFindBeanProductList);
                                            // moneyFindAdapter.addItem(productList);
                                            recyclemoney.setAdapter(moneyFindAdapter);*/

                                            // mHandler.sendEmptyMessage(MESSAGE_SET_ADAPTER);
                                        } catch (Exception e) {

                                            mLoadingView.notifyDataChanged(LoadingView.State.error);
                                        }

                                    }
                                });



                            } else {
                                return;
                            }

                            Log.d("TAG", "加载了" + currentPage);


                        }
                    });

                    break;
                case MESSAGE_REFRESH_NEWS:

                    break;
                case MESSAGE_SET_ADAPTERCHA:
                  /*  GridLayoutManager layoutManager1 = new GridLayoutManager(mActivity, 1);
                    recyclemoney.setLayoutManager(layoutManager1);
                    MoneyFindAdapter moneyFindAdapter = new MoneyFindAdapter(moneyFindBeanProductList);
                    // moneyFindAdapter.addItem(productList);
                    recyclemoney.setAdapter(moneyFindAdapter);
                    moneyFindAdapter.setOnItemClickListener(new MoneyFindAdapter.OnItemClickListener() {
                        @Override
                        public void onClick(int position) {


                            Bundle bundle = new Bundle();
                            bundle.putString("productcode", moneyFindBeanProductList.get(position).getCode());
                            open(MoneyDetailActivity.class, bundle);

                        }

                        @Override
                        public void onLongClick(int position) {

                        }
                    });*/

                    break;

                default:
            }
        }
    }

    private View.OnClickListener mTabOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            i = 4;
            int position = (int) v.getTag();
            tabLayout.getTabAt(position).select();
            if (position != 0) {
                //HttpPostUtils.putEventTag(getUserId(), "", categorylist.get(position - 1).getId(), "CL", CONTENT_EVENT_PRODUCT_FILTER, COMMENT_EVENT_PRODUCT_FILTER);

                i = 4;
                Map<String, String> paramsMap = getParamsMap();
                paramsMap.put("curpageNo", 1 + "");
                paramsMap.put("prdclass", "CL");
                paramsMap.put("categoryid", categorylist.get(position - 1).getId());
                OkhttpUtil.okHttpPost(GOOSDSPAGEFIND, paramsMap, new CallBackUtil.CallBackString() {
                    @Override
                    public void onFailure(Call call, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        Log.d("TAG", "GOOSDSPAGEFIND====" + response);
                        try {
                            MoneyFindBean moneyFindBean = gson.fromJson(response, MoneyFindBean.class);
                            final List<MoneyFindBean.ProductListBean> moneyFindBeanProductList = moneyFindBean.getProductList();
                            GridLayoutManager layoutManager1 = new GridLayoutManager(mActivity, 1);
                            recyclemoney.setLayoutManager(layoutManager1);
                            MoneyFindAdapter moneyFindAdapter = new MoneyFindAdapter(moneyFindBeanProductList);
                            // moneyFindAdapter.addItem(productList);
                            recyclemoney.setAdapter(moneyFindAdapter);
                            moneyFindAdapter.setOnItemClickListener(new MoneyFindAdapter.OnItemClickListener() {
                                @Override
                                public void onClick(int position) {

                                    if (position == moneyFindBeanProductList.size()) {
                                        recyclemoney.scrollToPosition(0);

                                    } else {

                                        Bundle bundle = new Bundle();
                                        bundle.putString("productcode", moneyFindBeanProductList.get(position).getCode());
                                        open(MoneyDetailActivity.class, bundle);

                                    }

                                }

                                @Override
                                public void onLongClick(int position) {

                                }
                            });
                            // mHandler.sendEmptyMessage(MESSAGE_SET_ADAPTER);
                        } catch (Exception e) {

                            mLoadingView.notifyDataChanged(LoadingView.State.error);
                        }


                    }
                });


            } else {

                mHandler.sendEmptyMessage(MESSAGE_SET_ADAPTER);
            }


        }
    };

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
        getMoneyData();
    }
}

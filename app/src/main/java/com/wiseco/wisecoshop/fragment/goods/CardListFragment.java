package com.wiseco.wisecoshop.fragment.goods;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wiseco.wisecoshop.R;
import com.wiseco.wisecoshop.activity.goods.CardDetailActivity;
import com.wiseco.wisecoshop.adapter.CardFindAdapter;
import com.wiseco.wisecoshop.adapter.CardListAdapter;
import com.wiseco.wisecoshop.adapter.EndLessOnScrollListener;
import com.wiseco.wisecoshop.adapter.MyGridAdapter;
import com.wiseco.wisecoshop.base.BaseFragment;
import com.wiseco.wisecoshop.bean.CardFindBean;
import com.wiseco.wisecoshop.bean.CardLiseBean;
import com.wiseco.wisecoshop.bean.CardListItemBean;
import com.wiseco.wisecoshop.bean.FirstCardBean;
import com.wiseco.wisecoshop.bean.GridViewBean;
import com.wiseco.wisecoshop.okhttp.CallBackUtil;
import com.wiseco.wisecoshop.okhttp.OkhttpUtil;
import com.wiseco.wisecoshop.utils.CacheUtil;
import com.wiseco.wisecoshop.view.LoadingView;
import com.wiseco.wisecoshop.view.PopuWindow;
import com.wiseco.wisecoshop.view.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

import static com.wiseco.wisecoshop.MyApplication.gson;
import static com.wiseco.wisecoshop.MyApplication.i;
import static com.wiseco.wisecoshop.MyApplication.sContext;
import static com.wiseco.wisecoshop.utils.HttpPostUtils.getParamsMap;
import static com.wiseco.wisecoshop.utils.UrlUtil.FIRSTUPDATA;
import static com.wiseco.wisecoshop.utils.UrlUtil.GOOSDS;
import static com.wiseco.wisecoshop.utils.UrlUtil.GOOSDSPAGEFIND;


/**
 * Created by wiseco on 2018/10/16.
 */
public class CardListFragment extends BaseFragment implements LoadingView.OnRetryListener, LoadingView.OnErroyClickListener {
    /**
     * The List.
     */
    List<String> list = new ArrayList<>();
    /**
     * The Tab layout.
     */
    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    /**
     * The Recyclecard.
     */
    @Bind(R.id.recyclecard)
    RecyclerView recyclecard;
    /**
     * The Main.
     */
    @Bind(R.id.main)
    RelativeLayout main;
    /**
     * The M loading view.
     */
    @Bind(R.id.mLoadingView)
    LoadingView mLoadingView;
    @Bind(R.id.line_one)
    LinearLayout lineOne;
    @Bind(R.id.line_two)
    LinearLayout lineTwo;
    private PopuWindow window;
    private CardListAdapter cardListAdapter;
    private List<CardLiseBean.ProductmsgBean.CategorylistBean> allTablist= new ArrayList<>();
    //private List<CardLiseBean.ProductmsgBean.CategorylistBean> categorylist;
    private GridLayoutManager layoutManager;
    private int page = 1;
   // private List<CardListItemBean.ProductlistBean> productlist;
    private List<CardListItemBean.ProductlistBean> productlistAll= new ArrayList<>();

    @Override
    public View initView() {

        View view = View.inflate(mActivity, R.layout.fragment_card, null);
        ButterKnife.bind(this, view);
        //mStateLayout.showLoadingView();
        mLoadingView.setOnRetryListener(this);
        mLoadingView.notifyDataChanged(LoadingView.State.ing);
        mLoadingView.setOnErroyClickListener(this);
        layoutManager = new GridLayoutManager(mActivity, 1);
        recyclecard.setLayoutManager(layoutManager);
        recyclecard.addItemDecoration(new SpaceItemDecoration(0, 10));
        lineOne.setVisibility(View.VISIBLE);
        lineTwo.setVisibility(View.INVISIBLE);
        // mLoadingDialog = LoadingDialog.getInstance(mActivity);
        return view;
    }


    @Override
    public void initData() {
        super.initData();
        productlistAll.clear();
      //  HttpPostUtils.putEventTag(getUserId(), "", "", "CC", CONTENT_EVENT_PRODUCT_SELECT, COMMENT_EVENT_PRODUCT_SELECT_CARDS);
        cardListPost();
    }
    public List<String> getCoadList(List<CardListItemBean.ProductlistBean> bankList) {
        List<String> stringList=null;

        if (stringList==null){
            stringList = new ArrayList<>();
        }
        for (int i = 0; i < bankList.size(); i++) {

            stringList.add(bankList.get(i).getCode());
        }


        return stringList;
    }
    private void cardListPost() {
        Map<String, String> paramsMap = getParamsMap();
        paramsMap.put("product", "CC");
        paramsMap.put("pagenum", 1 + "");
        i = 4;
        OkhttpUtil.okHttpPost(GOOSDS, paramsMap, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                mLoadingView.notifyDataChanged(LoadingView.State.error);
            }

            @Override
            public void onResponse(String response) {
                Log.d("TAG", "Cardrresponseresponseesponse============" + response);

                try {

                    CardLiseBean cardLiseBean = gson.fromJson(response, CardLiseBean.class);
                    if (cardLiseBean.getCode().equals("UCC")) {
                        mLoadingView.notifyDataChanged(LoadingView.State.done);
                        mIsDataInited = true;
                        GridViewBean gridViewBean = gson.fromJson(response, GridViewBean.class);
                        List<GridViewBean.BankListBean> bankList = gridViewBean.getBankList();
                        initPopupWindow(bankList);
                        window.showAtLocation(main, Gravity.CENTER, 0, 0);
                        WindowManager.LayoutParams lp = mActivity.getWindow().getAttributes();
                        lp.alpha = 0.3f;
                        mActivity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                        mActivity.getWindow().setAttributes(lp);
                    } else if (cardLiseBean.getCode().equals("S")) {

                        mIsDataInited = true;
                        mLoadingView.notifyDataChanged(LoadingView.State.done);


                        List<CardLiseBean.ProductmsgBean.CategorylistBean> categorylist = cardLiseBean.getProductmsg().getCategorylist();

                       if (allTablist.size()==0){
                           allTablist.addAll(categorylist);

                           //**动态添加值**//*
                           for (int i = 0; i < (allTablist.size() + 1); i++) {

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
                                       //  textView.setBackgroundResource(R.drawable.recommendbg);
                                       //textView.setTextColor(getResources().getColor(R.color.white));
                                   } else {
                                       textView.setText(categorylist.get(i - 1).getName());
                                   }
                               }

                           }

                       }else{
                           allTablist.clear();
                           allTablist.addAll(categorylist);
                       }



                        final int prdnum = cardLiseBean.getProductmsg().getPrdnum();

                        final int pagenum = cardLiseBean.getProductmsg().getPagenum();

                        String s = gson.toJson(cardLiseBean.getProductmsg().getProductlist());


                        CardListItemBean cardListItemBean = gson.fromJson("{" + "productlist :" + s + "}", CardListItemBean.class);


                        List<CardListItemBean.ProductlistBean> productlist = cardListItemBean.getProductlist();
                        productlistAll.addAll(productlist);
                        getCoadList(productlist);
                        cardListAdapter = new CardListAdapter(CardListFragment.this.productlistAll);
                        cardListAdapter.setMoreTar(true);

                        recyclecard.setAdapter(cardListAdapter);
                        cardListAdapter.notifyDataSetChanged();
                        cardListAdapter.setOnItemClickListener(new CardListAdapter.OnItemClickListener() {
                            @Override
                            public void onClick(int position) {


                                if (position == prdnum) {
                                    recyclecard.scrollToPosition(0);
                                } else {

                                    Log.d("TAG", "点击了" + position);
                                    Bundle bundle = new Bundle();

                                    bundle.putString("Smallicon", CardListFragment.this.productlistAll.get(position).getSmallicon());

                                    Log.d("TAG", "点击了" + position);
                                    bundle.putString("productcode", CardListFragment.this.productlistAll.get(position).getCode());

                                    open(CardDetailActivity.class, bundle);

                                }


                            }

                            @Override
                            public void onLongClick(int position) {

                            }
                        });

                        recyclecard.addOnScrollListener(new EndLessOnScrollListener(layoutManager) {
                            @Override
                            public void onLoadMore(final int currentPage) {
                               // Log.d("TAG", "加载了" + currentPage+"pagenum==="+pagenum);

                                if (currentPage <  pagenum && productlistAll.size()<prdnum) {
                                    cardListAdapter.setMoreTar(true);
                                    Log.d("TAG", "加载了" + productlistAll.size()+"pagenum==="+pagenum);
                                    Map<String, String> paramsMap = getParamsMap();
                                    paramsMap.put("curpageNo", (currentPage + 1) + "");
                                    paramsMap.put("prdclass", "CC");
                                    i = 4;
                                    OkhttpUtil.okHttpPost(GOOSDSPAGEFIND, paramsMap, new CallBackString() {
                                        @Override
                                        public void onFailure(Call call, Exception e) {
                                            Log.d("TAG", "加载了" + e);
                                        }

                                        @Override
                                        public void onResponse(String response) {
                                            try {

                                                CardFindBean cardFindBean = gson.fromJson(response, CardFindBean.class);

                                                String json = gson.toJson(cardFindBean.getProductList());
                                                CardListItemBean cardListItemBean = gson.fromJson("{" + "productlist :" + json + "}", CardListItemBean.class);

                                                productlistAll.addAll(cardListItemBean.getProductlist());

                                                cardListAdapter.notifyDataSetChanged();

                                            } catch (Exception e) {
                                                Log.d("TAG", "加载了" + e);


                                            }

                                        }
                                    });
                                } else if (currentPage == pagenum ||productlistAll.size()==prdnum) {

                                    cardListAdapter.setMoreTar(false);
                                   cardListAdapter.notifyDataSetChanged();
                                    Log.d("TAG", "加载了22222" + currentPage);
                                    Log.d("TAG", "加载了2222" + pagenum);

                                } else {
                                    cardListAdapter.setMoreTar(false);
                                    Log.d("TAG", "加载了3333333" + currentPage);
                                    Log.d("TAG", "加载了333333" + pagenum);
                                    return;
                                }


                            }
                        });

                    } else {

                        mLoadingView.notifyDataChanged(LoadingView.State.error);
                    }

                } catch (Exception e) {

                    Log.d("TAG", "加载了" + e);


                }

            }
        });
    }

    private View.OnClickListener mTabOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            int position = (int) v.getTag();
            tabLayout.getTabAt(position).select();

            if (position != 0) {
               // HttpPostUtils.putEventTag(getUserId(), "", allTablist.get(position - 1).getId(), "CC", CONTENT_EVENT_PRODUCT_FILTER, COMMENT_EVENT_PRODUCT_FILTER);
                i = 4;
                Map<String, String> paramsMap = getParamsMap();
                paramsMap.put("curpageNo", 1 + "");
                paramsMap.put("prdclass", "CC");
                paramsMap.put("categoryid", allTablist.get(position - 1).getId());
                OkhttpUtil.okHttpPost(GOOSDSPAGEFIND, paramsMap, new CallBackUtil.CallBackString() {
                    @Override
                    public void onFailure(Call call, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        Log.d("TAG", "GOOSDSPAGEFIND====" + response);
                        CardFindBean cardFindBean = gson.fromJson(response, CardFindBean.class);


                        final List<CardFindBean.ProductListBean> productList = cardFindBean.getProductList();
                        CardFindAdapter cardFindAdapter = new CardFindAdapter(productList);
                        recyclecard.setAdapter(cardFindAdapter);
                        cardFindAdapter.setOnItemClickListener(new CardFindAdapter.OnItemClickListener() {
                            @Override
                            public void onClick(int position) {
                                if (position == productList.size()) {
                                    recyclecard.scrollToPosition(0);
                                } else {

                                    Log.d("TAG", "点击了" + position);
                                    Bundle bundle = new Bundle();
                                    bundle.putString("Smallicon", productList.get(position).getSmallicon());
                                    bundle.putString("productcode", productList.get(position).getCode());
                                    open(CardDetailActivity.class, bundle);
                                }


                            }

                            @Override
                            public void onLongClick(int position) {

                            }
                        });
                    }
                });

            } else {
               // productlist.clear();
                //cardListPost();
                recyclecard.setAdapter(cardListAdapter);
                // cardListAdapter.notifyDataSetChanged();
            }


        }
    };

    private void initPopupWindow(final List<GridViewBean.BankListBean> bankList) {
        // get the height of screen
        DisplayMetrics metrics = new DisplayMetrics();
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int screenHeight = metrics.heightPixels;
        // create popup window
        window = new PopuWindow(mActivity, R.layout.bank_list, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT) {

            private Button btn_ok;
            private Button btn_first;
            private Button submit;
            private Map<Integer, Integer> gvChooseMap = new HashMap<Integer, Integer>();

            @Override
            protected void initView() {
                View view = getContentView();
                final GridView gridview = (GridView) view.findViewById(R.id.gridview);
                btn_first = (Button) view.findViewById(R.id.btn_first);
                btn_ok = (Button) view.findViewById(R.id.btn_ok);
                final MyGridAdapter myGridAdapter = new MyGridAdapter(bankList);
                gridview.setAdapter(myGridAdapter);
                gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {


                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // myGridAdapter.setSeclection(position);
                        //  myGridAdapter.notifyDataSetChanged();
                        int bankId = bankList.get(position).getId();


                        if (view.isActivated()) {
                            view.setActivated(false);
                            view.setSelected(false);
                            Log.d("TAG", "bankId====" + bankId);
                          FrameLayout viewById = (FrameLayout) view.findViewById(R.id.fram);
                            viewById.setBackgroundResource(R.drawable.finance_tab_orie_bg);
                           // myGridAdapter.setSeclection(-1);


                            gvChooseMap.remove(position);
                            myGridAdapter.notifyDataSetChanged();

                        } else {
                            view.setActivated(true);
                            view.setSelected(true);
                            FrameLayout viewById = (FrameLayout) view.findViewById(R.id.fram);
                           viewById.setBackgroundResource(R.drawable.finance_tab_orie_nor_bg);

                           // myGridAdapter.setSeclection(position);
                           // myGridAdapter.notifyDataSetChanged();
                            gvChooseMap.put(position, bankId);
                            Log.d("TAG", "bankIdbankIdbankId====" + bankId);
                        }


                    }
                });

            }

            @Override
            protected void initEvent() {
                btn_first.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CacheUtil.putBoolean(sContext, "isFirstCard", true);


                        setCard();
                        window.getPopupWindow().dismiss();


                    }
                });
                btn_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        StringBuffer sb = new StringBuffer();
                        //遍历map
                        for (Map.Entry<Integer, Integer> entry : gvChooseMap.entrySet()) {

                            int strkey = entry.getKey();


                            sb.append(gvChooseMap.get(strkey) + ",");


                        }
                        Log.d("TAG", "StringBuffer====" + sb.toString());
                        setCard(sb.toString());

                        CacheUtil.putBoolean(sContext, "isFirstCard", true);
                        window.getPopupWindow().dismiss();


                    }
                });
            }

            @Override
            protected void initWindow() {
                super.initWindow();
                PopupWindow instance = getPopupWindow();
                instance.setOutsideTouchable(false);
                instance.setFocusable(false);
                instance.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        WindowManager.LayoutParams lp = mActivity.getWindow().getAttributes();
                        lp.alpha = 1.0f;
                        mActivity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                        mActivity.getWindow().setAttributes(lp);
                    }
                });
            }
        };


    }

    private void setCard(String s) {
        Map<String, String> parasMap = getParamsMap();
        parasMap.put("bankid", s);
        OkhttpUtil.okHttpPost(FIRSTUPDATA, parasMap, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {

            }

            @Override
            public void onResponse(String response) {

                FirstCardBean firstCardBean = gson.fromJson(response, FirstCardBean.class);
                if (firstCardBean.getCode().equals("S")) {
                    cardListPost();

                }
            }
        });
    }

    private void setCard() {
        Map<String, String> parasMap = getParamsMap();
        parasMap.put("bankid", "");
        OkhttpUtil.okHttpPost(FIRSTUPDATA, parasMap, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                FirstCardBean firstCardBean = gson.fromJson(response, FirstCardBean.class);
                if (firstCardBean.getCode().equals("S")) {
                    cardListPost();

                }

            }
        });
    }

    @Override
    public void onRetry() {
        mLoadingView.notifyDataChanged(LoadingView.State.ing);
    }

    @Override
    public void erroyClick() {
        cardListPost();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

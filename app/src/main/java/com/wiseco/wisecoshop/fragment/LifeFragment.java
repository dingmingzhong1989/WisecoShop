package com.wiseco.wisecoshop.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;
import com.wiseco.wisecoshop.R;
import com.wiseco.wisecoshop.activity.dixconuts.DiscountActivity;
import com.wiseco.wisecoshop.activity.dixconuts.DiscountChannelAcyivity;
import com.wiseco.wisecoshop.activity.dixconuts.DiscountListAcyivity;
import com.wiseco.wisecoshop.adapter.GridviewChannelAdapter;
import com.wiseco.wisecoshop.adapter.LifeDiscountsAdapter;
import com.wiseco.wisecoshop.base.BaseFragment;
import com.wiseco.wisecoshop.bean.ChannelBean;
import com.wiseco.wisecoshop.bean.LifePromBean;
import com.wiseco.wisecoshop.bean.discount.CategoryListBean;
import com.wiseco.wisecoshop.bean.discount.NearbyShopBean;
import com.wiseco.wisecoshop.bean.discount.ShopDtoBean;
import com.wiseco.wisecoshop.dialog.LoginDialog;
import com.wiseco.wisecoshop.dialog.RegiestDialog;
import com.wiseco.wisecoshop.okhttp.CallBackUtil;
import com.wiseco.wisecoshop.okhttp.OkhttpUtil;
import com.wiseco.wisecoshop.utils.CacheUtil;
import com.wiseco.wisecoshop.utils.LocationUtil;
import com.wiseco.wisecoshop.utils.ToastUtils;
import com.wiseco.wisecoshop.utils.UtilsOther;
import com.wiseco.wisecoshop.view.ChannelGridView;
import com.wiseco.wisecoshop.view.ControlableScrollView;
import com.wiseco.wisecoshop.view.LoadingView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

import static com.wiseco.wisecoshop.MyApplication.gson;
import static com.wiseco.wisecoshop.MyApplication.i;
import static com.wiseco.wisecoshop.MyApplication.sContext;
import static com.wiseco.wisecoshop.utils.Constants.LATITUDE;
import static com.wiseco.wisecoshop.utils.Constants.LONGITUDE;
import static com.wiseco.wisecoshop.utils.HttpPostUtils.getParamsMap;
import static com.wiseco.wisecoshop.utils.UrlUtil.NEARBYSHOP;
import static com.wiseco.wisecoshop.utils.UrlUtil.PROM;
import static com.wiseco.wisecoshop.utils.UtilsOther.isRegist;
import static com.wiseco.wisecoshop.utils.UtilsOther.isTrueName;

/**
 * Created by wiseco on 2018/12/4.
 */

public class LifeFragment extends BaseFragment implements AdapterView.OnItemClickListener, LoadingView.OnRetryListener, LoadingView.OnErroyClickListener {


    private final int UODATA_UI = 100;
    private final int UODATA_UI_LIST = 101;
    @Bind(R.id.back)
    ImageButton back;
    @Bind(R.id.bar_tittle)
    TextView barTittle;
    @Bind(R.id.tv_address)
    TextView tvAddress;
    @Bind(R.id.lin_address_home)
    LinearLayout linAddressHome;
    @Bind(R.id.life_banner_partner)
    XBanner lifeBannerPartner;
    @Bind(R.id.life_vip_discounts)
    TextView lifeVipDiscounts;
    @Bind(R.id.life_discounts_list)
    ListView lifeDiscountsList;
    @Bind(R.id.location_img)
    ImageView locationImg;
    @Bind(R.id.location_text)
    TextView locationText;

    @Bind(R.id.mLoadingView)
    LoadingView mLoadingView;
    @Bind(R.id.ControlableScrollView)
    ControlableScrollView ControlableScrollView;
    @Bind(R.id.channel_fragment_layout_rlt_footer)
    RelativeLayout mLoadingRlt;
    @Bind(R.id.life_gridview_channel)
    ChannelGridView lifeGridviewChannel;
    @Bind(R.id.loading_text_view)
    TextView loadingTextView;
    @Bind(R.id.loading_progressBar)
    ProgressBar loadingProgressBar;

    private String address;

    private List<ChannelBean> list = new ArrayList();
    private ArrayList<String> images = new ArrayList<>();
    private Handler mHandler;
    private LifePromBean lifePromBean;
    private NearbyShopBean nearbyShopBean;
    private List<ShopDtoBean> shopDto = new ArrayList<>();
    private int PAGE = 1;
    private boolean FinishLoad = false;

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_life, null);
        ButterKnife.bind(this, view);
        barTittle.setVisibility(View.VISIBLE);
        barTittle.setText("信用卡优惠");
        linAddressHome.setVisibility(View.VISIBLE);
        mLoadingView.setOnRetryListener(this);
        mLoadingView.notifyDataChanged(LoadingView.State.ing);
        mLoadingView.setOnErroyClickListener(this);
        MyReceiver receiver = new MyReceiver();
        getActivity().registerReceiver(receiver, new IntentFilter("com.wiseco.wisecoshop.Lifefragment"));
        try {
            address = LocationUtil.getAddress(mActivity, Double.parseDouble(CacheUtil.getString(mActivity, "LOCATION_LATITUDE_SP_KEY", "")),
                    Double.parseDouble(CacheUtil.getString(mActivity, "LOCATION_lONGITUDE_SP_KEY", "")));
            if (address != null && address.contains(",")) {
                tvAddress.setText(address.split(",")[0]);

                locationText.setText(address.split(",")[1]);
            } else {
                tvAddress.setText("北京");
                locationText.setText(address);
            }

        } catch (Exception e) {


        }
        ControlableScrollView.setOnScrollListener(new ControlableScrollView.OnScrollListener() {
            @Override
            public void onScroll(ControlableScrollView v) {

                if (nearbyShopBean.getCode().equals("N")) {
                    ToastUtils.showToast("加载完毕");
                    mLoadingRlt.setVisibility(View.GONE);

                } else {
                    mLoadingRlt.setVisibility(View.VISIBLE);
                    if (FinishLoad) {
                        FinishLoad = false;

                        Map<String, String> paramsMap = getParamsMap();
                        paramsMap.put("pageNo", (PAGE) + "");
                        paramsMap.put("latitude", CacheUtil.getString(mActivity, "LOCATION_LATITUDE_SP_KEY", "").equals("") ? LATITUDE:CacheUtil.getString(mActivity, "LOCATION_LATITUDE_SP_KEY", ""));
                        paramsMap.put("longitude", CacheUtil.getString(mActivity, "LOCATION_lONGITUDE_SP_KEY", "").equals("") ? LONGITUDE:CacheUtil.getString(mActivity, "LOCATION_lONGITUDE_SP_KEY", ""));
                        if (address != null && address.contains(",")) {
                            paramsMap.put("districtName", address.split(",")[0]);

                        } else {
                            paramsMap.put("districtName", "北京");
                        }

                        String s = gson.toJson(paramsMap);

                        i = 4;
                        OkhttpUtil.okHttpPostJson(NEARBYSHOP, s, new CallBackUtil.CallBackString() {
                            @Override
                            public void onFailure(Call call, Exception e) {

                            }

                            @Override
                            public void onResponse(String response) {
                                Log.d("TAGPAGE", PAGE + response);
                                try {
                                    nearbyShopBean = gson.fromJson(response, NearbyShopBean.class);


                                    if (nearbyShopBean.getCode().equals("S")) {
                                        List<ShopDtoBean> shopDtoBeen = nearbyShopBean.getShopDto();
                                        shopDto.addAll(shopDtoBeen);
                                        PAGE++;
                                        FinishLoad = true;
                                    }
                                    mHandler.sendEmptyMessage(UODATA_UI_LIST);
                                    mLoadingView.notifyDataChanged(LoadingView.State.done);
                                    mLoadingRlt.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            mLoadingRlt.setVisibility(View.GONE);
                                        }
                                    });
                                } catch (Exception e) {


                                }


                            }
                        });
                    }


                }


            }
        });
        //Log.d("TAG", address);
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                handleResult(msg);
            }
        };
        return view;
    }

    private void handleResult(Message msg) {

        if (msg != null) {
            switch (msg.what) {

                case UODATA_UI:

                    List<LifePromBean.BannerListBean> bannerList = lifePromBean.getBannerList();
                    if (bannerList.size() > 0) {
                        images.clear();
                        for (int i = 0; i < bannerList.size(); i++) {

                            images.add(bannerList.get(i).getImageUrl());
                        }

                        lifeBannerPartner.setData(images, null);
                        lifeBannerPartner.setmAdapter(new XBanner.XBannerAdapter() {
                            @Override
                            public void loadBanner(XBanner banner, Object model, View view, int position) {

                                Glide.with(sContext).load(images.get(position)).placeholder(R.drawable.a).error(R.drawable.a).into((ImageView) view);


                            }


                        });

                    } else {
                        //显示的图片
                        images.add("android.resource://" + mActivity.getApplicationContext().getPackageName() + "/" + R.drawable.banner_life);
                        images.add("android.resource://" + mActivity.getApplicationContext().getPackageName() + "/" + R.drawable.banner_life);
                        images.add("android.resource://" + mActivity.getApplicationContext().getPackageName() + "/" + R.drawable.banner_life);

                        lifeBannerPartner.setData(images, null);
                        lifeBannerPartner.setmAdapter(new XBanner.XBannerAdapter() {
                            @Override
                            public void loadBanner(XBanner banner, Object model, View view, int position) {

                                Glide.with(sContext).load(images.get(position)).placeholder(R.drawable.banner).error(R.drawable.banner).into((ImageView) view);

                            }


                        });


                    }
                    final List<CategoryListBean> categoryList = lifePromBean.getCategoryList();


                    // UtilsOther.setListViewHeightBasedOnChildren(lifeGridviewChannel);
                    lifeGridviewChannel.setAdapter(new GridviewChannelAdapter(mActivity, categoryList));
                    lifeGridviewChannel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Bundle b = new Bundle();
                            b.putString("channelId", categoryList.get(position).getId() + "");
                            b.putString("channelName", categoryList.get(position).getName());

                            open(DiscountChannelAcyivity.class, b);

                        }
                    });
                    break;

                case UODATA_UI_LIST:


                    lifeDiscountsList.setAdapter(new LifeDiscountsAdapter(mActivity, shopDto));
                    UtilsOther.setListViewHeightBasedOnChildren(lifeDiscountsList);
                    lifeDiscountsList.setOnItemClickListener(this);


                    break;


            }
        }
    }

    @Override
    public void initData() {
        super.initData();


        getData();

        getShopData();
    }

    private void getShopData() {

        Map<String, String> paramsMap = getParamsMap();
        paramsMap.put("latitude", CacheUtil.getString(mActivity, "LOCATION_LATITUDE_SP_KEY", "").equals("") ?  LATITUDE:CacheUtil.getString(mActivity, "LOCATION_LATITUDE_SP_KEY", "") );
        paramsMap.put("longitude", CacheUtil.getString(mActivity, "LOCATION_lONGITUDE_SP_KEY", "").equals("") ? LONGITUDE:CacheUtil.getString(mActivity, "LOCATION_lONGITUDE_SP_KEY", "") );
        if (address != null && address.contains(",")) {
            paramsMap.put("districtName", address.split(",")[0]);

        } else {
            paramsMap.put("districtName", "北京");
        }

        String s = gson.toJson(paramsMap);

        i = 4;
        OkhttpUtil.okHttpPostJson(NEARBYSHOP, s, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                Log.d("TAG", response);
                try {
                    nearbyShopBean = gson.fromJson(response, NearbyShopBean.class);

                    if (nearbyShopBean.getCode().equals("S")) {
                        List<ShopDtoBean> shopDtoBeen = nearbyShopBean.getShopDto();
                        shopDto.addAll(shopDtoBeen);
                        PAGE++;
                        FinishLoad = true;
                    }
                    mHandler.sendEmptyMessage(UODATA_UI_LIST);
                    mLoadingView.notifyDataChanged(LoadingView.State.done);

                } catch (Exception e) {


                }


            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @OnClick({R.id.lin_address_home, R.id.life_vip_discounts})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_address_home:
               /* Bundle bundle = new Bundle();
                bundle.putString("ADDRESS", address);
                open(LocationActivity.class, bundle);*/
                break;


            case R.id.life_vip_discounts:
                if (isRegist()) {
                    if (isTrueName()) {

                        String text = tvAddress.getText().toString();
                        List<CategoryListBean> categoryList = lifePromBean.getCategoryList();


                        Bundle b = new Bundle();
                        b.putSerializable("categoryList", (Serializable) categoryList);

                        b.putString("tvAddress", text);
                        open(DiscountListAcyivity.class, b);
                    } else {

                        new RegiestDialog(mActivity).show();
                    }

                } else {
                    new LoginDialog(mActivity).show();

                }

                break;
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        String shopId = shopDto.get(position).getShopId();

        Bundle bundle = new Bundle();

        bundle.putString("shopId", shopId);
        open(DiscountActivity.class, bundle);

    }


    public void getData() {
        i = 4;
        OkhttpUtil.okHttpPost(PROM, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                Log.d("TAG", response);
                try {

                    lifePromBean = gson.fromJson(response, LifePromBean.class);
                    if (lifePromBean.getCode().equals("S")) {

                        mHandler.sendEmptyMessage(UODATA_UI);
                    }


                } catch (Exception e) {


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
        getShopData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }


    private class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

            String name = intent.getExtras().getString("name");

            Map<String, String> paramsMap = getParamsMap();
           /* paramsMap.put("latitude", CacheUtil.getString(mActivity, "LOCATION_LATITUDE_SP_KEY", ""));
            paramsMap.put("longitude", CacheUtil.getString(mActivity, "LOCATION_lONGITUDE_SP_KEY", ""));
            if (address != null && address.contains(",")) {
                paramsMap.put("districtName", address.split(",")[0]);

            } else {
                paramsMap.put("districtName", "北京");
            }*/
            paramsMap.put("districtName", name);
            String s = gson.toJson(paramsMap);

            i = 4;
            OkhttpUtil.okHttpPostJson(NEARBYSHOP, s, new CallBackUtil.CallBackString() {
                @Override
                public void onFailure(Call call, Exception e) {

                }

                @Override
                public void onResponse(String response) {
                    Log.d("TAG", response);
                    try {
                        nearbyShopBean = gson.fromJson(response, NearbyShopBean.class);

                        if (nearbyShopBean.getCode().equals("S")) {

                            mHandler.sendEmptyMessage(UODATA_UI_LIST);
                            mLoadingView.notifyDataChanged(LoadingView.State.done);
                        }


                    } catch (Exception e) {


                    }


                }
            });
            Log.d("TAG-----", "我要更新了" + name);
        }
    }

    public void onResume() {
        super.onResume();
        ControlableScrollView.scrollTo(0, 0);//从其他Activity回来,让ScrollView置顶
    }

    @Override
    public void onHiddenChanged(boolean hidd) {
        if (!hidd) {//当fragment从隐藏到出现的时候
            ControlableScrollView.scrollTo(0, 0);
        }
    }
}

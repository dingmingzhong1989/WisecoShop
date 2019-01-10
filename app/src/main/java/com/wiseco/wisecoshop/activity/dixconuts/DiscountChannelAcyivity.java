package com.wiseco.wisecoshop.activity.dixconuts;

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
import com.wiseco.wisecoshop.adapter.CardListAdapter;
import com.wiseco.wisecoshop.adapter.EndLessOnScrollListener;
import com.wiseco.wisecoshop.adapter.channel.ChannelAdapter;
import com.wiseco.wisecoshop.base.BaseActivity;
import com.wiseco.wisecoshop.bean.discount.ChannelListBean;
import com.wiseco.wisecoshop.bean.discount.ShopDtoBean;
import com.wiseco.wisecoshop.okhttp.CallBackUtil;
import com.wiseco.wisecoshop.okhttp.OkhttpUtil;
import com.wiseco.wisecoshop.utils.CacheUtil;
import com.wiseco.wisecoshop.view.LoadingView;

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
import static com.wiseco.wisecoshop.view.LoadingView.State.ing;

/**
 * Created by wiseco on 2018/12/27.
 */

public class DiscountChannelAcyivity extends BaseActivity implements LoadingView.OnRetryListener, LoadingView.OnErroyClickListener {


    @Bind(R.id.back)
    ImageButton back;
    @Bind(R.id.bar_tittle)
    TextView barTittle;
    @Bind(R.id.recyclechannel)
    RecyclerView recyclechannel;
    @Bind(R.id.mLoadingView)
    LoadingView mLoadingView;
    private Handler mHandler;
    private final int UPDATA_UI = 103;
    private String channelId;
    private ChannelListBean channelListBean;
    private List<ShopDtoBean> shopDto = new ArrayList<>();
    private int  PAGE=1;
    @Override
    protected void initViews() {
        setContentView(R.layout.activity_discount_channel);
        ButterKnife.bind(this);
        channelId = getIntent().getExtras().getString("channelId");
        String channelName = getIntent().getExtras().getString("channelName");
        barTittle.setVisibility(View.VISIBLE);
        barTittle.setText(channelName);
        back.setVisibility(View.VISIBLE);
        mLoadingView.setOnRetryListener(this);
        mLoadingView.notifyDataChanged(ing);
        mLoadingView.setOnErroyClickListener(this);
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                handleResult(msg);
            }


        };
        getCodePage(channelName);
    }
    public String getCodePage( String productcode) {

        return productcode;
    }
    private void handleResult(Message msg) {
        if (msg != null) {
            switch (msg.what) {
                case UPDATA_UI:

                    GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
                    recyclechannel.setLayoutManager(layoutManager);
                    final ChannelAdapter channelAdapter = new ChannelAdapter(shopDto);
                    recyclechannel.setAdapter(channelAdapter);
                    channelAdapter.setOnItemClickListener(new CardListAdapter.OnItemClickListener() {
                        @Override
                        public void onClick(int position) {
                            String shopId = shopDto.get(position).getShopId();

                            Bundle bundle = new Bundle();

                            bundle.putString("shopId", shopId);
                            open(DiscountActivity.class, bundle);
                        }

                        @Override
                        public void onLongClick(int position) {

                        }
                    });
                    recyclechannel.addOnScrollListener(new EndLessOnScrollListener(layoutManager) {
                        @Override
                        public void onLoadMore(int currentPage) {

                            if (channelListBean.getCode().equals("N") ||channelListBean.getShopDto().size()==0) {
                                channelAdapter.setMoreTar(false);
                                channelAdapter.notifyDataSetChanged();
                                Log.d("TAG", "加载了" + currentPage);

                            }else{
                                channelAdapter.setMoreTar(true);
                                channelAdapter.notifyDataSetChanged();
                                Map<String, String> paramsMap = getParamsMap();
                                paramsMap.put("categoryId", channelId);
                                paramsMap.put("pageNo", (PAGE) + "");
                                paramsMap.put("latitude", CacheUtil.getString(sContext, "LOCATION_LATITUDE_SP_KEY", "").equals("") ? LATITUDE:CacheUtil.getString(sContext, "LOCATION_LATITUDE_SP_KEY", "")  );
                                paramsMap.put("longitude", CacheUtil.getString(sContext, "LOCATION_lONGITUDE_SP_KEY", "").equals("") ? LONGITUDE:CacheUtil.getString(sContext, "LOCATION_lONGITUDE_SP_KEY", "")  );
                                i = 4;

                                String s = gson.toJson(paramsMap);
                                OkhttpUtil.okHttpPostJson(NEARBYSHOP, s, new CallBackUtil.CallBackString() {


                                    @Override
                                    public void onFailure(Call call, Exception e) {
                                        mLoadingView.notifyDataChanged(LoadingView.State.error);
                                    }

                                    @Override
                                    public void onResponse(String response) {
                                        try {
                                            channelListBean = gson.fromJson(response, ChannelListBean.class);
                                            mLoadingView.notifyDataChanged(LoadingView.State.done);
                                            if (channelListBean.getCode().equals("S")) {
                                                List<ShopDtoBean> shopDtoBeen = channelListBean.getShopDto();
                                               /* if (shopDtoBeen.size()==0){

                                                    channelAdapter.setMoreTar(false);
                                                    channelAdapter.notifyDataSetChanged();
                                                }*/
                                                shopDto.addAll(shopDtoBeen);
                                                PAGE++;
                                                mHandler.sendEmptyMessage(UPDATA_UI);
                                            } else {

                                                mLoadingView.notifyDataChanged(LoadingView.State.done);
                                            }


                                            Log.d("TAG", response);

                                        } catch (Exception e) {
                                        }


                                    }
                                });

                            }




                        }
                    });

                    break;
            }
        }

    }

    @Override
    public boolean getStatusBarColor() {
        return true;
    }

    @Override
    protected void loadData() {
        getData();

    }

    @Override
    protected void initListener() {

    }


    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();

    }

    private void getData() {
        Map<String, String> paramsMap = getParamsMap();
        paramsMap.put("categoryId", channelId);
        paramsMap.put("latitude", CacheUtil.getString(this, "LOCATION_LATITUDE_SP_KEY", "").equals("") ? LATITUDE:CacheUtil.getString(this, "LOCATION_LATITUDE_SP_KEY", "")  );
        paramsMap.put("longitude", CacheUtil.getString(this, "LOCATION_lONGITUDE_SP_KEY", "").equals("") ? LONGITUDE:CacheUtil.getString(this, "LOCATION_lONGITUDE_SP_KEY", "")  );
        i = 4;

        String s = gson.toJson(paramsMap);
        OkhttpUtil.okHttpPostJson(NEARBYSHOP, s, new CallBackUtil.CallBackString() {


            @Override
            public void onFailure(Call call, Exception e) {
                mLoadingView.notifyDataChanged(LoadingView.State.error);
            }

            @Override
            public void onResponse(String response) {
                try {
                    channelListBean = gson.fromJson(response, ChannelListBean.class);
                    mLoadingView.notifyDataChanged(LoadingView.State.done);
                    if (channelListBean.getCode().equals("S")) {
                        List<ShopDtoBean> shopDtoBeen = channelListBean.getShopDto();
                        shopDto.addAll(shopDtoBeen);
                        PAGE++;
                        mHandler.sendEmptyMessage(UPDATA_UI);
                    } else if (channelListBean.getCode().equals("N")){

                        mLoadingView.notifyDataChanged(LoadingView.State.done);
                    }else{

                        mLoadingView.notifyDataChanged(LoadingView.State.error);
                    }


                    Log.d("TAG", response);

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
        getData();
    }
    @Override
    protected void postAgain() {
        getData();
    }
}

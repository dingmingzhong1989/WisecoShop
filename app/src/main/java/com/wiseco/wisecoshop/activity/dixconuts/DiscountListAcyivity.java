package com.wiseco.wisecoshop.activity.dixconuts;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.wiseco.wisecoshop.R;
import com.wiseco.wisecoshop.adapter.CardListAdapter;
import com.wiseco.wisecoshop.adapter.EndLessOnScrollListener;
import com.wiseco.wisecoshop.adapter.channel.ChannelAdapter;
import com.wiseco.wisecoshop.adapter.drop.ConstellationAdapter;
import com.wiseco.wisecoshop.adapter.drop.GirdDropDownAdapter;
import com.wiseco.wisecoshop.adapter.drop.ListDropDownAdapter;
import com.wiseco.wisecoshop.base.BaseActivity;
import com.wiseco.wisecoshop.bean.discount.CategoryListBean;
import com.wiseco.wisecoshop.bean.discount.ShopDtoBean;
import com.wiseco.wisecoshop.bean.discount.ShopVipBean;
import com.wiseco.wisecoshop.bean.user.QueryBankBean;
import com.wiseco.wisecoshop.bean.user.UserBankListBean;
import com.wiseco.wisecoshop.dialog.AddCardDialog;
import com.wiseco.wisecoshop.okhttp.CallBackUtil;
import com.wiseco.wisecoshop.okhttp.OkhttpUtil;
import com.wiseco.wisecoshop.utils.CacheUtil;
import com.wiseco.wisecoshop.view.LoadingView;
import com.yyydjk.library.DropDownMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
import static com.wiseco.wisecoshop.utils.UrlUtil.QUERYUSBAERNKLIST;


/**
 * Created by wiseco on 2018/12/15.
 */

public class DiscountListAcyivity extends BaseActivity implements LoadingView.OnRetryListener, LoadingView.OnErroyClickListener {
    @Bind(R.id.back)
    ImageButton back;
    @Bind(R.id.bar_tittle)
    TextView barTittle;
    @Bind(R.id.dropDownMenu)
    DropDownMenu dropDownMenu;
    @Bind(R.id.discount_listview)
    RecyclerView discountListview;
    @Bind(R.id.mLoadingView)
    LoadingView mLoadingView;
    private List<View> popupViews = new ArrayList<>();

    private String headers[] = {"美食", "银行","距离优先"};
    // private String citys[] = {"美食", "火锅", "川菜", "徽菜", "粤菜", "东北菜"};

    List<String> listChannel = new ArrayList<>();
    List<String> listBank = new ArrayList<>();
    List<String> listId = new ArrayList<>();

    Map<String, Integer> stringMap = new HashMap<>();
    //private String ages[] = {"卡种", "白金卡", "金卡", "普卡", "标准卡", "车主卡"};
    // private String constellations[] = {"银行", "交通", "招商", "上海", "浦发"};
    private String poistion[] = {"距离优先"};
    private GirdDropDownAdapter cityAdapter;
    private ListDropDownAdapter ageAdapter;
    private ListDropDownAdapter sexAdapter;
    private ConstellationAdapter constellationAdapter;
    private int constellationPosition = 0;
    private Handler mHandler;

    private final int UPDATA_UI = 104;
    private String tvAddress;
    private ShopVipBean shopVipBean;
    private List<CategoryListBean> categoryList;
    private ListView cityView;
    private ListView sexView;
    private ListView ageView;
    private String bankId = "";
    private int  PAGE=1;
    /* private int[] types = new int[]{DropDownMenu.TYPE_LIST_CITY, DropDownMenu.TYPE_GRID,
            DropDownMenu.TYPE_LIST_SIMPLE, DropDownMenu.TYPE_LIST_SIMPLE};*/
    List<ShopDtoBean> shopDto = new ArrayList<>();
    @Override
    public boolean getStatusBarColor() {
        return true;
    }

    @Override
    protected void loadData() {
        getData();
        initView();

        initEvent();
    }
    @Override
    protected void postAgain() {
        getData();
    }
    @Override
    protected void initListener() {

    }

    @Override
    protected void initViews() {

        setContentView(R.layout.activity_discount_list);
        ButterKnife.bind(this);

        back.setVisibility(View.VISIBLE);
        barTittle.setVisibility(View.VISIBLE);
        barTittle.setText("专属优惠");
        tvAddress = getIntent().getExtras().getString("tvAddress");
        categoryList = (List<CategoryListBean>) getIntent().getSerializableExtra("categoryList");

        for (int i = 0; i < categoryList.size(); i++) {
            listChannel.add(categoryList.get(i).getName());
            stringMap.put(categoryList.get(i).getName(), categoryList.get(i).getId());
        }
        mLoadingView.notifyDataChanged(LoadingView.State.ing);
        mLoadingView.setOnRetryListener(this);

        mLoadingView.setOnErroyClickListener(this);
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                handleResult(msg);
            }


        };


    }

    private void handleResult(Message msg) {
        if (msg != null) {
            switch (msg.what) {
                case UPDATA_UI:


                    //final List<ShopDtoBean> shopDto = shopVipBean.getShopDto();
                    GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
                    discountListview.setLayoutManager(layoutManager);
                    final ChannelAdapter channelAdapter = new ChannelAdapter(shopDto);
                    discountListview.setAdapter(channelAdapter);
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
                    discountListview.addOnScrollListener(new EndLessOnScrollListener(layoutManager) {
                        @Override
                        public void onLoadMore(int currentPage) {
                            if (shopVipBean.getCode().equals("N")) {

                                channelAdapter.setMoreTar(false);
                                channelAdapter.notifyDataSetChanged();
                                Log.d("TAG", "加载了" + currentPage);
                            }else{
                                channelAdapter.setMoreTar(true);
                                channelAdapter.notifyDataSetChanged();
                                Map<String, String> paramsMap = getParamsMap();
                                paramsMap.put("bankIds", bankId);
                                paramsMap.put("categoryId", "");
                                paramsMap.put("pageNo", (PAGE) + "");
                                paramsMap.put("latitude", CacheUtil.getString(sContext, "LOCATION_LATITUDE_SP_KEY", "").equals("") ? LATITUDE:CacheUtil.getString(sContext, "LOCATION_LATITUDE_SP_KEY", "")  );
                                paramsMap.put("longitude", CacheUtil.getString(sContext, "LOCATION_lONGITUDE_SP_KEY", "").equals("") ? LONGITUDE:CacheUtil.getString(sContext, "LOCATION_lONGITUDE_SP_KEY", "") );

                                paramsMap.put("districtName", tvAddress);

                                String s = gson.toJson(paramsMap);

                                i = 4;
                                OkhttpUtil.okHttpPostJson(NEARBYSHOP, s, new CallBackUtil.CallBackString() {
                                    @Override
                                    public void onFailure(Call call, Exception e) {
                                        mLoadingView.notifyDataChanged(LoadingView.State.empty);
                                    }

                                    @Override
                                    public void onResponse(String response) {

                                        Log.d("TAG", response);
                                        try {
                                            shopVipBean = gson.fromJson(response, ShopVipBean.class);

                                            if (shopVipBean.getCode().equals("S")) {
                                                List<ShopDtoBean> shopDtoBeanList = shopVipBean.getShopDto();
                                                shopDto.addAll(shopDtoBeanList);
                                                mHandler.sendEmptyMessage(UPDATA_UI);
                                                PAGE++;
                                                mLoadingView.notifyDataChanged(LoadingView.State.done);
                                            }

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

    private void initView() {
        //init city menu
        cityView = new ListView(this);


        cityAdapter = new GirdDropDownAdapter(this, listChannel);
        cityView.setDividerHeight(0);
        cityView.setAdapter(cityAdapter);

        //init age menu
        ageView = new ListView(this);
        ageView.setDividerHeight(0);
        ageAdapter = new ListDropDownAdapter(this, listBank);
        ageView.setAdapter(ageAdapter);

        //init sex menu
        sexView = new ListView(this);
        sexView.setDividerHeight(0);
        sexAdapter = new ListDropDownAdapter(this, Arrays.asList(poistion));
        sexView.setAdapter(sexAdapter);

        /*final ListView constellation = new ListView(this);
        constellation.setDividerHeight(0);
        constellationAdapter = new ConstellationAdapter(this, Arrays.asList(poistion));
        constellation.setAdapter(constellationAdapter);*/

        //init popupViews
        popupViews.add(cityView);
        popupViews.add(ageView);
        popupViews.add(sexView);

       /* constellation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                constellationAdapter.setCheckItem(position);
                constellationPosition = position;
            }
        });*/

        //init context view
        TextView contentView = new TextView(this);
        contentView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        contentView.setText("");
        contentView.setGravity(Gravity.CENTER);
        contentView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 1);

        //init dropdownview

        Log.d("TAG",Arrays.asList(headers).size()+"===="+popupViews.size());
        dropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, contentView);
    }

    public void initEvent() {

        // popupViews.add(constellation);

        //add item click event
        cityView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cityAdapter.setCheckItem(position);
                dropDownMenu.setTabText(position == 0 ? headers[0] : listChannel.get(position));
                dropDownMenu.closeMenu();

                Map<String, String> paramsMap = getParamsMap();
                // paramsMap.put("bankIds", );
                paramsMap.put("categoryId", stringMap.get(listChannel.get(position)) + "");
                paramsMap.put("latitude", CacheUtil.getString(sContext, "LOCATION_LATITUDE_SP_KEY", "").equals("") ? LATITUDE:CacheUtil.getString(sContext, "LOCATION_LATITUDE_SP_KEY", "") );
                paramsMap.put("longitude", CacheUtil.getString(sContext, "LOCATION_lONGITUDE_SP_KEY", "").equals("") ? LONGITUDE:CacheUtil.getString(sContext, "LOCATION_lONGITUDE_SP_KEY", "") );
                paramsMap.put("districtName", tvAddress);

                String s = gson.toJson(paramsMap);

                i = 4;
                OkhttpUtil.okHttpPostJson(NEARBYSHOP, s, new CallBackUtil.CallBackString() {
                    @Override
                    public void onFailure(Call call, Exception e) {
                        mLoadingView.notifyDataChanged(LoadingView.State.empty);
                    }

                    @Override
                    public void onResponse(String response) {

                        Log.d("TAG", response);
                        try {
                            shopVipBean = gson.fromJson(response, ShopVipBean.class);

                            if (shopVipBean.getCode().equals("S")) {

                                mHandler.sendEmptyMessage(UPDATA_UI);

                                mLoadingView.notifyDataChanged(LoadingView.State.done);
                            }

                        } catch (Exception e) {


                        }


                    }
                });


            }
        });

        ageView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ageAdapter.setCheckItem(position);
                dropDownMenu.setTabText(position == 0 ? headers[1] : listBank.get(position));
                dropDownMenu.closeMenu();
                Map<String, String> paramsMap = getParamsMap();
                 paramsMap.put("bankIds",listId.get(position) );
                paramsMap.put("categoryId", stringMap.get(listChannel.get(position)) + "");
                paramsMap.put("latitude", CacheUtil.getString(sContext, "LOCATION_LATITUDE_SP_KEY", "").equals("") ? LATITUDE:CacheUtil.getString(sContext, "LOCATION_LATITUDE_SP_KEY", "")  );
                paramsMap.put("longitude", CacheUtil.getString(sContext, "LOCATION_lONGITUDE_SP_KEY", "").equals("") ? LONGITUDE:CacheUtil.getString(sContext, "LOCATION_lONGITUDE_SP_KEY", "") );

                paramsMap.put("districtName", tvAddress);

                String s = gson.toJson(paramsMap);

                i = 4;
                OkhttpUtil.okHttpPostJson(NEARBYSHOP, s, new CallBackUtil.CallBackString() {
                    @Override
                    public void onFailure(Call call, Exception e) {
                        mLoadingView.notifyDataChanged(LoadingView.State.empty);
                    }

                    @Override
                    public void onResponse(String response) {

                        Log.d("TAG", response);
                        try {
                            shopVipBean = gson.fromJson(response, ShopVipBean.class);

                            if (shopVipBean.getCode().equals("S")) {

                                mHandler.sendEmptyMessage(UPDATA_UI);

                                mLoadingView.notifyDataChanged(LoadingView.State.done);
                            }

                        } catch (Exception e) {


                        }


                    }
                });

            }
        });

        sexView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sexAdapter.setCheckItem(position);
                dropDownMenu.setTabText(position == 0 ? headers[2] : poistion[position]);
                dropDownMenu.closeMenu();
            }
        });
    }

    @OnClick({R.id.back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;


        }
    }

    private void getData() {


        i = 4;
        OkhttpUtil.okHttpPost(QUERYUSBAERNKLIST, new CallBackUtil.CallBackString() {


            @Override
            public void onFailure(Call call, Exception e) {
                mLoadingView.notifyDataChanged(LoadingView.State.error);
            }

            @Override
            public void onResponse(String response) {
                Log.d("TAG", response);
                try {
                    QueryBankBean queryBankBean = gson.fromJson(response, QueryBankBean.class);


                    // mLoadingView.notifyDataChanged(LoadingView.State.done);
                    if (queryBankBean.getCode().equals("S")) {

                        List<UserBankListBean> userBankList = queryBankBean.getUserBankList();

                        for (int i = 0; i < userBankList.size(); i++) {
                            bankId += userBankList.get(i).getId() + ",";

                            listBank.add(userBankList.get(i).getName());
                            listId.add(userBankList.get(i).getId()+"");

                        }
                        Log.d("TAG", bankId);

                        Map<String, String> paramsMap = getParamsMap();
                        paramsMap.put("bankIds", bankId);
                        paramsMap.put("categoryId", "");
                        paramsMap.put("latitude", CacheUtil.getString(sContext, "LOCATION_LATITUDE_SP_KEY", "").equals("") ? LATITUDE:CacheUtil.getString(sContext, "LOCATION_LATITUDE_SP_KEY", "")  );
                        paramsMap.put("longitude", CacheUtil.getString(sContext, "LOCATION_lONGITUDE_SP_KEY", "").equals("") ? LONGITUDE:CacheUtil.getString(sContext, "LOCATION_lONGITUDE_SP_KEY", "") );

                        paramsMap.put("districtName", tvAddress);

                        String s = gson.toJson(paramsMap);

                        i = 4;
                        OkhttpUtil.okHttpPostJson(NEARBYSHOP, s, new CallBackUtil.CallBackString() {
                            @Override
                            public void onFailure(Call call, Exception e) {
                                mLoadingView.notifyDataChanged(LoadingView.State.empty);
                            }

                            @Override
                            public void onResponse(String response) {

                                Log.d("TAG", response);
                                try {
                                    shopVipBean = gson.fromJson(response, ShopVipBean.class);

                                    if (shopVipBean.getCode().equals("S")) {
                                        List<ShopDtoBean> shopDtoBeanList = shopVipBean.getShopDto();
                                        shopDto.addAll(shopDtoBeanList);
                                        mHandler.sendEmptyMessage(UPDATA_UI);
                                        PAGE++;
                                        mLoadingView.notifyDataChanged(LoadingView.State.done);
                                    }

                                } catch (Exception e) {


                                }


                            }
                        });


                    } else if (queryBankBean.getCode().equals("N")){

                        new AddCardDialog(DiscountListAcyivity.this).show();

                        mLoadingView.notifyDataChanged(LoadingView.State.error);



                    }else{

                        mLoadingView.notifyDataChanged(LoadingView.State.error);
                    }


                } catch (Exception e) {
                }


            }
        });

    }

    @Override
    public void onRetry() {

    }

    @Override
    public void erroyClick() {
        getData();

    }

    @Override
    public void onBackPressed() {
        //退出activity前关闭菜单
        if (dropDownMenu.isShowing()) {
            dropDownMenu.closeMenu();
        } else {
            super.onBackPressed();
        }
    }


}

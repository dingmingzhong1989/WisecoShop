package com.wiseco.wisecoshop.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;
import com.wiseco.wisecoshop.R;
import com.wiseco.wisecoshop.activity.goods.CardDetailActivity;
import com.wiseco.wisecoshop.activity.goods.GoodsListActivity;
import com.wiseco.wisecoshop.activity.goods.MoneyDetailActivity;
import com.wiseco.wisecoshop.activity.user.MessageActivity;
import com.wiseco.wisecoshop.adapter.GridviewPartnerGAdapter;
import com.wiseco.wisecoshop.adapter.MainCardLiseViewAdapter;
import com.wiseco.wisecoshop.adapter.MainMoneyLiseViewAdapter;
import com.wiseco.wisecoshop.base.BaseFragment;
import com.wiseco.wisecoshop.bean.BannerBean;
import com.wiseco.wisecoshop.bean.HomeBean;
import com.wiseco.wisecoshop.bean.PartnerBean;
import com.wiseco.wisecoshop.bean.goods.CcRLBean;
import com.wiseco.wisecoshop.bean.goods.ClRLBean;
import com.wiseco.wisecoshop.bean.goods.MainFragmentGoodsBean;
import com.wiseco.wisecoshop.dialog.LoginDialog;
import com.wiseco.wisecoshop.okhttp.CallBackUtil;
import com.wiseco.wisecoshop.okhttp.OkhttpUtil;
import com.wiseco.wisecoshop.utils.ToastUtils;
import com.wiseco.wisecoshop.utils.UtilsOther;
import com.wiseco.wisecoshop.view.ListViewForScrollView;
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
import static com.wiseco.wisecoshop.utils.HttpPostUtils.getParamsMap;
import static com.wiseco.wisecoshop.utils.UrlUtil.APPDFPRDLIST;
import static com.wiseco.wisecoshop.utils.UrlUtil.HORSE;
import static com.wiseco.wisecoshop.utils.UtilsOther.isRegist;

/**
 * Created by wiseco on 2018/12/4.
 */

public class HomeFragment extends BaseFragment implements AdapterView.OnItemClickListener, LoadingView.OnRetryListener, LoadingView.OnErroyClickListener {


    @Bind(R.id.back)
    ImageButton back;
    @Bind(R.id.bar_tittle)
    TextView barTittle;
    @Bind(R.id.message)
    ImageButton message;
    @Bind(R.id.banner_partner)
    XBanner bannerPartner;
    @Bind(R.id.regiest)
    ImageView regiest;
    @Bind(R.id.fragment_home_new_user)
    RelativeLayout fragmentHomeNewUser;
    @Bind(R.id.fragmeng_home_more_card)
    TextView fragmengHomeMoreCard;
    @Bind(R.id.fragmeng_home_card_list)
    ListViewForScrollView fragmengHomeCardList;
    @Bind(R.id.fragmeng_home_more_money)
    TextView fragmengHomeMoreMoney;
    @Bind(R.id.fragmeng_home_money_list)
    ListViewForScrollView fragmengHomeMoneyList;
    @Bind(R.id.gridview_partner)
    GridView gridviewPartner;
    @Bind(R.id.mLoadingView)
    LoadingView mLoadingView;
    @Bind(R.id.myScrollView)
    ScrollView myScrollView;

    private Handler mHandler;
    private final int UODATA_UI = 200;
    private MainFragmentGoodsBean mainFragmentGoodsBean;
    private List<CcRLBean> ccRL;
    private List<ClRLBean> clRL;
    ArrayList<String> images = new ArrayList<>();

    @Override
    public View initView() {

        View view = View.inflate(mActivity, R.layout.fragment_home, null);
        ButterKnife.bind(this, view);

        //显示的图片
        MyReceiver receiver = new MyReceiver();
        getActivity().registerReceiver(receiver, new IntentFilter("com.wiseco.wisecoshop.fragment"));

      /*  images.add("android.resource://" + mActivity.getApplicationContext().getPackageName() + "/" + R.drawable.banner);
        images.add("android.resource://" + mActivity.getApplicationContext().getPackageName() + "/" + R.drawable.banner);
        images.add("android.resource://" + mActivity.getApplicationContext().getPackageName() + "/" + R.drawable.banner);*/

        mLoadingView.setOnRetryListener(this);
        mLoadingView.notifyDataChanged(LoadingView.State.done);
        mLoadingView.setOnErroyClickListener(this);
        if (isRegist()) {
            regiest.setVisibility(View.INVISIBLE);

        } else {

        }


        barTittle.setText("产品推荐");
        barTittle.setVisibility(View.VISIBLE);
        message.setVisibility(View.INVISIBLE);
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
                    if (ccRL.size() != 0 && ccRL != null) {


                        fragmengHomeCardList.setAdapter(new MainCardLiseViewAdapter(mActivity, ccRL));

                        UtilsOther.setListViewHeightBasedOnChildren(fragmengHomeCardList);
                        fragmengHomeCardList.setOnItemClickListener(this);
                    } else {
                    }

                    if (clRL.size() != 0 && clRL != null) {
                        fragmengHomeMoneyList.setAdapter(new MainMoneyLiseViewAdapter(mActivity, clRL));

                        UtilsOther.setListViewHeightBasedOnChildren(fragmengHomeMoneyList);

                        fragmengHomeMoneyList.setOnItemClickListener(this);
                    } else {
                    }


                    mLoadingView.notifyDataChanged(LoadingView.State.done);


                    break;

            }
        }

    }

    @Override
    public void initData() {

        super.initData();
        getData();
        getOther();

    }

    private void getOther() {
        i = 4;
        Map<String, String> paramsMap = getParamsMap();
        paramsMap.put("Id", "");
        String s = gson.toJson(paramsMap);
        OkhttpUtil.okHttpPostJson(HORSE, s, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {

                ToastUtils.showToast("无网络请检查网络设置");
            }

            @Override
            public void onResponse(String response) {

                try {
                    Log.d("TAG+++++++", response);
                    HomeBean homeBean = gson.fromJson(response, HomeBean.class);

                    if (homeBean.getLittleRabbit()) {

                    } else {

                        regiest.setVisibility(View.INVISIBLE);

                    }
                    List<BannerBean> banner = homeBean.getBanner();
                    for (int i = 0; i < banner.size(); i++) {

                        images.add(banner.get(i).getImageUrl());

                    }
                    bannerPartner.setData(images, null);
                    bannerPartner.setmAdapter(new XBanner.XBannerAdapter() {
                        @Override
                        public void loadBanner(XBanner banner, Object model, View view, int position) {

                            Glide.with(sContext).load(images.get(position)).placeholder(R.drawable.banner).error(R.drawable.banner).into((ImageView) view);

                        }


                    });
                    List<PartnerBean> partner = homeBean.getPartner();

                    gridviewPartner.setAdapter(new GridviewPartnerGAdapter(mActivity, partner));


                } catch (Exception e) {


                }

            }
        });


    }

    public void getData() {
        i = 4;
        Map<String, String> paramsMap = getParamsMap();
        paramsMap.put("Id", "");
        String s = gson.toJson(paramsMap);

        OkhttpUtil.okHttpPostJson(APPDFPRDLIST, s, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                Log.d("TAG", "首页数据"+response);
                try {
                    mainFragmentGoodsBean = gson.fromJson(response, MainFragmentGoodsBean.class);

                    if (mainFragmentGoodsBean.getCode().equals("S")) {
                        ccRL = mainFragmentGoodsBean.getCcRL();
                        clRL = mainFragmentGoodsBean.getClRL();
                        getMainCardCode(ccRL);
                        getMainMoneyCode(clRL);
                        mHandler.sendEmptyMessage(UODATA_UI);
                    }


                } catch (Exception e) {


                }


            }
        });
    }

    private  List<String> getMainMoneyCode(List<ClRLBean> clRL) {
        List<String> stringList=null;

        if (stringList==null){
            stringList = new ArrayList<>();
        }
        for (int i = 0; i < clRL.size(); i++) {

            stringList.add(clRL.get(i).getCode());
        }


        return stringList;

    }

    private  List<String> getMainCardCode(List<CcRLBean> ccRL) {
        List<String> stringList=null;

        if (stringList==null){
            stringList = new ArrayList<>();
        }
        for (int i = 0; i < ccRL.size(); i++) {

            stringList.add(ccRL.get(i).getCode());
        }


        return stringList;
    }

    @OnClick({R.id.message, R.id.fragmeng_home_more_card, R.id.fragmeng_home_more_money, R.id.regiest})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.message:

                open(MessageActivity.class);

                break;
            case R.id.fragmeng_home_more_card:

                Bundle bundle1 = new Bundle();
                bundle1.putInt("tag", 0);
                if (isRegist()) {
                    open(GoodsListActivity.class, bundle1);
                   /* if (isTrueName()) {
                        open(GoodsListActivity.class, bundle1);
                    }else{

                        new RegiestDialog(mActivity).show();
                    }*/
                } else {
                    new LoginDialog(mActivity).show();

                }

                break;
            case R.id.fragmeng_home_more_money:
                Bundle bundle = new Bundle();
                bundle.putInt("tag", 1);
                if (isRegist()) {
                    open(GoodsListActivity.class, bundle);
                   /* if (isTrueName()) {

                    }else{

                        new RegiestDialog(mActivity).show();
                    }*/

                    // finish();
                } else {
                    new LoginDialog(mActivity).show();
                    // open(LoginActivity.class, bundle1);
                    // open(RegistIDCardActivity.class, bundle1);
                    //open(PhoneCodeActivity.class, bundle1);
                }

                break;

            case R.id.regiest:

                if (isRegist()) {
                  /*  if (isTrueName()) {

                    }else{

                        new RegiestDialog(mActivity).show();
                    }*/

                } else {
                    new LoginDialog(mActivity).show();

                }
                break;
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        getTagId();

        switch (parent.getId()) {

            case R.id.fragmeng_home_card_list:

                if (isRegist()) {
                    Log.d("TAG", "跳转详情页");
                    Bundle bundle = new Bundle();
                    bundle.putString("Smallicon", ccRL.get(position).getSmallicon());

                    Log.d("TAG", "点击了" + position);
                    bundle.putString("productcode", ccRL.get(position).getCode());

                    open(CardDetailActivity.class, bundle);

                } else {
                    new LoginDialog(mActivity).show();

                }


                break;


            case R.id.fragmeng_home_money_list:
                if (isRegist()) {
                    Bundle bundle = new Bundle();
                    bundle.putString("productcode", clRL.get(position).getCode());
                    open(MoneyDetailActivity.class, bundle);

                } else {
                    new LoginDialog(mActivity).show();

                }
                break;


        }


    }

    private String getTagId() {

        return "PFG_OO";
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
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

            if (isRegist()) {
                regiest.setVisibility(View.INVISIBLE);

            } else {

            }
            getData();
          //  getOther();

            Log.d("TAG-----", "我要更新了");
        }
    }

    public void onResume() {
        super.onResume();
        getData();
       // getOther();
        myScrollView.scrollTo(0, 0);//从其他Activity回来,让ScrollView置顶
    }

    @Override
    public void onHiddenChanged(boolean hidd) {
        if (!hidd) {
            //当fragment从隐藏到出现的时候
           // getData();
           // getOther();

            myScrollView.scrollTo(0, 0);
        }
    }
}

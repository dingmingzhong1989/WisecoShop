package com.wiseco.wisecoshop.activity.dixconuts;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.wiseco.wisecoshop.R;
import com.wiseco.wisecoshop.base.BaseActivity;
import com.wiseco.wisecoshop.bean.discount.DiscountDetailBean;
import com.wiseco.wisecoshop.bean.discount.PromActivityBean;
import com.wiseco.wisecoshop.okhttp.CallBackUtil;
import com.wiseco.wisecoshop.okhttp.OkhttpUtil;
import com.wiseco.wisecoshop.view.LoadingView;

import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

import static com.wiseco.wisecoshop.MyApplication.gson;
import static com.wiseco.wisecoshop.MyApplication.i;
import static com.wiseco.wisecoshop.utils.HttpPostUtils.getParamsMap;
import static com.wiseco.wisecoshop.utils.UrlUtil.ACTIVEDETAIL;

/**
 * Created by wiseco on 2018/12/17.
 */

public class DiscountDetailAcyivity extends BaseActivity implements LoadingView.OnRetryListener, LoadingView.OnErroyClickListener {
    @Bind(R.id.back)
    ImageButton back;
    @Bind(R.id.bar_tittle)
    TextView barTittle;
    @Bind(R.id.discount_detail_image)
    ImageView discountDetailImage;
    @Bind(R.id.mLoadingView)
    LoadingView mLoadingView;
    @Bind(R.id.discount_detail_title)
    TextView discountDetailTitle;
    @Bind(R.id.discount_detail_time)
    TextView discountDetailTime;
    @Bind(R.id.discount_detail_address)
    TextView discountDetailAddress;
    @Bind(R.id.discount_detail_person)
    TextView discountDetailPerson;
    @Bind(R.id.discount_detail_backname)
    TextView discountDetailBackname;
    @Bind(R.id.discount_detail_content)
    TextView discountDetailContent;
    @Bind(R.id.discount_detail_notice)
    TextView discountDetailNotice;
    @Bind(R.id.discount_detail_way)
    TextView discountDetailWay;
    private String id;
    private Handler mHandler;
    private final int UPDATA_UI = 105;
    private DiscountDetailBean discountDetailBean;

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
    @Override
    protected void postAgain() {
        getData();
    }
    @Override
    protected void initViews() {
        setContentView(R.layout.activity_discount_detail);
        ButterKnife.bind(this);
        back.setVisibility(View.VISIBLE);
        barTittle.setVisibility(View.VISIBLE);
        barTittle.setText("优惠详情");
        id = getIntent().getExtras().getString("id");

        mLoadingView.setOnRetryListener(this);
        mLoadingView.notifyDataChanged(LoadingView.State.ing);
        mLoadingView.setOnErroyClickListener(this);
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                handleResult(msg);
            }


        };
        ;
    }

    private void handleResult(Message msg) {
        if (msg != null) {
            switch (msg.what) {
                case UPDATA_UI:
                    PromActivityBean promActivity = discountDetailBean.getPromActivity();

                    discountDetailTitle.setText(promActivity.getTitle());
                    discountDetailTime.setText(promActivity.getAvailabletime());
                    discountDetailAddress.setText(promActivity.getAvailablecity());
                    discountDetailBackname.setText(promActivity.getBankname());
                    discountDetailContent.setText(promActivity.getContent());
                    discountDetailNotice.setText(promActivity.getNote());
                    discountDetailWay.setText(promActivity.getWay()+"  "+promActivity.getType());
                    Glide.with(this).load(promActivity.getBigimgurl()).error(R.drawable.banner).into(new SimpleTarget<GlideDrawable>() {
                        @Override
                        public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                            discountDetailImage.setImageDrawable(resource);
                            mLoadingView.notifyDataChanged(LoadingView.State.done);
                        }
                    });

                    break;
            }
        }

    }

    private void getData() {
        Map<String, String> paramsMap = getParamsMap();
        paramsMap.put("id", id);

        i = 4;

        String s = gson.toJson(paramsMap);
        OkhttpUtil.okHttpPostJson(ACTIVEDETAIL, s, new CallBackUtil.CallBackString() {


            @Override
            public void onFailure(Call call, Exception e) {
                mLoadingView.notifyDataChanged(LoadingView.State.error);
            }

            @Override
            public void onResponse(String response) {
                try {
                    discountDetailBean = gson.fromJson(response, DiscountDetailBean.class);
                  //  mLoadingView.notifyDataChanged(LoadingView.State.done);
                    if (discountDetailBean.getCode().equals("S")) {

                        mHandler.sendEmptyMessage(UPDATA_UI);
                    } else {

                        mLoadingView.notifyDataChanged(LoadingView.State.error);
                    }


                    Log.d("TAG", response);

                } catch (Exception e) {
                }


            }
        });

    }


    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onRetry() {

    }

    @Override
    public void erroyClick() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

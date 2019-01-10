package com.wiseco.wisecoshop.activity.goods;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.wiseco.wisecoshop.R;
import com.wiseco.wisecoshop.base.BaseActivity;
import com.wiseco.wisecoshop.bean.CardDetailBean;
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
import static com.wiseco.wisecoshop.MyApplication.sContext;
import static com.wiseco.wisecoshop.utils.HttpPostUtils.getParamsMap;
import static com.wiseco.wisecoshop.utils.UrlUtil.BASE;
import static com.wiseco.wisecoshop.utils.UrlUtil.GOOSDSDEDAIL;
import static com.wiseco.wisecoshop.utils.UtilsOther.isFastClick;
import static com.wiseco.wisecoshop.utils.UtilsOther.setKeyWord;



/*
 * Created by wiseco on 2018/10/17.
 */

public class CardDetailActivity extends BaseActivity implements LoadingView.OnRetryListener, LoadingView.OnErroyClickListener {

    @Bind(R.id.back)
    ImageButton back;
    @Bind(R.id.bar_tittle)
    TextView barTittle;
    @Bind(R.id.tv_address)
    TextView tvAddress;
    @Bind(R.id.lin_address_home)
    LinearLayout linAddressHome;
    @Bind(R.id.message)
    ImageButton message;
    @Bind(R.id.line)
    View line;
    @Bind(R.id.card_image)
    ImageView cardImage;
    @Bind(R.id.card_name)
    TextView cardName;
    @Bind(R.id.card_detail)
    LinearLayout cardDetail;
    @Bind(R.id.Lin)
    LinearLayout Lin;
    @Bind(R.id.characteristic)
    TextView characteristic;
    @Bind(R.id.cardguide)
    CardView cardguide;
    @Bind(R.id.applycondition)
    TextView applycondition;
    @Bind(R.id.appay)
    ImageButton appay;
    private Bundle bundle;
    private String productcode;
    @Bind(R.id.mLoadingView)
    LoadingView mLoadingView;
    private CardDetailBean cardDetailBean;

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_carddetail);
        ButterKnife.bind(this);
        back.setVisibility(View.VISIBLE);
        barTittle.setVisibility(View.VISIBLE);
        bundle = getIntent().getExtras();
        productcode = bundle.getString("productcode");

        mLoadingView.setOnRetryListener(this);
        mLoadingView.notifyDataChanged(LoadingView.State.ing);
        mLoadingView.setOnErroyClickListener(this);
        getCodePage( productcode);
    }
    public String getCodePage( String productcode) {

        return productcode;
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
        Map<String, String> paramsMap = getParamsMap();
        paramsMap.put("productcode", productcode);
        OkhttpUtil.okHttpPost(GOOSDSDEDAIL, paramsMap, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                Log.d("TAG", "ExceptionExceptionExceptionException=====" + e);
                mLoadingView.notifyDataChanged(LoadingView.State.error);
            }

            @Override
            public void onResponse(String response) {

                try {
                    Log.d("TAG", "responsePassIdCardActivity" + response);
                    cardDetailBean = gson.fromJson(response, CardDetailBean.class);
                    if (cardDetailBean.getCode().equals("S")) {

                        barTittle.setText(cardDetailBean.getProductdtl().getBankname());
                        Glide.with(sContext).load(BASE + bundle.get("Smallicon")).error(R.drawable.card_img).into(
                                new SimpleTarget<GlideDrawable>() {
                                    @Override
                                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                                        cardImage.setImageDrawable(resource);
                                        mLoadingView.notifyDataChanged(LoadingView.State.done);
                                    }
                                });

                        cardName.setText(cardDetailBean.getProductdtl().getName());
                        setKeyWord(sContext, cardDetailBean.getProductdtl().getKeyword(), Lin, "gray", 4);
                        String characteristic = cardDetailBean.getProductdtl().getCharacteristic();
                        String replace = characteristic.replace("|", "\n");

                        CardDetailActivity.this.characteristic.setText(replace);
                        applycondition.setText(cardDetailBean.getProductdtl().getApplycondition().replace("|", "\n"));
                       // HttpPostUtils.putEventTag(getUserId(), "", cardDetailBean.getProductdtl().getBankname(), "CC", CONTENT_EVENT_PRODUCT_DETAIL, cardDetailBean.getProductdtl().getName());
                    } else {

                        mLoadingView.notifyDataChanged(LoadingView.State.error);

                    }
                } catch (Exception e) {
                    mLoadingView.notifyDataChanged(LoadingView.State.error);
                }


            }
        });
    }

    @Override
    public boolean getStatusBarColor() {
        return true;
    }

    @Override
    public void initListener() {

    }


    @OnClick({R.id.back, R.id.appay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();


                break;
            case R.id.appay:

                if (isFastClick()) {

                } else {
                    //HttpPostUtils.putEventTag(getUserId(), "", cardDetailBean.getProductdtl().getBankname(), "CC", CONTENT_EVENT_APPAY, cardDetailBean.getProductdtl().getName());
                     open(AppayActivity.class, bundle);
                }

                break;
        }
    }

    @Override
    public void onRetry() {

    }

    @Override
    public void erroyClick() {
        getData();
    }


}

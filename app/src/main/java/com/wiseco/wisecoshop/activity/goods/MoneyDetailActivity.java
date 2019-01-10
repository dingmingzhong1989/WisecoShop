package com.wiseco.wisecoshop.activity.goods;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wiseco.wisecoshop.R;
import com.wiseco.wisecoshop.base.BaseActivity;
import com.wiseco.wisecoshop.bean.MoneyDetailBean;
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
import static com.wiseco.wisecoshop.utils.UtilsOther.addComma;
import static com.wiseco.wisecoshop.utils.UtilsOther.isFastClick;
import static com.wiseco.wisecoshop.utils.UtilsOther.setKeyWord;

/**
 * Created by wiseco on 2018/10/17.
 */

public class MoneyDetailActivity extends BaseActivity implements LoadingView.OnRetryListener, LoadingView.OnErroyClickListener {
    private static final int UPDATA_UI = 100;
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
    @Bind(R.id.maxlimit)
    TextView maxlimit;
    @Bind(R.id.lin_max)
    LinearLayout linMax;
    @Bind(R.id.minrate)
    TextView minrate;
    @Bind(R.id.approvaltime)
    TextView approvaltime;
    @Bind(R.id.term)
    TextView term;
    @Bind(R.id.money_guide)
    CardView moneyGuide;
    @Bind(R.id.Lin)
    LinearLayout Lin;
    @Bind(R.id.characteristic)
    TextView characteristic;
    @Bind(R.id.goods)
    LinearLayout goods;
    @Bind(R.id.applycondition)
    TextView applycondition;
    @Bind(R.id.appay)
    ImageButton appay;

    private Bundle bundle;
    private String productcode;
    private Handler mHandler;
    private MoneyDetailBean moneyDetailBean;
    @Bind(R.id.mLoadingView)
    LoadingView mLoadingView;

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_moneydetail);
        ButterKnife.bind(this);
        back.setVisibility(View.VISIBLE);
        barTittle.setVisibility(View.VISIBLE);
        bundle = getIntent().getExtras();
        productcode = bundle.getString("productcode");
        mLoadingView.setOnRetryListener(this);
        mLoadingView.notifyDataChanged(LoadingView.State.ing);
        mLoadingView.setOnErroyClickListener(this);
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                handleResult(msg);
            }
        };
        getCodePage( productcode);
    }
    @Override
    protected void postAgain() {
        getData();
    }
    public String getCodePage( String productcode) {

       return productcode;
    }

    public void handleResult(Message msg) {
        if (msg != null) {
            switch (msg.what) {
                case UPDATA_UI:
                    barTittle.setText(moneyDetailBean.getProductdtl().getName());
                    maxlimit.setText(addComma(moneyDetailBean.getProductdtl().getMaxlimit() + ""));
                    minrate.setText(moneyDetailBean.getProductdtl().getMinrate());
                    term.setText(moneyDetailBean.getProductdtl().getTerm());
                    approvaltime.setText(moneyDetailBean.getProductdtl().getApprovaltime());
                    setKeyWord(sContext, moneyDetailBean.getProductdtl().getKeyword(), Lin, "gray", 5);
                    String characteristic1 = moneyDetailBean.getProductdtl().getCharacteristic();
                    String replace = characteristic1.replace("|", "\n");
                    characteristic.setText(replace);
                    applycondition.setText(moneyDetailBean.getProductdtl().getApplycondition().replace("|", "\n"));

                    break;
                default:
            }
        }
    }

    @Override
    public boolean getStatusBarColor() {
        return true;
    }

    @Override
    protected void loadData() {
        // HttpPostUtils.putEventTag(getUserId(), "", productcode, "CL", CONTENT_EVENT_PRODUCT_DETAIL, COMMENT_EVENT_PRODUCT_DETAIL);
        getData();
    }

    private void getData() {
        i = 4;
        Map<String, String> paramsMap = getParamsMap();
        paramsMap.put("productcode", productcode);
        Log.d("TAG", "BASEBASEBASEBASEBASE====" + BASE);
        Log.d("TAG", "GOOSDSDEDAILGOOSDSDEDAIL====" + GOOSDSDEDAIL);
        OkhttpUtil.okHttpPost(GOOSDSDEDAIL, paramsMap, new CallBackUtil.CallBackString() {

            @Override
            public void onFailure(Call call, Exception e) {
                Log.d("TAG", "ExceptionExceptionExceptionException=====" + e);
                mLoadingView.notifyDataChanged(LoadingView.State.error);
            }

            @Override
            public void onResponse(String response) {

                try {
                    Log.d("TAG", "MoneyDetailActivity====" + response);
                    moneyDetailBean = gson.fromJson(response, MoneyDetailBean.class);
                    if (moneyDetailBean.getCode().equals("S")) {
                        mLoadingView.notifyDataChanged(LoadingView.State.done);
                        // HttpPostUtils.putEventTag(getUserId(), "", moneyDetailBean.getProductdtl().getBankname(), "CL", CONTENT_EVENT_PRODUCT_DETAIL, moneyDetailBean.getProductdtl().getName());
                        mHandler.sendEmptyMessage(UPDATA_UI);

                    } else {
                        mLoadingView.notifyDataChanged(LoadingView.State.error);
                        //ToastUtils.showToast("数据有误");
                    }
                } catch (Exception e) {
                    mLoadingView.notifyDataChanged(LoadingView.State.error);
                }


            }
        });
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
                    // ToastUtils.showToast("点击太快了");
                } else {
                    // HttpPostUtils.putEventTag(getUserId(), "", moneyDetailBean.getProductdtl().getBankname(), "CL", CONTENT_EVENT_APPAY, moneyDetailBean.getProductdtl().getName());
                    open(AppayActivity.class, bundle);
                }


                break;
        }
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

package com.wiseco.wisecoshop.activity.order;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wiseco.wisecoshop.R;
import com.wiseco.wisecoshop.base.BaseActivity;
import com.wiseco.wisecoshop.bean.OrderCardDetailBean;
import com.wiseco.wisecoshop.okhttp.CallBackUtil;
import com.wiseco.wisecoshop.okhttp.OkhttpUtil;
import com.wiseco.wisecoshop.view.LoadingView;

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
import static com.wiseco.wisecoshop.utils.UrlUtil.BASE;
import static com.wiseco.wisecoshop.utils.UrlUtil.ORDERDEATAIL;

/**
 * Created by wiseco on 2018/11/9.
 */

public class OrderCardDetailActivity extends BaseActivity implements LoadingView.OnRetryListener, LoadingView.OnErroyClickListener {
    private static final int UPDATA_UI = 101;
    @Bind(R.id.back)
    ImageButton back;
    @Bind(R.id.bar_tittle)
    TextView barTittle;
    @Bind(R.id.order_card_detail_cardimage)
    ImageView orderCardDetailCardimage;
    @Bind(R.id.order_card_detail_productname)
    TextView orderCardDetailProductname;
    @Bind(R.id.order_card_detail_username)
    TextView orderCardDetailUsername;
    @Bind(R.id.order_card_detail_userphone)
    TextView orderCardDetailUserphone;
    @Bind(R.id.order_card_detail_orderid)
    TextView orderCardDetailOrderid;
    @Bind(R.id.order_card_detail_appaytime)
    TextView orderCardDetailAppaytime;
    @Bind(R.id.order_card_detail_advantage)
    TextView orderCardDetailAdvantage;
    @Bind(R.id.order_card_detail_appaystate)
    TextView orderCardDetailAppaystate;
    @Bind(R.id.order_card_detail_appaystep)
    TextView orderCardDetailAppaystep;
    @Bind(R.id.mLoadingView)
    LoadingView mLoadingView;
    private Handler mHandler;
    private OrderCardDetailBean orderCardDetailBean;

    @Override
    protected void postAgain() {
        getData();
    }
    @Override
    protected void initViews() {
        setContentView(R.layout.activity_ordercarddetail);
        ButterKnife.bind(this);
        back.setVisibility(View.VISIBLE);
        barTittle.setVisibility(View.VISIBLE);
        barTittle.setText("订单详情");
        mLoadingView.setOnRetryListener(this);
        mLoadingView.notifyDataChanged(LoadingView.State.ing);
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
                    Glide.with(sContext).load(BASE + orderCardDetailBean.getItem().getApplyLog().getBigIcon()).error(R.drawable.card_img).into(orderCardDetailCardimage);


                    orderCardDetailProductname.setText(orderCardDetailBean.getItem().getApplyLog().getProductName());
                    orderCardDetailUsername.setText(orderCardDetailBean.getItem().getUserDto().getName());

                    orderCardDetailUserphone.setText(orderCardDetailBean.getItem().getUserDto().getMobile());
                    orderCardDetailOrderid.setText(orderCardDetailBean.getItem().getApplyLog().getId() + "");
                    orderCardDetailAppaytime.setText(orderCardDetailBean.getItem().getApplyLog().getApplyDateStr());
                    orderCardDetailAdvantage.setText(orderCardDetailBean.getItem().getApplyLog().getCharacteristic().replace("|", "\n"));

                    if (orderCardDetailBean.getItem().getApplyLog().getState() == 0 || orderCardDetailBean.getItem().getApplyLog().getState() == 1) {
                        orderCardDetailAppaystate.setText(orderCardDetailBean.getItem().getApplyLog().getStateName());
                        orderCardDetailAppaystate.setTextColor(getResources().getColor(R.color.statetext));
                    } else {

                        orderCardDetailAppaystate.setText(orderCardDetailBean.getItem().getApplyLog().getStateName());
                        orderCardDetailAppaystate.setTextColor(getResources().getColor(R.color.ind_red));
                    }
                    List<OrderCardDetailBean.ItemBean.LoanRecordsBean> loanRecords = orderCardDetailBean.getItem().getLoanRecords();
                    String s = "";
                    for (int i = 0; i < loanRecords.size(); i++) {
                        s += loanRecords.get(i).getEventTimeStr() + "  " + loanRecords.get(i).getEventName() + "\n";
                    }

                    orderCardDetailAppaystep.setText(s);
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
        getData();
    }

    @Override
    public void initListener() {

    }


    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onRetry() {
        mLoadingView.notifyDataChanged(LoadingView.State.ing);
    }

    @Override
    public void erroyClick() {
        getData();
    }

    private void getData() {
        Map<String, String> paramsMap = getParamsMap();
        paramsMap.put("orderId", getIntent().getIntExtra("orderId", 0) + "");

        i = 4;
        //  JSONObject jsonObject = JSONObject.fromObject(paramsMap);
        String s = gson.toJson(paramsMap);
        OkhttpUtil.okHttpPostJson(ORDERDEATAIL, s, new CallBackUtil.CallBackString() {


            @Override
            public void onFailure(Call call, Exception e) {
                mLoadingView.notifyDataChanged(LoadingView.State.error);
            }

            @Override
            public void onResponse(String response) {
                try {
                    orderCardDetailBean = gson.fromJson(response, OrderCardDetailBean.class);
                    mLoadingView.notifyDataChanged(LoadingView.State.done);
                    if (orderCardDetailBean.getCode().equals("S")) {

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


}

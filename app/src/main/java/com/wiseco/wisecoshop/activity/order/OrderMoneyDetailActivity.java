package com.wiseco.wisecoshop.activity.order;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.wiseco.wisecoshop.R;
import com.wiseco.wisecoshop.base.BaseActivity;
import com.wiseco.wisecoshop.bean.OrderMoneyDetailBean;
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
import static com.wiseco.wisecoshop.utils.HttpPostUtils.getParamsMap;
import static com.wiseco.wisecoshop.utils.UrlUtil.ORDERDEATAIL;
import static com.wiseco.wisecoshop.utils.UtilsOther.addComma;

/**
 * Created by wiseco on 2018/12/12.
 */

public class OrderMoneyDetailActivity extends BaseActivity implements LoadingView.OnRetryListener, LoadingView.OnErroyClickListener {
    private static final int UPDATA_UI = 100;
    @Bind(R.id.back)
    ImageButton back;
    @Bind(R.id.bar_tittle)
    TextView barTittle;
    @Bind(R.id.order_money_detail_productname)
    TextView orderMoneyDetailProductname;
    @Bind(R.id.order_money_detail_username)
    TextView orderMoneyDetailUsername;
    @Bind(R.id.order_money_detail_userphone)
    TextView orderMoneyDetailUserphone;
    @Bind(R.id.order_money_detail_orderid)
    TextView orderMoneyDetailOrderid;
    @Bind(R.id.order_money_detail_appaytime)
    TextView orderMoneyDetailAppaytime;
    @Bind(R.id.order_money_detail_maxlimit)
    TextView orderMoneyDetailMaxlimit;
    @Bind(R.id.order_money_detail_term)
    TextView orderMoneyDetailTerm;
    @Bind(R.id.order_money_detail_rate)
    TextView orderMoneyDetailRate;
    @Bind(R.id.order_money_detail_applystate)
    TextView orderMoneyDetailApplystate;
    @Bind(R.id.order_money_detail_appaystep)
    TextView orderMoneyDetailAppaystep;
    @Bind(R.id.mLoadingView)
    LoadingView mLoadingView;
    private Handler mHandler;
    private OrderMoneyDetailBean orderMoneyDetailBean;

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_ordermoneydetail);
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
    @Override
    protected void postAgain() {
        getData();
    }
    private void handleResult(Message msg) {
        if (msg != null) {
            switch (msg.what) {
                case UPDATA_UI:
                    orderMoneyDetailProductname.setText(orderMoneyDetailBean.getItem().getApplyLog().getProductName());
                    orderMoneyDetailUsername.setText(orderMoneyDetailBean.getItem().getUserDto().getName());
                    orderMoneyDetailUserphone.setText(orderMoneyDetailBean.getItem().getUserDto().getMobile());


                    // Date d =new Date(orderMoneyDetailBean.getItem().getApplyLog().getApplyDate());

                    //   String times=d.getYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate() + " " + d.getHours() + ":" + d.getMinutes();


                    orderMoneyDetailAppaytime.setText(orderMoneyDetailBean.getItem().getApplyLog().getApplyDateStr());
                    orderMoneyDetailOrderid.setText(orderMoneyDetailBean.getItem().getApplyLog().getId() + "");

                    orderMoneyDetailMaxlimit.setText(orderMoneyDetailBean.getItem().getApplyLog().getApprovedAmount() == 0 ? "-" : addComma(orderMoneyDetailBean.getItem().getApplyLog().getApprovedAmount() + ""));

                    orderMoneyDetailTerm.setText(orderMoneyDetailBean.getItem().getApplyLog().getProductTermNumber() == 0 ? "-" : orderMoneyDetailBean.getItem().getApplyLog().getProductTermNumber() + "");
                    orderMoneyDetailRate.setText(orderMoneyDetailBean.getItem().getApplyLog().getMinRate());
                    if (orderMoneyDetailBean.getItem().getApplyLog().getState() == 0 || orderMoneyDetailBean.getItem().getApplyLog().getState() == 1) {
                        orderMoneyDetailApplystate.setText(orderMoneyDetailBean.getItem().getApplyLog().getStateName());
                        orderMoneyDetailApplystate.setTextColor(getResources().getColor(R.color.statetext));
                    } else {

                        orderMoneyDetailApplystate.setText(orderMoneyDetailBean.getItem().getApplyLog().getStateName());
                        orderMoneyDetailApplystate.setTextColor(getResources().getColor(R.color.ind_red));
                    }
                    List<OrderMoneyDetailBean.ItemBean.LoanRecordsBean> loanRecords = orderMoneyDetailBean.getItem().getLoanRecords();
                    String s = "";
                    for (int i = 0; i < loanRecords.size(); i++) {
                        s += loanRecords.get(i).getEventTimeStr() + "  " + loanRecords.get(i).getEventName() + "\n";
                    }

                    orderMoneyDetailAppaystep.setText(s);
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

    private void getData() {

        Map<String, String> paramsMap = getParamsMap();
        paramsMap.put("orderId", getIntent().getIntExtra("orderId", 0) + "");

        i =4;
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
                    orderMoneyDetailBean = gson.fromJson(response, OrderMoneyDetailBean.class);
                    if (orderMoneyDetailBean.getCode().equals("S")) {
                        mLoadingView.notifyDataChanged(LoadingView.State.done);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}


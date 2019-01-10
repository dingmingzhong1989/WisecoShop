package com.wiseco.wisecoshop.activity.user;

import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wiseco.wisecoshop.R;
import com.wiseco.wisecoshop.base.BaseActivity;
import com.wiseco.wisecoshop.bean.user.AddCardBean;
import com.wiseco.wisecoshop.bean.user.BankListBean;
import com.wiseco.wisecoshop.bean.user.CardBrandsListBean;
import com.wiseco.wisecoshop.okhttp.CallBackUtil;
import com.wiseco.wisecoshop.okhttp.OkhttpUtil;
import com.wiseco.wisecoshop.utils.ToastUtils;
import com.wiseco.wisecoshop.view.PopuWindow;
import com.wiseco.wisecoshop.view.WheelView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

import static com.wiseco.wisecoshop.MyApplication.gson;
import static com.wiseco.wisecoshop.MyApplication.i;
import static com.wiseco.wisecoshop.utils.HttpPostUtils.getParamsMap;
import static com.wiseco.wisecoshop.utils.UrlUtil.ADDCARD;

/**
 * Created by wiseco on 2018/12/18.
 */

public class AddCardActivity extends BaseActivity {
    @Bind(R.id.back)
    ImageButton back;
    @Bind(R.id.rel_main)
    RelativeLayout rel_main;
    @Bind(R.id.bar_tittle)
    TextView barTittle;
    @Bind(R.id.choice_bank)
    TextView choiceBank;
    @Bind(R.id.choice_card_type)
    TextView choiceCardType;
    @Bind(R.id.choice_data)
    TextView choiceData;
    @Bind(R.id.cardnum)
    EditText cardnum;
    @Bind(R.id.commit)
    TextView commit;
    private List<String> popupBanks = new ArrayList<>();
    private List<String> popupCards = new ArrayList<>();
    private List<String> popupDatds = new ArrayList<>();
    Map<String, Integer> mapCard = new HashMap();
    Map<String, Integer> mapType = new HashMap();

    private String bank[] = {"请选择银行", "交通银行", "招商银行", "上海银行", "浦发银行"};
    private String card[] = {"请选择卡种", "白金卡", "金卡", "普卡", "标准卡", "车主卡"};
    private String data[] = {"请选择还款日", "1", "2", "3", "4", "5", "6", "7",
            "8", "9", "10", "11", "12", "13", "14", "15", "16", "17",
            "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};


    @Override
    public boolean getStatusBarColor() {
        return true;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void postAgain() {

    }

    @Override
    protected void initViews() {

        setContentView(R.layout.activity_add_card);
        ButterKnife.bind(this);
        back.setVisibility(View.VISIBLE);
        barTittle.setVisibility(View.VISIBLE);
        barTittle.setText("添加信用卡");

        List<BankListBean> bankList = (List<BankListBean>) getIntent().getExtras().getSerializable("bankList");

        List<CardBrandsListBean> cardBrandsList = (List<CardBrandsListBean>) getIntent().getExtras().getSerializable("cardBrandsList");
        popupBanks.add("请选择银行");
        for (int i = 0; i < bankList.size(); i++) {

            popupBanks.add(bankList.get(i).getName());
            mapCard.put(bankList.get(i).getName(), bankList.get(i).getId());


        }
        popupCards.add("请选择卡种");
        for (int i = 0; i < cardBrandsList.size(); i++) {

            popupCards.add(cardBrandsList.get(i).getName());
            mapType.put(cardBrandsList.get(i).getName(), cardBrandsList.get(i).getId());

        }
        for (int i = 0; i < data.length; i++) {

            popupDatds.add(data[i]);

        }


    }


    @OnClick({R.id.back, R.id.choice_bank, R.id.choice_card_type, R.id.choice_data, R.id.commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.commit:
                dissmissKey();
                //cardnum.setFocusable(false);
                if (choiceBank.getText().equals("请选择银行")
                        || choiceCardType.getText().toString().equals("请选择卡种") ||
                        cardnum.getText().toString().trim() == null || choiceData.getText().toString().equals("请选择还款日")
                        ) {

                    ToastUtils.showToast("请填写正确信息");
                    return;

                } else {
                    i = 4;
                    Map<String, String> paramsMap = getParamsMap();
                    paramsMap.put("bankId", mapCard.get(choiceBank.getText()) + "");
                    paramsMap.put("typeId", mapType.get(choiceCardType.getText()) + "");
                    paramsMap.put("cardEndNum", cardnum.getText().toString().trim());
                    paramsMap.put("repayDay", choiceData.getText().toString());
                    String s = gson.toJson(paramsMap);
                    Log.d("TAG", s);
                    OkhttpUtil.okHttpPostJson(ADDCARD, s, new CallBackUtil.CallBackString() {
                        @Override
                        public void onFailure(Call call, Exception e) {

                        }

                        @Override
                        public void onResponse(String response) {
                            Log.d("TAG", "mycard" + response);

                            AddCardBean addCardBean = gson.fromJson(response, AddCardBean.class);
                            if (addCardBean.equals("S")) {

                                ToastUtils.showToast("添加成功");
                            } else {
                                ToastUtils.showToast("添加失败");
                            }
                            Intent intent = new Intent();
                            intent.setAction("com.wiseco.wisecoshop.mycard");
                            sendBroadcast(intent);
                            finish();


                        }
                    });
                }
                break;
            case R.id.choice_bank:
                dissmissKey();
                // cardnum.setFocusable(false);
                initPopuWindow(R.layout.choice_wheelview, popupBanks, choiceBank);

                break;
            case R.id.choice_card_type:
                // cardnum.setFocusable(false);
                initPopuWindow(R.layout.choice_wheelview, popupCards, choiceCardType);


                break;
            case R.id.choice_data:
                // cardnum.setFocusable(false);
                dissmissKey();
                initPopuWindow(R.layout.choice_wheelview, popupDatds, choiceData);

                break;
        }
    }

    private void initPopuWindow(int layoutRes, final List<String> list, final TextView textView) {

        PopuWindow bankPopuWindow = new PopuWindow(getApplication(), layoutRes, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT) {

            private PopupWindow instance;
            private TextView tv_not;
            private TextView tv_ok;

            @Override
            protected void initView() {
                final View view = getContentView();
                tv_ok = view.findViewById(R.id.tv_ok);
                tv_not = view.findViewById(R.id.tv_not);

                WheelView wva = view.findViewById(R.id.main_wv);

                wva.setOffset(1);
                wva.setItems(list);
                wva.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
                    @Override
                    public void onSelected(int selectedIndex, String item) {
                        Log.d("TAG", "selectedIndex: " + selectedIndex + ", item: " + item);
                        textView.setText(item);

                    }
                });
            }

            @Override
            protected void initEvent() {
                tv_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        instance.dismiss();
                        // choiceBank.setText(popupBanks.get(selectedIndex));

                    }
                });
                tv_not.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        instance.dismiss();

                    }
                });


            }

            @Override
            protected void initWindow() {


                super.initWindow();
                instance = getPopupWindow();
                instance.setOutsideTouchable(true);
                instance.setFocusable(false);
            }
        };

        bankPopuWindow.showAtLocation(rel_main, Gravity.BOTTOM, 0, 0);
    }

    public void dissmissKey()

    {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

}

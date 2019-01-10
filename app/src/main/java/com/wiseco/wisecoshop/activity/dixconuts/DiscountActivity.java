package com.wiseco.wisecoshop.activity.dixconuts;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wiseco.wisecoshop.R;
import com.wiseco.wisecoshop.adapter.DisconutAdapter;
import com.wiseco.wisecoshop.base.BaseActivity;
import com.wiseco.wisecoshop.bean.discount.ActsBean;
import com.wiseco.wisecoshop.bean.discount.ShopActivityBean;
import com.wiseco.wisecoshop.bean.discount.ShopBean;
import com.wiseco.wisecoshop.dialog.CallDialog;
import com.wiseco.wisecoshop.okhttp.CallBackUtil;
import com.wiseco.wisecoshop.okhttp.OkhttpUtil;
import com.wiseco.wisecoshop.view.LoadingView;
import com.wiseco.wisecoshop.view.PopuWindow;

import java.util.Arrays;
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
import static com.wiseco.wisecoshop.utils.UrlUtil.SHOPDETAIL;

/**
 * Created by wiseco on 2018/12/17.
 */

public class DiscountActivity extends BaseActivity implements AdapterView.OnItemClickListener, LoadingView.OnRetryListener, LoadingView.OnErroyClickListener {
    @Bind(R.id.mLoadingView)
    LoadingView mLoadingView;
    @Bind(R.id.back)
    ImageButton back;
    @Bind(R.id.bar_tittle)
    TextView barTittle;
    @Bind(R.id.address)
    TextView address;
    @Bind(R.id.distance)
    TextView distance;
    @Bind(R.id.phone)
    ImageView phone;
    @Bind(R.id.lin_phone)
    LinearLayout linPhone;
    @Bind(R.id.discount_list)
    ListView discountList;
    @Bind(R.id.shop_image)
    ImageView shopImage;
    @Bind(R.id.shop_title)
    TextView shopTitle;
    @Bind(R.id.shop_type)
    TextView shopType;
    @Bind(R.id.rel_main)
    RelativeLayout rel_main;
    private String shopId;
    private Handler mHandler;
    private final int UODATA_UI = 102;
    private ShopBean shopBean;
    private List<ActsBean> acts;
    private int MY_PERMISSION_REQUEST_CODE = 1002;
    private String tel;

    @Override
    public boolean getStatusBarColor() {
        return true;
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

        Map<String, String> paramsMap = getParamsMap();
        paramsMap.put("shopId", shopId);
        String s = gson.toJson(paramsMap);

        i = 4;
        OkhttpUtil.okHttpPostJson(SHOPDETAIL, s, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                mLoadingView.notifyDataChanged(LoadingView.State.empty);
            }

            @Override
            public void onResponse(String response) {
                Log.d("TAG", response);
                try {
                    shopBean = gson.fromJson(response, ShopBean.class);
                    if (shopBean.getCode().equals("S")) {

                        mHandler.sendEmptyMessage(UODATA_UI);
                        mLoadingView.notifyDataChanged(LoadingView.State.done);
                    } else {

                        mLoadingView.notifyDataChanged(LoadingView.State.error);
                    }


                } catch (Exception e) {


                }


            }
        });


    }

    @Override
    protected void initListener() {


    }

    @Override
    protected void initViews() {

        setContentView(R.layout.activity_discount);
        ButterKnife.bind(this);
        back.setVisibility(View.VISIBLE);
        barTittle.setVisibility(View.VISIBLE);
        barTittle.setText("商户详情");
        shopId = getIntent().getExtras().getString("shopId");
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

                case UODATA_UI:
                    ShopActivityBean shopActivity = shopBean.getShopActivity();
                    Glide.with(this).load(shopActivity.getLogoSmall()).placeholder(R.color.background).into(shopImage);
                    shopTitle.setText(shopActivity.getShopName());
                    address.setText(shopActivity.getAddress());
                    distance.setText(shopActivity.getMiles() + "米");
                    acts = shopActivity.getActs();
                    discountList.setAdapter(new DisconutAdapter(this, acts));
                    discountList.setOnItemClickListener(this);
                    tel = shopActivity.getTel();
                    if (tel == null || tel.equals("")) {
                        phone.setVisibility(View.INVISIBLE);
                    } else {
                        phone.setVisibility(View.VISIBLE);
                    }
                    mLoadingView.notifyDataChanged(LoadingView.State.done);

                    break;
            }
        }


    }


    @OnClick({R.id.back, R.id.lin_phone})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.lin_phone:

                /**
                 * 第 1 步: 检查是否有相应的权限
                 */
                boolean isAllGranted = checkPermissionAllGranted(
                        new String[]{
                                Manifest.permission.CALL_PHONE
                        });


                if (isAllGranted) {
                    if (tel == null || tel.equals("")) {
                        phone.setVisibility(View.INVISIBLE);
                        return;

                    } else {
                        phone.setVisibility(View.VISIBLE);
                        String str= tel.replace("\"", "");
                        Log.d("TAG",tel);
                        if (!str.contains(",")) {
                            new CallDialog(this, str).show();
                        } else {


                            String[] split = str.split(",");



                            initPopuWindow(R.layout.tel, Arrays.asList(split));

                        }

                    }

                    return;
                }
                //第 2 步: 请求权限

                // 一次请求多个权限, 如果其他有权限是已经授予的将会自动忽略掉
                ActivityCompat.requestPermissions(this,
                        new String[]{
                                Manifest.permission.CALL_PHONE},
                        MY_PERMISSION_REQUEST_CODE);

                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        Bundle b = new Bundle();
        b.putString("id", acts.get(position).getId());
        open(DiscountDetailAcyivity.class, b);
    }



    @Override
    public void onRetry() {
        mLoadingView.notifyDataChanged(LoadingView.State.ing);
    }

    @Override
    public void erroyClick() {
        getData();
    }


    /**
     * 检查是否拥有指定的所有权限
     */
    private boolean checkPermissionAllGranted(String[] permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                // 只要有一个权限没有被授予, 则直接返回 false
                return false;
            }
        }
        return true;
    }

    /**
     * 打开 APP 的详情设置
     */
    private void openAppDetails() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("请到 “应用信息 -> 权限” 中授予！");
        builder.setPositiveButton("去手动授权", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.setData(Uri.parse("package:" + getPackageName()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("取消", null);
        builder.show();
    }


    private void initPopuWindow(int layoutRes, final List<String> list) {

        PopuWindow bankPopuWindow = new PopuWindow(getApplication(), layoutRes, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT) {

            private ListView tel_list;
            private TextView tv_cannel;
            private PopupWindow instance;


            @Override
            protected void initView() {
                final View view = getContentView();
                tel_list = view.findViewById(R.id.tel_list);

                tv_cannel = view.findViewById(R.id.tv_cannel);

                //为适配器添加数据源
                ArrayAdapter adapter = new ArrayAdapter(sContext, android.R.layout.simple_list_item_1, list){



                    @Override
                    public View getView(int position,  View convertView,  ViewGroup parent) {
                        View view   =  super.getView(position, convertView, parent);

                        TextView et = (TextView)view;
                        et.setGravity(Gravity.CENTER);
                        return view;
                    }
                };

                tel_list.setAdapter(adapter);


            }

            @Override
            protected void initEvent() {

                tv_cannel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        instance.dismiss();

                    }
                });
                //设置点击事件mlv
                tel_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent myIntentDial = new Intent("android.intent.action.CALL", Uri.parse("tel:" + list.get(position)));
                        startActivity(myIntentDial);
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
}

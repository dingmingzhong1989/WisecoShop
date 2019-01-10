package com.wiseco.wisecoshop.fragment;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tamic.statInterface.statsdk.core.TcStatInterface;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.wiseco.wisecoshop.R;
import com.wiseco.wisecoshop.activity.login.RegistIDCardActivity_;
import com.wiseco.wisecoshop.activity.order.OrderActivity;
import com.wiseco.wisecoshop.activity.user.MyCardActivity;
import com.wiseco.wisecoshop.activity.user.MyTicketActivity;
import com.wiseco.wisecoshop.activity.user.SettingActivity;
import com.wiseco.wisecoshop.base.BaseFragment;
import com.wiseco.wisecoshop.bean.user.UserBean;
import com.wiseco.wisecoshop.dialog.LoginDialog;
import com.wiseco.wisecoshop.okhttp.CallBackUtil;
import com.wiseco.wisecoshop.okhttp.OkhttpUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

import static com.wiseco.wisecoshop.MyApplication.gson;
import static com.wiseco.wisecoshop.MyApplication.i;
import static com.wiseco.wisecoshop.utils.UrlUtil.USERCRENTER;
import static com.wiseco.wisecoshop.utils.UtilsOther.isRegist;
import static com.wiseco.wisecoshop.utils.UtilsOther.isTrueName;

/**
 * Created by wiseco on 2018/12/4.
 */

public class UserFragment extends BaseFragment {


    private static final int UODATA_UI_DEFAULT = 101;
    @Bind(R.id.back)
    ImageButton back;
    @Bind(R.id.bar_tittle)
    TextView barTittle;
    @Bind(R.id.user_icon)
    ImageView userIcon;
    @Bind(R.id.user_phone_num)
    TextView userPhoneNum;
    @Bind(R.id.user_female)
    ImageView userFemale;
    @Bind(R.id.user_location)
    TextView userLocation;
    @Bind(R.id.user_star)
    TextView userStar;
    @Bind(R.id.user)
    LinearLayout user;
    @Bind(R.id.user_appay)
    TextView userAppay;
    @Bind(R.id.user_appay_num)
    TextView userAppayNum;
    @Bind(R.id.user_my_card)
    TextView userMyCard;
    @Bind(R.id.user_my_card_num)
    TextView userMyCardNum;
    @Bind(R.id.user_ticket)
    TextView userTicket;
    @Bind(R.id.user_ticket_num)
    TextView userTicketNum;
    @Bind(R.id.user_tv_name)
    TextView userTvName;
    @Bind(R.id.user_true_name)
    LinearLayout userTrueName;
    @Bind(R.id.user_message)
    LinearLayout userMessage;
    @Bind(R.id.user_invite)
    LinearLayout userInvite;
    @Bind(R.id.user_share)
    LinearLayout userShare;
    @Bind(R.id.user_setting)
    LinearLayout userSetting;
    @Bind(R.id.user_content)
    LinearLayout userContent;
    private Handler handler;
    private static final int UODATA_UI = 100;
    private UserBean userBean;

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_user, null);
        ButterKnife.bind(this, view);
        // head.setBackgroundColor(mActivity.getResources().getColor(R.color.transparent));
        barTittle.setVisibility(View.VISIBLE);
        barTittle.setText("个人中心");
        if (isRegist()) {

            //userPhoneNum.setText(userBean.getEncPhone());


        } else {
            userPhoneNum.setText("点击登录/注册");
            userPhoneNum.setVisibility(View.VISIBLE);
            userFemale.setVisibility(View.INVISIBLE);
            userStar.setVisibility(View.INVISIBLE);
            userLocation.setVisibility(View.INVISIBLE);
        }
        MyReceiver receiver = new MyReceiver();
        getActivity().registerReceiver(receiver, new IntentFilter("com.wiseco.wisecoshop.Userfragment"));
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                handeResult(msg);


            }
        };


        return view;
    }

    private void handeResult(Message msg) {
        if (msg != null) {
            switch (msg.what) {

                case UODATA_UI:

                    if (isRegist()) {

                        userPhoneNum.setText(userBean.getEncPhone());


                    } else {
                        userPhoneNum.setText("点击登录/注册");
                        userPhoneNum.setVisibility(View.VISIBLE);
                        userFemale.setVisibility(View.INVISIBLE);
                        userStar.setVisibility(View.INVISIBLE);
                        userLocation.setVisibility(View.INVISIBLE);
                    }

                    if (userBean.isIdcardStatus()) {
                        userTvName.setText("已实名");
                        userTrueName.setClickable(false);
                    } else {
                        userTrueName.setClickable(true);

                    }

                   /* if (userBean.getUserCardNum() > 0) {
                        userMyCardNum.setVisibility(View.VISIBLE);
                        userMyCardNum.setText(userBean.getUserCardNum() + "");
                    }*/

                    if (userBean.getUserWalletNum() > 0) {
                        userTicketNum.setVisibility(View.VISIBLE);
                        userTicketNum.setText(userBean.getUserWalletNum() + "");
                    }
                    if (userBean.isGendar()) {

                        userFemale.setImageDrawable(getResources().getDrawable(R.drawable.female));
                    } else{

                        userFemale.setImageDrawable(getResources().getDrawable(R.drawable.male));
                    }
                    userStar.setText(userBean.getConstellation());

                    break;

                case UODATA_UI_DEFAULT:
                    if (isTrueName()) {

                        userTvName.setText("已实名");
                        userTrueName.setClickable(false);
                    } else {
                        userTrueName.setClickable(true);

                    }
                    break;
            }
        }


    }

    @Override
    public void initData() {
        super.initData();

        getData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @OnClick({R.id.user, R.id.user_appay, R.id.user_my_card, R.id.user_ticket, R.id.user_true_name, R.id.user_message, R.id.user_invite, R.id.user_share, R.id.user_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.user:


                if (isRegist()) {

                } else {

                    new LoginDialog(mActivity).show();
                   // open(LoginActivity_.class);
                }
                break;
            case R.id.user_appay:
                if (isRegist()) {
                    open(OrderActivity.class);
                } else {
                    new LoginDialog(mActivity).show();

                }
                break;
            case R.id.user_my_card:
                if (isRegist()) {
                    open(MyCardActivity.class);
                } else {
                    new LoginDialog(mActivity).show();

                }


                break;
            case R.id.user_ticket:
                if (isRegist()) {
                    open(MyTicketActivity.class);
                } else {
                    new LoginDialog(mActivity).show();
                }


                break;
            case R.id.user_true_name:
                if (isRegist()) {
                    if (isTrueName()) {


                    } else {
                        open(RegistIDCardActivity_.class);

                    }
                } else {
                    new LoginDialog(mActivity).show();
                }


                break;
            case R.id.user_message:
                TcStatInterface.reportData();
                break;
            case R.id.user_invite:
                break;
            case R.id.user_share:


                if (Build.VERSION.SDK_INT >= 23) {
                    String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS};
                    ActivityCompat.requestPermissions(mActivity, mPermissionList, 123);
                }

                UMWeb web = new UMWeb("https://m.wisecofincloud.com/share");
                web.setTitle("快易省");
                web.setThumb(new UMImage(mActivity, R.drawable.crs_icon));
                web.setDescription("开启你的信用生活");
                new ShareAction(mActivity).withText("").setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE).withMedia(web )
                        .setCallback(shareListener).open();

                break;
            case R.id.user_setting:

                open(SettingActivity.class);
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {


    }

    public void getData() {
        i = 4;
        OkhttpUtil.okHttpPost(USERCRENTER, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                handler.sendEmptyMessage(UODATA_UI_DEFAULT);
            }

            @Override
            public void onResponse(String response) {
                Log.d("TAG", response);
                try {

                    userBean = gson.fromJson(response, UserBean.class);

                    if (userBean.getCode().equals("S")){
                        handler.sendEmptyMessage(UODATA_UI);
                    }


                } catch (Exception e) {
                }


            }
        });

    }

    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {


            Log.d("TAG", "11111111111111111");
            // Toast.makeText(ShareDetailActivity.this,"成功了",Toast.LENGTH_LONG).show()；
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Log.d("TAG", "22222222222222222");
            //Toast.makeText(ShareDetailActivity.this,"失 败"+t.getMessage(),Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            //Toast.makeText(ShareDetailActivity.this,"取消了",Toast.LENGTH_LONG).show();
            Log.d("TAG", "333333333333333333333");
        }
    };



    private class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {


            userPhoneNum.setText("点击登录/注册");
            userPhoneNum.setVisibility(View.VISIBLE);
            userFemale.setVisibility(View.INVISIBLE);
            userStar.setVisibility(View.INVISIBLE);
            userLocation.setVisibility(View.INVISIBLE);
            userTicketNum.setVisibility(View.INVISIBLE);
            Log.d("TAG-----","我要更新了");
        }
    }
}

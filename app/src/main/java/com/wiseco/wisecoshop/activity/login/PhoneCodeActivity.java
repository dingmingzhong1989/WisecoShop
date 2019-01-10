package com.wiseco.wisecoshop.activity.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wiseco.wisecoshop.R;
import com.wiseco.wisecoshop.activity.MainActivity;
import com.wiseco.wisecoshop.base.BaseActivity;
import com.wiseco.wisecoshop.bean.GetCodeBean;
import com.wiseco.wisecoshop.bean.MessageCodeBean;
import com.wiseco.wisecoshop.okhttp.CallBackUtil;
import com.wiseco.wisecoshop.okhttp.OkhttpUtil;
import com.wiseco.wisecoshop.utils.CacheUtil;
import com.wiseco.wisecoshop.utils.ToastUtils;
import com.wiseco.wisecoshop.view.PhoneCode;

import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

import static com.wiseco.wisecoshop.MyApplication.IS_TRUE_NAME;
import static com.wiseco.wisecoshop.MyApplication.IS_USER_REGIEST;
import static com.wiseco.wisecoshop.MyApplication.gson;
import static com.wiseco.wisecoshop.MyApplication.i;
import static com.wiseco.wisecoshop.MyApplication.sContext;
import static com.wiseco.wisecoshop.utils.HttpPostUtils.getParamsMap;
import static com.wiseco.wisecoshop.utils.UrlUtil.GETCODE;
import static com.wiseco.wisecoshop.utils.UrlUtil.PHONEPREVALID;


/**
 * Created by wiseco on 2018/10/18.
 */

public class PhoneCodeActivity extends BaseActivity {
    @Bind(R.id.back)
    ImageButton back;
    @Bind(R.id.rel)
    RelativeLayout rel;
    @Bind(R.id.text_phone)
    TextView textPhone;
    @Bind(R.id.phonecode)
    PhoneCode phonecode;
    @Bind(R.id.text_again)
    TextView textAgain;
    @Bind(R.id.btn_login_regist)
    Button btnLoginRegist;

    private Bundle bundle;
    private CountTimer countTimerView;

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_phonecode);
        ButterKnife.bind(this);
         bundle = getIntent().getExtras();
        textPhone.setText(CacheUtil.getString(sContext,"mobile",""));
        textAgain.setClickable(false);
       // textAgain.setText();
        countTimerView = new CountTimer(60000, 1000, this);
        countTimerView.start();

    }

    @Override
    public boolean getStatusBarColor() {
        return true;
    }

    @Override
    protected void loadData() {
        phonecode.setOnInputListener(new PhoneCode.OnInputListener() {
            @Override
            public void onSucess(String code) {


                phoneCode();
            }

            @Override
            public void onInput() {

            }
        });
    }

    @Override
    public void initListener() {

    }
    @Override
    protected void postAgain() {

    }
    @OnClick({R.id.back, R.id.text_again, R.id.btn_login_regist})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                open(LoginActivity_.class,bundle);
                finish();
                break;
            case R.id.text_again:
                phonecode.delete();
                getPhoneCodeAgain();


                break;
            case R.id.btn_login_regist:


                break;
        }
    }

    private void phoneCode() {
        String phoneCode = phonecode.getPhoneCode();
        Log.d("TAG", "phoneCode=====" + phoneCode);
        if (phoneCode != null) {
            i = 2;
            Map<String, String> paramsMap = getParamsMap();
            paramsMap.put("mobile", CacheUtil.getString(sContext, "mobile", ""));
            paramsMap.put("validCode", phoneCode);
            OkhttpUtil.okHttpPost(PHONEPREVALID, paramsMap, new CallBackUtil.CallBackString() {
                @Override
                public void onFailure(Call call, Exception e) {

                }

                @Override
                public void onResponse(String response) {
                    try{
                        MessageCodeBean messageCodeBean = gson.fromJson(response, MessageCodeBean.class);
                        if (messageCodeBean.getCode().equals("S")) {

                           // HttpPostUtils.putEventTag(getUserId(),"","","",CONTENT_EVENT_VERIFY_CODE,COMMENT_EVENT_VERIFY_CODE_SUCCESS);

                            CacheUtil.putString(sContext, "USERID", messageCodeBean.getUserId());

                            getUserId(messageCodeBean.getUserId());

                            if (messageCodeBean.getIdcstatus().equals("F")) {

                                countTimerView.cancel();

                                open(RegistIDCardActivity_.class,bundle);
                                finish();

                            } else if (messageCodeBean.getIdcstatus().equals("T")) {
                                CacheUtil.putBoolean(sContext, IS_USER_REGIEST, true);
                                CacheUtil.putBoolean(sContext, IS_TRUE_NAME, true);
                                countTimerView.cancel();
                                ToastUtils.showToast("登录成功");
                                Intent intent = new Intent();
                                intent.setAction("com.wiseco.wisecoshop.fragment");
                                sendBroadcast(intent);
                                open(MainActivity.class, bundle);
                                open(MainActivity.class);
                                finish();


                            }

                        } else if (messageCodeBean.getCode().equals("FS")) {
                            ToastUtils.showToast("验证码错误");
                           // HttpPostUtils.putEventTag(getUserId(),"","","",CONTENT_EVENT_VERIFY_CODE,COMMENT_EVENT_VERIFY_CODE_FAILED);

                        } else if (messageCodeBean.getCode().equals("FSP")) {
                           // HttpPostUtils.putEventTag(getUserId(),"","","",CONTENT_EVENT_VERIFY_CODE,COMMENT_EVENT_VERIFY_CODE_FAILED);
                            ToastUtils.showToast("手机号验证错误");
                        } else if (messageCodeBean.getCode().equals("FSO")) {
                           // HttpPostUtils.putEventTag(getUserId(),"","","",CONTENT_EVENT_VERIFY_CODE,COMMENT_EVENT_VERIFY_CODE_FAILED);
                            ToastUtils.showToast("重新发送");
                        } else if (messageCodeBean.getCode().equals("FSNULL")) {
                           // HttpPostUtils.putEventTag(getUserId(),"","","",CONTENT_EVENT_VERIFY_CODE,COMMENT_EVENT_VERIFY_CODE_FAILED);
                            ToastUtils.showToast("session中没有当前数据或session超时");
                        } else {
                           // HttpPostUtils.putEventTag(getUserId(),"","","",CONTENT_EVENT_VERIFY_CODE,COMMENT_EVENT_VERIFY_CODE_FAILED);
                        }
                    }catch (Exception e){

                    }
                    Log.d("TAG", "phoneCoderesponseresponse=====" + response);



                }
            });
        } else {
            ToastUtils.showToast("输入验证码");
        }
    }

    private String getUserId(String userId) {
        return userId;
    }

    private void getPhoneCodeAgain() {
        Map<String, String> paramsMap = getParamsMap();
        paramsMap.put("mobile", CacheUtil.getString(sContext, "mobile", ""));
        paramsMap.put("token", CacheUtil.getString(sContext, "TOKEN", ""));
        OkhttpUtil.okHttpPost(GETCODE, paramsMap, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                try{
                    GetCodeBean getCodeBean = gson.fromJson(response, GetCodeBean.class);
                    if (getCodeBean.getCode().equals("S")) {
                        countTimerView.start();
                        textAgain.setClickable(false);
                        textAgain.setTextColor(getResources().getColor(R.color.color_69));
                        ToastUtils.showToast("短信发送成功,注意查收");
                    } else if (getCodeBean.getCode().equals("F")) {
                        ToastUtils.showToast("发送短信异常");
                    } else {
                        ToastUtils.showToast("加载失败");
                    }
                }catch (Exception e){

                }

            }
        });
    }

    public void open(Class<?> cls, Bundle b) {
        Intent intent = new Intent(this, cls);
        intent.putExtras(b);
        startActivity(intent);
    }

    public class CountTimer extends CountDownTimer {
        private Context context;

        /**
         * 参数 millisInFuture       倒计时总时间（如60S，120s等）
         * 参数 countDownInterval    渐变时间（每次倒计1s）
         */
        public CountTimer(long millisInFuture, long countDownInterval, Context context) {
            super(millisInFuture, countDownInterval);
            this.context = context;
        }

        // 计时完毕时触发
        @Override
        public void onFinish() {
            countTimerView.cancel();
            textAgain.setText("重新获取验证码");
            textAgain.setTextColor(getResources().getColor(R.color.blue));
            textAgain.setClickable(true);
        }

        // 计时过程显示
        @Override
        public void onTick(long millisUntilFinished) {
            textAgain.setText("接收短信大约需要"+millisUntilFinished / 1000 + "秒");
        }
    }
}

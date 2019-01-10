package com.wiseco.wisecoshop.activity.login;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.wiseco.wisecoshop.MyApplication;
import com.wiseco.wisecoshop.R;
import com.wiseco.wisecoshop.activity.AgreementActivity;
import com.wiseco.wisecoshop.activity.MainActivity;
import com.wiseco.wisecoshop.base.BaseActivity;
import com.wiseco.wisecoshop.bean.GetCodeBean;
import com.wiseco.wisecoshop.bean.GetTokenBean;
import com.wiseco.wisecoshop.bean.PassImgBean;
import com.wiseco.wisecoshop.okhttp.CallBackUtil;
import com.wiseco.wisecoshop.okhttp.OkhttpUtil;
import com.wiseco.wisecoshop.utils.CacheUtil;
import com.wiseco.wisecoshop.utils.KeyboardHelper;
import com.wiseco.wisecoshop.utils.ToastUtils;

import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

import static com.wiseco.wisecoshop.MyApplication.gson;
import static com.wiseco.wisecoshop.MyApplication.sContext;
import static com.wiseco.wisecoshop.utils.HttpPostUtils.getParamsMap;
import static com.wiseco.wisecoshop.utils.UrlUtil.GETCODE;
import static com.wiseco.wisecoshop.utils.UrlUtil.GETPASSIMG;
import static com.wiseco.wisecoshop.utils.UrlUtil.GETTOKEN;
import static com.wiseco.wisecoshop.utils.UtilsOther.isFastClick;
import static com.wiseco.wisecoshop.utils.UtilsOther.isMobileNumber;
import static com.wiseco.wisecoshop.utils.UtilsOther.stringtoBitmap;


/**
 * Created by wiseco on 2018/10/18.
 */

public class LoginActivity_ extends BaseActivity implements TextWatcher, View.OnFocusChangeListener {
    @Bind(R.id.back)
    ImageButton back;
    @Bind(R.id.rel)
    RelativeLayout rel;
    @Bind(R.id.login_check)
    CheckBox loginCheck;
    @Bind(R.id.text_one)
    TextView textOne;
    @Bind(R.id.text_two)
    TextView textTwo;
    @Bind(R.id.text_three)
    TextView textThree;
    @Bind(R.id.userPhone)
    EditText userPhone;
    @Bind(R.id.passimgcode)
    EditText passimgcode;
    @Bind(R.id.passimg)
    ImageView passimg;
    @Bind(R.id.usernameLinear)
    TableLayout usernameLinear;
    @Bind(R.id.btn_getcode)
    Button btnGetcode;
    @Bind(R.id.jump)
    Button jump;
    @Bind(R.id.linearLayout)
    LinearLayout linearLayout;
    @Bind(R.id.lin_max)
    LinearLayout linMax;
    private Bundle bundle;
    private KeyboardHelper keyboardHelper;
    private int bottomHeight;
    private int heightWin;

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_login_);
        ButterKnife.bind(this);
        bundle = getIntent().getExtras();

         keyboardHelper = new KeyboardHelper(this);
        keyboardHelper.onCreate();
         keyboardHelper.setOnKeyboardStatusChangeListener(onKeyBoardStatusChangeListener);
        WindowManager wm1 = this.getWindowManager();
        int width1 = wm1.getDefaultDisplay().getWidth();
        heightWin = wm1.getDefaultDisplay().getHeight();
        int height = rel.getHeight();
        Log.d("TAG", "height====" + height);

        //userPhone.addTextChangedListener(this);
        userPhone.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                userPhone.setFocusable(true);
                userPhone.setFocusableInTouchMode(true);
                userPhone.requestFocus();
                return false;
            }
        });
        userPhone.setOnFocusChangeListener(this);
    }
    @Override
    protected void postAgain() {

    }
    private KeyboardHelper.OnKeyboardStatusChangeListener onKeyBoardStatusChangeListener = new KeyboardHelper.OnKeyboardStatusChangeListener() {
        @Override
        public void onKeyboardPop(int keyboardHeight) {
            int offset=0;
            final int height = keyboardHeight;
            int height2 = linMax.getHeight();
            Log.d("TAG", "height1====" + "-----" + heightWin + "---------" + height+"--------"+height2);
            if ((heightWin-height2) < height) {
                offset = (heightWin -height2) - height ;
            }else{

                offset = 0;
            }

            final ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) linMax.getLayoutParams();
            lp.topMargin = offset;
            linMax.setLayoutParams(lp);

        }

        @Override
        public void onKeyboardClose(int keyboardHeight) {

            final ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) linMax.getLayoutParams();
            if (lp.topMargin != 0) {
                lp.topMargin = 0;
                linMax.setLayoutParams(lp);
            }
        }
    };

    // View宽，高
    public int[] getLocation(View v) {
        int[] loc = new int[4];
        int[] location = new int[2];
        v.getLocationOnScreen(location);
        loc[0] = location[0];
        loc[1] = location[1];
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        v.measure(w, h);

        loc[2] = v.getMeasuredWidth();
        loc[3] = v.getMeasuredHeight();

        //base = computeWH();
        return loc;
    }

    @Override
    protected void loadData() {

        //   getPassImg();

    }

    private void getPassImg() {
        MyApplication.i = 1;
        OkhttpUtil.okHttpPost(GETPASSIMG, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
                ToastUtils.showToast("网络错误");
            }

            @Override
            public void onResponse(String response) {
                try {
                    PassImgBean passImgBean = gson.fromJson(response, PassImgBean.class);
                    if (passImgBean.getCode().equals("S")) {
                        String item = passImgBean.getItem();
                        Bitmap bitmap = stringtoBitmap(item);
                        passimg.setImageBitmap(bitmap);
                    } else {
                        ToastUtils.showToast("加载失败");
                    }
                } catch (Exception e) {
                }


            }
        });


    }

    @Override
    public void initListener() {

    }

    @OnClick({R.id.back, R.id.text_one, R.id.text_two, R.id.text_three, R.id.passimg, R.id.btn_getcode, R.id.jump})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                open(MainActivity.class);
                finish();
                break;
            case R.id.text_one:
                getAgreement(1);
                break;
            case R.id.text_two:
                getAgreement(2);
                break;
            case R.id.text_three:
                getAgreement(3);
                break;
            case R.id.passimg:
                if (isFastClick()) {
                } else {
                    // getPassImg();
                }

                break;
            case R.id.jump:

                break;
            case R.id.btn_getcode:
                userPhone.setFocusable(false);
                if (isFastClick()) {

                } else {
                    final String trim = userPhone.getText().toString().trim();
                    final String passimg = passimgcode.getText().toString().trim();

                    if (trim != null && !trim.equals("") && loginCheck.isChecked()) {
                        if (isMobileNumber(trim)) {
                            Map<String, String> paramsMap = getParamsMap();
                            paramsMap.put("mobile", trim);
                            OkhttpUtil.okHttpPost(GETTOKEN, paramsMap, new CallBackUtil.CallBackString()

                            {
                                @Override
                                public void onFailure(Call call, Exception e) {

                                }

                                @Override
                                public void onResponse(String response) {
                                    try {

                                       // HttpPostUtils.putEventTag(getUserId(), "", "", "", CONTENT_EVENT_START_VERIFY, COMMENT_EVENT_START_VERIFY);
                                        GetTokenBean getTokenBean = gson.fromJson(response, GetTokenBean.class);
                                        if (getTokenBean.getCode().equals("S")) {
                                            final String token = getTokenBean.getToken();
                                            CacheUtil.putString(sContext, "TOKEN", token);
                                            Map<String, String> paramsMap = getParamsMap();
                                            paramsMap.put("mobile", trim);
                                            paramsMap.put("token", token);
                                            OkhttpUtil.okHttpPost(GETCODE, paramsMap, new CallBackString() {
                                                @Override
                                                public void onFailure(Call call, Exception e) {

                                                }

                                                @Override
                                                public void onResponse(String response) {
                                                    GetCodeBean getCodeBean = gson.fromJson(response, GetCodeBean.class);
                                                    if (getCodeBean.getCode().equals("S")) {
                                                        ToastUtils.showToast("短信已成功发送");
                                                        CacheUtil.putString(sContext, "mobile", trim);
                                                        open(PhoneCodeActivity.class, bundle);
                                                        finish();


                                                    } else if (getCodeBean.getCode().equals("F")) {
                                                        ToastUtils.showToast("发送短信异常");
                                                    } else {
                                                        ToastUtils.showToast("加载失败");
                                                    }
                                                }
                                            });
                                         /*
                                            Map<String, String> paramsMap = getParamsMap();
                                            paramsMap.put("imgcode", passimg);
                                            OkhttpUtil.okHttpPost(VAILDPASSIMG, paramsMap, new CallBackString() {
                                                @Override
                                                public void onFailure(Call call, Exception e) {

                                                }

                                                @Override
                                                public void onResponse(String response) {
                                                    Log.d("TAG", response);
                                                    ImageCodeBean imageCodeBean = gson.fromJson(response, ImageCodeBean.class);
                                                    if (imageCodeBean.getCode().equals("S")) {


                                                    } else {
                                                        ToastUtils.showToast("验证码错误");
                                                    }
                                                }
                                            });*/

                                        }

                                    } catch (Exception e) {
                                    }

                                }
                            });

                        } else {
                            ToastUtils.showToast("手机号有误");

                        }


                    } else {

                        ToastUtils.showToast("请输入正确信息或勾选协议");
                    }


                }


                break;
        }
    }

    private void getAgreement(int tag) {
        Bundle bundle = new Bundle();
        bundle.putInt("taghtml", tag);

        open(AgreementActivity.class, bundle);
    }




    @Override
    public boolean getStatusBarColor() {
        return true;
    }





    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {


    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {


    }
}

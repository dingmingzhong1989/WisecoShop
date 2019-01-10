package com.wiseco.wisecoshop.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.wiseco.wisecoshop.R;
import com.wiseco.wisecoshop.activity.AgreementActivity;
import com.wiseco.wisecoshop.activity.MainActivity;
import com.wiseco.wisecoshop.base.BaseActivity;
import com.wiseco.wisecoshop.bean.IDCodeBean;
import com.wiseco.wisecoshop.bean.UpdateUserInfoBean;
import com.wiseco.wisecoshop.dialog.CancelDialog;
import com.wiseco.wisecoshop.dialog.FinishTrueNameDialog;
import com.wiseco.wisecoshop.okhttp.CallBackUtil;
import com.wiseco.wisecoshop.okhttp.OkhttpUtil;
import com.wiseco.wisecoshop.utils.CacheUtil;
import com.wiseco.wisecoshop.utils.KeyboardHelper;
import com.wiseco.wisecoshop.utils.ToastUtils;
import com.wiseco.wisecoshop.view.PopuWindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

import static com.wiseco.wisecoshop.MyApplication.IS_TRUE_NAME;
import static com.wiseco.wisecoshop.MyApplication.IS_USER_REGIEST;
import static com.wiseco.wisecoshop.MyApplication.gson;
import static com.wiseco.wisecoshop.MyApplication.i;
import static com.wiseco.wisecoshop.MyApplication.sContext;
import static com.wiseco.wisecoshop.utils.CheckCID.isIdCard;
import static com.wiseco.wisecoshop.utils.CheckCID.validateIDCard;
import static com.wiseco.wisecoshop.utils.UrlUtil.FILLIDCARD;
import static com.wiseco.wisecoshop.utils.UrlUtil.UPDATAUSERINFO;
import static com.wiseco.wisecoshop.utils.UtilsOther.isFastClick;
import static com.wiseco.wisecoshop.utils.UtilsOther.isLegalName;
import static com.wiseco.wisecoshop.utils.UtilsOther.isTrueName;


/**
 * Created by wiseco on 2018/10/18.
 */

public class RegistIDCardActivity_ extends BaseActivity implements View.OnFocusChangeListener {
    @Bind(R.id.back)
    ImageButton back;
    @Bind(R.id.pup)
    RelativeLayout pup;
    @Bind(R.id.userName)
    EditText userName;
    @Bind(R.id.id_code)
    EditText idCode;
    @Bind(R.id.usernameLinear)
    TableLayout usernameLinear;
    @Bind(R.id.btn_next)
    Button btnNext;
    @Bind(R.id.jump)
    Button jump;
    @Bind(R.id.login_check)
    CheckBox loginCheck;
    @Bind(R.id.text_one)
    TextView textOne;
    @Bind(R.id.text_two)
    TextView textTwo;
    @Bind(R.id.text_three)
    TextView textThree;
    @Bind(R.id.lin)
    LinearLayout lin;
    @Bind(R.id.lin_max)
    LinearLayout linMax;
    @Bind(R.id.bar)
    LinearLayout bar;
    @Bind(R.id.step)
    LinearLayout step;
    private PopuWindow window;
    private ArrayList<Object> datas;
    private Bundle bundle;
    private KeyboardHelper keyboardHelper;
    private int heightWin;

    @Override
    protected void postAgain() {

    }
    @Override
    protected void initViews() {
        setContentView(R.layout.activity_registidcard_);
        ButterKnife.bind(this);
        bundle = getIntent().getExtras();

        keyboardHelper = new KeyboardHelper(this);
        keyboardHelper.onCreate();
        keyboardHelper.setOnKeyboardStatusChangeListener(onKeyBoardStatusChangeListener);
        WindowManager wm1 = this.getWindowManager();
        int width1 = wm1.getDefaultDisplay().getWidth();
        heightWin = wm1.getDefaultDisplay().getHeight();
        userName.setOnFocusChangeListener(this);
        idCode.setOnFocusChangeListener(this);
        // bottomHeight = linearLayout.getHeight();
        //String[] stringArray = getResources().getStringArray(R.array.spinner_xuearr);
    }

    private KeyboardHelper.OnKeyboardStatusChangeListener onKeyBoardStatusChangeListener = new KeyboardHelper.OnKeyboardStatusChangeListener() {
        @Override
        public void onKeyboardPop(int keyboardHeight) {
            int offset=0;
            final int height = keyboardHeight;

            getLocation(btnNext);
            int height1 = bar.getHeight();
            int height2 = linMax.getHeight();
            //Arrays.toString(getLocation(btnNext));

            Log.d("TAG", "height1====" + height1 + "-----" + heightWin + "---------" + height+"--------"+height2);
            Log.d("TAG", Arrays.toString(getLocation(btnNext)));
            if ((heightWin - height1-height2) < height) {
                 offset = (heightWin - height1-height2) - height ;
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
        //initPopupWindow();


    }

    @Override
    public void initListener() {

    }

    public void initDataList() {
        datas = new ArrayList<>();
        for (int i = 1; i <= 20; i++)
            datas.add("Item " + i);
    }

    @OnClick({R.id.back, R.id.text_one, R.id.text_two, R.id.text_three, R.id.btn_next, R.id.jump})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.text_one:
                getAgreement(1);
                break;
            case R.id.text_two:
                getAgreement(2);
                break;
            case R.id.text_three:
                getAgreement(3);
                break;
            case R.id.back:

                if (isTrueName()) {
                  /*  open(UserCentreActivity.class);
                    finish();*/
                    new FinishTrueNameDialog(this).show();
                } else {
                    new CancelDialog(this).show();
                }

                //
                break;
            case R.id.jump:
               /* open(MainActivity.class);
                finish();*/
                break;
            case R.id.btn_next:
                if (isFastClick()) {
                } else {
                    String name = userName.getText().toString().trim();
                    String idcode = idCode.getText().toString().trim();
                    Log.d("TAG", "namenamenamenamename====" + name);
                    if (isTrueName()) {

                        if (isIdCard(idcode)) {
                            if (loginCheck.isChecked() && (name != null && !name.equals("")) && isLegalName(name) && name.length() >= 2 && name.length() <= 10) {
                                i = 4;
                                HashMap<String, String> headsMap = new HashMap<>();
                                headsMap.put("idcard", idcode);
                                headsMap.put("userName", name);
                                OkhttpUtil.okHttpPost(UPDATAUSERINFO, headsMap, new CallBackUtil.CallBackString() {
                                    @Override
                                    public void onFailure(Call call, Exception e) {
                                        ToastUtils.showToast("加载失败");
                                    }

                                    @Override
                                    public void onResponse(String response) {
                                        try {
                                            Log.d("TAG", "responseresponse====" + response);
                                            UpdateUserInfoBean updateUserInfoBean = gson.fromJson(response, UpdateUserInfoBean.class);
                                            if (updateUserInfoBean.getCode().equals("S")) {
                                                ToastUtils.showToast("修改成功");
                                                //open(UserCentreActivity.class);
                                                finish();
                                            } else {

                                                ToastUtils.showToast("修改失败");
                                            }

                                        } catch (Exception e) {


                                        }

                                    }
                                });

                            } else {
                                ToastUtils.showToast("输入正确姓名或勾选协议");
                            }

                        } else {

                            ToastUtils.showToast(validateIDCard(idcode));


                        }


                    } else {

                        if (isIdCard(idcode)) {
                            if (loginCheck.isChecked() && (name != null && !name.equals("")) && isLegalName(name) && name.length() >= 2) {
                                i = 3;
                                HashMap<String, String> headsMap = new HashMap<>();
                                headsMap.put("mobile", CacheUtil.getString(sContext, "mobile", ""));
                                headsMap.put("idcard", idcode);
                                headsMap.put("phonevok", CacheUtil.getString(sContext, "phonevok", ""));
                                headsMap.put("userName", name);
                                OkhttpUtil.okHttpPost(FILLIDCARD, headsMap, new CallBackUtil.CallBackString() {
                                    @Override
                                    public void onFailure(Call call, Exception e) {
                                        ToastUtils.showToast("加载失败");
                                    }

                                    @Override
                                    public void onResponse(String response) {
                                        try {
                                            Log.d("TAG", "responseresponse====" + response);
                                            IDCodeBean idCodeBean = gson.fromJson(response, IDCodeBean.class);

                                            if (idCodeBean.getCode().equals("S")) {
                                               // HttpPostUtils.putEventTag(getUserId(), "", "", "", CONTENT_EVENT_VERIFY_ID, COMMENT_EVENT_VERIFY_ID_SUCCESS);
                                                CacheUtil.putBoolean(sContext, IS_TRUE_NAME, true);
                                                CacheUtil.putBoolean(sContext, IS_USER_REGIEST, true);
                                                ToastUtils.showToast("注册成功");

                                                Intent intent = new Intent();
                                                intent.setAction("com.wiseco.wisecoshop.fragment");
                                                sendBroadcast(intent);
                                                open(MainActivity.class, bundle);
                                                finish();

                                            } else if (idCodeBean.getCode().equals("IV")) {
                                               // HttpPostUtils.putEventTag(getUserId(), "", "", "", CONTENT_EVENT_VERIFY_ID, COMMENT_EVENT_VERIFY_ID_FAILED);
                                                // CacheUtil.putBoolean(sContext, IS_TRUE_NAME, true);
                                                ToastUtils.showToast("非法的证件号码");

                                            } else {
                                                ToastUtils.showToast("数据有误");

                                            }

                                        } catch (Exception e) {


                                        }

                                    }
                                });

                            } else {
                                ToastUtils.showToast("输入正确姓名或勾选协议");
                            }

                        } else {

                            ToastUtils.showToast(validateIDCard(idcode));
                        }


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
    public void onFocusChange(View v, boolean hasFocus) {




    }
}

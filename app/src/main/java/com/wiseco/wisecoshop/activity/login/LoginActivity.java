package com.wiseco.wisecoshop.activity.login;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.wiseco.wisecoshop.R;
import com.wiseco.wisecoshop.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wiseco on 2018/12/6.
 */

public class LoginActivity extends BaseActivity {
    @Bind(R.id.back)
    ImageButton back;
    @Bind(R.id.login_phone_number)
    EditText loginPhoneNumber;
    @Bind(R.id.login_password)
    EditText loginPassword;
    @Bind(R.id.login_show_password)
    ImageView loginShowPassword;
    @Bind(R.id.login_now)
    TextView loginNow;
    @Bind(R.id.login_message)
    TextView loginMessage;
    @Bind(R.id.login_find_password)
    TextView loginFindPassword;
    @Bind(R.id.login_regiest_now)
    TextView loginRegiestNow;
    private boolean showing=false;

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
    protected void initViews() {
        setContentView(R.layout.activity_login_password);
        ButterKnife.bind(this);
    }
    @Override
    protected void postAgain() {

    }


    @OnClick({R.id.back, R.id.login_show_password, R.id.login_now, R.id.login_message, R.id.login_find_password, R.id.login_regiest_now})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
           case R.id.login_show_password:
                if (showing){
                    loginPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    showing=false;
                }else{
                    loginPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    showing=true;
                }


                break;
            case R.id.login_now:




                break;
            case R.id.login_message:
                open(LoginMessageActivity.class);
                break;
            case R.id.login_find_password:
                open(FindPasswordActivity.class);
                break;
            case R.id.login_regiest_now:
                open(RegiestActivity.class);
                break;
        }
    }
}

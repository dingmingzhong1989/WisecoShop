package com.wiseco.wisecoshop.activity.login;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.wiseco.wisecoshop.R;
import com.wiseco.wisecoshop.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wiseco on 2018/12/19.
 */

public class LoginMessageActivity extends BaseActivity {
    @Bind(R.id.back)
    ImageButton back;
    @Bind(R.id.login_phone_number)
    EditText loginPhoneNumber;
    @Bind(R.id.login_code)
    EditText loginCode;
    @Bind(R.id.login_get_code)
    TextView loginGetCode;
    @Bind(R.id.login_now)
    TextView loginNow;
    @Bind(R.id.login_password)
    TextView loginPassword;
    @Bind(R.id.login_find_password)
    TextView loginFindPassword;
    @Bind(R.id.login_regiest_now)
    TextView loginRegiestNow;

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
        setContentView(R.layout.activity_login_message);
        ButterKnife.bind(this);

    }
    @Override
    protected void postAgain() {

    }

    @OnClick({R.id.back, R.id.login_get_code, R.id.login_now, R.id.login_password, R.id.login_find_password, R.id.login_regiest_now})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.login_get_code:
                break;
     case R.id.login_now:
                open(SetPasswordActivity.class);
                break;
            case R.id.login_password:
                open(LoginActivity.class);
                finish();
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

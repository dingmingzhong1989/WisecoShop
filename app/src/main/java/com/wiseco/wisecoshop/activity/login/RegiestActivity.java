package com.wiseco.wisecoshop.activity.login;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wiseco.wisecoshop.R;
import com.wiseco.wisecoshop.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wiseco on 2018/12/19.
 */

public class RegiestActivity extends BaseActivity {
    @Bind(R.id.back)
    ImageButton back;
    @Bind(R.id.regiest_phone_number)
    EditText regiestPhoneNumber;
    @Bind(R.id.regiest_code)
    EditText regiestCode;
    @Bind(R.id.regiest_get_code)
    TextView regiestGetCode;
    @Bind(R.id.regiest_password)
    EditText regiestPassword;
    @Bind(R.id.login_show_password)
    ImageView loginShowPassword;
    @Bind(R.id.regiest_next)
    TextView regiestNext;
    @Bind(R.id.regiest_login)
    LinearLayout regiestLogin;
    @Bind(R.id.user_serve)
    TextView userServe;
    @Bind(R.id.user_privacy)
    TextView userPrivacy;
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
    protected void postAgain() {

    }
    @Override
    protected void initViews() {
        setContentView(R.layout.activity_regiest);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.back, R.id.regiest_get_code, R.id.login_show_password, R.id.regiest_next, R.id.regiest_login, R.id.user_serve, R.id.user_privacy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.regiest_get_code:
                break;
            case R.id.login_show_password:

                if (showing){
                    regiestPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    showing=true;
                }else{
                    regiestPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    showing=false;
                }
                break;
            case R.id.regiest_next:

                open(RegistIDCardActivity.class);
                break;
            case R.id.regiest_login:

                open(LoginActivity.class);
                finish();
                break;
            case R.id.user_serve:
                break;
            case R.id.user_privacy:
                break;
        }
    }
}

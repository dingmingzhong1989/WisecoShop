package com.wiseco.wisecoshop.activity.login;

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
 * Created by wiseco on 2018/12/19.
 */

public class SetPasswordActivity extends BaseActivity {
    @Bind(R.id.back)
    ImageButton back;
    @Bind(R.id.set_password)
    EditText setPassword;
    @Bind(R.id.set_show_password)
    ImageView setShowPassword;
    @Bind(R.id.set_next)
    TextView setNext;
    @Bind(R.id.user_serve)
    TextView userServe;
    @Bind(R.id.user_privacy)
    TextView userPrivacy;

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
        setContentView(R.layout.activity_setpassword);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.back, R.id.set_show_password, R.id.set_next, R.id.user_serve, R.id.user_privacy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.set_show_password:
                break;
            case R.id.set_next:
                open(RegistIDCardActivity.class);
                finish();
                break;
            case R.id.user_serve:
                break;
            case R.id.user_privacy:
                break;
        }
    }
}

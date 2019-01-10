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

public class FindByIDActivity extends BaseActivity {
    @Bind(R.id.back)
    ImageButton back;
    @Bind(R.id.bar_tittle)
    TextView barTittle;
    @Bind(R.id.find_phone_number)
    EditText findPhoneNumber;
    @Bind(R.id.find_code)
    EditText findCode;
    @Bind(R.id.find_next)
    TextView findNext;

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
        setContentView(R.layout.activity_find_by_id);
        ButterKnife.bind(this);
        back.setVisibility(View.VISIBLE);
        barTittle.setVisibility(View.VISIBLE);
        barTittle.setText("找回密码");
    }



    @OnClick({R.id.back, R.id.find_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.find_next:
                break;
        }
    }
}

package com.wiseco.wisecoshop.activity.user;

import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wiseco.wisecoshop.R;
import com.wiseco.wisecoshop.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wiseco on 2018/11/15.
 */

public class AboutUsActivity extends BaseActivity {
    @Bind(R.id.back)
    ImageButton back;
    @Bind(R.id.card_tittle)
    TextView cardTittle;
    @Bind(R.id.bar)
    LinearLayout bar;

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_aboutus);
        ButterKnife.bind(this);

    }

    @Override
    public boolean getStatusBarColor() {
        return true;
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void initListener() {

    }

    @Override
    protected void postAgain() {

    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }


}

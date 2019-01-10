package com.wiseco.wisecoshop.activity.login;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.wiseco.wisecoshop.R;
import com.wiseco.wisecoshop.base.BaseActivity;
import com.wiseco.wisecoshop.dialog.CancelDialog;
import com.wiseco.wisecoshop.dialog.FinishTrueNameDialog;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.wiseco.wisecoshop.utils.UtilsOther.isTrueName;

/**
 * Created by wiseco on 2018/12/19.
 */

public class RegistIDCardActivity extends BaseActivity {
    @Bind(R.id.back)
    ImageButton back;
    @Bind(R.id.user_name)
    EditText userName;
    @Bind(R.id.idcard_number)
    EditText idcardNumber;
    @Bind(R.id.regiest_finsh)
    TextView regiestFinsh;
    @Bind(R.id.login_check)
    CheckBox loginCheck;
    @Bind(R.id.user_use)
    TextView userUse;
    @Bind(R.id.user_data)
    TextView userData;

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
        setContentView(R.layout.activity_id_info);
        ButterKnife.bind(this);
    }

    @Override
    protected void postAgain() {

    }

    @OnClick({R.id.back, R.id.regiest_finsh, R.id.user_use, R.id.user_data})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                if (isTrueName()) {
                  /*  open(UserCentreActivity.class);
                    finish();*/
                  //  new FinishTrueNameDialog(this).show();
                } else {
                    new CancelDialog(this).show();
                }

                break;
            case R.id.regiest_finsh:

                new FinishTrueNameDialog(this).show();

                break;
            case R.id.user_use:
                break;
            case R.id.user_data:
                break;
        }
    }
}

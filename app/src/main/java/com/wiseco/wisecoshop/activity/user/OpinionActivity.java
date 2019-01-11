package com.wiseco.wisecoshop.activity.user;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.wiseco.wisecoshop.R;
import com.wiseco.wisecoshop.base.BaseActivity;
import com.wiseco.wisecoshop.okhttp.CallBackUtil;
import com.wiseco.wisecoshop.okhttp.OkhttpUtil;
import com.wiseco.wisecoshop.utils.CacheUtil;
import com.wiseco.wisecoshop.utils.ToastUtils;

import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

import static com.wiseco.wisecoshop.MyApplication.gson;
import static com.wiseco.wisecoshop.MyApplication.i;
import static com.wiseco.wisecoshop.MyApplication.sContext;
import static com.wiseco.wisecoshop.utils.HttpPostUtils.getParamsMap;
import static com.wiseco.wisecoshop.utils.UrlUtil.WRITECOMMENT;

/**
 * Created by wiseco on 2018/12/19.
 */

public class OpinionActivity extends BaseActivity {
    @Bind(R.id.back)
    ImageButton back;
    @Bind(R.id.bar_tittle)
    TextView barTittle;
    @Bind(R.id.commit_opinion)
    TextView commitOpinion;
    @Bind(R.id.opinion_content)
    EditText opinionContent;

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
        setContentView(R.layout.activity_opinion);
        ButterKnife.bind(this);
    }

    @Override
    protected void postAgain() {

    }

    @OnClick({R.id.back, R.id.commit_opinion})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.commit_opinion:


                i = 4;
                Map<String, String> paramsMap = getParamsMap();
                paramsMap.put("userID", CacheUtil.getString(sContext, "USERID", ""));
                paramsMap.put("comment", opinionContent.getText().toString().trim());
                String s = gson.toJson(paramsMap);

                OkhttpUtil.okHttpPostJson(WRITECOMMENT, s, new CallBackUtil.CallBackString() {
                    @Override
                    public void onFailure(Call call, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        Log.d("TAG", response);
                        try {

                            if (response.contains("S")) {

                                ToastUtils.showToast("提交成功");
                                finish();
                            } else {


                            }

                        } catch (Exception e) {

                            //ToastUtils.showToast("提交失败");
                        }


                    }
                });
                break;
        }
    }
}

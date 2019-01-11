package com.wiseco.wisecoshop.activity;

import android.os.Handler;
import android.view.KeyEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wiseco.wisecoshop.R;
import com.wiseco.wisecoshop.base.BaseMainActivity;
import com.wiseco.wisecoshop.utils.CacheUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.wiseco.wisecoshop.MyApplication.IS_APP_FIRST_OPEN;

/**
 * Created by wiseco on 2018/11/13.22
 */

public class SplashActivity extends BaseMainActivity {
    @Bind(R.id.splash_activity_scene)
    ImageView splashActivityScene;
    @Bind(R.id.version)
    TextView version;
    @Bind(R.id.splash_activity_rl_logo)
    RelativeLayout splashActivityRlLogo;
    private Animation mFadeIn;
    private Animation mFadeScale;

   // @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void initViews() {
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
              //  open(WelcomeActivity.class);
               // finish();
                isFirstOpen();
            }
        }, 5000);
    }
    @Override
    protected void postAgain() {

    }
    @Override
    public boolean getStatusBarColor() {
        return true;
    }

    @Override
    protected void loadData() {
        mFadeIn = AnimationUtils.loadAnimation(this, R.anim.welcome_fade_in);
        mFadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                splashActivityScene.startAnimation(mFadeScale);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mFadeScale = AnimationUtils.loadAnimation(this, R.anim.welcome_fade_in_scale);
        mFadeScale.setFillAfter(true);
        mFadeScale.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
               /* Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                      open(WelcomeActivity.class);
                    }
                }, 100);*/

                open(WelcomeActivity.class);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
       // splashActivityScene.startAnimation(mFadeIn);
       // splashActivityRlLogo.startAnimation(mFadeIn);
    }

    @Override
    public void initListener() {

    }

    //禁止用返回键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    //是否是第一次进入或者是登陆失效

    public void isFirstOpen() {
        // 从sp中取boolean值，判断用户是否已经打开过应用
        boolean isAppFirstOpen = CacheUtil.getBoolean(this, IS_APP_FIRST_OPEN, true);
        if (isAppFirstOpen) {
            // 第一次打开应用，跳转到登陆界面
            open(WelcomeActivity.class);
            finish();
            System.out.println("第一次打开应用，跳转到激活界面");

        } else {
            // 不是第一次打开应用，跳转到主界面
            open(MainActivity.class);
            finish();
            System.out.println("不是第一次打开应，跳转到主界面");

        }


    }

}

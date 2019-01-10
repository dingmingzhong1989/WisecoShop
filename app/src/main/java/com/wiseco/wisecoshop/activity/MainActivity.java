package com.wiseco.wisecoshop.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wiseco.wisecoshop.R;
import com.wiseco.wisecoshop.base.BaseMainActivity;
import com.wiseco.wisecoshop.bean.UpDataVersionBean;
import com.wiseco.wisecoshop.fragment.HomeFragment;
import com.wiseco.wisecoshop.fragment.LifeFragment;
import com.wiseco.wisecoshop.fragment.UserFragment;
import com.wiseco.wisecoshop.okhttp.CallBackUtil;
import com.wiseco.wisecoshop.okhttp.OkhttpUtil;
import com.wiseco.wisecoshop.utils.AppUtil;
import com.wiseco.wisecoshop.utils.CacheUtil;
import com.wiseco.wisecoshop.utils.LocationUtil;
import com.wiseco.wisecoshop.utils.PointUpdataUtils;
import com.wiseco.wisecoshop.view.PopuWindow;

import java.lang.reflect.Field;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

import static com.wiseco.wisecoshop.MyApplication.IS_APP_FIRST_OPEN;
import static com.wiseco.wisecoshop.MyApplication.gson;
import static com.wiseco.wisecoshop.MyApplication.sContext;
import static com.wiseco.wisecoshop.utils.UrlUtil.EVENTLOG;
import static com.wiseco.wisecoshop.utils.UrlUtil.UPDATA;



public class MainActivity extends BaseMainActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    @Bind(R.id.home_fragment)
    FrameLayout homeFragment;
    @Bind(R.id.navigation)
    BottomNavigationView navigation;
    @Bind(R.id.main)
    RelativeLayout main;

    //默认选择第一个fragment
    private int lastSelectedPosition = 0;
    private Fragment[] fragments;
    private String mCurrentVersion;
    private PopuWindow window;

    private TextView content1;
    private UpDataVersionBean upDataVersionBean;

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

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        CacheUtil.putBoolean(this, IS_APP_FIRST_OPEN, false);
        int[][] states = new int[][]{
                new int[]{-android.R.attr.state_checked},
                new int[]{android.R.attr.state_checked}
        };

        int[] colors = new int[]{getResources().getColor(R.color.black_51617e),
                getResources().getColor(R.color.blue)
        };
        ColorStateList csl = new ColorStateList(states, colors);
        navigation.setItemTextColor(csl);
        initFragments();
        //CacheUtil.putString(this,"IDCard", "130722198903283411");

        try {
            LocationUtil.getLongitudeAndLatitude(sContext);
          //  PointUpdataUtils.sendUserExt(this,"https://dev.wisecofincloud.com/api/eventlog/sendEventLog");
            PointUpdataUtils.sendNetwork(this,EVENTLOG);
        } catch (Exception e) {


        }
        //检查版本
        mCurrentVersion =  AppUtil.getVersionName(this);
        checkVersion();
    }
    @Override
    protected void postAgain() {

    }
    //检查版本
    private void checkVersion() {
        //newestVersion从服务端获取
        // final String newestVersion = "1.2.0";

        OkhttpUtil.okHttpGet(UPDATA, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                try {
                    Log.d("TAG", response);
                    upDataVersionBean = gson.fromJson(response, UpDataVersionBean.class);

                    if (upDataVersionBean.getCode().equals("S")) {

                        String newestVersion = upDataVersionBean.getItem().getVersion();

                        String con = upDataVersionBean.getItem().getContent();
                        if (newestVersion.equals(mCurrentVersion)) {
                            // ToastUtils.toastInCenter(this, R.string.check_version_toast_newest);

                        } else {

                            initPopupWindow();
                            content1.setText(con.replace("|", "\n"));
                            window.showAtLocation(main, Gravity.CENTER, 0, 0);
                            WindowManager.LayoutParams lp = getWindow().getAttributes();
                            lp.alpha = 0.3f;
                            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                            getWindow().setAttributes(lp);
                        }
                    } else {


                    }

                } catch (Exception e) {
                }


            }
        });

    }

    private void initPopupWindow() {
        // get the height of screen
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int screenHeight = metrics.heightPixels;
        // create popup window
        window = new PopuWindow(this, R.layout.update, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT) {

            private TextView updata;
            private ImageButton cancel;

            @Override
            protected void initView() {
                View view = getContentView();

                content1 = (TextView) view.findViewById(R.id.content);
                updata = (TextView) view.findViewById(R.id.updata);
                cancel = (ImageButton) view.findViewById(R.id.cancel);


            }

            @Override
            protected void initEvent() {

                if (upDataVersionBean.getItem().isNecessary()){
                    cancel.setVisibility(View.INVISIBLE);
                    updata.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            shareAppShop(MainActivity.this, getPackageName());

                        }
                    });
                }else{

                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            window.getPopupWindow().dismiss();
                        }
                    });
                }

            }

            @Override
            protected void initWindow() {
                super.initWindow();
                PopupWindow instance = getPopupWindow();
                instance.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        WindowManager.LayoutParams lp = getWindow().getAttributes();
                        lp.alpha = 1.0f;
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                        getWindow().setAttributes(lp);
                    }
                });
            }
        };


    }
    /**
     * 根据应用包名，跳转到应用市场 * * @param activity 承载跳转的Activity * @param packageName 所需下载（评论）的应用包名
     */
    public void shareAppShop(Activity activity, String packageName) {
        try {
            Uri uri = Uri.parse("market://details?id=" + packageName);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            activity.startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(activity, "您没有安装应用市场", Toast.LENGTH_SHORT).show();
            // upDataApp("");
        }
    }


    private void initFragments() {
        //navigation.setItemTextColor(null);
        navigation.setItemIconTintList(null);
       /* Resources resources=(Resources)getBaseContext().getResources();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            navigation.setItemTextColor(resources.getColorStateList(R.color.home_color_shape, null));
        }*/
        //监听切换事件
        navigation.setOnNavigationItemSelectedListener(this);
        //平均布局
        setItemType(navigation);
        // 添加角标消息数
        // setAddNumber();
        HomeFragment homeFragment = new HomeFragment();
        LifeFragment lifeFragment = new LifeFragment();
        UserFragment userFragment = new UserFragment();
        fragments = new Fragment[]{homeFragment, lifeFragment, userFragment};
        lastSelectedPosition = 0;
        // 默认提交第一个
        getSupportFragmentManager().beginTransaction()
                .add(R.id.home_fragment, homeFragment)//添加
                .show(homeFragment)//展示
                .commit();//提交


    }

    /**
     * 防止超过3个fragment布局不平分
     */
    @SuppressLint("RestrictedApi")
    private void setItemType(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);

        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                //noinspection RestrictedApi
                item.setShiftingMode(false);
                // set once again checked value, so view will be updated
                // noinspection RestrictedApi
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Log.e("BNVHelper", "Unable to get shift mode field", e);
        } catch (IllegalAccessException e) {
            Log.e("BNVHelper", "Unable to change value of shift mode", e);
        }
    }

    /**
     * 切换事件
     */
    @Override
    public boolean onNavigationItemSelected( MenuItem item) {
        switch (item.getItemId()) {

            case R.id.navigation_home_goods:
                if (0 != lastSelectedPosition) {
                    setDefaultFragment(lastSelectedPosition, 0);
                    lastSelectedPosition = 0;
                }
                return true;
            case R.id.navigation_home_life:
                if (1 != lastSelectedPosition) {
                    setDefaultFragment(lastSelectedPosition, 1);
                    lastSelectedPosition = 1;
                }
                return true;
            case R.id.navigation_home_user:
                if (2 != lastSelectedPosition) {
                    setDefaultFragment(lastSelectedPosition, 2);
                    lastSelectedPosition = 2;
                }
                return true;


        }


        return false;
    }

    /**
     * 切换Fragment
     *
     * @param lastIndex 上个显示Fragment的索引
     * @param index     需要显示的Fragment的索引
     */
    private void setDefaultFragment(int lastIndex, int index) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(fragments[lastIndex]);
        if (!fragments[index].isAdded()) {
            transaction.add(R.id.home_fragment, fragments[index]);
        }
        //需要展示fragment下标的位置
        // commit：安排该事务的提交。这一承诺不会立即发生;它将被安排在主线程上，以便在线程准备好的时候完成。
        // commitAllowingStateLoss：与 commit类似，但允许在活动状态保存后执行提交。这是危险的，因为如果Activity需要从其状态恢复，
        // 那么提交就会丢失，因此，只有在用户可以意外地更改UI状态的情况下，才可以使用该提交
        transaction.show(fragments[index]).commit();
    }
}


package com.wiseco.wisecoshop.activity.order;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wiseco.wisecoshop.R;
import com.wiseco.wisecoshop.base.BaseActivity;
import com.wiseco.wisecoshop.fragment.order.OrderCardFragment;
import com.wiseco.wisecoshop.fragment.order.OrderMoneyFragment;
import com.wiseco.wisecoshop.view.NoScrollViewpager;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wiseco on 2018/10/18.
 */

public class OrderActivity extends BaseActivity {

    @Bind(R.id.back)
    ImageButton back;
    @Bind(R.id.bar_tittle)
    TextView barTittle;
    @Bind(R.id.tv_address)
    TextView tvAddress;
    @Bind(R.id.lin_address_home)
    LinearLayout linAddressHome;
    @Bind(R.id.message)
    ImageButton message;
    @Bind(R.id.line)
    View line;
    @Bind(R.id.tablayout)
    TabLayout tablayout;
    @Bind(R.id.tab_viewpager)
    NoScrollViewpager tabViewpager;
    private String[] mTabTitles = new String[2];
    private ArrayList<Fragment> fragments = new ArrayList<>();

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_order);
        ButterKnife.bind(this);
        barTittle.setVisibility(View.VISIBLE);
        back.setVisibility(View.VISIBLE);
        barTittle.setText("订单中心");
        OrderMoneyFragment orderMoneyFragment = new OrderMoneyFragment();
        OrderCardFragment orderCardFragment = new OrderCardFragment();


        fragments.add(orderMoneyFragment);
        fragments.add(orderCardFragment);

        // fragments.add(orderNoPassFragment);
        mTabTitles[0] = "现金分期";
        mTabTitles[1] = "信用卡";

        // mTabTitles[3] = "未通过";
        tablayout.setTabMode(TabLayout.GRAVITY_CENTER);
        PagerAdapter pagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        tabViewpager.setAdapter(pagerAdapter);
        //将ViewPager和TabLayout绑定
        tablayout.setupWithViewPager(tabViewpager);

        setSelect(0);
    }

    private void setSelect(int i) {

        tabViewpager.setCurrentItem(i);
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


    final class MyViewPagerAdapter extends FragmentPagerAdapter {
        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Log.d("TAG", position + "");

            return fragments.get(position);
        }


        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {

            return mTabTitles[position];

        }
    }

}
package com.wiseco.wisecoshop.activity.user;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.wiseco.wisecoshop.R;
import com.wiseco.wisecoshop.base.BaseActivity;
import com.wiseco.wisecoshop.fragment.ticket.TicketNoUsedFragment;
import com.wiseco.wisecoshop.fragment.ticket.TicketTimeOutFragment;
import com.wiseco.wisecoshop.fragment.ticket.TicketUsedFragment;
import com.wiseco.wisecoshop.view.NoScrollViewpager;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wiseco on 2018/12/18.
 */

public class MyTicketActivity extends BaseActivity {
    @Bind(R.id.back)
    ImageButton back;
    @Bind(R.id.bar_tittle)
    TextView barTittle;
    @Bind(R.id.tablayout)
    TabLayout tablayout;
    @Bind(R.id.tab_viewpager)
    NoScrollViewpager tabViewpager;

    private String[] mTabTitles = new String[3];
    private ArrayList<Fragment> fragments = new ArrayList<>();

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
        setContentView(R.layout.activity_myticket);

        ButterKnife.bind(this);

        barTittle.setVisibility(View.VISIBLE);
        back.setVisibility(View.VISIBLE);
        barTittle.setText("我的券包");

        TicketNoUsedFragment ticketNoUsedFragment = new TicketNoUsedFragment();
        TicketUsedFragment ticketUsedFragment = new TicketUsedFragment();
        TicketTimeOutFragment ticketTimeOutFragment = new TicketTimeOutFragment();
        fragments.add(ticketNoUsedFragment);
        fragments.add(ticketUsedFragment);
        fragments.add(ticketTimeOutFragment);


        // fragments.add(orderNoPassFragment);
        mTabTitles[0] = "未使用";
        mTabTitles[1] = "已使用";
        mTabTitles[2] = "已过期";
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

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}

package com.wiseco.wisecoshop.activity.user;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wiseco.wisecoshop.R;
import com.wiseco.wisecoshop.base.BaseActivity;
import com.wiseco.wisecoshop.fragment.MessageFragment;
import com.wiseco.wisecoshop.fragment.SystemMessageFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wiseco on 2018/12/4.
 */

public class MessageActivity extends BaseActivity {

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
    @Bind(R.id.tablayout)
    TabLayout tablayout;
    @Bind(R.id.rel)
    RelativeLayout rel;
    @Bind(R.id.tab_viewpager)
    com.wiseco.wisecoshop.view.NoScrollViewpager tabViewpager;
    private String[] mTabTitles = new String[2];
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
        setContentView(R.layout.activity_message);
        ButterKnife.bind(this);
        barTittle.setText("消息中心");
        back.setVisibility(View.VISIBLE);
        MessageFragment messageFragment = new MessageFragment();
        SystemMessageFragment systemMessageFragment = new SystemMessageFragment();
        //添加fragment到集合中时注意顺序

        fragments.add(messageFragment);
        fragments.add(systemMessageFragment);
        mTabTitles[0] = "我的消息";
        mTabTitles[1] = "系统消息";
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

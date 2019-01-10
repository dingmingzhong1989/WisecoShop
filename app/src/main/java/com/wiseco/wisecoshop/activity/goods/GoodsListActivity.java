package com.wiseco.wisecoshop.activity.goods;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.wiseco.wisecoshop.R;
import com.wiseco.wisecoshop.activity.MainActivity;
import com.wiseco.wisecoshop.base.BaseActivity;
import com.wiseco.wisecoshop.fragment.goods.CardListFragment;
import com.wiseco.wisecoshop.fragment.goods.MoneyListFragment;
import com.wiseco.wisecoshop.view.NoScrollViewpager;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wiseco on 2018/12/6.
 */

public class GoodsListActivity extends BaseActivity {
    @Bind(R.id.back)
    ImageButton back;
    @Bind(R.id.tablayout)
    TabLayout tablayout;
    @Bind(R.id.bar)
    RelativeLayout bar;
    @Bind(R.id.view)
    View view;
    @Bind(R.id.tab_viewpager)
    NoScrollViewpager tabViewpager;
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
    protected void initViews() {
        setContentView(R.layout.activity_goodslist);
        ButterKnife.bind(this);
        CardListFragment cardListFragment = new CardListFragment();
        MoneyListFragment moneyListFragment = new MoneyListFragment();

        //  moneyFragment moneyFragment = new moneyFragment();
        //添加fragment到集合中时注意顺序
        fragments.add(cardListFragment);
        fragments.add(moneyListFragment);

        // fragments.add(cardListFragment);

        mTabTitles[0] = "  信用卡";
        mTabTitles[1] = "现金分期";
        tablayout.setTabMode(TabLayout.GRAVITY_CENTER);

        PagerAdapter pagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        tabViewpager.setAdapter(pagerAdapter);
        //将ViewPager和TabLayout绑定
        tablayout.setupWithViewPager(tabViewpager);

        setSelect(getIntent().getIntExtra("tag", 0));
    }
    @Override
    protected void postAgain() {

    }
    //当前的fragment
    private void setSelect(int i) {

        tabViewpager.setCurrentItem(i);
    }

    //viewPager的adapter
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

    @OnClick(R.id.back)
    public void onViewClicked(View view) {
        open(MainActivity.class);
        finish();
    }
}

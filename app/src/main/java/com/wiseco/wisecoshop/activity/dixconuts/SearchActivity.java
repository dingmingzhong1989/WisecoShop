package com.wiseco.wisecoshop.activity.dixconuts;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wiseco.wisecoshop.R;
import com.wiseco.wisecoshop.adapter.channel.SearchListAdapter;
import com.wiseco.wisecoshop.base.BaseActivity;
import com.wiseco.wisecoshop.dao.GreenDaoHelper;
import com.wiseco.wisecoshop.greendao.UserDao;
import com.wiseco.wisecoshop.greendao.model.User;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import scut.carson_ho.searchview.ICallBack;
import scut.carson_ho.searchview.SearchView;

/**
 * Created by wiseco on 2018/12/16.
 */

public class SearchActivity extends BaseActivity {
    @Bind(R.id.back)
    ImageButton back;
    @Bind(R.id.tv_search)
    TextView tvSearch;
    @Bind(R.id.search_view)
    SearchView searchView;
    @Bind(R.id.lin_search)
    LinearLayout linSearch;
    @Bind(R.id.hot_flowlayout)
    TagFlowLayout hotFlowlayout;
    @Bind(R.id.list_search)
    ListView listSearch;
    @Bind(R.id.search_greendao_rl)
    RelativeLayout searchGreendaoRl;
    @Bind(R.id.history_flowlayout)
    TagFlowLayout historyFlowlayout;
    @Bind(R.id.search_greendao_delete)
    Button searchGreendaoDelete;
    List<User> list;
    String[] names = {"美食", "汽车", "旅游", "过年回家没票了", "过年版本上线了", "吃饭睡觉打豆豆", "天王盖地虎", "宝塔镇河妖"};
    List<String> searchList = new ArrayList<>();
    private GreenDaoHelper helper;
    private UserDao userDao;
    String name = "";

    @Override
    public boolean getStatusBarColor() {
        return true;
    }

    @Override
    protected void loadData() {

        //搜索历史列表
        updateList();
        //热门搜索
        hotFlowlayout.setAdapter(new TagAdapter<String>(names) {
            @Override
            public View getView(FlowLayout parent, int position, final String s) {
                final TextView tv = (TextView) LayoutInflater.from(SearchActivity.this).inflate(
                        R.layout.search_page_flowlayout_tv, hotFlowlayout, false);
                tv.setText(s);
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "" + s, Toast.LENGTH_SHORT).show();

                    }
                });
                return tv;
            }
        });

        //搜索监听
        searchView.setOnClickSearch(new ICallBack() {
            @Override
            public void SearchAciton(String string) {

                name = string;
                Log.e("name--------", name + "");


                listSearch.setAdapter(new SearchListAdapter(SearchActivity.this, searchList));
                insertDB();

            }
        });

    }

    /**
     * 初始化adapter，更新list，重新加载列表
     */
    private void updateList() {
        //查询所有
        list = userDao.queryBuilder().list();
        //这里用于判断是否有数据
        if (list.size() == 0) {
            searchGreendaoRl.setVisibility(View.VISIBLE);
            searchGreendaoDelete.setVisibility(View.GONE);
            historyFlowlayout.setVisibility(View.INVISIBLE);
        } else {
            searchGreendaoRl.setVisibility(View.GONE);
            searchGreendaoDelete.setVisibility(View.VISIBLE);
            historyFlowlayout.setVisibility(View.VISIBLE);
            List<String> stringList = new ArrayList<>();

            for (int i = 0; i < list.size(); i++) {
                stringList.add(list.get(i).getName());

            }

            historyFlowlayout.setAdapter(new TagAdapter<String>(stringList) {
                @Override
                public View getView(FlowLayout parent, int position, final String s) {
                    final TextView tv = (TextView) LayoutInflater.from(SearchActivity.this).inflate(
                            R.layout.search_page_flowlayout_tv, historyFlowlayout, false);
                    tv.setText(s);
                    tv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getApplicationContext(), "" + s, Toast.LENGTH_SHORT).show();

                        }
                    });
                    return tv;
                }
            });
        }


    }

    @Override
    protected void postAgain() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initViews() {

        setContentView(R.layout.search_activity);
        ButterKnife.bind(this);
        linSearch.setVisibility(View.VISIBLE);
        //初始化数据库
        initDbHelp();


    }

    //    /*初始化数据库相关*/
    private void initDbHelp() {
        helper = new GreenDaoHelper(this);
        userDao = helper.initDao().getUserDao();
    }

    @OnClick({R.id.back, R.id.tv_search, R.id.hot_flowlayout, R.id.history_flowlayout, R.id.search_greendao_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.tv_search:

               /* searchView.setOnClickSearch(new ICallBack() {
                    @Override
                    public void SearchAciton(String string) {

                    }
                });*/

                break;
            case R.id.hot_flowlayout:
                break;
            case R.id.history_flowlayout:
                break;
            case R.id.search_greendao_delete:

                delectAllDB();
                break;
        }
    }

    //增加数据
    private void insertDB() {
        try {
            if (list.size() < 6) {
                //删除已经存在重复的搜索历史
                List<User> list2 = userDao.queryBuilder()
                        .where(UserDao.Properties.Name.eq(name)).build().list();
                userDao.deleteInTx(list2);
                //添加
                if (!name.equals(""))
                    userDao.insert(new User(null, name));

                Toast.makeText(getApplicationContext(), "插入数据成功:" + name, Toast.LENGTH_SHORT).show();
            } else {
                //删除第一条数据，用于替换最后一条搜索
                userDao.delete(userDao.queryBuilder().list().get(0));
                //删除已经存在重复的搜索历史
                List<User> list3 = userDao.queryBuilder()
                        .where(UserDao.Properties.Name.eq(name)).build().list();
                userDao.deleteInTx(list3);
                //添加
                if (!name.equals(""))
                    userDao.insert(new User(null, name));
            }
            Log.d("TAG", "list.size()=====" + list.size());
            updateList();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "插入失败", Toast.LENGTH_SHORT).show();
        }

    }

    //清空数据库
    private void delectAllDB() {
        try {
            userDao.deleteAll();
            list.clear();
            updateList();

            Toast.makeText(getApplicationContext(), "清空数据库", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            // Log.e("exception-----delete", user + "message:" + e.getMessage() + "");
        }
    }
}

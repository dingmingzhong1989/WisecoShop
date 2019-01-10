package com.wiseco.wisecoshop.activity.dixconuts;


import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.wiseco.wisecoshop.R;
import com.wiseco.wisecoshop.base.BaseActivity;
import com.wiseco.wisecoshop.view.city.CityAdapter;
import com.wiseco.wisecoshop.view.city.CitySortModel;
import com.wiseco.wisecoshop.view.city.EditTextWithDel;
import com.wiseco.wisecoshop.view.city.HisCityAdapter;
import com.wiseco.wisecoshop.view.city.PinyinComparator;
import com.wiseco.wisecoshop.view.city.PinyinUtils;
import com.wiseco.wisecoshop.view.city.SideBar;
import com.wiseco.wisecoshop.view.city.SortAdapter;
import com.wiseco.wisecoshop.view.city.SpinerPopWindow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.wiseco.wisecoshop.R.id.poistion_dropDownMenu;

/**
 * Created by wiseco on 2018/12/17.
 */

public class LocationActivity extends BaseActivity {


    @Bind(R.id.back)
    ImageButton back;
    @Bind(R.id.tv_search)
    TextView tvSearch;
    @Bind(R.id.et_search)
    EditTextWithDel etSearch;
    @Bind(R.id.listView)
    ListView listView;
    @Bind(R.id.sidrbar)
    SideBar sidrbar;
    @Bind(R.id.current_poistion)
    TextView currentPoistion;
    @Bind(poistion_dropDownMenu)
    TextView poistionDropDownMenu;
    private List<CitySortModel> SourceDateList;
    private SortAdapter adapter;
    private List<String> popupViews = new ArrayList<>();
    private String headers[] = {"选择区县"};
    private String citys[] = {"选择区县", "选择区县选择区县", "选择区县选择区县", "选择区县", "选择区县", "选择区县选择区县"};
    private SpinerPopWindow<String> mSpinerPopWindow;

    ArrayList<String> city = new ArrayList<>();

    @Override
    public boolean getStatusBarColor() {
        return true;
    }

    @Override
    protected void loadData() {

    }
    @Override
    protected void postAgain() {

    }
    @Override
    protected void initListener() {

    }

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_location);
        ButterKnife.bind(this);
        // initDropDownMenu();

        String stringExtra = getIntent().getStringExtra("ADDRESS");
        if (stringExtra != null) {

            city.add(stringExtra.split(",")[0]);
            currentPoistion.setText(stringExtra.split(",")[1]);
        } else {


        }

        initEvents();
        setAdapter();
        for (int i = 0; i < citys.length; i++) {
            popupViews.add(citys[i]);
        }

        poistionDropDownMenu.setOnClickListener(clickListener);
        mSpinerPopWindow = new SpinerPopWindow<String>(this, popupViews, itemClickListener);
        mSpinerPopWindow.setOnDismissListener(dismissListener);
    }

    /**
     * 监听popupwindow取消
     */
    private PopupWindow.OnDismissListener dismissListener = new PopupWindow.OnDismissListener() {
        @Override
        public void onDismiss() {
            //  setTextImage(R.drawable.icon_down);
        }
    };

    /**
     * popupwindow显示的ListView的item点击事件
     */
    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            mSpinerPopWindow.dismiss();
            poistionDropDownMenu.setText(popupViews.get(position));
            Toast.makeText(getApplicationContext(), "点击了:" + popupViews.get(position), Toast.LENGTH_LONG).show();
        }
    };

    /**
     * 显示PopupWindow
     */
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case poistion_dropDownMenu:
                    mSpinerPopWindow.setWidth(poistionDropDownMenu.getWidth());
                    mSpinerPopWindow.showAsDropDown(poistionDropDownMenu);

                    //setTextImage(R.drawable.icon_up);
                    break;
            }
        }
    };

    private void setAdapter() {
        SourceDateList = filledData(getResources().getStringArray(R.array.data));
        Collections.sort(SourceDateList, new PinyinComparator());
        adapter = new SortAdapter(this, SourceDateList);
        listView.addHeaderView(initHeadView());
        listView.setAdapter(adapter);
    }

    private View initHeadView() {
        View headView = getLayoutInflater().inflate(R.layout.headview, null);
        final GridView mGvCity = (GridView) headView.findViewById(R.id.gv_hot_city);
        GridView mHisCity = (GridView) headView.findViewById(R.id.gv_his_city);
        String[] datas = getResources().getStringArray(R.array.city);
        final ArrayList<String> cityList = new ArrayList<>();
        for (int i = 0; i < datas.length; i++) {
            cityList.add(datas[i]);
        }
        final CityAdapter adapter = new CityAdapter(getApplicationContext(), R.layout.gridview_item, cityList);
        HisCityAdapter hisadapter = new HisCityAdapter(getApplicationContext(), R.layout.gridview_item, city);
        mGvCity.setAdapter(adapter);
        mHisCity.setAdapter(hisadapter);
        mGvCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setAction("com.wiseco.wisecoshop.Lifefragment");
                intent.putExtra("name", cityList.get(position).replace("市",""));
                sendBroadcast(intent);
                finish();
            }
        });
        return headView;
    }

    private void initEvents() {
        //设置右侧触摸监听
        sidrbar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                //该字母首次出现的位置
                int position = adapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    listView.setSelection(position + 1);
                }
            }
        });

        //ListView的点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // mTvTitle.setText(((CitySortModel) adapter.getItem(position - 1)).getName());
                Toast.makeText(getApplication(), ((CitySortModel) adapter.getItem(position)).getName(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent();
                intent.setAction("com.wiseco.wisecoshop.Lifefragment");
                intent.putExtra("name",  ((CitySortModel) adapter.getItem(position)).getName().replace("市",""));
                sendBroadcast(intent);
                finish();

            }
        });

        //根据输入框输入值的改变来过滤搜索
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
                filterData(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    /**
     * 根据输入框中的值来过滤数据并更新ListView
     *
     * @param filterStr
     */
    private void filterData(String filterStr) {
        List<CitySortModel> mSortList = new ArrayList<>();
        if (TextUtils.isEmpty(filterStr)) {
            mSortList = SourceDateList;
        } else {
            mSortList.clear();
            for (CitySortModel sortModel : SourceDateList) {
                String name = sortModel.getName();
                if (name.toUpperCase().indexOf(filterStr.toString().toUpperCase()) != -1 ||
                        PinyinUtils.getPingYin(name).toUpperCase().startsWith(filterStr.toString().toUpperCase())) {
                    mSortList.add(sortModel);
                }
            }
        }
        // 根据a-z进行排序
        Collections.sort(mSortList, new PinyinComparator());
        adapter.updateListView(mSortList);
    }

    private List<CitySortModel> filledData(String[] date) {
        List<CitySortModel> mSortList = new ArrayList<>();
        ArrayList<String> indexString = new ArrayList<>();

        for (int i = 0; i < date.length; i++) {
            CitySortModel sortModel = new CitySortModel();
            sortModel.setName(date[i]);
            String pinyin = PinyinUtils.getPingYin(date[i]);
            String sortString = pinyin.substring(0, 1).toUpperCase();
            if (sortString.matches("[A-Z]")) {
                sortModel.setSortLetters(sortString.toUpperCase());
                if (!indexString.contains(sortString)) {
                    indexString.add(sortString);
                }
            }
            mSortList.add(sortModel);
        }
        Collections.sort(indexString);
        //sidrbar.setIndexText(indexString);
        return mSortList;
    }

    @OnClick({R.id.back, R.id.tv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.tv_search:
                break;
        }
    }

   /* @Override
    public void onBackPressed() {
        //退出activity前关闭菜单
        if (poistionDropDownMenu.isShowing()) {
            poistionDropDownMenu.closeMenu();
        } else {
            super.onBackPressed();
        }
    }*/

}

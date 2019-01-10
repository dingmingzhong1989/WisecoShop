package com.wiseco.wisecoshop.adapter.channel;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by wiseco on 2019/1/9.
 */

public class SearchListAdapter extends BaseAdapter {


    private Context mContext;
    private List<String> mSearchList;

    public SearchListAdapter(Context context, List<String> searchList) {

        mContext = context;
        mSearchList = searchList;

    }

    @Override
    public int getCount() {
        return mSearchList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {



        return null;
    }
}

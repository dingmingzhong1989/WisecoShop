package com.wiseco.wisecoshop.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wiseco.wisecoshop.R;
import com.wiseco.wisecoshop.bean.GridViewBean;

import java.util.HashMap;
import java.util.List;

import static com.wiseco.wisecoshop.MyApplication.sContext;
import static com.wiseco.wisecoshop.utils.UrlUtil.BASE;


/**
 * Created by wiseco on 2018/10/23.
 */

public class MyGridAdapter extends BaseAdapter {
    private List<GridViewBean.BankListBean> data;//数据
    private int clickTemp = -1;
    private HashMap<Integer, View> map = new HashMap<Integer, View>();

    public MyGridAdapter(List<GridViewBean.BankListBean> data) {
        this.data = data;
    }


    //标识选择的Item
    public void setSeclection(int position) {
        clickTemp = position;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder viewHolder;

       if (map.get(position) == null) {
            view = LayoutInflater.from(sContext).inflate(R.layout.grid_item, parent, false);
            viewHolder = new ViewHolder();
            map.put(position, view);
            viewHolder.imageView = (ImageView) view.findViewById(R.id.image1);
            viewHolder.textView = (TextView) view.findViewById(R.id.text1);
            viewHolder.fram = (FrameLayout) view.findViewById(R.id.fram);
            view.setTag(viewHolder);
        } else {
            view = map.get(position);
            viewHolder = (ViewHolder) view.getTag();
        }
       viewHolder.textView.setText(data.get(position).getName());
        Glide.with(sContext).load(BASE + data.get(position).getIcon()).into(viewHolder.imageView);

        return view;
    }

    private class ViewHolder {
        ImageView imageView;
        TextView textView;
        FrameLayout fram;
    }

}
package com.wiseco.wisecoshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wiseco.wisecoshop.R;
import com.wiseco.wisecoshop.bean.discount.ActsBean;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * Created by wiseco on 2018/12/17.
 */

public class DisconutAdapter extends BaseAdapter {


    private final List<ActsBean> mActsList;
    private  Context mContent;

    public DisconutAdapter(Context context, List<ActsBean> acts) {

        this.mContent=context;
        this.mActsList=acts;

    }

    @Override
    public int getCount() {
        return mActsList.size();
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

        LayoutInflater inflater = LayoutInflater.from(mContent);
        ViewHolder viewholder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.disconnts_item, null);
             viewholder = new ViewHolder();
            viewholder.imageView = convertView.findViewById(R.id.lv_image);
            viewholder.textTitle = convertView.findViewById(R.id.lv_text_til);
            viewholder.textContent = convertView.findViewById(R.id.lv_text_con);
            viewholder.times = convertView.findViewById(R.id.times);
             convertView.setTag(viewholder);
            AutoUtils.auto(convertView);
        } else {
            viewholder = (ViewHolder) convertView.getTag();
        }
        viewholder.textTitle.setText(mActsList.get(position).getBankName());
        viewholder.textTitle.setText(mActsList.get(position).getTitle());
        viewholder.times.setText(mActsList.get(position).getAvailableTime());

        Glide.with(mContent).load(mActsList.get(position).getBigImgUrl()).error(R.color.background).into(viewholder.imageView );
        return convertView;

    }

    class ViewHolder {
        public ImageView imageView;
        public TextView textTitle;
        public TextView textContent;
        public TextView times;
    }
}

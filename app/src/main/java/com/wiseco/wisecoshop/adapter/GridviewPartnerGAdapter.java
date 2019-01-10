package com.wiseco.wisecoshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wiseco.wisecoshop.R;
import com.wiseco.wisecoshop.bean.PartnerBean;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * Created by wiseco on 2018/12/9.
 */

public class GridviewPartnerGAdapter extends BaseAdapter {

    private final List<PartnerBean> mPartner;
    private  Context mContext;

    public GridviewPartnerGAdapter(Context context, List<PartnerBean> partner) {

        mContext=context;
        mPartner=partner;

    }
    @Override
    public int getCount() {
        return mPartner.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewholder;
        LayoutInflater inflater = LayoutInflater.from(mContext);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.gridview_partner_item, null);
            viewholder = new ViewHolder();
            viewholder.imageView = convertView.findViewById(R.id.image1);

            convertView.setTag(viewholder);
            AutoUtils.auto(convertView);
        } else {
            viewholder = (ViewHolder) convertView.getTag();
        }

        Glide.with(mContext).load(mPartner.get(position).getImageUrl()).error(R.drawable.itemic).into(viewholder.imageView );
        return convertView;
    }

    class ViewHolder {
        public ImageView imageView;


    }
}

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
import com.wiseco.wisecoshop.bean.discount.ShopDtoBean;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by wiseco on 2018/12/12.
 */

public class LifeDiscountsAdapter extends BaseAdapter {

    private final List<ShopDtoBean> mShopList;
    private  Context mContext;


    public LifeDiscountsAdapter(Context context,List<ShopDtoBean> shopDto){


        mContext=context;

        mShopList=shopDto;
    }



    @Override
    public int getCount() {
        return mShopList.size();
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
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewHolder viewholder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.disconnts_listview_item, null);
             viewholder = new ViewHolder();
            viewholder.imageView = convertView.findViewById(R.id.cardicon);
            viewholder.textTitle = convertView.findViewById(R.id.name);
            viewholder.textContent = convertView.findViewById(R.id.tag_one_content);
            viewholder.miles = convertView.findViewById(R.id.miles);
            viewholder.tag_one_type = convertView.findViewById(R.id. tag_one_type);
            viewholder.tag_two_type = convertView.findViewById(R.id.tag_two_type);
            viewholder.tag_two_content = convertView.findViewById(R.id.tag_two_content);

             convertView.setTag(viewholder);
            AutoUtils.auto(convertView);
        } else {
            viewholder = (ViewHolder) convertView.getTag();
        }
        Glide.with(mContext).load(mShopList.get(position).getLogoSmall()).
                placeholder(R.drawable.itemic)
                .error(R.drawable.itemic)
                .into( ( viewholder.imageView));
        viewholder.textTitle.setText(mShopList.get(position).getShopName());

        Map<String, String> actDescribe = mShopList.get(position).getActDescribe();
        List<String> listKey = new ArrayList<>();
        List<String> listValue = new ArrayList<>();

        for (Map.Entry<String, String> entry : actDescribe.entrySet()) {
            listKey.add(entry.getKey());
            listValue.add(entry.getValue());
        }

        if (listKey.size()==1){
            viewholder.tag_one_type.setText(listKey.get(0));
            viewholder.textContent.setText(listValue.get(0));
            viewholder.tag_two_type.setVisibility(View.INVISIBLE);
            viewholder.tag_two_content.setVisibility(View.INVISIBLE);
        }else{
            viewholder.tag_one_type.setText(listKey.get(0));
            viewholder.textContent.setText(listValue.get(0));
            viewholder.tag_two_type.setText(listKey.get(1));
            viewholder.tag_two_content.setText(listValue.get(1));

        }



        //
        viewholder.miles.setText(mShopList.get(position).getMiles()+"米");

        return convertView;

    }

    class ViewHolder {
        public ImageView imageView;
        public TextView textTitle;
        public TextView textContent;
        public TextView miles;
        public TextView tag_one_type;
        public TextView tag_two_type;
        public TextView tag_two_content;

    }
}

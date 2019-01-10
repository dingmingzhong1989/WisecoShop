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
import com.wiseco.wisecoshop.bean.goods.CcRLBean;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import static com.wiseco.wisecoshop.utils.UrlUtil.BASE;

/**
 * Created by wiseco on 2018/12/6.
 */

public class MainCardLiseViewAdapter extends BaseAdapter {

    private final List<CcRLBean> mCcRL;
    private  Context mContext;

    public MainCardLiseViewAdapter(Context context, List<CcRLBean> ccRL) {
        mContext=context;

        mCcRL=ccRL;

    }


    @Override
    public int getCount() {
        return mCcRL.size();
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

        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewHolder viewholder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.card_listview_item, null);
            viewholder = new ViewHolder();
            viewholder.cardicon = convertView.findViewById(R.id.cardicon);
            viewholder.bankicon = convertView.findViewById(R.id.bankicon);
            viewholder.name = convertView.findViewById(R.id.name);
            viewholder.money = convertView.findViewById(R.id.money);
            viewholder.tag_one = convertView.findViewById(R.id.tag_one);
            viewholder.tag_two = convertView.findViewById(R.id.tag_two);
            convertView.setTag(viewholder);
            AutoUtils.auto(convertView);
        } else {
            viewholder = (ViewHolder) convertView.getTag();
                 }

        Glide.with(mContext).load(BASE+mCcRL.get(position).getSmallicon()).error(R.drawable.card_normal).into(viewholder.cardicon );
        Glide.with(mContext).load(BASE+mCcRL.get(position).getBankicon()).error(R.drawable.bank_logo).into(viewholder.bankicon );
        viewholder.name.setText(mCcRL.get(position).getName());
        viewholder.money.setText(mCcRL.get(position).getMaxlimit()+"");
        String keyword = mCcRL.get(position).getKeyword();
        String[] split = keyword.split(",");


        viewholder.tag_one.setText(split[0].split("\\|")[1]);
        viewholder.tag_two.setText(split[1].split("\\|")[1]);

        return convertView;

    }

    class ViewHolder {
        public ImageView cardicon;
        public ImageView bankicon;
        public TextView name;
        public TextView money;
        public TextView tag_one;
        public TextView tag_two;

    }
}

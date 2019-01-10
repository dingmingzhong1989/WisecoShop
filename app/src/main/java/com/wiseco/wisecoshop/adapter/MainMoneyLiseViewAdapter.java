package com.wiseco.wisecoshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wiseco.wisecoshop.R;
import com.wiseco.wisecoshop.bean.goods.ClRLBean;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wiseco on 2018/12/6.
 */

public class MainMoneyLiseViewAdapter extends BaseAdapter {

    private final List<ClRLBean> mClRL;
    private Context mContext;

    public MainMoneyLiseViewAdapter(Context context, List<ClRLBean> clRL) {
        mContext = context;
        mClRL = clRL;

    }


    @Override
    public int getCount() {
        return mClRL.size();
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
            convertView = inflater.inflate(R.layout.money_listview_item, null);
            viewholder = new ViewHolder();

            viewholder.name = convertView.findViewById(R.id.name);
            viewholder.text = convertView.findViewById(R.id.text);

            viewholder.minrate = convertView.findViewById(R.id.minrate);
            viewholder.tag_one = convertView.findViewById(R.id.tag_one);
            viewholder.tag_two = convertView.findViewById(R.id.tag_two);
            viewholder.tag_three = convertView.findViewById(R.id.tag_three);
            convertView.setTag(viewholder);
            AutoUtils.auto(convertView);
        } else {
            viewholder = (ViewHolder) convertView.getTag();
        }

        viewholder.name.setText(mClRL.get(position).getName());

        Pattern p = Pattern.compile("\\d.\\d+");

        Matcher m = p.matcher(mClRL.get(position).getMinrate());
        m.find();
       // viewholder.text.setText();
        viewholder.minrate.setText(m.group()+"%");
        if (mClRL.get(position).getMinrate().contains("日")){


            viewholder.text.setText("/天");

        }else{
            viewholder.text.setText("/期");
        }

        String keyword = mClRL.get(position).getKeyword();
       // viewholder.minrate.setText(setNumColor(mClRL.get(position).getMinrate()));
        String[] split = keyword.split(",");
        viewholder.tag_one.setText(split[0].split("\\|")[1]);
        viewholder.tag_two.setText(split[1].split("\\|")[1]);
        viewholder.tag_three.setText(split[2].split("\\|")[1]);

        return convertView;

    }

    class ViewHolder {

        public TextView name;
        public TextView text;
        public TextView minrate;
        public TextView tag_one;
        public TextView tag_two;
        public TextView tag_three;


    }


}

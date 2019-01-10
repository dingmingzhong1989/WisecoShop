package com.wiseco.wisecoshop.view.city;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wiseco.wisecoshop.R;

import java.util.List;

/**
 * Created by wiseco on 2018/12/19.
 */

public class HisCityAdapter extends ArrayAdapter<String> {
    /**
     * 需要渲染的item布局文件
     */
    private int resource;

    public HisCityAdapter(Context context, int textViewResourceId, List<String> objects) {
        super(context, textViewResourceId, objects);
        resource = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RelativeLayout layout = null;
        if (convertView == null) {
            layout = (RelativeLayout) LayoutInflater.from(getContext()).inflate(resource, null);
        } else {
            layout = (RelativeLayout) convertView;
        }
        TextView name = (TextView) layout.findViewById(R.id.tv_city);
        ImageView imageView = (ImageView) layout.findViewById(R.id.location_icon);
        name.setText(getItem(position));
        if (position == 0) {
            imageView.setVisibility(View.VISIBLE);

        } else {
            imageView.setVisibility(View.GONE);
        }
        return layout;
    }
}

package com.wiseco.wisecoshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.wiseco.wisecoshop.R;
import com.wiseco.wisecoshop.bean.discount.CategoryListBean;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import static com.wiseco.wisecoshop.utils.UrlUtil.BASE;

/**
 * Created by wiseco on 2018/12/21.
 */

public class GridviewChannelAdapter extends BaseAdapter {

    private final List<CategoryListBean> mList;
    //存放图片的id
    private int[] imageIds = new int[]{
            R.drawable.food_icon,
            R.drawable.movie_icon,
            R.drawable.hotel_icon,
            R.drawable.leisure_icon,
            R.drawable.fitness_icon,
            R.drawable.tickets_icon,
            R.drawable.shopping_icon,
            R.drawable.service_icon
    };
    private String citys[] = {"美食","电影/演出","酒店","休闲娱乐", "健身", "景点/门票", "购物", "生活服务"};
    private final Context mContext;

    public GridviewChannelAdapter(Context context, List<CategoryListBean> categoryList) {
        mContext = context;
        mList=categoryList;

    }

    @Override
    public int getCount() {
        return mList.size();
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
        final ViewHolder viewholder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.gridview_channel_item, null);
            viewholder = new ViewHolder();
            viewholder.imageView = convertView.findViewById(R.id.life_channel_image);
            viewholder.textTitle = convertView.findViewById(R.id.life_channel_text);

            convertView.setTag(viewholder);
            AutoUtils.auto(convertView);
        } else {
            viewholder = (ViewHolder) convertView.getTag();
        }

      //  viewholder.imageView.setImageDrawable(mContext.getResources().getDrawable(imageIds[position]));
        viewholder.textTitle.setText(mList.get(position).getName());
        Glide.with(mContext).load(BASE+mList.get(position).getIcon()).placeholder(R.drawable.food_icon).into(new SimpleTarget<GlideDrawable>() {
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                viewholder.imageView.setImageDrawable(resource);
               // mLoadingView.notifyDataChanged(LoadingView.State.done);
            }
        });
        return convertView;
    }

    class ViewHolder {
        public ImageView imageView;
        public TextView textTitle;


    }
}

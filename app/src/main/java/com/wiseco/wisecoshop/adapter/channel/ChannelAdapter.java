package com.wiseco.wisecoshop.adapter.channel;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wiseco.wisecoshop.R;
import com.wiseco.wisecoshop.adapter.CardListAdapter;
import com.wiseco.wisecoshop.bean.discount.ShopDtoBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by wiseco on 2018/12/27.
 */

public class ChannelAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final List<ShopDtoBean> mShopDto;
    private Context mContext;
    private CardListAdapter.OnItemClickListener mOnItemClickListener;
    private boolean moreTar;

    public ChannelAdapter(List<ShopDtoBean> shopDto) {
        mShopDto=shopDto;


    }
    public void setOnItemClickListener(CardListAdapter.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (mContext == null) {
            mContext = parent.getContext();
        }


        if (viewType == TYPE_FOOTER_VIEW) {
            /*这里返回的是FooterView*/
            View footerView = LayoutInflater.from(mContext).inflate(R.layout.adapter_foot_view_more, parent, false);


            return new FooterViewHolder(footerView);

        } else { /*这里返回的是普通的View*/


            view = LayoutInflater.from(mContext).inflate(R.layout.disconnts_listview_item, parent, false);


            return new BodyViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof FooterViewHolder) {
            if (getMoreTar()) {

                ((FooterViewHolder) holder).viewById.setText("上拉加载更多");


            } else {

                ((FooterViewHolder) holder).viewById.setText("已经全部加载完毕");
               /* if (mOnItemClickListener != null) {
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            mOnItemClickListener.onClick(position);
                        }
                    });

                    holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            mOnItemClickListener.onLongClick(position);
                            return false;
                        }
                    });
                } else {
                    return;
                }*/
            }


        } else if (holder instanceof BodyViewHolder) {



            Glide.with(mContext).load(mShopDto.get(position).getLogoSmall()).
                    placeholder(R.drawable.itemic)
                    .error(R.drawable.itemic)
                    .into( (  ((BodyViewHolder) holder).imageView));
            ((BodyViewHolder) holder).textTitle.setText(mShopDto.get(position).getShopName());

            ((BodyViewHolder) holder).miles.setText(mShopDto.get(position).getMiles()+"米");
            Map<String, String> actDescribe = mShopDto.get(position).getActDescribe();
            //Log.d("TAG=======",actDescribe.size()+"");
            List<String> listKey = new ArrayList<>();
            List<String> listValue = new ArrayList<>();
            for (Map.Entry<String, String> entry : actDescribe.entrySet()) {
                listKey.add(entry.getKey());
                listValue.add(entry.getValue());
            }

           // Log.d("TAG=======",listKey.size()+"");
            if (listKey.size()==1){
                ((BodyViewHolder) holder).tag_one_type.setText(listKey.get(0));
                ((BodyViewHolder) holder).textContent.setText(listValue.get(0));
                ((BodyViewHolder) holder).tag_two_type.setVisibility(View.INVISIBLE);
                ((BodyViewHolder) holder).tag_two_content.setVisibility(View.INVISIBLE);
            }else{
                ((BodyViewHolder) holder).tag_one_type.setText(listKey.get(0));
                ((BodyViewHolder) holder).textContent.setText(listValue.get(0));
                ((BodyViewHolder) holder).tag_two_type.setText(listKey.get(1));
                ((BodyViewHolder) holder).tag_two_content.setText(listValue.get(1));

            }

            if (mOnItemClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        mOnItemClickListener.onClick(position);
                    }
                });

                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        mOnItemClickListener.onLongClick(position);
                        return false;
                    }
                });
            } else {
                return;
            }

        }

    }
    public void setMoreTar(boolean moreTar) {
        this.moreTar = moreTar;
    }

    public boolean getMoreTar() {
        return moreTar;
    }

    public interface OnItemClickListener {
        void onClick(int position);

        void onLongClick(int position);
    }


    public static final int TYPE_FOOTER_VIEW = 1;
    @Override
    public int getItemViewType(int position) {

  /*当position是最后一个的时候，也就是比list的数量多一个的时候，则表示FooterView*/
        if (position + 1 == mShopDto.size() + 1) {

            return TYPE_FOOTER_VIEW;
        }
        return super.getItemViewType(position);
    }
    @Override
    public int getItemCount() {
        return mShopDto.size()+1;
    }

    public class BodyViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView textTitle;
        public TextView textContent;
        public TextView miles;
        public TextView tag_one_type;
        public TextView tag_two_type;
        public TextView tag_two_content;

        public BodyViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.cardicon);
            textTitle = itemView.findViewById(R.id.name);
            textContent = itemView.findViewById(R.id.tag_one_content);
            miles = itemView.findViewById(R.id.miles);
           tag_one_type = itemView.findViewById(R.id. tag_one_type);
            tag_two_type = itemView.findViewById(R.id.tag_two_type);
           tag_two_content = itemView.findViewById(R.id.tag_two_content);
        }
    }

    /**
     * 一个很简单的ViewHolder，小伙伴可以根据自己的需求自定义
     */
    public static class FooterViewHolder extends RecyclerView.ViewHolder {
        TextView viewById;

        public FooterViewHolder(View itemView) {
            super(itemView);

            viewById = itemView.findViewById(R.id.finish);
        }
    }
}

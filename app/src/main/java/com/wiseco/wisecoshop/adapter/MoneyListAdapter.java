package com.wiseco.wisecoshop.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wiseco.wisecoshop.R;
import com.wiseco.wisecoshop.bean.MoneyListItemBean;

import java.util.List;

import static com.wiseco.wisecoshop.MyApplication.sContext;
import static com.wiseco.wisecoshop.utils.UrlUtil.BASE;
import static com.wiseco.wisecoshop.utils.UtilsOther.addComma;
import static com.wiseco.wisecoshop.utils.UtilsOther.setKeyWord;


public class MoneyListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static MoneyListAdapter moneyListAdapter;
    private List<MoneyListItemBean.ProductlistBean> mMoneyList;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;

    static class BodyViewHolder extends RecyclerView.ViewHolder {

        TextView bankName;
        TextView maxLimit;
        TextView approvaltime;
        TextView minrate;
        TextView term;
        LinearLayout keyword;
        ImageView bankicon;

        public BodyViewHolder(View view) {
            super(view);

            bankName = (TextView) view.findViewById(R.id.money_backname);
            maxLimit = (TextView) view.findViewById(R.id.maxLimit);
            approvaltime = (TextView) view.findViewById(R.id.approvaltime);
            minrate = (TextView) view.findViewById(R.id.minrate);
            term = (TextView) view.findViewById(R.id.term);
            keyword = (LinearLayout) view.findViewById(R.id.keyword);
            bankicon = (ImageView) view.findViewById(R.id.bankicon);

        }
    }

    /**
     * 一个很简单的ViewHolder，小伙伴可以根据自己的需求自定义
     */
    public static class FooterViewHolder extends RecyclerView.ViewHolder {
        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }

    public MoneyListAdapter(List<MoneyListItemBean.ProductlistBean> moneyList) {
        this.mMoneyList = moneyList;


    }

    public static MoneyListAdapter getInstance(List<MoneyListItemBean.ProductlistBean> moneyList) {
        if (moneyListAdapter == null) {
            synchronized (CardFindAdapter.class) {
                if (moneyListAdapter == null) {
                    moneyListAdapter = new MoneyListAdapter(moneyList);
                }
            }
        }
        return moneyListAdapter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        if (viewType == TYPE_FOOTER_VIEW) { /*这里返回的是FooterView*/
            final View footerView = LayoutInflater.from(mContext).inflate(R.layout.adapter_foot_view, parent, false);
            return new FooterViewHolder(footerView);
        } else { /*这里返回的是普通的View*/
            View view = LayoutInflater.from(mContext).inflate(R.layout.moneyitem, parent, false);
            return new BodyViewHolder(view);
        }


    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        /*holder.bankName.setText(mMoneyList.get(position).getName());
        *//*Glide.with(sContext).load(mMoneyList.get(position).getBankicon()).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                BitmapDrawable drawable= new BitmapDrawable(sContext.getResources(), resource);
                drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());

                holder.bankName.setCompoundDrawables(drawable, null, null, null);
            }
        });*//*
        Glide.with(sContext).load(BASE + mMoneyList.get(position).getBankicon()).into(holder.bankicon);
        holder.approvaltime.setText(mMoneyList.get(position).getApprovaltime());
        //holder.keyword.setText(mMoneyList.get(position).getKeyword());
        holder.maxLimit.setText(addComma(mMoneyList.get(position).getMaxlimit() + ""));
        holder.minrate.setText(mMoneyList.get(position).getMinrate());
        holder.term.setText(mMoneyList.get(position).getTerm());
        setKeyWord(mContext, mMoneyList.get(position).getKeyword(), holder.keyword, "blue", 5);*/
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
        }

        if (holder instanceof FooterViewHolder) {

        } else {
            ((BodyViewHolder) holder).bankName.setText(mMoneyList.get(position).getName());
       /* Glide.with(sContext).load(mMoneyList.get(position).getBankicon()).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                BitmapDrawable drawable= new BitmapDrawable(sContext.getResources(), resource);
                drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());

                (BodyViewHolder)holder.bankName.setCompoundDrawables(drawable, null, null, null);
            }
        });*/
            Glide.with(sContext).load(BASE + mMoneyList.get(position).getBankicon()).into(((BodyViewHolder) holder).bankicon);
            ((BodyViewHolder) holder).approvaltime.setText(mMoneyList.get(position).getApprovaltime());
            //holder.keyword.setText(mMoneyList.get(position).getKeyword());
            ((BodyViewHolder) holder).maxLimit.setText(addComma(mMoneyList.get(position).getMaxlimit() + ""));
            ((BodyViewHolder) holder).minrate.setText(mMoneyList.get(position).getMinrate());
            ((BodyViewHolder) holder).term.setText(mMoneyList.get(position).getTerm());
            setKeyWord(mContext, mMoneyList.get(position).getKeyword(), ((BodyViewHolder) holder).keyword, "blue", 5);

        }
    }

    @Override
    public int getItemCount() {
        return mMoneyList.size() + 1;
    }

    public static final int TYPE_FOOTER_VIEW = 1;

    @Override
    public int getItemViewType(int position) {

  /*当position是最后一个的时候，也就是比list的数量多一个的时候，则表示FooterView*/
        if (position + 1 == mMoneyList.size() + 1) {
            return TYPE_FOOTER_VIEW;
        }
        return super.getItemViewType(position);
    }

    public interface OnItemClickListener {
        void onClick(int position);

        void onLongClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }


}

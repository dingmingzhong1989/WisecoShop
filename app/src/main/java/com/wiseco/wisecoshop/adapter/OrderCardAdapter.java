package com.wiseco.wisecoshop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wiseco.wisecoshop.R;
import com.wiseco.wisecoshop.bean.OrderCardBean;

import java.util.List;

import static com.wiseco.wisecoshop.MyApplication.sContext;
import static com.wiseco.wisecoshop.utils.UrlUtil.BASE;

/**
 * Created by wiseco on 2018/11/9.
 */

public class OrderCardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final List<OrderCardBean.ItemBean> mItemList;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;

    public OrderCardAdapter(List<OrderCardBean.ItemBean> orderCardBeanItem) {
        this.mItemList = orderCardBeanItem;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (mContext == null) {
            mContext = parent.getContext();
        }

        if (viewType == TYPE_FOOTER_VIEW) { /*这里返回的是FooterView*/

            final View footerView = LayoutInflater.from(mContext).inflate(R.layout.adapter_foot_view_order, parent, false);


            return new FooterViewHolder(footerView);
        } else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.ordercarditem, parent, false);
            return new BodyViewHolder(view);

        }

    }

    public void setOnItemClickListener(OrderCardAdapter.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
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


        } else{
            Glide.with(sContext).load(BASE+mItemList.get(position).getSmallIcon()).
                    placeholder(R.drawable.card_img)
                    .error(R.drawable.card_img)
                    .into( ((BodyViewHolder)holder).cardicon);
            Glide.with(sContext).load(BASE+mItemList.get(position).getBankIcon()).
                    placeholder(R.drawable.bank_logo)
                    .error(R.drawable.bank_logo)
                    .into( ((BodyViewHolder)holder).order_card_bankicon);

            ((BodyViewHolder)holder).order_card_backname.setText(mItemList.get(position).getProductName());
            ((BodyViewHolder)holder).order_card_orderid.setText(mItemList.get(position).getId()+"");
            ((BodyViewHolder)holder).order_card_time.setText(mItemList.get(position).getApplyDate());
            ((BodyViewHolder)holder).advantage.setText(mItemList.get(position).getAdvantage().replace("|", "\n"));

            if (mItemList.get(position).getState() == 0 || mItemList.get(position).getState() == 1) {
                ((BodyViewHolder)holder).order_card_state.setText(mItemList.get(position).getStateName());
                ((BodyViewHolder)holder).order_card_state.setTextColor(mContext.getResources().getColor(R.color.statetext));
            } else {
                ((BodyViewHolder)holder).order_card_state.setText(mItemList.get(position).getStateName());

                ((BodyViewHolder)holder).order_card_state.setTextColor(mContext.getResources().getColor(R.color.ind_red));
            }
        }



    }

    public interface OnItemClickListener {
        void onClick(int position);

        void onLongClick(int position);
    }

    @Override
    public int getItemCount() {
        return mItemList.size()+1;
    }
    public static final int TYPE_FOOTER_VIEW = 1;

    @Override
    public int getItemViewType(int position) {

  /*当position是最后一个的时候，也就是比list的数量多一个的时候，则表示FooterView*/
        if (position + 1 == mItemList.size() + 1) {
            return TYPE_FOOTER_VIEW;
        }
        return super.getItemViewType(position);
    }

    public class BodyViewHolder extends RecyclerView.ViewHolder {

        TextView order_card_orderid;

        TextView order_card_backname;
        TextView order_card_time;
        TextView advantage;
        TextView order_card_state;
        ImageView cardicon;
        ImageView order_card_bankicon;

        public BodyViewHolder(View itemView) {
            super(itemView);
            order_card_orderid = (TextView) itemView.findViewById(R.id.order_card_orderid);
            cardicon = (ImageView) itemView.findViewById(R.id.cardicon);
            order_card_bankicon = (ImageView) itemView.findViewById(R.id.order_card_bankicon);
            order_card_backname = (TextView) itemView.findViewById(R.id.order_card_backname);
            order_card_time = (TextView) itemView.findViewById(R.id.order_card_time);
            advantage = (TextView) itemView.findViewById(R.id.advantage);
            order_card_state = (TextView) itemView.findViewById(R.id.order_card_state);
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
}

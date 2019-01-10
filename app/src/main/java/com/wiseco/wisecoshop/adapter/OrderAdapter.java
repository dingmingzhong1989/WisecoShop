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
import com.wiseco.wisecoshop.bean.OrderMoneyBean;

import java.util.List;

import static com.wiseco.wisecoshop.MyApplication.sContext;
import static com.wiseco.wisecoshop.utils.UrlUtil.BASE;
import static com.wiseco.wisecoshop.utils.UtilsOther.addComma;


/**
 * Created by wiseco on 2018/10/18.
 */

public class OrderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<OrderMoneyBean.ItemBean> mItenList;
    private OrderAdapter.OnItemClickListener mOnItemClickListener;
    private Context mContext;

    public OrderAdapter(List<OrderMoneyBean.ItemBean> item) {
        this.mItenList = item;

    }

    public void setOnItemClickListener(OrderAdapter.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;

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
            View view = LayoutInflater.from(mContext).inflate(R.layout.ordermoneyitem, parent, false);
            return new BodyViewHolder(view);
        }
        }


    @SuppressWarnings("ResourceAsColor")
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

            Glide.with(sContext).load(BASE+mItenList.get(position).getBankIcon()).
                    placeholder(R.drawable.bank_logo)
                    .error(R.drawable.bank_logo)
                    .into( ((BodyViewHolder)holder).order_money_bankicon);
            ((BodyViewHolder)holder).order_money_backname.setText(mItenList.get(position).getProductName());

            ((BodyViewHolder)holder).order_money_time.setText(mItenList.get(position).getApplyDateStr());
            ((BodyViewHolder)holder).order_money_approvaltime.setText(mItenList.get(position).getTerm());
            ((BodyViewHolder)holder).order_money_orderid.setText(mItenList.get(position).getId() + "");
            ((BodyViewHolder)holder).order_money_minrate.setText(mItenList.get(position).getMinRate());
            ((BodyViewHolder)holder).order_money_maxLimit.setText(mItenList.get(position).getApprovedAmount()==0 ? "-" :addComma(mItenList.get(position).getApprovedAmount() + ""));
            if (mItenList.get(position).getState() == 0 || mItenList.get(position).getState() == 1) {
                ((BodyViewHolder)holder).order_money_state.setText(mItenList.get(position).getStateName());
                ((BodyViewHolder)holder).order_money_state.setTextColor(mContext.getResources().getColor(R.color.statetext));
            } else {
                ((BodyViewHolder)holder).order_money_state.setText(mItenList.get(position).getStateName());

                ((BodyViewHolder)holder).order_money_state.setTextColor(mContext.getResources().getColor(R.color.ind_red));
            }
        }



    }

    public interface OnItemClickListener {
        void onClick(int position);

        void onLongClick(int position);
    }

    @Override
    public int getItemCount() {
        return mItenList.size()+1;
    }


    public static final int TYPE_FOOTER_VIEW = 1;

    @Override
    public int getItemViewType(int position) {

  /*当position是最后一个的时候，也就是比list的数量多一个的时候，则表示FooterView*/
        if (position + 1 == mItenList.size() + 1) {
            return TYPE_FOOTER_VIEW;
        }
        return super.getItemViewType(position);
    }

    public class BodyViewHolder extends RecyclerView.ViewHolder {

        TextView order_money_orderid;
        TextView order_money_backname;
        TextView order_money_time;
        TextView order_money_maxLimit;
        TextView order_money_approvaltime;
        TextView order_money_minrate;
        TextView order_money_state;
        ImageView order_money_bankicon;


        public BodyViewHolder(View itemView) {
            super(itemView);

            order_money_orderid = (TextView) itemView.findViewById(R.id.order_money_orderid);
            order_money_bankicon = (ImageView)itemView.findViewById(R.id.order_money_bankicon);
            order_money_backname = (TextView) itemView.findViewById(R.id.order_money_backname);
            order_money_time = (TextView) itemView.findViewById(R.id.order_money_time);
            order_money_maxLimit = (TextView) itemView.findViewById(R.id.order_money_maxLimit);
            order_money_approvaltime = (TextView) itemView.findViewById(R.id.order_money_approvaltime);

            order_money_minrate = (TextView) itemView.findViewById(R.id.order_money_minrate);
            order_money_state = (TextView) itemView.findViewById(R.id.order_money_state);


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

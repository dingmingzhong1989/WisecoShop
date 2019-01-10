package com.wiseco.wisecoshop.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wiseco.wisecoshop.R;
import com.wiseco.wisecoshop.bean.CardFindBean;

import java.util.List;

import static com.wiseco.wisecoshop.MyApplication.sContext;
import static com.wiseco.wisecoshop.utils.UrlUtil.BASE;
import static com.wiseco.wisecoshop.utils.UtilsOther.addComma;
import static com.wiseco.wisecoshop.utils.UtilsOther.setKeyWord;


/**
 * Created by wiseco on 2018/10/23.
 */

public class CardFindAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final List<CardFindBean.ProductListBean> mMoneyList;
    private OnItemClickListener mOnItemClickListener;
    private Context mContext;
    private static volatile CardFindAdapter cardFindAdapter;
    public CardFindAdapter(List<CardFindBean.ProductListBean> productList) {
        this.mMoneyList = productList;
    }




    public static CardFindAdapter getInstance(List<CardFindBean.ProductListBean> productList) {
        if (cardFindAdapter == null) {
            synchronized (CardFindAdapter.class) {
                if (cardFindAdapter == null) {
                    cardFindAdapter = new CardFindAdapter(productList);
                }
            }
        }
        return cardFindAdapter;
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
            View view = LayoutInflater.from(mContext).inflate(R.layout.carditem, parent, false);
            return new BodyViewHolder(view);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof FooterViewHolder) {

        } else {
            ((BodyViewHolder)holder).advantage.setText(mMoneyList.get(position).getAdvantage().replace("|", "\n\r"));
            ((BodyViewHolder)holder).name.setText(mMoneyList.get(position).getName());
            ((BodyViewHolder)holder).money.setText(addComma(mMoneyList.get(position).getMaxlimit()+""));
            setKeyWord(mContext,mMoneyList.get(position).getKeyword(), ((BodyViewHolder)holder).keyword,"blue",4);
            Glide.with(sContext).load(BASE+mMoneyList.get(position).getSmallicon()).
                    placeholder(R.drawable.card_img)
                    .error(R.drawable.card_img)
                    .into( ((BodyViewHolder)holder).cardicon);
            if (position == 0) {
                ((BodyViewHolder)holder).bankicon.setImageDrawable(sContext.getDrawable(R.drawable.first));

            }else if(position == 1){
                ((BodyViewHolder)holder).bankicon.setImageDrawable(sContext.getDrawable(R.drawable.second));

            }else if(position == 2){
                ((BodyViewHolder)holder).bankicon.setImageDrawable(sContext.getDrawable(R.drawable.third));

            }else{
           /* Glide.with(sContext).load(mMoneyList.get(position).getBankicon()).
                    placeholder(R.drawable.card_img)
                    .error(R.drawable.card_img)
                    .into(holder.bankicon);*/

                ((BodyViewHolder)holder).bankicon.setVisibility(View.GONE);
            }

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
        }
    }

    @Override
    public int getItemCount() {
        return mMoneyList.size()+1;
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
    /**
     * 一个很简单的ViewHolder，小伙伴可以根据自己的需求自定义
     */
    public static class FooterViewHolder extends RecyclerView.ViewHolder {
        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }
    public class BodyViewHolder extends RecyclerView.ViewHolder {
        TextView money;
        ImageView bankicon;
        TextView name;
        TextView advantage;
        ImageView cardicon;
        LinearLayout keyword;

        public BodyViewHolder(View itemView) {
            super(itemView);
            bankicon = (ImageView) itemView.findViewById(R.id.bankicon);
            cardicon = (ImageView) itemView.findViewById(R.id.cardicon);
            name = (TextView) itemView.findViewById(R.id.name);
            advantage = (TextView) itemView.findViewById(R.id.advantage);
            money = (TextView) itemView.findViewById(R.id.money);
            keyword = (LinearLayout) itemView.findViewById(R.id.keyword);
        }
    }
    public interface OnItemClickListener {
        void onClick(int position);

        void onLongClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}

package com.wiseco.wisecoshop.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wiseco.wisecoshop.R;
import com.wiseco.wisecoshop.bean.CardListItemBean;

import java.util.ArrayList;
import java.util.List;

import static com.wiseco.wisecoshop.MyApplication.sContext;
import static com.wiseco.wisecoshop.utils.UrlUtil.BASE;
import static com.wiseco.wisecoshop.utils.UtilsOther.addComma;
import static com.wiseco.wisecoshop.utils.UtilsOther.setKeyWord;


/**
 * Created by wiseco on 2018/10/11.
 */

public class CardListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static volatile CardListAdapter cardListAdapter;
    private final List<CardListItemBean.ProductlistBean> mBankList;
    ;
    private Context mContext;
    private CardListAdapter.OnItemClickListener mOnItemClickListener;
    private boolean moreTar;



    public CardListAdapter(List<CardListItemBean.ProductlistBean> bankList) {
        this.mBankList = bankList;

    }

    public static CardListAdapter getInstance(List<CardListItemBean.ProductlistBean> bankList) {
        if (cardListAdapter == null) {
            synchronized (CardListAdapter.class) {
                if (cardListAdapter == null) {
                    cardListAdapter = new CardListAdapter(bankList);
                }
            }
        }
        return cardListAdapter;
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


            view = LayoutInflater.from(mContext).inflate(R.layout.carditem, parent, false);


            return new BodyViewHolder(view);
        }


    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        Log.d("TAG", "getMoreTar()getMoreTar()getMoreTar()" + getMoreTar());

        if (holder instanceof FooterViewHolder) {
            if (getMoreTar()) {

                ((FooterViewHolder) holder).viewById.setText("上拉加载更多");


            } else {

                ((FooterViewHolder) holder).viewById.setText("已经全部加载完毕");
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


        } else if (holder instanceof BodyViewHolder) {
            ((BodyViewHolder) holder).advantage.setText(mBankList.get(position).getAdvantage().replace("|", "\n"));
            ((BodyViewHolder) holder).name.setText(mBankList.get(position).getName());
            ((BodyViewHolder) holder).money.setText(addComma(mBankList.get(position).getMaxlimit() + ""));

            setKeyWord(mContext, mBankList.get(position).getKeyword(), ((BodyViewHolder) holder).keyword, "blue", 4);
            Glide.with(sContext).load(BASE + mBankList.get(position).getSmallicon()).
                    placeholder(R.drawable.card_img)
                    .error(R.drawable.card_img)
                    .into(((BodyViewHolder) holder).cardicon);
            if (position == 0) {
                ((BodyViewHolder) holder).bankicon.setImageDrawable(sContext.getDrawable(R.drawable.first));

            } else if (position == 1) {
                ((BodyViewHolder) holder).bankicon.setImageDrawable(sContext.getDrawable(R.drawable.second));

            } else if (position == 2) {
                ((BodyViewHolder) holder).bankicon.setImageDrawable(sContext.getDrawable(R.drawable.third));

            } else {
           /* Glide.with(sContext).load(mBankList.get(position).getBankicon()).
                    placeholder(R.drawable.card_img)
                    .error(R.drawable.card_img)
                    .into(holder.bankicon);*/

                ((BodyViewHolder) holder).bankicon.setVisibility(View.GONE);
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

    @Override
    public int getItemCount() {
        return mBankList.size() + 1;
    }


    public static final int TYPE_FOOTER_VIEW = 1;

    @Override
    public int getItemViewType(int position) {

  /*当position是最后一个的时候，也就是比list的数量多一个的时候，则表示FooterView*/


        if (position + 1 == mBankList.size() + 1) {

            // moreTarGet = getMoreTar();

            // ISMORE=true;
            return TYPE_FOOTER_VIEW;
        }
        return super.getItemViewType(position);
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

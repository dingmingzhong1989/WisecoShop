package com.wiseco.wisecoshop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wiseco.wisecoshop.R;
import com.wiseco.wisecoshop.bean.user.UserCardListBean;
import com.wiseco.wisecoshop.okhttp.CallBackUtil;
import com.wiseco.wisecoshop.okhttp.OkhttpUtil;

import java.util.List;
import java.util.Map;

import okhttp3.Call;

import static com.wiseco.wisecoshop.MyApplication.gson;
import static com.wiseco.wisecoshop.MyApplication.i;
import static com.wiseco.wisecoshop.MyApplication.sContext;
import static com.wiseco.wisecoshop.utils.HttpPostUtils.getParamsMap;
import static com.wiseco.wisecoshop.utils.UrlUtil.BASE;
import static com.wiseco.wisecoshop.utils.UrlUtil.DELETECARD;

/**
 * Created by wiseco on 2018/12/18.
 */

public class MyCardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Map<Integer, String> mMapCard;
    private final Map<Integer, String> mMapUrl;
    private List<UserCardListBean> mUserCardList;
    private Context mContext;
    private OrderCardAdapter.OnItemClickListener mOnItemClickListener;
    public static final int TYPE_FOOTER_VIEW = 1;

    public MyCardAdapter(List<UserCardListBean> userCardList, Map<Integer, String> mapCard, Map<Integer, String> mapUrl) {
        mUserCardList=userCardList;
        mMapCard=mapCard;
        mMapUrl=mapUrl;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }

        View view = LayoutInflater.from(mContext).inflate(R.layout.mycardtitem, parent, false);
        return new BodyViewHolder(view);


    }
    public void setOnItemClickListener(OrderCardAdapter.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;

    }
    public interface OnItemClickListener {
        void onClick(int position);

        void onLongClick(int position);
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


        ((BodyViewHolder)holder).card_bank_name.setText(mMapCard.get(mUserCardList.get(position).getBankId()));

        Glide.with(sContext).load(BASE+mMapUrl.get(mUserCardList.get(position).getBankId())).
                placeholder(R.drawable.itemic)
                .error(R.drawable.itemic)
                .into( ((BodyViewHolder)holder).bank_icon);

        ((BodyViewHolder)holder).card_num.setText("**** **** ****"+mUserCardList.get(position).getCardEndNum());
        Log.d("TAG","huankuanri"+mMapCard.get(mUserCardList.get(position).getRepayDay())+"");

        ((BodyViewHolder)holder).card_bank_time.setText("每月"+mUserCardList.get(position).getRepayDay()+"日还款");

    }

    @Override
    public int getItemCount() {
        return mUserCardList.size();
    }

    public class BodyViewHolder extends RecyclerView.ViewHolder {

        TextView card_bank_name;
        TextView card_num;
        TextView card_bank_time;
        TextView card_content;
        ImageView bank_icon;


        public BodyViewHolder(View itemView) {
            super(itemView);
            card_bank_name = (TextView) itemView.findViewById(R.id.card_bank_name);
            bank_icon = (ImageView) itemView.findViewById(R.id.bank_icon);
            card_num = (TextView) itemView.findViewById(R.id.card_num);
            card_bank_time = (TextView) itemView.findViewById(R.id.card_bank_time);
            card_content = (TextView) itemView.findViewById(R.id.card_content);

        }
    }

    public void delete(int position) {
        if(position < 0 || position > getItemCount()) {
            return;
        }

        i=4;
        Map<String, String> paramsMap = getParamsMap();
        paramsMap.put("id", mUserCardList.get(position).getId() + "");
        String s = gson.toJson(paramsMap);
        OkhttpUtil.okHttpPostJson(DELETECARD, s,new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {
            }

            @Override
            public void onResponse(String response) {
                Log.d("DELETECARD", response);
                try {

                } catch (Exception e) {


                }


            }
        });

        mUserCardList.remove(position);
        notifyItemRemoved(position);
    }


}

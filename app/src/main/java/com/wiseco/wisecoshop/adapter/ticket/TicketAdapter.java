package com.wiseco.wisecoshop.adapter.ticket;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.ClipboardManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.wiseco.wisecoshop.R;
import com.wiseco.wisecoshop.adapter.OrderCardAdapter;
import com.wiseco.wisecoshop.bean.ticket.WalletListBean;
import com.wiseco.wisecoshop.okhttp.CallBackUtil;
import com.wiseco.wisecoshop.okhttp.OkhttpUtil;
import com.wiseco.wisecoshop.utils.ToastUtils;

import java.util.List;
import java.util.Map;

import okhttp3.Call;

import static com.wiseco.wisecoshop.MyApplication.gson;
import static com.wiseco.wisecoshop.MyApplication.i;
import static com.wiseco.wisecoshop.utils.HttpPostUtils.getParamsMap;
import static com.wiseco.wisecoshop.utils.SystemUtil.getDateString;
import static com.wiseco.wisecoshop.utils.UrlUtil.SETWALLETUSED;

/**
 * Created by wiseco on 2018/12/18.
 */

public class TicketAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<WalletListBean> mWalletList;
    private Context mContext;
    private OrderCardAdapter.OnItemClickListener mOnItemClickListener;
    private boolean moreTar;

    public TicketAdapter(List<WalletListBean> walletList) {

        mWalletList = walletList;


    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (mContext == null) {
            mContext = parent.getContext();
        }

        if (viewType == TYPE_FOOTER_VIEW) { /*这里返回的是FooterView*/

            final View footerView = LayoutInflater.from(mContext).inflate(R.layout.adapter_foot_view_order, parent, false);


            return new TicketAdapter.FooterViewHolder(footerView);
        } else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.ticketitem, parent, false);

            return new TicketAdapter.BodyViewHolder(view);

        }

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
            ((BodyViewHolder) holder).ticket_name.setText(mWalletList.get(position).getName());
            ((BodyViewHolder) holder).ticket_out_time.setText(getDateString(mWalletList.get(position).getUseEndTime(), "yyyy-MM-dd HH:mm:ss"));
            ((BodyViewHolder) holder).ticket_num.setText(mWalletList.get(position).getCardNo());
            ((BodyViewHolder) holder).ticket_password.setText(mWalletList.get(position).getCardPwd());
            ((BodyViewHolder) holder).ticket_money.setText(mWalletList.get(position).getValue());

            ((BodyViewHolder) holder).ticket_used_now.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                    i=4;

                    Map<String, String> paramsMap = getParamsMap();

                    paramsMap.put("id", mWalletList.get(position).getId()+"");


                    OkhttpUtil.okHttpPostJson(SETWALLETUSED, gson.toJson(paramsMap), new CallBackUtil.CallBackString() {
                        @Override
                        public void onFailure(Call call, Exception e) {

                        }

                        @Override
                        public void onResponse(String response) {
                            Log.d("TAG","11111111111111111====="+response);
                            try {

                                Intent intent = new Intent();
                                intent.setAction("com.wiseco.wisecoshop.fragmentTicket");
                                mContext.sendBroadcast(intent);

                            } catch (Exception e) {
                            }


                        }
                    });
                }
            });



            ((BodyViewHolder) holder).ticket_copy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("TAG","12222222222222222=====");

                    // 从API11开始android推荐使用android.content.ClipboardManager
                    // 为了兼容低版本我们这里使用旧版的android.text.ClipboardManager，虽然提示deprecated，但不影响使用。
                    ClipboardManager cm = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
                    // 将文本内容放到系统剪贴板里。
                    cm.setText(mWalletList.get(position).getCardPwd());
                    ToastUtils.showToast("复制成功");

                }
            });

        }
    }

    public void setMoreTar(boolean moreTar) {
        this.moreTar = moreTar;
    }

    public boolean getMoreTar() {
        return moreTar;
    }

    @Override
    public int getItemCount() {
        return mWalletList.size()+1;
    }

    public void setOnItemClickListener(OrderCardAdapter.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;

    }

    public interface OnItemClickListener {
        void onClick(int position);

        void onLongClick(int position);
    }

    public static final int TYPE_FOOTER_VIEW = 1;

    @Override
    public int getItemViewType(int position) {

  /*当position是最后一个的时候，也就是比list的数量多一个的时候，则表示FooterView*/
        if (position + 1 == mWalletList.size() + 1) {
            return TYPE_FOOTER_VIEW;
        }
        return super.getItemViewType(position);
    }

    public class BodyViewHolder extends RecyclerView.ViewHolder {

        TextView ticket_name;
        TextView ticket_out_time;
        TextView ticket_num;
        TextView ticket_password;
        TextView ticket_money;
        Button ticket_used_now;
        TextView ticket_copy;

        public BodyViewHolder(View itemView) {
            super(itemView);
            ticket_name = (TextView) itemView.findViewById(R.id.ticket_name);
            ticket_out_time = (TextView) itemView.findViewById(R.id.ticket_out_time);
            ticket_num = (TextView) itemView.findViewById(R.id.ticket_num);
            ticket_password = (TextView) itemView.findViewById(R.id.ticket_password);
            ticket_money = (TextView) itemView.findViewById(R.id.ticket_money);

            ticket_copy = (TextView) itemView.findViewById(R.id.ticket_copy);
            ticket_used_now = (Button) itemView.findViewById(R.id.ticket_used_now);

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


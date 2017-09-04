package com.qingfeng.livesocial.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.bean.PayBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/26.
 */
public class PayAdapter extends RecyclerView.Adapter<PayAdapter.PayHolder> {

    private final Context context;
    private List<PayBean> mDatas;
    private OnItemClickListener onItemClickListener;
    private List<View> itemViews = new ArrayList<>();

    public PayAdapter(Context context, List<PayBean> datas) {
        this.context = context;
        this.mDatas = datas;
    }

    @Override
    public PayHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.gridview_pay_item_layout, parent, false);
        PayHolder PhotoHolder = new PayHolder(view);
        return PhotoHolder;
    }

    @Override
    public void onBindViewHolder(final PayHolder holder, int position) {
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(holder.getLayoutPosition(), itemViews);
                }
            });
        }
        holder.tvMengNum.setText(mDatas.get(position).getMengNum());
        holder.tvMengPrice.setText(mDatas.get(position).getMengPrice());
        itemViews.add(holder.itemView);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class PayHolder extends RecyclerView.ViewHolder {
        public TextView tvMengNum;
        public TextView tvMengPrice;

        public PayHolder(View itemView) {
            super(itemView);
            tvMengNum = (TextView) itemView.findViewById(R.id.tv_mengNum);
            tvMengPrice = (TextView) itemView.findViewById(R.id.tv_mengPrice);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position, List<View> views);
    }
}
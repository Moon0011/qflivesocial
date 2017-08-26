package com.qingfeng.livesocial.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.bean.RecommedRespBean;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2017/8/26.
 */
public class HRecyclerViewAdapter extends RecyclerView.Adapter<HRecyclerViewAdapter.TodayRecommendHolder> {

    private final Context context;
    private OnItemClickListener onItemClickListener;
    private List<RecommedRespBean.RecommendBean> mDatas;
    private ImageOptions mImageOptions;

    public HRecyclerViewAdapter(Context context, List<RecommedRespBean.RecommendBean> datas, ImageOptions imageOptions) {
        this.context = context;
        this.mDatas = datas;
        this.mImageOptions = imageOptions;
    }

    @Override
    public TodayRecommendHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.anchor_recommend_layout, parent, false);
        TodayRecommendHolder todayRecommendHolder = new TodayRecommendHolder(view);
        return todayRecommendHolder;
    }

    @Override
    public void onBindViewHolder(final TodayRecommendHolder holder, int position) {
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(holder.getLayoutPosition());
                }
            });
        }
        holder.name.setText(mDatas.get(position).getNickname());
        holder.age.setText(mDatas.get(position).getAge());
        holder.totaltalktime.setText(mDatas.get(position).getTotaltime() + " 分钟");
        x.image().bind(holder.imgHead,
                mDatas.get(position).getAnchorpic(),
                mImageOptions,
                null);

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class TodayRecommendHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView age;
        public TextView totaltalktime;
        public ImageView imgHead;

        public TodayRecommendHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tv_nickname);
            age = (TextView) itemView.findViewById(R.id.tv_age);
            totaltalktime = (TextView) itemView.findViewById(R.id.tv_total_talktime);
            imgHead = (ImageView) itemView.findViewById(R.id.img_head);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

}
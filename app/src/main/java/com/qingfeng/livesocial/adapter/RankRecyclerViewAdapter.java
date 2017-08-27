package com.qingfeng.livesocial.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.bean.AttentionRankListRespBean;
import com.qingfeng.livesocial.widget.RoundedImageView;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;


public class RankRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context mContext;

    private static final int TYPE_IMG_THREE = 1;      //2F(显示3张图片)
    private static final int TYPE_IMG_RECYCLER = 2;   //3F(显示无数张图片)

    private List<AttentionRankListRespBean.AttentionRanklistBean> mDatas;
    private ImageOptions mImageOptions;

    public RankRecyclerViewAdapter(Context context, List<AttentionRankListRespBean.AttentionRanklistBean> datas, ImageOptions imageOptions) {
        this.mContext = context;
        this.mDatas = datas;
        this.mImageOptions = imageOptions;
    }

    /**
     * 根据不同的position，设置不同的ViewType
     * position表示当前是第几个Item，通过position拿到当前的Item对象，然后判断这个item对象需要那种视图
     */
    @Override
    public int getItemViewType(int position) {
        if (position >= 3) {
            return TYPE_IMG_THREE;
        } else {
            return TYPE_IMG_RECYCLER;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_IMG_THREE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rank_other_layout, parent, false);
            NormalViewHolder normalViewHolder = new NormalViewHolder(view);
            return normalViewHolder;
        } else if (viewType == TYPE_IMG_RECYCLER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rank_top_three_layout, parent, false);
            Top3ViewHolder top3ViewHolder = new Top3ViewHolder(view);
            return top3ViewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof NormalViewHolder) {
            NormalViewHolder normalViewHolder = (NormalViewHolder) holder;
            normalViewHolder.name.setText(mDatas.get(position).getNickname());
            normalViewHolder.attentionNum.setText(mDatas.get(position).getAttentionnum());
            x.image().bind(normalViewHolder.imgHead,
                    mDatas.get(position).getAnchorpic(),
                    mImageOptions,
                    null);
        } else if (holder instanceof Top3ViewHolder) {
            Top3ViewHolder top3ViewHolder = (Top3ViewHolder) holder;
            top3ViewHolder.name.setText(mDatas.get(position).getNickname());
            top3ViewHolder.attentionNum.setText(mDatas.get(position).getAttentionnum());
            x.image().bind(top3ViewHolder.imgHead,
                    mDatas.get(position).getAnchorpic(),
                    mImageOptions,
                    null);
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class NormalViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView attentionNum;
        public RoundedImageView imgHead;

        public NormalViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tv_nickname);
            attentionNum = (TextView) itemView.findViewById(R.id.tv_attention_num);
            imgHead = (RoundedImageView) itemView.findViewById(R.id.imghead);
        }
    }

    public class Top3ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView attentionNum;
        public RoundedImageView imgHead;

        public Top3ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tv_nickname);
            attentionNum = (TextView) itemView.findViewById(R.id.tv_attention_num);
            imgHead = (RoundedImageView) itemView.findViewById(R.id.imghead);
        }
    }
}

package com.qingfeng.livesocial.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.bean.RankListRespBean;
import com.qingfeng.livesocial.widget.RoundedImageView;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;


public class RankRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context mContext;

    private static final int TYPE_RANK_OTHER = 3;//非top3
    private static final int TYPE_RANK_TOP1 = 0; //top3
    private static final int TYPE_RANK_TOP2 = 1; //top3
    private static final int TYPE_RANK_TOP3 = 2; //top3

    private List<RankListRespBean.RanklistBean> mDatas;
    private ImageOptions mImageOptions;
    private List<Integer> typeList = new ArrayList<>();

    public RankRecyclerViewAdapter(Context context, List<RankListRespBean.RanklistBean> datas, ImageOptions imageOptions) {
        this.mContext = context;
        this.mDatas = datas;
        this.mImageOptions = imageOptions;
        for (int i = 0; i < mDatas.size(); i++) {
            if (i == 0) {
                typeList.add(TYPE_RANK_TOP1);
            } else if (i == 1) {
                typeList.add(TYPE_RANK_TOP2);
            } else if (i == 2) {
                typeList.add(TYPE_RANK_TOP3);
            } else if (i >= 3) {
                typeList.add(TYPE_RANK_OTHER);
            }
        }
    }

    /**
     * 根据不同的position，设置不同的ViewType
     * position表示当前是第几个Item，通过position拿到当前的Item对象，然后判断这个item对象需要那种视图
     */
    @Override
    public int getItemViewType(int position) {
        return typeList.get(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_RANK_OTHER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rank_other_layout, parent, false);
            NormalViewHolder normalViewHolder = new NormalViewHolder(view);
            return normalViewHolder;
        } else if (viewType == TYPE_RANK_TOP1) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rank_top_one_layout, parent, false);
            Top1ViewHolder top1ViewHolder = new Top1ViewHolder(view);
            return top1ViewHolder;
        } else if (viewType == TYPE_RANK_TOP2) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rank_top_two_layout, parent, false);
            Top2ViewHolder top2ViewHolder = new Top2ViewHolder(view);
            return top2ViewHolder;
        } else if (viewType == TYPE_RANK_TOP3) {
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
            normalViewHolder.rankNum.setText(position + 1 + "");
            normalViewHolder.attentionNum.setText(mDatas.get(position).getAttentionnum());
            x.image().bind(normalViewHolder.imgHead,
                    mDatas.get(position).getAnchorpic(),
                    mImageOptions,
                    null);
        } else if (holder instanceof Top1ViewHolder) {
            Top1ViewHolder top1ViewHolder = (Top1ViewHolder) holder;
            top1ViewHolder.name.setText(mDatas.get(position).getNickname());
            top1ViewHolder.attentionNum.setText(mDatas.get(position).getAttentionnum());
            x.image().bind(top1ViewHolder.imgHead,
                    mDatas.get(position).getAnchorpic(),
                    mImageOptions,
                    null);
        } else if (holder instanceof Top2ViewHolder) {
            Top2ViewHolder top2ViewHolder = (Top2ViewHolder) holder;
            top2ViewHolder.name.setText(mDatas.get(position).getNickname());
            top2ViewHolder.attentionNum.setText(mDatas.get(position).getAttentionnum());
            x.image().bind(top2ViewHolder.imgHead,
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
        public TextView rankNum;
        public TextView attentionNum;
        public RoundedImageView imgHead;

        public NormalViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tv_nickname);
            rankNum = (TextView) itemView.findViewById(R.id.tv_rank_num);
            attentionNum = (TextView) itemView.findViewById(R.id.tv_attention_num);
            imgHead = (RoundedImageView) itemView.findViewById(R.id.imghead);
        }
    }

    public class Top1ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView attentionNum;
        public RoundedImageView imgHead;

        public Top1ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tv_nickname);
            attentionNum = (TextView) itemView.findViewById(R.id.tv_attention_num);
            imgHead = (RoundedImageView) itemView.findViewById(R.id.imghead);
        }
    }

    public class Top2ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView attentionNum;
        public RoundedImageView imgHead;

        public Top2ViewHolder(View itemView) {
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

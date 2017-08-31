package com.qingfeng.livesocial.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.bean.AttentionRespBean;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2017/8/31.
 */

public class AttentionAdapter extends RecyclerView.Adapter<AttentionAdapter.AttentionViewHolder> {
    private final Context mContext;
    private List<AttentionRespBean.AttentionBean> mDatas;
    private ImageOptions mImageOptions;

    public AttentionAdapter(Context context, List<AttentionRespBean.AttentionBean> datas, ImageOptions imageOptions) {
        this.mContext = context;
        this.mDatas = datas;
        this.mImageOptions = imageOptions;
    }

    @Override
    public AttentionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.attention_item_layout, parent, false);
        AttentionViewHolder normalViewHolder = new AttentionViewHolder(view);
        return normalViewHolder;
    }

    @Override
    public void onBindViewHolder(AttentionViewHolder holder, int position) {
        AttentionViewHolder attentionViewHolder = (AttentionViewHolder) holder;
        attentionViewHolder.name.setText(mDatas.get(position).getNickname());
        attentionViewHolder.age.setText(String.valueOf(mDatas.get(position).getAge()));
        attentionViewHolder.signature.setText(mDatas.get(position).getSignture());
        x.image().bind(attentionViewHolder.imgHead,
                mDatas.get(position).getAnchorpic(),
                mImageOptions,
                null);

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class AttentionViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView age;
        public TextView signature;
        public ImageView imgHead;

        public AttentionViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tv_nickname);
            age = (TextView) itemView.findViewById(R.id.tv_age);
            signature = (TextView) itemView.findViewById(R.id.tv_signature);
            imgHead = (ImageView) itemView.findViewById(R.id.img_head);
        }
    }

}

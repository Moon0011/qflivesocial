package com.qingfeng.livesocial.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.bean.CallEvaluationRespBean;
import com.qingfeng.livesocial.util.TimeUtils;
import com.qingfeng.livesocial.widget.RoundedImageView;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2017/8/31.
 */

public class CommendAdapter extends RecyclerView.Adapter<CommendAdapter.CommendViewHolder> {
    private final Context mContext;
    private List<CallEvaluationRespBean.ResultBean.CommentinfoBean> mDatas;
    private ImageOptions mImageOptions;

    public CommendAdapter(Context context, List<CallEvaluationRespBean.ResultBean.CommentinfoBean> datas, ImageOptions imageOptions) {
        this.mContext = context;
        this.mDatas = datas;
        this.mImageOptions = imageOptions;
    }

    @Override
    public CommendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.commend_item_layout, parent, false);
        CommendViewHolder normalViewHolder = new CommendViewHolder(view);
        return normalViewHolder;
    }

    @Override
    public void onBindViewHolder(CommendViewHolder holder, int position) {
        CommendViewHolder commendViewHolder = (CommendViewHolder) holder;
        commendViewHolder.name.setText(mDatas.get(position).getNickname());
        commendViewHolder.createTime.setText(TimeUtils.getStrTime(mDatas.get(position).getCreattime(), "yyyy/MM/dd hh:mm:ss"));
        commendViewHolder.content.setText(mDatas.get(position).getContent());
        x.image().bind(commendViewHolder.anchorPic,
                mDatas.get(position).getAnchorpic(),
                mImageOptions,
                null);

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class CommendViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView createTime;
        public TextView content;
        public RoundedImageView anchorPic;

        public CommendViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tv_nickname);
            createTime = (TextView) itemView.findViewById(R.id.tv_createtime);
            content = (TextView) itemView.findViewById(R.id.tv_content);
            anchorPic = (RoundedImageView) itemView.findViewById(R.id.img_anchorpic);
        }
    }

//    private String msToDate(long ms) {
//        Date date = new Date(ms);
//        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
//        return format.format(date);
//    }

//    public static String msToDate(long timeStamp) {
//        SimpleDateFormat sdr = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
//        return sdr.format(new Date(timeStamp));
//    }
}

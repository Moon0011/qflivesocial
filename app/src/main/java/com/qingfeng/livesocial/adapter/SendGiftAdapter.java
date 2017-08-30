package com.qingfeng.livesocial.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.bean.SendGiftListRespBean;

import org.xutils.image.ImageOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/26.
 */
public class SendGiftAdapter extends RecyclerView.Adapter<SendGiftAdapter.SendGiftHolder> {

    private final Context context;
    private OnItemClickListener onItemClickListener;
    private List<SendGiftListRespBean.ResultBean.GiftinfoBean> mDatas;
    private ImageOptions mImageOptions;

    public SendGiftAdapter(Context context, List<SendGiftListRespBean.ResultBean.GiftinfoBean> datas, ImageOptions imageOptions) {
        this.context = context;
        this.mDatas = datas;
        this.mImageOptions = imageOptions;
    }

    @Override
    public SendGiftHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.send_gift_item_layout, parent, false);
        SendGiftHolder sendGiftHolder = new SendGiftHolder(view);
        return sendGiftHolder;
    }

    @Override
    public void onBindViewHolder(final SendGiftHolder holder, int position) {
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(holder.getLayoutPosition());
                }
            });
        }
        int totalPager = mDatas.size() % 8 == 0 ? mDatas.size() / 8 : mDatas.size() / 8 + 1;
        if (mDatas.size() <= 0) {
            return;
        }
        for (int i = 0; i < totalPager; i++) {
            for (int j = 0; j < mDatas.size(); j += 8) {
                holder.rls.get(j).setVisibility(View.VISIBLE);
                holder.giftNames.get(j).setText(mDatas.get(j).getGiftname());
//                holder.giftname1.setText(mDatas.get(j).getGiftname());
//                holder.giftname2.setText(mDatas.get(j + 1).getGiftname());
//                holder.giftname3.setText(mDatas.get(j + 2).getGiftname());
//                holder.giftname4.setText(mDatas.get(j + 3).getGiftname());
//                holder.giftname5.setText(mDatas.get(j + 4).getGiftname());
//                holder.giftname6.setText(mDatas.get(j + 5).getGiftname());
//                holder.giftname7.setText(mDatas.get(j + 6).getGiftname());
//                holder.giftname8.setText(mDatas.get(j + 7).getGiftname());
            }
        }

        holder.giftname1.setText(mDatas.get(position).getGiftname());
//        holder.age.setText(mDatas.get(position).getAge());
//        holder.totaltalktime.setText(mDatas.get(position).getTotaltime() + " 分钟");
//        x.image().bind(holder.imgHead,
//                mDatas.get(position).getAnchorpic(),
//                mImageOptions,
//                null);

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class SendGiftHolder extends RecyclerView.ViewHolder {
        public TextView giftname1, giftname2, giftname3, giftname4, giftname5, giftname6, giftname7, giftname8;
        public TextView needCoin1, needCoin2, needCoin3, needCoin4, needCoin5, needCoin6, needCoin7, needCoin8;
        public RelativeLayout rl_gift1, rl_gift2, rl_gift3, rl_gift4, rl_gift5, rl_gift6, rl_gift7, rl_gift8;
        public ImageView imggift1, imggift2, imggift3, imggift4, imggift5, imggift6, imggift7, imggift8;
        public List<RelativeLayout> rls;
        public List<TextView> giftNames;
        public List<TextView> needCoins;

        public SendGiftHolder(View itemView) {
            super(itemView);
            giftname1 = (TextView) itemView.findViewById(R.id.tv_giftname1);
            giftname2 = (TextView) itemView.findViewById(R.id.tv_giftname2);
            giftname3 = (TextView) itemView.findViewById(R.id.tv_giftname3);
            giftname4 = (TextView) itemView.findViewById(R.id.tv_giftname4);
            giftname5 = (TextView) itemView.findViewById(R.id.tv_giftname5);
            giftname6 = (TextView) itemView.findViewById(R.id.tv_giftname6);
            giftname7 = (TextView) itemView.findViewById(R.id.tv_giftname7);
            giftname8 = (TextView) itemView.findViewById(R.id.tv_giftname8);

            needCoin1 = (TextView) itemView.findViewById(R.id.tv_needcoin1);
            needCoin2 = (TextView) itemView.findViewById(R.id.tv_needcoin2);
            needCoin3 = (TextView) itemView.findViewById(R.id.tv_needcoin3);
            needCoin4 = (TextView) itemView.findViewById(R.id.tv_needcoin4);
            needCoin5 = (TextView) itemView.findViewById(R.id.tv_needcoin5);
            needCoin6 = (TextView) itemView.findViewById(R.id.tv_needcoin6);
            needCoin7 = (TextView) itemView.findViewById(R.id.tv_needcoin7);
            needCoin8 = (TextView) itemView.findViewById(R.id.tv_needcoin8);

            rl_gift1 = (RelativeLayout) itemView.findViewById(R.id.rl_gift1);
            rl_gift2 = (RelativeLayout) itemView.findViewById(R.id.rl_gift2);
            rl_gift3 = (RelativeLayout) itemView.findViewById(R.id.rl_gift3);
            rl_gift4 = (RelativeLayout) itemView.findViewById(R.id.rl_gift4);
            rl_gift5 = (RelativeLayout) itemView.findViewById(R.id.rl_gift5);
            rl_gift6 = (RelativeLayout) itemView.findViewById(R.id.rl_gift6);
            rl_gift7 = (RelativeLayout) itemView.findViewById(R.id.rl_gift7);
            rl_gift8 = (RelativeLayout) itemView.findViewById(R.id.rl_gift8);

            rls = new ArrayList<>();
            rls.add(rl_gift1);
            rls.add(rl_gift2);
            rls.add(rl_gift3);
            rls.add(rl_gift4);
            rls.add(rl_gift5);
            rls.add(rl_gift6);
            rls.add(rl_gift7);
            rls.add(rl_gift8);

            giftNames = new ArrayList<>();
            giftNames.add(giftname1);
            giftNames.add(giftname2);
            giftNames.add(giftname3);
            giftNames.add(giftname4);
            giftNames.add(giftname5);
            giftNames.add(giftname6);
            giftNames.add(giftname7);
            giftNames.add(giftname8);

            imggift1 = (ImageView) itemView.findViewById(R.id.img_gift1);
            imggift2 = (ImageView) itemView.findViewById(R.id.img_gift2);
            imggift3 = (ImageView) itemView.findViewById(R.id.img_gift3);
            imggift4 = (ImageView) itemView.findViewById(R.id.img_gift4);
            imggift5 = (ImageView) itemView.findViewById(R.id.img_gift5);
            imggift6 = (ImageView) itemView.findViewById(R.id.img_gift6);
            imggift7 = (ImageView) itemView.findViewById(R.id.img_gift7);
            imggift8 = (ImageView) itemView.findViewById(R.id.img_gift8);

        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

}
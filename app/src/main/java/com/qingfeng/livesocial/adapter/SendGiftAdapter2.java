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
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/26.
 */
public class SendGiftAdapter2 extends RecyclerView.Adapter<SendGiftAdapter2.SendGiftHolder> {

    private final Context context;
    private OnItemClickListener onItemClickListener;
    private List<SendGiftListRespBean.ResultBean.GiftinfoBean> mDatas;
    private ImageOptions mImageOptions;
    private int totalPager;
    private int m = 0;

    public SendGiftAdapter2(Context context, List<SendGiftListRespBean.ResultBean.GiftinfoBean> datas, ImageOptions imageOptions) {
        this.context = context;
        this.mDatas = datas;
        this.mImageOptions = imageOptions;
        totalPager = mDatas.size() % 8 == 0 ? mDatas.size() / 8 : mDatas.size() / 8 + 1;
    }

    @Override
    public SendGiftHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.send_gift_item_layout2, parent, false);
        SendGiftHolder sendGiftHolder = new SendGiftHolder(view);
        return sendGiftHolder;
    }

    @Override
    public void onBindViewHolder(final SendGiftHolder holder, int position) {
        if (mDatas.size() <= 0) {
            return;
        }
        int totalSize = mDatas.size();
        int lastNum = mDatas.size() - (totalPager - 1) * 8;
        if (totalPager > 1) {
            if (position == totalPager - 1) {
                for (int i = 0; i < holder.rls.size(); i++) {
                    if (i >= lastNum) {
                        holder.rls.get(i).setVisibility(View.GONE);
                    }
                }

                if (m < totalSize) {
                    holder.giftname1.setText(mDatas.get(m).getGiftname());
                    holder.needCoin1.setText(String.valueOf(mDatas.get(m).getNeedcoin()));
                    x.image().bind(holder.imggift1,
                            mDatas.get(m).getGifticon(),
                            mImageOptions,
                            null);
                }
                if (m + 1 < totalSize) {
                    holder.giftname2.setText(mDatas.get(m + 1).getGiftname());
                    holder.needCoin2.setText(String.valueOf(mDatas.get(m + 1).getNeedcoin()));
                    x.image().bind(holder.imggift2,
                            mDatas.get(m + 1).getGifticon(),
                            mImageOptions,
                            null);
                }
                if (m + 2 < totalSize) {
                    holder.giftname3.setText(mDatas.get(m + 2).getGiftname());
                    holder.needCoin3.setText(String.valueOf(mDatas.get(m + 2).getNeedcoin()));
                    x.image().bind(holder.imggift3,
                            mDatas.get(m + 2).getGifticon(),
                            mImageOptions,
                            null);
                }
                if (m + 3 < totalSize) {
                    holder.giftname4.setText(mDatas.get(m + 3).getGiftname());
                    holder.needCoin4.setText(String.valueOf(mDatas.get(m + 3).getNeedcoin()));
                    x.image().bind(holder.imggift4,
                            mDatas.get(m + 3).getGifticon(),
                            mImageOptions,
                            null);
                }
                if (m + 4 < totalSize) {
                    holder.giftname5.setText(mDatas.get(m + 4).getGiftname());
                    holder.needCoin5.setText(String.valueOf(mDatas.get(m + 4).getNeedcoin()));
                    x.image().bind(holder.imggift5,
                            mDatas.get(m + 4).getGifticon(),
                            mImageOptions,
                            null);
                }
                if (m + 5 < totalSize) {
                    holder.giftname6.setText(mDatas.get(m + 5).getGiftname());
                    holder.needCoin6.setText(String.valueOf(mDatas.get(m + 5).getNeedcoin()));
                    x.image().bind(holder.imggift6,
                            mDatas.get(m + 5).getGifticon(),
                            mImageOptions,
                            null);
                }
                if (m + 6 < totalSize) {
                    holder.giftname7.setText(mDatas.get(m + 6).getGiftname());
                    holder.needCoin7.setText(String.valueOf(mDatas.get(m + 6).getNeedcoin()));
                    x.image().bind(holder.imggift7,
                            mDatas.get(m + 6).getGifticon(),
                            mImageOptions,
                            null);
                }
                if (m + 7 < totalSize) {
                    holder.giftname8.setText(mDatas.get(m + 7).getGiftname());
                    holder.needCoin8.setText(String.valueOf(mDatas.get(m + 7).getNeedcoin()));
                    x.image().bind(holder.imggift8,
                            mDatas.get(m + 7).getGifticon(),
                            mImageOptions,
                            null);
                }
            } else {
                holder.giftname1.setText(mDatas.get(m).getGiftname());
                holder.giftname2.setText(mDatas.get(m + 1).getGiftname());
                holder.giftname3.setText(mDatas.get(m + 2).getGiftname());
                holder.giftname4.setText(mDatas.get(m + 3).getGiftname());
                holder.giftname5.setText(mDatas.get(m + 4).getGiftname());
                holder.giftname6.setText(mDatas.get(m + 5).getGiftname());
                holder.giftname7.setText(mDatas.get(m + 6).getGiftname());
                holder.giftname8.setText(mDatas.get(m + 7).getGiftname());

                holder.needCoin1.setText(String.valueOf(mDatas.get(m).getNeedcoin()));
                holder.needCoin2.setText(String.valueOf(mDatas.get(m + 1).getNeedcoin()));
                holder.needCoin3.setText(String.valueOf(mDatas.get(m + 2).getNeedcoin()));
                holder.needCoin4.setText(String.valueOf(mDatas.get(m + 3).getNeedcoin()));
                holder.needCoin5.setText(String.valueOf(mDatas.get(m + 4).getNeedcoin()));
                holder.needCoin6.setText(String.valueOf(mDatas.get(m + 5).getNeedcoin()));
                holder.needCoin7.setText(String.valueOf(mDatas.get(m + 6).getNeedcoin()));
                holder.needCoin8.setText(String.valueOf(mDatas.get(m + 7).getNeedcoin()));

                x.image().bind(holder.imggift1,
                        mDatas.get(m).getGifticon(),
                        mImageOptions,
                        null);
                x.image().bind(holder.imggift2,
                        mDatas.get(m + 1).getGifticon(),
                        mImageOptions,
                        null);
                x.image().bind(holder.imggift3,
                        mDatas.get(m + 2).getGifticon(),
                        mImageOptions,
                        null);
                x.image().bind(holder.imggift4,
                        mDatas.get(m + 3).getGifticon(),
                        mImageOptions,
                        null);
                x.image().bind(holder.imggift5,
                        mDatas.get(m + 4).getGifticon(),
                        mImageOptions,
                        null);
                x.image().bind(holder.imggift6,
                        mDatas.get(m + 5).getGifticon(),
                        mImageOptions,
                        null);
                x.image().bind(holder.imggift7,
                        mDatas.get(m + 6).getGifticon(),
                        mImageOptions,
                        null);
                x.image().bind(holder.imggift8,
                        mDatas.get(m + 7).getGifticon(),
                        mImageOptions,
                        null);
                m += 8;
            }
        } else {
            holder.giftname1.setText(mDatas.get(m).getGiftname());
            holder.giftname2.setText(mDatas.get(m + 1).getGiftname());
            holder.giftname3.setText(mDatas.get(m + 2).getGiftname());
            holder.giftname4.setText(mDatas.get(m + 3).getGiftname());
            holder.giftname5.setText(mDatas.get(m + 4).getGiftname());
            holder.giftname6.setText(mDatas.get(m + 5).getGiftname());
            holder.giftname7.setText(mDatas.get(m + 6).getGiftname());
            holder.giftname8.setText(mDatas.get(m + 7).getGiftname());

            holder.needCoin1.setText(String.valueOf(mDatas.get(m).getNeedcoin()));
            holder.needCoin2.setText(String.valueOf(mDatas.get(m + 1).getNeedcoin()));
            holder.needCoin3.setText(String.valueOf(mDatas.get(m + 2).getNeedcoin()));
            holder.needCoin4.setText(String.valueOf(mDatas.get(m + 3).getNeedcoin()));
            holder.needCoin5.setText(String.valueOf(mDatas.get(m + 4).getNeedcoin()));
            holder.needCoin6.setText(String.valueOf(mDatas.get(m + 5).getNeedcoin()));
            holder.needCoin7.setText(String.valueOf(mDatas.get(m + 6).getNeedcoin()));
            holder.needCoin8.setText(String.valueOf(mDatas.get(m + 7).getNeedcoin()));

            x.image().bind(holder.imggift1,
                    mDatas.get(m).getGifticon(),
                    mImageOptions,
                    null);
            x.image().bind(holder.imggift2,
                    mDatas.get(m + 1).getGifticon(),
                    mImageOptions,
                    null);
            x.image().bind(holder.imggift3,
                    mDatas.get(m + 2).getGifticon(),
                    mImageOptions,
                    null);
            x.image().bind(holder.imggift4,
                    mDatas.get(m + 3).getGifticon(),
                    mImageOptions,
                    null);
            x.image().bind(holder.imggift5,
                    mDatas.get(m + 4).getGifticon(),
                    mImageOptions,
                    null);
            x.image().bind(holder.imggift6,
                    mDatas.get(m + 5).getGifticon(),
                    mImageOptions,
                    null);
            x.image().bind(holder.imggift7,
                    mDatas.get(m + 6).getGifticon(),
                    mImageOptions,
                    null);
            x.image().bind(holder.imggift8,
                    mDatas.get(m + 7).getGifticon(),
                    mImageOptions,
                    null);
            m += 8;
        }

    }

    @Override
    public int getItemCount() {
        return totalPager;
    }

    public class SendGiftHolder extends RecyclerView.ViewHolder {
        public TextView giftname1, giftname2, giftname3, giftname4, giftname5, giftname6, giftname7, giftname8;
        public TextView needCoin1, needCoin2, needCoin3, needCoin4, needCoin5, needCoin6, needCoin7, needCoin8;
        public RelativeLayout rl_gift1, rl_gift2, rl_gift3, rl_gift4, rl_gift5, rl_gift6, rl_gift7, rl_gift8;
        public ImageView imggift1, imggift2, imggift3, imggift4, imggift5, imggift6, imggift7, imggift8;
        public List<RelativeLayout> rls;
        public boolean isSelected1, isSelected2, isSelected3, isSelected4, isSelected5, isSelected6, isSelected7, isSelected8;

        private void onClickListener(View view) {
            if (rl_gift1.equals(view)) {
                if (!isSelected1) {
                    itemView.findViewById(R.id.rl_selected1).setVisibility(View.VISIBLE);
                    itemView.findViewById(R.id.rl_gift1).setBackgroundResource(R.mipmap.gift_selected_bg);
                } else {
                    itemView.findViewById(R.id.rl_selected1).setVisibility(View.GONE);
                    itemView.findViewById(R.id.rl_gift1).setBackgroundResource(0);
                }
                isSelected1 = !isSelected1;
            }
            if (rl_gift2.equals(view)) {
                if (!isSelected2) {
                    itemView.findViewById(R.id.rl_selected2).setVisibility(View.VISIBLE);
                    itemView.findViewById(R.id.rl_gift2).setBackgroundResource(R.mipmap.gift_selected_bg);
                } else {
                    itemView.findViewById(R.id.rl_selected2).setVisibility(View.GONE);
                    itemView.findViewById(R.id.rl_gift2).setBackgroundResource(0);
                }
                isSelected2 = !isSelected2;
            }
            if (rl_gift3.equals(view)) {
                if (!isSelected3) {
                    itemView.findViewById(R.id.rl_selected3).setVisibility(View.VISIBLE);
                    itemView.findViewById(R.id.rl_gift3).setBackgroundResource(R.mipmap.gift_selected_bg);
                } else {
                    itemView.findViewById(R.id.rl_selected3).setVisibility(View.GONE);
                    itemView.findViewById(R.id.rl_gift3).setBackgroundResource(0);
                }
                isSelected3 = !isSelected3;
            }
            if (rl_gift4.equals(view)) {
                if (!isSelected4) {
                    itemView.findViewById(R.id.rl_selected4).setVisibility(View.VISIBLE);
                    itemView.findViewById(R.id.rl_gift4).setBackgroundResource(R.mipmap.gift_selected_bg);
                } else {
                    itemView.findViewById(R.id.rl_selected4).setVisibility(View.GONE);
                    itemView.findViewById(R.id.rl_gift4).setBackgroundResource(0);
                }
                isSelected4 = !isSelected4;
            }
            if (rl_gift5.equals(view)) {
                if (!isSelected5) {
                    itemView.findViewById(R.id.rl_selected5).setVisibility(View.VISIBLE);
                    itemView.findViewById(R.id.rl_gift5).setBackgroundResource(R.mipmap.gift_selected_bg);
                } else {
                    itemView.findViewById(R.id.rl_selected5).setVisibility(View.GONE);
                    itemView.findViewById(R.id.rl_gift5).setBackgroundResource(0);
                }
                isSelected5 = !isSelected5;
            }
            if (rl_gift6.equals(view)) {
                if (!isSelected6) {
                    itemView.findViewById(R.id.rl_selected6).setVisibility(View.VISIBLE);
                    itemView.findViewById(R.id.rl_gift6).setBackgroundResource(R.mipmap.gift_selected_bg);
                } else {
                    itemView.findViewById(R.id.rl_selected6).setVisibility(View.GONE);
                    itemView.findViewById(R.id.rl_gift6).setBackgroundResource(0);
                }
                isSelected6 = !isSelected6;
            }
            if (rl_gift7.equals(view)) {
                if (!isSelected7) {
                    itemView.findViewById(R.id.rl_selected7).setVisibility(View.VISIBLE);
                    itemView.findViewById(R.id.rl_gift7).setBackgroundResource(R.mipmap.gift_selected_bg);
                } else {
                    itemView.findViewById(R.id.rl_selected7).setVisibility(View.GONE);
                    itemView.findViewById(R.id.rl_gift7).setBackgroundResource(0);
                }
                isSelected7 = !isSelected7;
            }
            if (rl_gift8.equals(view)) {
                if (!isSelected8) {
                    itemView.findViewById(R.id.rl_selected8).setVisibility(View.VISIBLE);
                    itemView.findViewById(R.id.rl_gift8).setBackgroundResource(R.mipmap.gift_selected_bg);
                } else {
                    itemView.findViewById(R.id.rl_selected8).setVisibility(View.GONE);
                    itemView.findViewById(R.id.rl_gift8).setBackgroundResource(0);
                }
                isSelected8 = !isSelected8;
            }
        }


        public SendGiftHolder(final View itemView) {
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

            imggift1 = (ImageView) itemView.findViewById(R.id.img_gift1);
            imggift2 = (ImageView) itemView.findViewById(R.id.img_gift2);
            imggift3 = (ImageView) itemView.findViewById(R.id.img_gift3);
            imggift4 = (ImageView) itemView.findViewById(R.id.img_gift4);
            imggift5 = (ImageView) itemView.findViewById(R.id.img_gift5);
            imggift6 = (ImageView) itemView.findViewById(R.id.img_gift6);
            imggift7 = (ImageView) itemView.findViewById(R.id.img_gift7);
            imggift8 = (ImageView) itemView.findViewById(R.id.img_gift8);


            rl_gift1 = (RelativeLayout) itemView.findViewById(R.id.rl_gift1);
            rl_gift2 = (RelativeLayout) itemView.findViewById(R.id.rl_gift2);
            rl_gift3 = (RelativeLayout) itemView.findViewById(R.id.rl_gift3);
            rl_gift4 = (RelativeLayout) itemView.findViewById(R.id.rl_gift4);
            rl_gift5 = (RelativeLayout) itemView.findViewById(R.id.rl_gift5);
            rl_gift6 = (RelativeLayout) itemView.findViewById(R.id.rl_gift6);
            rl_gift7 = (RelativeLayout) itemView.findViewById(R.id.rl_gift7);
            rl_gift8 = (RelativeLayout) itemView.findViewById(R.id.rl_gift8);

            rl_gift1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener(rl_gift1);
                }
            });
            rl_gift2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener(rl_gift2);
                }
            });
            rl_gift3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener(rl_gift3);
                }
            });
            rl_gift4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener(rl_gift4);
                }
            });
            rl_gift5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener(rl_gift5);
                }
            });
            rl_gift6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener(rl_gift6);
                }
            });
            rl_gift7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener(rl_gift7);
                }
            });
            rl_gift8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener(rl_gift8);
                }
            });

            rls = new ArrayList<>();
            rls.add(rl_gift1);
            rls.add(rl_gift2);
            rls.add(rl_gift3);
            rls.add(rl_gift4);
            rls.add(rl_gift5);
            rls.add(rl_gift6);
            rls.add(rl_gift7);
            rls.add(rl_gift8);

        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

}
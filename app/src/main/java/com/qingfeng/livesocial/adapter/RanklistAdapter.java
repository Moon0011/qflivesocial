package com.qingfeng.livesocial.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.bean.AttentionRankListRespBean.AttentionRanklistBean;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2017/8/26.
 */

public class RanklistAdapter extends BaseAdapter {
    private Context mContext;
    private List<AttentionRanklistBean> mDatas;
    private ImageOptions mImageOptions;

    public RanklistAdapter(Context context) {
        mContext = context;
    }

    public RanklistAdapter(Context context, List<AttentionRanklistBean> datas, ImageOptions imageOptions) {
        this.mContext = context;
        this.mDatas = datas;
        this.mImageOptions = imageOptions;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderTop3 holderTop3 = null;
        ViewHolderOther holderOther = null;
        int type = getItemViewType(position);
        if (convertView == null) {
//            if (type == 1) {
                holderOther = new ViewHolderOther();
                convertView = LayoutInflater.from(mContext).inflate(R.layout.rank_other_layout, null);
                holderOther.name = (TextView) convertView.findViewById(R.id.tv_nickname);
                holderOther.attentionNum = (TextView) convertView.findViewById(R.id.tv_attention_num);
                holderOther.imgHead = (ImageView) convertView.findViewById(R.id.imghead);
                convertView.setTag(holderOther);
//            } else if (type == 0) {
//                holderTop3 = new ViewHolderTop3();
//                convertView = LayoutInflater.from(mContext).inflate(R.layout.rank_top_three_layout, null);
//                holderTop3.name = (TextView) convertView.findViewById(R.id.tv_nickname);
//                holderTop3.attentionNum = (TextView) convertView.findViewById(R.id.tv_attention_num);
//                holderTop3.imgHead = (ImageView) convertView.findViewById(R.id.imghead);
//                convertView.setTag(holderTop3);
//            }
        } else {
//            if (type == 1) {
                holderOther = (ViewHolderOther) convertView.getTag();
                holderOther.name.setText(mDatas.get(position).getNickname());
                holderOther.attentionNum.setText(mDatas.get(position).getAttentionnum());
                x.image().bind(holderOther.imgHead,
                        mDatas.get(position).getAnchorpic(),
                        mImageOptions,
                        null);
//            } else if (type == 0) {
//                holderTop3 = (ViewHolderTop3) convertView.getTag();
//                holderTop3.name.setText(mDatas.get(position).getNickname());
//                holderTop3.attentionNum.setText(mDatas.get(position).getAttentionnum());
//                x.image().bind(holderTop3.imgHead,
//                        mDatas.get(position).getAnchorpic(),
//                        mImageOptions,
//                        null);
//            }
        }
        return convertView;
    }

//    /**
//     * 根据数据源的position返回需要显示的的layout的type
//     * type的值必须从0开始
//     */
//    @Override
//    public int getItemViewType(int position) {
//        Log.e("TYPE:", "" + type);
//        int type = -1;
//        if (position >= 3) {
//            type = 1;//other
//        } else {
//            type = 0;//top3
//        }
//        return type;
//    }

//    /**
//     * 返回layout种类数
//     */
//    @Override
//    public int getViewTypeCount() {
//        return 2;
//    }


    class ViewHolderTop3 {
        public TextView name;
        public TextView attentionNum;
        public ImageView imgHead;
    }

    class ViewHolderOther {
        public TextView name;
        public TextView attentionNum;
        public ImageView imgHead;
    }

}

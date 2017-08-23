package com.qingfeng.livesocial.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.bean.RecommedRespBean.RecommendBean;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2017/8/22.
 */
public class RecomAdapter extends BaseAdapter {
    private Context mContext;
    private List<RecommendBean> mDatas;
    private ImageOptions mImageOptions;

    public RecomAdapter(Context context, List<RecommendBean> datas, ImageOptions imageOptions) {
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
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.recommend_item_layout, null);
            holder.name = (TextView) convertView.findViewById(R.id.tv_name);
            holder.age = (TextView) convertView.findViewById(R.id.tv_age);
            holder.imgHead = (ImageView) convertView.findViewById(R.id.img_head);
            holder.imgSex = (ImageView) convertView.findViewById(R.id.img_sex);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.name.setText(mDatas.get(position).getNickname());
        holder.age.setText(String.valueOf(mDatas.get(position).getAge()));
        x.image().bind(holder.imgHead,
                mDatas.get(position).getAnchorpic(),
                mImageOptions,
                null);
        String sex = mDatas.get(position).getSex();
        if ("女".equals(sex)) {
            holder.imgSex.setImageResource(R.mipmap.sex_count_f);
        }
        return convertView;
    }

    class ViewHolder {
        public TextView name;
        public TextView age;
        public ImageView imgHead;
        public ImageView imgSex;
    }
}

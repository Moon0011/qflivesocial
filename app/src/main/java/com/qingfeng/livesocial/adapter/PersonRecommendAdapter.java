package com.qingfeng.livesocial.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.bean.TodayStarBean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/22.
 */

public class PersonRecommendAdapter extends BaseAdapter {
    private Context mContext;
    private List<TodayStarBean> mDatas;

    public PersonRecommendAdapter(Context context) {
        mContext = context;
    }

    public PersonRecommendAdapter(Context context, List<TodayStarBean> datas) {
        this.mContext = context;
        this.mDatas = datas;
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewHolder holder = null;
//        if (convertView == null) {
//            holder = new ViewHolder();
//            convertView = LayoutInflater.from(mContext).inflate(R.layout.person_show_layout, null);
//            holder.name = (TextView) convertView.findViewById(R.id.tv_name);
//            holder.age = (TextView) convertView.findViewById(R.id.tv_age);
//            holder.imgHead = (ImageView) convertView.findViewById(R.id.img_head);
//            convertView.setTag(holder);
//        } else {
//            holder = (ViewHolder) convertView.getTag();
//        }
//        holder.name.setText(mDatas.get(position).getName());
//        holder.age.setText(String.valueOf(mDatas.get(position).getAge()));
        return LayoutInflater.from(mContext).inflate(R.layout.person_recommend_layout, null);
    }

    class ViewHolder {
        public TextView name;
        public TextView age;
        public ImageView imgHead;
    }
}

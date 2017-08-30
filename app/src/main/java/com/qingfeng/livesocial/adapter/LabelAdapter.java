package com.qingfeng.livesocial.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.bean.LabelRespBean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/30.
 */

public class LabelAdapter extends BaseAdapter {
    private Context mContext;
    private List<LabelRespBean.LabelBean> mDatas;

    public LabelAdapter(Context context) {
        mContext = context;
    }

    public LabelAdapter(Context context, List<LabelRespBean.LabelBean> datas) {
        this.mContext = context;
        this.mDatas = datas;
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.lable_item_layout, null);
            holder.tvLable = (TextView) convertView.findViewById(R.id.tv_label);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvLable.setText(mDatas.get(position).getContent());
        return convertView;
    }

    class ViewHolder {
        public TextView tvLable;

    }
}

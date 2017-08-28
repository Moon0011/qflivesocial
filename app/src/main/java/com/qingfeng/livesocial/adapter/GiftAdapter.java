package com.qingfeng.livesocial.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.qingfeng.livesocial.R;

/**
 * Created by Administrator on 2017/8/28.
 */

public class GiftAdapter extends BaseAdapter {
    private Context mContext;

    public GiftAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return 7;
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
        return LayoutInflater.from(mContext).inflate(R.layout.gv_gift_item_layout, null);
    }
}

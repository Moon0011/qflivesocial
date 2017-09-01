package com.qingfeng.livesocial.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.bean.PersonalCenterRespBean;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2017/8/26.
 */
public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoHolder> {

    private final Context context;
    private List<PersonalCenterRespBean.PersonalCenterBean.PhotoBean> mDatas;
    private ImageOptions mImageOptions;
    private OnItemClickListener onItemClickListener;

    public PhotoAdapter(Context context, List<PersonalCenterRespBean.PersonalCenterBean.PhotoBean> datas) {
        this.context = context;
        this.mDatas = datas;
        mImageOptions = new ImageOptions.Builder()
                .setLoadingDrawableId(R.mipmap.ic_launcher)
                .setFailureDrawableId(R.mipmap.ic_launcher)
                .build();
    }

    @Override
    public PhotoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.photo_item_layout, parent, false);
        PhotoHolder PhotoHolder = new PhotoHolder(view);
        return PhotoHolder;
    }

    @Override
    public void onBindViewHolder(final PhotoHolder holder, int position) {
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(holder.getLayoutPosition());
                }
            });
        }
        x.image().bind(holder.imgPhoto,
                mDatas.get(position).getPicurl(),
                mImageOptions,
                null);

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class PhotoHolder extends RecyclerView.ViewHolder {
        public ImageView imgPhoto;

        public PhotoHolder(View itemView) {
            super(itemView);
            imgPhoto = (ImageView) itemView.findViewById(R.id.img_photo);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
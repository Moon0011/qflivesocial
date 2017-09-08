package com.qingfeng.livesocial.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.adapter.PhotoAdapter;
import com.qingfeng.livesocial.bean.PersonalCenterRespBean;
import com.qingfeng.livesocial.bean.RespBean;
import com.qingfeng.livesocial.common.QFApplication;
import com.qingfeng.livesocial.common.UIHelper;
import com.qingfeng.livesocial.common.Urls;
import com.qingfeng.livesocial.ui.AnchorAnthenActivity;
import com.qingfeng.livesocial.ui.AnchorInfoActivity;
import com.qingfeng.livesocial.ui.AttentionActivity;
import com.qingfeng.livesocial.ui.BigPicActivity;
import com.qingfeng.livesocial.ui.CallSettingActivity;
import com.qingfeng.livesocial.ui.GiftActivity;
import com.qingfeng.livesocial.ui.MyWalletActivity;
import com.qingfeng.livesocial.ui.base.BaseFragment;
import com.qingfeng.livesocial.util.GlideImageLoader;
import com.qingfeng.livesocial.util.StringUtils;
import com.qingfeng.livesocial.widget.RoundedImageView;

import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.ByteArrayOutputStream;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

import static com.qingfeng.livesocial.common.Constants.PARAM_A;
import static com.qingfeng.livesocial.common.Constants.PARAM_HEADPIC;
import static com.qingfeng.livesocial.common.Constants.PARAM_HEADPIC_INFO_EDIT_TYPE_VALUE;
import static com.qingfeng.livesocial.common.Constants.PARAM_PHOTO;
import static com.qingfeng.livesocial.common.Constants.PARAM_PHOTO_ID;
import static com.qingfeng.livesocial.common.Constants.PARAM_PHOTO_INFO_EDIT_TYPE_VALUE;
import static com.qingfeng.livesocial.common.Constants.PARAM_TYPE;
import static com.qingfeng.livesocial.common.Constants.PARAM_UID;
import static com.qingfeng.livesocial.common.Constants.PARAM_Y;

/**
 * Created by Administrator on 2017/8/29.
 * 个人中心
 */

public class PersonalCenterFragment extends BaseFragment implements PhotoAdapter.OnItemClickListener {
    @Bind(R.id.img_head)
    RoundedImageView imgHead;
    @Bind(R.id.tv_nickname)
    TextView tvNickname;
    @Bind(R.id.tv_curr_roomnum)
    TextView tvCurrRoomnum;
    @Bind(R.id.img_sex)
    ImageView imgSex;
    @Bind(R.id.tv_age)
    TextView tvAge;
    @Bind(R.id.tv_signature)
    TextView tvSignature;
    @Bind(R.id.img_room_bg)
    ImageView imgRoomBg;
    @Bind(R.id.recyclelistview)
    RecyclerView recyclelistview;
    private String filePath = "";
    private static final int MSG_REFRESH = 0x01;
    private List<PersonalCenterRespBean.PersonalCenterBean.PhotoBean> photoDatas;
    private PhotoAdapter photoAdapter;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_REFRESH:
                    getPersonalCenter();
                    break;
            }
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.personal_center_fragment_layout;
    }

    @Override
    protected void initWidget(View root) {

    }

    @Override
    protected void initData() {
    }

    @Override
    public void onResume() {
        super.onResume();
        getPersonalCenter();
    }

    @OnClick({R.id.rl_wallet, R.id.rl_gift, R.id.rl_attention, R.id.rl_anchor_anthen, R.id.rl_setting, R.id.ll_data, R.id.img_addphoto})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_wallet://钱包
                gotoActivity(getActivity(), MyWalletActivity.class);
                break;
            case R.id.rl_gift://礼物
                gotoActivity(getActivity(), GiftActivity.class);
                break;
            case R.id.rl_attention://关注
                gotoActivity(getActivity(), AttentionActivity.class);
                break;
            case R.id.rl_anchor_anthen://主播认证
                gotoActivity(getActivity(), AnchorAnthenActivity.class);
                break;
            case R.id.rl_setting://通话设置
                gotoActivity(getActivity(), CallSettingActivity.class);
                break;
            case R.id.ll_data://资料编辑
                gotoActivity(getActivity(), AnchorInfoActivity.class);
                break;
            case R.id.img_addphoto://添加相册
                gotoAlbum();
                break;
        }
    }

    public void gotoAlbum() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());
        imagePicker.setShowCamera(true);
        imagePicker.setSelectLimit(9);
        imagePicker.setCrop(false);
        Intent intent = new Intent(getActivity(), ImageGridActivity.class);
        startActivityForResult(intent, 100);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == 100) {
                List<ImageItem> images = (List<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                filePath = images.get(0).path;
                if (!StringUtils.isEmpty(filePath)) {
                    updatePhoto();
                }
            } else {
                showToast("没有数据");
            }
        }
    }

    /**
     * 添加相册
     */
    private void updatePhoto() {
        RequestParams params = new RequestParams(Urls.PERSONAL_CENTER_EDIT_INFO);
        params.addParameter(PARAM_UID, QFApplication.getInstance().getLoginUser().getUid());
        params.addParameter(PARAM_TYPE, PARAM_PHOTO_INFO_EDIT_TYPE_VALUE);
        params.addParameter(PARAM_PHOTO, encode(filePath));
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtil.e("updatePhoto == " + result);
                RespBean respBean = new Gson().fromJson(result, RespBean.class);
                if (PARAM_Y.equals(respBean.getMsg())) {
                    showToast("添加成功");
                } else if (PARAM_A.equals(respBean.getMsg())) {
                    showToast("不能为空");
                }
                mHandler.sendEmptyMessage(MSG_REFRESH);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.e(ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });
    }

    /**
     * 设置背景
     */
    private void updateHeadPic(String picUrl) {
        RequestParams params = new RequestParams(Urls.PERSONAL_CENTER_EDIT_INFO);
        params.addParameter(PARAM_UID, QFApplication.getInstance().getLoginUser().getUid());
        params.addParameter(PARAM_TYPE, PARAM_HEADPIC_INFO_EDIT_TYPE_VALUE);
        params.addParameter(PARAM_HEADPIC, picUrl);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtil.e("updateHeadPic == " + result);
                RespBean respBean = new Gson().fromJson(result, RespBean.class);
                if (PARAM_Y.equals(respBean.getMsg())) {
                    showToast("设置成功");
                } else if (PARAM_A.equals(respBean.getMsg())) {
                    showToast("不能为空");
                }
                mHandler.sendEmptyMessage(MSG_REFRESH);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.e(ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });
    }

    /**
     * 删除相册图片
     */
    private void deletePic(int id) {
        RequestParams params = new RequestParams(Urls.DELETE_PHOTO);
        params.addParameter(PARAM_UID, QFApplication.getInstance().getLoginUser().getUid());
        params.addParameter(PARAM_PHOTO_ID, id);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtil.e("deletePic == " + result);
                RespBean respBean = new Gson().fromJson(result, RespBean.class);
                if (PARAM_Y.equals(respBean.getMsg())) {
                    showToast("删除成功");
                } else if (PARAM_A.equals(respBean.getMsg())) {
                    showToast("不能为空");
                }
                mHandler.sendEmptyMessage(MSG_REFRESH);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.e(ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });
    }

    private void getPersonalCenter() {
        RequestParams params = new RequestParams(Urls.PERSONAL_CENTER);
        params.addParameter(PARAM_UID, QFApplication.getInstance().getLoginUser().getUid());
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtil.e("getPersonalCenter == " + result);
                PersonalCenterRespBean respBean = new Gson().fromJson(result, PersonalCenterRespBean.class);
                if (PARAM_Y.equals(respBean.getMsg()) && respBean != null) {
                    PersonalCenterRespBean.PersonalCenterBean bean = respBean.getResult();
                    if (bean != null) {
                        tvNickname.setText(bean.getNickname());
                        tvAge.setText(String.valueOf(bean.getAge()));
                        tvSignature.setText(bean.getSignature());
                        tvCurrRoomnum.setText(String.valueOf(bean.getCurroomnum()));
                        UIHelper.setSexLabel(bean.getSex(), imgSex);
                        x.image().bind(imgHead,
                                bean.getAnchorpic(),
                                imageOptions,
                                null);
                        Glide.with(getActivity()).load(bean.getRoompic()).placeholder(R.mipmap.error_pic).error(R.mipmap.error_pic).into(imgRoomBg);
                        photoDatas = bean.getPhoto();
                        if (null != photoDatas && photoDatas.size() > 0) {
                            photoAdapter = new PhotoAdapter(getActivity(), photoDatas);
                            photoAdapter.setOnItemClickListener(PersonalCenterFragment.this);
                            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                            layoutManager.setOrientation(OrientationHelper.HORIZONTAL);
                            recyclelistview.setLayoutManager(layoutManager);
                            recyclelistview.setHasFixedSize(true);
                            recyclelistview.setAdapter(photoAdapter);
                        }
                    }
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.e(ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        showPopueWindow(getActivity(), position);
    }

    private void showPopueWindow(final Activity context, final int pos) {
        View popView = View.inflate(context, R.layout.select_img_pop_layout, null);
        Button btSetBigBg = (Button) popView.findViewById(R.id.btn_setBigBg);
        Button btSeePic = (Button) popView.findViewById(R.id.btn_watchbigPic);
        Button btDelete = (Button) popView.findViewById(R.id.btn_delete);
        Button btCancel = (Button) popView.findViewById(R.id.btn_pop_cancel);
        //获取屏幕宽高
        int weight = context.getResources().getDisplayMetrics().widthPixels;
        int height = context.getResources().getDisplayMetrics().heightPixels * 1 / 3;
        final PopupWindow popupWindow = new PopupWindow(popView, weight, height);
        popupWindow.setFocusable(true);
        //点击外部popueWindow消失
        popupWindow.setOutsideTouchable(true);
        btSetBigBg.setOnClickListener(new View.OnClickListener() {//设置为背景
            @Override
            public void onClick(View v) {
                updateHeadPic(photoDatas.get(pos).getPicurl());
                popupWindow.dismiss();
            }
        });
        btSeePic.setOnClickListener(new View.OnClickListener() {//查看大图
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                b.putString("picurl", photoDatas.get(pos).getPicurl());
                gotoActivityWithBundle(getActivity(), BigPicActivity.class, b);
//                BigPicFragment bigPicFragment = new BigPicFragment(photoDatas.get(pos).getPicurl(), imageOptions);
//                bigPicFragment.show(getFragmentManager(), "show");
                popupWindow.dismiss();
            }
        });
        btDelete.setOnClickListener(new View.OnClickListener() {//删除
            @Override
            public void onClick(View v) {
                deletePic(photoDatas.get(pos).getId());
                popupWindow.dismiss();
            }
        });
        btCancel.setOnClickListener(new View.OnClickListener() {//取消
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        //popupWindow消失屏幕变为不透明
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = context.getWindow().getAttributes();
                lp.alpha = 1.0f;
                context.getWindow().setAttributes(lp);
            }
        });
        //popupWindow出现屏幕变为半透明
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = 0.5f;
        context.getWindow().setAttributes(lp);
        popupWindow.showAtLocation(popView, Gravity.BOTTOM, 0, 50);
    }

    private String encode(String path) {
        //decode to bitmap
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        //convert to byte array
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] bytes = baos.toByteArray();
        //base64 encode
        byte[] encode = Base64.encode(bytes, Base64.DEFAULT);
        String encodeString = new String(encode);
        return encodeString;
    }
}

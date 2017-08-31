package com.qingfeng.livesocial.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.bean.AnchorInfoRespBean;
import com.qingfeng.livesocial.bean.RespBean;
import com.qingfeng.livesocial.common.QFApplication;
import com.qingfeng.livesocial.common.Urls;
import com.qingfeng.livesocial.ui.base.BaseActivity;
import com.qingfeng.livesocial.util.GlideImageLoader;
import com.qingfeng.livesocial.util.StringUtils;
import com.qingfeng.livesocial.widget.RoundedImageView;

import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

import static com.qingfeng.livesocial.common.Constants.PARAM_A;
import static com.qingfeng.livesocial.common.Constants.PARAM_ANCHORPIC_INFO_EDIT_TYPE_VALUE;
import static com.qingfeng.livesocial.common.Constants.PARAM_HEADPIC;
import static com.qingfeng.livesocial.common.Constants.PARAM_TYPE;
import static com.qingfeng.livesocial.common.Constants.PARAM_UID;
import static com.qingfeng.livesocial.common.Constants.PARAM_Y;

/**
 * Created by Administrator on 2017/8/29.
 */

public class AnchorInfoActivity extends BaseActivity {
    @Bind(R.id.img_head)
    RoundedImageView roundedImageView;
    @Bind(R.id.tv_nickname)
    TextView tvNickname;
    @Bind(R.id.tv_qianming)
    TextView tvQianming;
    @Bind(R.id.tv_address)
    TextView tvAddress;
    @Bind(R.id.tv_birthday)
    TextView tvBirthday;
    @Bind(R.id.tv_sex)
    TextView tvSex;
    @Bind(R.id.tv_constell)
    TextView tvConstell;
    @Bind(R.id.btn_label1)
    Button btnLabel1;
    @Bind(R.id.btn_label2)
    Button btnLabel2;
    @Bind(R.id.btn_label3)
    Button btnLabel3;
    private List<Button> labelContainer;
    private String filePath = "";

    @Override
    protected int getLayoutById() {
        return R.layout.anchor_data_layout;
    }

    @Override
    protected void onResume() {
        super.onResume();
        btnLabel1.setVisibility(View.GONE);
        btnLabel2.setVisibility(View.GONE);
        btnLabel3.setVisibility(View.GONE);
        getAnchorData();
    }

    @Override
    protected void initView() {
        labelContainer = new ArrayList<Button>();
        labelContainer.add(btnLabel1);
        labelContainer.add(btnLabel2);
        labelContainer.add(btnLabel3);
    }

    @Override
    protected void initData() {
        getAnchorData();
    }

    @OnClick({R.id.rl_nickname, R.id.rl_qianming, R.id.rl_labels, R.id.img_arrow_left, R.id.img_head})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_nickname:
                gotoActivity(this, NicknameActivity.class);
                break;
            case R.id.rl_qianming:
                gotoActivity(this, QianmingActivity.class);
                break;
            case R.id.rl_labels:
                gotoActivity(this, LabelsActivity.class);
                break;
            case R.id.img_arrow_left:
                finish();
                break;
            case R.id.img_head:
                gotoAlbum();
                break;
        }
    }

    private void getAnchorData() {
        RequestParams params = new RequestParams(Urls.PERSONAL_CENTER_ANCHOR_INFO);
        params.addParameter(PARAM_UID, QFApplication.getInstance().getLoginUser().getUid());
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtil.e("getAnchorData == " + result);
                AnchorInfoRespBean respBean = new Gson().fromJson(result, AnchorInfoRespBean.class);
                if (PARAM_Y.equals(respBean.getMsg()) && respBean != null) {
                    AnchorInfoRespBean.AnchorInfoBean bean = respBean.getResult();
                    if (bean != null) {
                        tvNickname.setText(bean.getNickname());
                        tvSex.setText(bean.getSex());
                        tvQianming.setText(bean.getSignature());
                        tvAddress.setText(bean.getAddress());
                        tvBirthday.setText(bean.getBirthday());
                        tvConstell.setText(bean.getConstellation());
                        tvSex.setText(bean.getSex());
                        x.image().bind(roundedImageView,
                                bean.getAnchorpic(),
                                imageOptions,
                                null);
                        if (!StringUtils.isEmpty(bean.getLabelinfo())) {
                            String[] labelArr = bean.getLabelinfo().split(",");
                            for (int k = 0; k < labelArr.length; k++) {
                                labelContainer.get(k).setVisibility(View.VISIBLE);
                                labelContainer.get(k).setText(labelArr[k]);
                            }
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == 100) {
                List<ImageItem> images = (List<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                filePath = images.get(0).path;
                File uploadFile = new File(filePath);
                roundedImageView.setImageURI(Uri.fromFile(uploadFile));
                updateHeadPic();
//                getAnchorData();
            } else {
                showToast("没有数据");
            }
        }
    }

    private void updateHeadPic() {
        RequestParams params = new RequestParams(Urls.PERSONAL_CENTER_EDIT_INFO);
        params.addParameter(PARAM_UID, QFApplication.getInstance().getLoginUser().getUid());
        params.addParameter(PARAM_TYPE, PARAM_ANCHORPIC_INFO_EDIT_TYPE_VALUE);
        params.addParameter(PARAM_HEADPIC, encode(filePath));
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtil.e("updateHeadPic == " + result);
                RespBean respBean = new Gson().fromJson(result, RespBean.class);
                if (PARAM_Y.equals(respBean.getMsg())) {
                    showToast("修改成功");
                } else if (PARAM_A.equals(respBean.getMsg())) {
                    showToast("不能为空");
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

    private void gotoAlbum() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());
        imagePicker.setShowCamera(true);
        imagePicker.setSelectLimit(9);
        imagePicker.setCrop(false);
        Intent intent = new Intent(mContext, ImageGridActivity.class);
        startActivityForResult(intent, 100);
    }
}

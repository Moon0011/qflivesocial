package com.qingfeng.livesocial.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.qingfeng.livesocial.R;
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
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

import static com.qingfeng.livesocial.common.Constants.PARAM_ANCHORPIC;
import static com.qingfeng.livesocial.common.Constants.PARAM_NICKNAME;
import static com.qingfeng.livesocial.common.Constants.PARAM_SEX;
import static com.qingfeng.livesocial.common.Constants.PARAM_UID;

/**
 * Created by Administrator on 2017/8/22.
 */

public class PerfectInfoActivity extends BaseActivity {
    @Bind(R.id.img_photo_add)
    RoundedImageView imgPhotoAdd;
    @Bind(R.id.rl_regist_succ)
    RelativeLayout rlRegistSucc;
    @Bind(R.id.img_man)
    ImageView imgMan;
    @Bind(R.id.img_arrow_left)
    ImageView imgArrowLeft;
    @Bind(R.id.img_woman)
    ImageView imgWoman;
    @Bind(R.id.et_nickname)
    EditText etNickname;
    private String sex = "";
    private String filePath = "";

    @Override
    protected int getLayoutById() {
        return R.layout.perfect_info_layout;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.img_photo_add, R.id.rl_regist_succ, R.id.img_arrow_left, R.id.img_man, R.id.img_woman})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_photo_add:
                gotoAlbum();
                break;
            case R.id.rl_regist_succ:
                perfectInfoSucc();
                break;
            case R.id.img_arrow_left:
                finish();
                break;
            case R.id.img_man:
                sex = "m";
                imgMan.setImageResource(R.mipmap.man_selected);
                imgWoman.setImageResource(R.mipmap.woman);
                break;
            case R.id.img_woman:
                sex = "f";
                imgWoman.setImageResource(R.mipmap.woman_selected);
                imgMan.setImageResource(R.mipmap.man);
                break;
        }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == 100) {
                List<ImageItem> images = (List<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                filePath = images.get(0).path;
                File uploadFile = new File(filePath);
                imgPhotoAdd.setImageURI(Uri.fromFile(uploadFile));
            } else {
                showToast("没有数据");
            }
        }
    }

    private void perfectInfoSucc() {
        String nickName = etNickname.getText().toString().trim();
        int uid = QFApplication.getInstance().getLoginUser().getUid();
        RequestParams params = new RequestParams(Urls.PERFECT_INFO);
        params.addParameter(PARAM_UID, uid);
        if (StringUtils.isEmpty(filePath)) {
            showToast("头像未选");
            return;
        }
        params.addParameter(PARAM_ANCHORPIC, encode(filePath));
        params.addQueryStringParameter(PARAM_NICKNAME, nickName);
        params.addQueryStringParameter(PARAM_SEX, sex);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtil.e("perfectInfoSucc == " + result);
                RespBean respBean = new Gson().fromJson(result, RespBean.class);
                if ("a".equals(respBean.getMsg())) {
                    showToast("值不能为空");
                } else {
                    showToast("更新完成");
                    gotoActivity(PerfectInfoActivity.this, RecommendActivity.class);
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
}

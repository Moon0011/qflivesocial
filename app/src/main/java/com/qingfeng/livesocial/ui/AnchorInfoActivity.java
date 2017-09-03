package com.qingfeng.livesocial.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.google.gson.Gson;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.bean.AnchorInfoRespBean;
import com.qingfeng.livesocial.bean.JsonBean;
import com.qingfeng.livesocial.bean.RespBean;
import com.qingfeng.livesocial.common.QFApplication;
import com.qingfeng.livesocial.common.UIHelper;
import com.qingfeng.livesocial.common.Urls;
import com.qingfeng.livesocial.ui.base.BaseActivity;
import com.qingfeng.livesocial.util.GetJsonDataUtil;
import com.qingfeng.livesocial.util.GlideImageLoader;
import com.qingfeng.livesocial.util.StringUtils;
import com.qingfeng.livesocial.widget.RoundedImageView;

import org.json.JSONArray;
import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

import static com.qingfeng.livesocial.common.Constants.PARAM_A;
import static com.qingfeng.livesocial.common.Constants.PARAM_ADDRESS;
import static com.qingfeng.livesocial.common.Constants.PARAM_ADDRESS_INFO_EDIT_TYPE_VALUE;
import static com.qingfeng.livesocial.common.Constants.PARAM_ANCHORPIC;
import static com.qingfeng.livesocial.common.Constants.PARAM_ANCHORPIC_INFO_EDIT_TYPE_VALUE;
import static com.qingfeng.livesocial.common.Constants.PARAM_BIRTHDAY;
import static com.qingfeng.livesocial.common.Constants.PARAM_BIRTHDAY_INFO_EDIT_TYPE_VALUE;
import static com.qingfeng.livesocial.common.Constants.PARAM_SEX;
import static com.qingfeng.livesocial.common.Constants.PARAM_SEX_INFO_EDIT_TYPE_VALUE;
import static com.qingfeng.livesocial.common.Constants.PARAM_TYPE;
import static com.qingfeng.livesocial.common.Constants.PARAM_UID;
import static com.qingfeng.livesocial.common.Constants.PARAM_Y;

/**
 * Created by Administrator on 2017/8/29.
 * 编辑资料页
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
    private static final int MSG_LOAD_SUCCESS = 0x0001;
    private static final int MSG_LOAD_FAILED = 0x0002;
    private static final int MSG_REFRESH = 0x0003;
    private TimePickerView pvTime;
    private Thread thread;
    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_REFRESH:
                    getAnchorData();
                    break;
                case MSG_LOAD_SUCCESS:
                    LogUtil.e("解析城市数据成功");
                    break;
                case MSG_LOAD_FAILED:
                    LogUtil.e("解析城市数据失败");
                    break;
            }
        }
    };

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
        if (thread == null) {
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    initJsonData();
                }
            });
            thread.start();
        }
        initTimePicker();
    }

    @OnClick({R.id.rl_nickname, R.id.rl_qianming, R.id.rl_labels, R.id.img_arrow_left, R.id.img_head, R.id.rl_address, R.id.rl_sex, R.id.rl_birthday})
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
            case R.id.rl_address:
                showAddressPickerView();
                break;
            case R.id.rl_sex:
                new AlertDialog.Builder(this)
                        .setTitle("选择您的性别")
                        .setItems(R.array.sex_arr, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                final String[] aryShop = getResources().getStringArray(R.array.sex_arr);
                                tvSex.setText(aryShop[which]);
                                updateSex(UIHelper.setSex2(aryShop[which]));
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
                break;
            case R.id.rl_birthday:
                pvTime.show();
                break;
        }
    }

    //时间选择器
    private void initTimePicker() {
        pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                tvBirthday.setText(format.format(date));
                updateBirthday(format.format(date));
            }
        })//年月日时分秒 的显示与否，不设置则默认全部显示
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("", "", "", "", "", "")
                .isCenterLabel(false)
                .setDividerColor(Color.DKGRAY)
                .setContentSize(21)
                .setBackgroundId(0x00FFFFFF) //设置外部遮罩颜色
                .setDecorView(null)
                .build();
    }

    private void initJsonData() {//解析数据
        String JsonData = new GetJsonDataUtil().getJson(this, "province.json");//获取assets目录下的json文件数据
        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体
        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;
        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）
            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCityList().get(c).getName();
                CityList.add(CityName);//添加城市
                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表
                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    City_AreaList.add("");
                } else {
                    for (int d = 0; d < jsonBean.get(i).getCityList().get(c).getArea().size(); d++) {//该城市对应地区所有数据
                        String AreaName = jsonBean.get(i).getCityList().get(c).getArea().get(d);
                        City_AreaList.add(AreaName);//添加该城市所有地区数据
                    }
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }
            options2Items.add(CityList);//添加城市数据
            options3Items.add(Province_AreaList);//添加地区数据
        }
        mHandler.sendEmptyMessage(MSG_LOAD_SUCCESS);
    }

    private void showAddressPickerView() {
        OptionsPickerView pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getPickerViewText() + " " +
                        options2Items.get(options1).get(options2) + " " +
                        options3Items.get(options1).get(options2).get(options3);
                tvAddress.setText(tx);
                updateAddress(tx);

            }
        })
                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }


    public ArrayList<JsonBean> parseData(String result) {
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            mHandler.sendEmptyMessage(MSG_LOAD_FAILED);
        }
        return detail;
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
                        tvSex.setText(UIHelper.setSex(bean.getSex()));
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
                updateAnchorHead();
            } else {
                showToast("没有数据");
            }
        }
    }

    private void updateAnchorHead() {
        RequestParams params = new RequestParams(Urls.PERSONAL_CENTER_EDIT_INFO);
        params.addParameter(PARAM_UID, QFApplication.getInstance().getLoginUser().getUid());
        params.addParameter(PARAM_TYPE, PARAM_ANCHORPIC_INFO_EDIT_TYPE_VALUE);
        params.addParameter(PARAM_ANCHORPIC, encode(filePath));
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtil.e("updateHeadPic == " + result);
                RespBean respBean = new Gson().fromJson(result, RespBean.class);
                if (PARAM_Y.equals(respBean.getMsg())) {
                    showToast("修改成功");
                    mHandler.sendEmptyMessage(MSG_REFRESH);
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

    private void updateSex(String sex) {
        RequestParams params = new RequestParams(Urls.PERSONAL_CENTER_EDIT_INFO);
        params.addParameter(PARAM_UID, QFApplication.getInstance().getLoginUser().getUid());
        params.addParameter(PARAM_TYPE, PARAM_SEX_INFO_EDIT_TYPE_VALUE);
        params.addParameter(PARAM_SEX, sex);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtil.e("updateHeadPic == " + result);
                RespBean respBean = new Gson().fromJson(result, RespBean.class);
                if (PARAM_Y.equals(respBean.getMsg())) {
                    showToast("修改成功");
                    mHandler.sendEmptyMessage(MSG_REFRESH);
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

    private void updateAddress(String address) {
        RequestParams params = new RequestParams(Urls.PERSONAL_CENTER_EDIT_INFO);
        params.addParameter(PARAM_UID, QFApplication.getInstance().getLoginUser().getUid());
        params.addParameter(PARAM_TYPE, PARAM_ADDRESS_INFO_EDIT_TYPE_VALUE);
        params.addParameter(PARAM_ADDRESS, address);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtil.e("updateHeadPic == " + result);
                RespBean respBean = new Gson().fromJson(result, RespBean.class);
                if (PARAM_Y.equals(respBean.getMsg())) {
                    showToast("修改成功");
                    mHandler.sendEmptyMessage(MSG_REFRESH);
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

    private void updateBirthday(String birthday) {
        RequestParams params = new RequestParams(Urls.PERSONAL_CENTER_EDIT_INFO);
        params.addParameter(PARAM_UID, QFApplication.getInstance().getLoginUser().getUid());
        params.addParameter(PARAM_TYPE, PARAM_BIRTHDAY_INFO_EDIT_TYPE_VALUE);
        params.addParameter(PARAM_BIRTHDAY, birthday);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtil.e("updateHeadPic == " + result);
                RespBean respBean = new Gson().fromJson(result, RespBean.class);
                if (PARAM_Y.equals(respBean.getMsg())) {
                    showToast("修改成功");
                    mHandler.sendEmptyMessage(MSG_REFRESH);
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
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] bytes = baos.toByteArray();
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

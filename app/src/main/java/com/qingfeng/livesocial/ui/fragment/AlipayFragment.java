package com.qingfeng.livesocial.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.adapter.PayAdapter;
import com.qingfeng.livesocial.bean.AuthResult;
import com.qingfeng.livesocial.bean.PayBean;
import com.qingfeng.livesocial.bean.PayResult;
import com.qingfeng.livesocial.common.Constants;
import com.qingfeng.livesocial.ui.base.BaseFragment;
import com.qingfeng.livesocial.util.OrderInfoUtil2_0;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

import static com.qingfeng.livesocial.common.Constants.RSA2_PRIVATE;
import static com.qingfeng.livesocial.common.Constants.RSA_PRIVATE;

/**
 * Created by Administrator on 2017/9/4.
 */

public class AlipayFragment extends BaseFragment implements PayAdapter.OnItemClickListener {
    @Bind(R.id.recycleView)
    RecyclerView recyclerView;
    @Bind(R.id.btn_pay)
    Button btnPay;
    private List<PayBean> payBeanList = new ArrayList<>();
    private PayAdapter adapter;
    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(getActivity(), "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(getActivity(), "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();

                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
                        Toast.makeText(getActivity(),
                                "授权成功\n" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        // 其他状态值则为授权失败
                        Toast.makeText(getActivity(),
                                "授权失败" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT).show();

                    }
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };

    @Override
    protected int getLayoutId() {
        return R.layout.alipay_fragment_layout;
    }

    @Override
    protected void initWidget(View root) {
        if (payBeanList != null) {
            payBeanList.clear();
        }
        PayBean payBean1 = new PayBean("60萌点", "¥6");
        PayBean payBean2 = new PayBean("180萌点", "¥18");
        PayBean payBean3 = new PayBean("500萌点", "¥50");
        PayBean payBean4 = new PayBean("980萌点", "¥98");
        PayBean payBean5 = new PayBean("1980萌点", "¥198");
        PayBean payBean6 = new PayBean("2880萌点", "¥288");
        PayBean payBean7 = new PayBean("4880萌点", "¥488");
        PayBean payBean8 = new PayBean("19880萌点", "¥1988");
        payBeanList.add(payBean1);
        payBeanList.add(payBean2);
        payBeanList.add(payBean3);
        payBeanList.add(payBean4);
        payBeanList.add(payBean5);
        payBeanList.add(payBean6);
        payBeanList.add(payBean7);
        payBeanList.add(payBean8);
    }

    @Override
    protected void initData() {
        adapter = new PayAdapter(getActivity(), payBeanList);
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);
        final GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void onItemClick(int position, List<View> views) {
        boolean isSelectd = false;
        View selectedView = views.get(position);
        for (int i = 0; i < views.size(); i++) {
            if (position != i) {
                View unSelectView = views.get(i);
                unSelectView.setBackgroundResource(R.mipmap.grid_pay_item_bg);
                TextView tvMengNum = (TextView) unSelectView.findViewById(R.id.tv_mengNum);
                TextView tvMengPrice = (TextView) unSelectView.findViewById(R.id.tv_mengPrice);
                tvMengNum.setTextColor(getResources().getColor(R.color.txt_normal_color));
                tvMengPrice.setTextColor(getResources().getColor(R.color.txt_normal_color));
                unSelectView.setTag(false);
            }
        }
        if (null != selectedView.getTag()) {
            isSelectd = (boolean) selectedView.getTag();
        }
        if (isSelectd) {
            selectedView.setBackgroundResource(R.mipmap.grid_pay_item_bg);
            TextView tvMengNum = (TextView) selectedView.findViewById(R.id.tv_mengNum);
            TextView tvMengPrice = (TextView) selectedView.findViewById(R.id.tv_mengPrice);
            tvMengNum.setTextColor(getResources().getColor(R.color.txt_normal_color));
            tvMengPrice.setTextColor(getResources().getColor(R.color.txt_normal_color));
            selectedView.setTag(false);
            btnPay.setText(tvMengPrice.getText().toString());
        } else {
            selectedView.setBackgroundResource(R.mipmap.grid_pay_item_selected_bg);
            TextView tvMengNum = (TextView) selectedView.findViewById(R.id.tv_mengNum);
            TextView tvMengPrice = (TextView) selectedView.findViewById(R.id.tv_mengPrice);
            tvMengNum.setTextColor(getResources().getColor(R.color.txt_selected_color));
            tvMengPrice.setTextColor(getResources().getColor(R.color.txt_selected_color));
            selectedView.setTag(true);
            btnPay.setText("立即支付 : " + tvMengPrice.getText().toString());
        }
    }

    @OnClick({R.id.btn_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_pay:
                payV2();
                break;
        }
    }

    public void payV2() {
        if (TextUtils.isEmpty(Constants.APPID) || TextUtils.isEmpty(RSA_PRIVATE)) {
            return;
        }
        /**
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * orderInfo的获取必须来自服务端；
         */
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(Constants.APPID, rsa2);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
        final String orderInfo = orderParam + "&" + sign;

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(getActivity());
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }
}

package com.qingfeng.livesocial.ui.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.qingfeng.livesocial.R;
import com.qingfeng.livesocial.adapter.PayAdapter;
import com.qingfeng.livesocial.bean.PayBean;
import com.qingfeng.livesocial.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

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

}

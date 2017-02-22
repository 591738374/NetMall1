package com.example.netmall.type.fragment;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.netmall.base.BaseFragment;

/**
 * Created by WZ on 2017/2/22.
 * 类型fragment
 */

public class TypeFragment extends BaseFragment {
    private TextView textView;
    @Override
    public View initView() {
        textView=new TextView(mContext);
        textView.setTextSize(25);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        textView.setText("类型fragment数据初始化");
    }
}

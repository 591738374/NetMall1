package com.example.netmall.home.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.netmall.R;
import com.example.netmall.base.BaseFragment;
import com.example.netmall.home.adapter.HomeAdapter;
import com.example.netmall.home.bean.HomeBean;
import com.example.netmall.utils.Constacts;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.Call;

import static android.content.ContentValues.TAG;

/**
 * Created by WZ on 2017/2/22.
 * 主页fragment
 */

public class HomeFragment extends BaseFragment {
    @InjectView(R.id.tv_search_home)
    TextView tvSearchHome;
    @InjectView(R.id.tv_message_home)
    TextView tvMessageHome;
    @InjectView(R.id.rv_home)
    RecyclerView rvHome;
    @InjectView(R.id.ib_top)
    ImageButton ibTop;

    private HomeAdapter adapter;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_home, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        getDataFromNet();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.tv_search_home, R.id.tv_message_home, R.id.ib_top})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_search_home:
                Toast.makeText(mContext, "搜索", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_message_home:
                Toast.makeText(mContext, "进入消息中心", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ib_top:
//                Toast.makeText(mContext, "置顶", Toast.LENGTH_SHORT).show();
                rvHome.scrollToPosition(0);
                break;
        }
    }

    public void getDataFromNet() {
        OkHttpUtils.get()
                .url(Constacts.HOME_URL)
                .id(100)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "联网失败" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        //当联网成功后会回调这里
                        Log.e("TAG", "联网请求成功");
                        progressData(response);
                    }
                });
    }

    private void progressData(String response) {
        HomeBean homeBean = JSON.parseObject(response, HomeBean.class);
        Log.e(TAG, "请求的数据" + homeBean.getResult().getAct_info().get(0).getName());
        //设置RecycleView的适配器
        adapter = new HomeAdapter(mContext, homeBean.getResult());
        rvHome.setAdapter(adapter);

        GridLayoutManager manager = new GridLayoutManager(mContext, 1);
        rvHome.setLayoutManager(manager);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position <= 3) {
                    //按钮隐藏
                    ibTop.setVisibility(View.GONE);
                } else {
                    ibTop.setVisibility(View.VISIBLE);
                }

                return 1;
            }
        });
    }
}

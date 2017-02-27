package com.example.netmall.home.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.netmall.home.bean.HomeBean;
import com.example.netmall.utils.Constacts;

import java.util.List;

/**
 * Created by WZ on 2017/2/25.
 */

public class ViewPagerAdapter extends PagerAdapter {
    private Context mContext;
    private List<HomeBean.ResultBean.ActInfoBean> datas;

    public ViewPagerAdapter(Context mContext, List<HomeBean.ResultBean.ActInfoBean> datas) {
        this.mContext = mContext;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(mContext).load(Constacts.BASE_URL_IMAGE + datas.get(position).getIcon_url()).into(imageView);
        //添加到容器内
        container.addView(imageView);

        //点击监听，具体实现在HomeAdapter
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.OnItemClick(view, position);
                }
            }
        });
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    //定义接口，实现在HomeAdapter内点击监听
    public interface OnItemClickListener {
        public void OnItemClick(View view, int position);
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}

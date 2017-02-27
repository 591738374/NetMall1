package com.example.netmall.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.netmall.R;
import com.example.netmall.home.bean.HomeBean;
import com.example.netmall.utils.Constacts;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by WZ on 2017/2/26.
 */

public class HotViewAdapter extends BaseAdapter {

    private Context mContext;
    private List<HomeBean.ResultBean.HotInfoBean> datas;

    public HotViewAdapter(Context mContext, List<HomeBean.ResultBean.HotInfoBean> hot_info) {
        this.mContext = mContext;
        this.datas = hot_info;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_hot_grid_view, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        HomeBean.ResultBean.HotInfoBean hotInfoBean = datas.get(position);
        holder.tvName.setText(hotInfoBean.getName());
        holder.tvPrice.setText(hotInfoBean.getCover_price());
        Glide.with(mContext).load(Constacts.BASE_URL_IMAGE + hotInfoBean.getFigure()).into(holder.ivHot);

        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.iv_hot)
        ImageView ivHot;
        @InjectView(R.id.tv_name)
        TextView tvName;
        @InjectView(R.id.tv_price)
        TextView tvPrice;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}

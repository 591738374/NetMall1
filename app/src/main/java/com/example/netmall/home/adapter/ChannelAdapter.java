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
 * Created by WZ on 2017/2/23.
 */

public class ChannelAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<HomeBean.ResultBean.ChannelInfoBean> datas;

    public ChannelAdapter(Context mContext, List<HomeBean.ResultBean.ChannelInfoBean> datas) {
        this.mContext = mContext;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int i) {
        return datas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_channel, null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        //根据位置取对应的数据
        HomeBean.ResultBean.ChannelInfoBean channelInfoBean=datas.get(position);
        holder.tvChannel.setText(channelInfoBean.getChannel_name());

        //Glide请求图片
        Glide.with(mContext)
                .load(Constacts.BASE_URL_IMAGE+channelInfoBean.getImage())
                .crossFade()
                .into(holder.ivChannel);
        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.iv_channel)
        ImageView ivChannel;
        @InjectView(R.id.tv_channel)
        TextView tvChannel;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}

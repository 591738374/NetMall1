package com.example.netmall.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.netmall.R;
import com.example.netmall.home.bean.HomeBean;
import com.example.netmall.utils.Constacts;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by WZ on 2017/2/25.
 */

public class SeckillRecyclerViewAdapter extends RecyclerView.Adapter {

    private final LayoutInflater inflater;
    private Context mContext;
    private List<HomeBean.ResultBean.SeckillInfoBean.ListBean> datas;

    public SeckillRecyclerViewAdapter(Context mContext, HomeBean.ResultBean.SeckillInfoBean seckill_info) {
        this.mContext = mContext;
        this.datas = seckill_info.getList();
        this.inflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_seckill, null);
        return new ViewHolder(mContext, view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;

        viewHolder.tvCoverPrice.setText("￥" + datas.get(position).getCover_price());
        viewHolder.tvOriginPrice.setText("￥" + datas.get(position).getOrigin_price());
        Glide.with(mContext).load(Constacts.BASE_URL_IMAGE + datas.get(position).getFigure())
                .into(viewHolder.ivFigure);

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.iv_figure)
        ImageView ivFigure;
        @InjectView(R.id.tv_cover_price)
        TextView tvCoverPrice;
        @InjectView(R.id.tv_origin_price)
        TextView tvOriginPrice;
        @InjectView(R.id.ll_root)
        LinearLayout llRoot;
        private Context mContext;

        ViewHolder(Context mContext, View view) {
            super(view);
            this.mContext = mContext;
            ButterKnife.inject(this, view);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onSckillItemClickListener.onClick(view, getLayoutPosition());
                }
            });
        }
    }

    /*
    item的点击监听接口
     */
    public interface OnSeckillItemClickListener {
        public void onClick(View view, int position);
    }

    public OnSeckillItemClickListener onSckillItemClickListener;

    public void setSeckillItemClickListener(OnSeckillItemClickListener seckillItemClickListener) {
        this.onSckillItemClickListener = seckillItemClickListener;
    }
}

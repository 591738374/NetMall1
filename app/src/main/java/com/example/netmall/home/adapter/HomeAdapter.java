package com.example.netmall.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.netmall.R;
import com.example.netmall.home.bean.HomeBean;

import java.util.List;

/**
 * Created by WZ on 2017/2/23.
 * 分类型的RecyclerView
 */

public class HomeAdapter extends RecyclerView.Adapter {

    /**
     * 六种类型
     */
    /**
     * 横幅广告
     */
    public static final int BANNER = 0;
    /**
     * 频道
     */
    public static final int CHANNEL = 1;

    /**
     * 活动
     */
    public static final int ACT = 2;

    /**
     * 秒杀
     */
    public static final int SECKILL = 3;
    /**
     * 推荐
     */
    public static final int RECOMMEND = 4;
    /**
     * 热卖
     */
    public static final int HOT = 5;

    /**
     * 当前类型
     */
    public int currentType = BANNER;


    private Context mContext;
    private final HomeBean.ResultBean results;
    private final LayoutInflater inflater;

    public HomeAdapter(Context mContext, HomeBean.ResultBean results) {
        this.mContext = mContext;
        this.results = results;
        this.inflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == BANNER) {
//            View view = View.inflate(mContext, R.layout.banner_viewpager, null);
//            return new BannerViewHolder(mContext, view);
            return new BannerViewHolder(mContext, inflater.inflate(R.layout.banner_viewpager, null));

        } else if (viewType == CHANNEL) {

        } else if (viewType == ACT) {

        } else if (viewType == SECKILL) {

        } else if (viewType == RECOMMEND) {

        } else if (viewType == HOT) {

        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == BANNER) {
            BannerViewHolder bannerViewHolder= (BannerViewHolder) holder;
            //绑定数据
            bannerViewHolder.setData(results.getBanner_info());

        } else if (getItemViewType(position) == CHANNEL) {

        } else if (getItemViewType(position) == ACT) {

        } else if (getItemViewType(position) == SECKILL) {

        } else if (getItemViewType(position) == RECOMMEND) {

        } else if (getItemViewType(position) == HOT) {

        }
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == BANNER) {
            currentType = BANNER;
        } else if (position == CHANNEL) {
            currentType = CHANNEL;
        } else if (position == ACT) {
            currentType = ACT;
        } else if (position == SECKILL) {
            currentType = SECKILL;
        } else if (position == RECOMMEND) {
            currentType = RECOMMEND;
        } else if (position == HOT) {
            currentType = HOT;
        }
        return currentType;
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {
        private final Context mContext;
        private final TextView banner_tv_title;

        public BannerViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            banner_tv_title = (TextView) itemView.findViewById(R.id.banner_tv_title);
        }

        public void setData(List<HomeBean.ResultBean.BannerInfoBean> banner_info) {
            banner_tv_title.setText(banner_info.get(0).getImage().toString());
        }
    }
}

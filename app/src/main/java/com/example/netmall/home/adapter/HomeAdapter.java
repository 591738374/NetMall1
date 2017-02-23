package com.example.netmall.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.netmall.R;
import com.example.netmall.home.bean.HomeBean;
import com.example.netmall.utils.Constacts;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.loader.ImageLoader;
import com.youth.banner.transformer.BackgroundToForegroundTransformer;

import java.util.ArrayList;
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
            return new ChannelViewHolder(mContext, inflater.inflate(R.layout.channel_item, null));

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
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            //绑定数据
            bannerViewHolder.setData(results.getBanner_info());

        } else if (getItemViewType(position) == CHANNEL) {
            ChannelViewHolder channelViewHolder = (ChannelViewHolder) holder;
            //绑定数据
            channelViewHolder.setData(results.getChannel_info());

        } else if (getItemViewType(position) == ACT) {

        } else if (getItemViewType(position) == SECKILL) {

        } else if (getItemViewType(position) == RECOMMEND) {

        } else if (getItemViewType(position) == HOT) {

        }
    }

    @Override
    public int getItemCount() {
        return 2;
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
        private final Banner banner;

        public BannerViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            banner = (Banner) itemView.findViewById(R.id.banner);
        }

        public void setData(List<HomeBean.ResultBean.BannerInfoBean> banner_info) {
            //1.得到数据
            //2.设置Banner的数据
            List<String> images = new ArrayList<>();
            for (int i = 0; i < banner_info.size(); i++) {
                images.add(Constacts.BASE_URL_IMAGE + banner_info.get(i).getImage());
            }
            //简单使用
            banner.setImages(images)
                    .setImageLoader(new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object path, ImageView imageView) {
                            //具体方法内容自己去选择，此方法是为了减少banner过多的依赖第三方包，所以将这个权限开放给使用者去选择
                            Glide.with(context)
                                    .load(path)
                                    .crossFade()
                                    .into(imageView);

                        }
                    })
                    .start();
            //设置样式
            banner.setBannerAnimation(BackgroundToForegroundTransformer.class);
            //设置Banner的点击事件
            banner.setOnBannerClickListener(new OnBannerClickListener() {
                @Override
                public void OnBannerClick(int position) {
                    int realPosition = position - 1;
                    Toast.makeText(mContext, "realPosition" + realPosition, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    class ChannelViewHolder extends RecyclerView.ViewHolder {
        private final Context mContext;
        private final GridView gv_channel;
        private ChannelAdapter adapter;


        public ChannelViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            gv_channel = (GridView) itemView.findViewById(R.id.gv_channel);
        }

        public void setData(List<HomeBean.ResultBean.ChannelInfoBean> channelInfoBean) {
            //设置GridView的适配器
            adapter = new ChannelAdapter(mContext, channelInfoBean);
            gv_channel.setAdapter(adapter);
            gv_channel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    Toast.makeText(mContext, "position==" + position, Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}

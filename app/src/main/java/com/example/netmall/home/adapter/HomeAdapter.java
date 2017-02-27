package com.example.netmall.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.netmall.R;
import com.example.netmall.app.GoodsInfoActivity;
import com.example.netmall.home.bean.GoodsBean;
import com.example.netmall.home.bean.HomeBean;
import com.example.netmall.home.view.MyGridView;
import com.example.netmall.utils.Constacts;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.loader.ImageLoader;
import com.youth.banner.transformer.BackgroundToForegroundTransformer;
import com.zhy.magicviewpager.transformer.RotateYTransformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.iwgang.countdownview.CountdownView;

/**
 * Created by WZ on 2017/2/23.
 * 分类型的RecyclerView
 */

public class HomeAdapter extends RecyclerView.Adapter {

    public static final String GOODS_BEAN = "goodsBean";

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
            return new ActViewHolder(mContext, inflater.inflate(R.layout.act_item, null));

        } else if (viewType == SECKILL) {
            //SeckillViewHolder
            return new SeckillViewHolder(mContext, inflater.inflate(R.layout.seckill_item, null));

        } else if (viewType == RECOMMEND) {

            return new RecommendViewHolder(mContext, inflater.inflate(R.layout.recommend_item, null));
        } else if (viewType == HOT) {

            return new HotViewHolder(mContext, inflater.inflate(R.layout.hot_item, null));
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
            ActViewHolder actViewHolder = (ActViewHolder) holder;
            //绑定数据
            actViewHolder.setData(results.getAct_info());

        } else if (getItemViewType(position) == SECKILL) {
            SeckillViewHolder seckillViewHolder = (SeckillViewHolder) holder;
            //绑定数据
            seckillViewHolder.setData(results.getSeckill_info());

        } else if (getItemViewType(position) == RECOMMEND) {
            RecommendViewHolder recommendViewHolder = (RecommendViewHolder) holder;
            //绑定数据
            recommendViewHolder.setData(results.getRecommend_info());

        } else if (getItemViewType(position) == HOT) {
            HotViewHolder hotViewHolder = (HotViewHolder) holder;
            //绑定数据
            hotViewHolder.setData(results.getHot_info());

        }
    }

    @Override
    public int getItemCount() {
        return 6;
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

    public class ActViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;
        private final ViewPager act_viewpager;
        private ViewPagerAdapter adapter;

        public ActViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            act_viewpager = (ViewPager) itemView.findViewById(R.id.act_viewpager);
        }

        public void setData(final List<HomeBean.ResultBean.ActInfoBean> actInfoBeans) {
            //设置适配器
            adapter = new ViewPagerAdapter(mContext, actInfoBeans);

            //调用第三方，美化ViewPager库
            act_viewpager.setPageMargin(20);//设置page间间距，自行根据需求设置
            act_viewpager.setOffscreenPageLimit(3);//>=3
            act_viewpager.setAdapter(adapter);
            //setPageTransformer 决定动画效果
            act_viewpager.setPageTransformer(true, new
                    RotateYTransformer());

            adapter.setOnItemClickListener(new ViewPagerAdapter.OnItemClickListener() {
                @Override
                public void OnItemClick(View view, int position) {
                    Toast.makeText(mContext, "点击了：" + actInfoBeans.get(position).getName().toString(), Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    public class SeckillViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.countdownview)
        CountdownView countdownview;
        @InjectView(R.id.tv_more_seckill)
        TextView tvMoreSeckill;
        @InjectView(R.id.rv_seckill)
        RecyclerView rvSeckill;
        private final Context mContext;
        private SeckillRecyclerViewAdapter adapter;

        public SeckillViewHolder(Context mContext, View itemView) {
            super(itemView);
            Log.e("TAG", "SeckillViewHolder: " + "构造器11");
            this.mContext = mContext;
            ButterKnife.inject(this, itemView);
            Log.e("TAG", "SeckillViewHolder: " + "构造器222");
        }

        public void setData(HomeBean.ResultBean.SeckillInfoBean seckill_info) {
            Log.e("TAG", "SeckillViewHolder: setData" + "11111");
            adapter = new SeckillRecyclerViewAdapter(mContext, seckill_info);
            rvSeckill.setAdapter(adapter);
            rvSeckill.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));

            adapter.setSeckillItemClickListener(new SeckillRecyclerViewAdapter.OnSeckillItemClickListener() {
                @Override
                public void onClick(View view, int position) {
                    Toast.makeText(mContext, "点击监听" + position, Toast.LENGTH_SHORT).show();
                }
            });
            //设置秒杀时间
            long duration = Long.parseLong(seckill_info.getEnd_time()) - Long.parseLong(seckill_info.getStart_time());
            countdownview.start(duration);
            Log.e("TAG", "SeckillViewHolder: setData" + "22222");
        }
    }

    public class RecommendViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.tv_more_recommend)
        TextView tvMoreRecommend;
        @InjectView(R.id.gv_recommend)
        GridView gvRecommend;

        private final Context mContext;

        private RecommendGridViewAdapter adapter;

        public RecommendViewHolder(Context mContext, View itemView) {

            super(itemView);
            Log.e("TAG", "RecommendViewHolder: 构造器" + "11111");
            this.mContext = mContext;
            ButterKnife.inject(this, itemView);
            Log.e("TAG", "RecommendViewHolder: 构造器" + "22222");
        }

        public void setData(final List<HomeBean.ResultBean.RecommendInfoBean> recommend_info) {
            Log.e("TAG", "RecommendViewHolder: setData" + "11111");
            adapter = new RecommendGridViewAdapter(mContext, recommend_info);
            gvRecommend.setAdapter(adapter);
            Log.e("TAG", "RecommendViewHolder: setData" + "2222");

            gvRecommend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    Toast.makeText(mContext, "推荐" + position, Toast.LENGTH_SHORT).show();

                    HomeBean.ResultBean.RecommendInfoBean recommendInfoBean = recommend_info.get(position);
                    GoodsBean goodsBean = new GoodsBean();
                    goodsBean.setName(recommendInfoBean.getName());
                    goodsBean.setCover_price(recommendInfoBean.getCover_price());
                    goodsBean.setFigure(recommendInfoBean.getFigure());
                    goodsBean.setProduct_id(recommendInfoBean.getProduct_id());

                    Intent intent = new Intent(mContext, GoodsInfoActivity.class);
                    intent.putExtra(GOODS_BEAN, goodsBean);

                    mContext.startActivity(intent);
                }
            });
        }
    }

    public class HotViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.tv_more_hot)
        TextView tvMoreHot;
        @InjectView(R.id.gv_hot)
        MyGridView gvHot;
        private Context mContext;
        private HotViewAdapter adapter;

        public HotViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            ButterKnife.inject(this, itemView);
        }

        public void setData(List<HomeBean.ResultBean.HotInfoBean> hot_info) {
            adapter = new HotViewAdapter(mContext, hot_info);
            gvHot.setAdapter(adapter);

            gvHot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    Toast.makeText(mContext, "点击监听" + position, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}

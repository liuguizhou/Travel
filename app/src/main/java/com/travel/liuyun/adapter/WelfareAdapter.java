package com.travel.liuyun.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.travel.liuyun.R;
import com.travel.liuyun.activity.scene.ImageActivity;
import com.travel.liuyun.bean.GankRootBean;
import com.travel.liuyun.utils.BitmapCache;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liuguizhou on 2017/4/19.
 */

public class WelfareAdapter extends RecyclerView.Adapter<WelfareAdapter.ImageHolder> {
    private Context context;
    private List<GankRootBean.ResultsBean> beanList;

    public WelfareAdapter(Context context) {
        this.context = context;
    }

    public void setBeanList(List<GankRootBean.ResultsBean> beanList) {
        this.beanList = beanList;
    }

    @Override
    public ImageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_network__image, parent, false);
        return new ImageHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageHolder holder, int position) {
        GankRootBean.ResultsBean bean = beanList.get(position);
        holder.itemImage.setDefaultImageResId(R.mipmap.ic_launcher);
        ImageLoader imageLoader = new ImageLoader(((ImageActivity) context).getRequestQueue(), BitmapCache.instance());
        holder.itemImage.setImageUrl(bean.getUrl(), imageLoader);


    }

    @Override
    public int getItemCount() {
        return beanList == null ? 0 : beanList.size();
    }

    class ImageHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_image)
        NetworkImageView itemImage;

        public ImageHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}

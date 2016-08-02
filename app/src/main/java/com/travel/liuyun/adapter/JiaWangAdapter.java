package com.travel.liuyun.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.travel.liuyun.R;

/**
 * Created by liuguizhou on 2016/5/4.
 */
public class JiaWangAdapter extends RecyclerView.Adapter<JiaWangAdapter.ViewHolder> {

    private Context ctx;

    private int[] bgs = { R.mipmap.recycler_item3,R.mipmap.recycler_item1, R.mipmap.recycler_item2, R.mipmap.recycler_item4};

    private int images [] = {R.mipmap.icon,R.mipmap.icon,R.mipmap.icon,R.mipmap.icon};
    private String str[] = {"贾汪概况", "历史名人", "美图", "视频"};

    public JiaWangAdapter(Context context) {
        ctx = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.recycle_item_impress, parent, false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(JiaWangAdapter.ViewHolder holder, int position) {
        holder.name.setText(str[position]);
        holder.image.setImageResource(images[position]);
        holder.background.setImageResource(bgs[position]);
    }

    @Override
    public int getItemCount() {
        return images.length == 0 ? 0 : images.length;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private RoundedImageView image;
        private ImageView background;

        public ViewHolder(View itemView) {
            super(itemView);
            background = (ImageView) itemView.findViewById(R.id.recyclerview_item_image);
            image = (RoundedImageView) itemView.findViewById(R.id.item_image);
            name = (TextView) itemView.findViewById(R.id.name);
        }
    }

}

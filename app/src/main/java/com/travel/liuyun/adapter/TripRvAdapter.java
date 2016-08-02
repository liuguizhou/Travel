package com.travel.liuyun.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.travel.liuyun.R;

/**
 * Created by liuguizhou on 2016/5/2.
 */
public class TripRvAdapter extends RecyclerView.Adapter<TripRvAdapter.ViewHolder> {

    private Context ctx;

    private int[] image = {R.mipmap.recycler_item3,R.mipmap.recycler_item1, R.mipmap.recycler_item2, R.mipmap.recycler_item4};

    private String str[] = {"贾汪庄一日游", "贾汪庄一日游", "贾汪庄一日游", "贾汪庄一日游"};

    private String tag1[] = {"二日游", "一日游", "二日游", "一日游"};

    private String tag2[] = {"休闲", "休闲", "休闲", "休闲"};

    public TripRvAdapter(Context context) {
        ctx = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.trip_recycler_item, parent, false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(TripRvAdapter.ViewHolder holder, int position) {
        holder.sceneName.setText(str[position]);
        holder.imageView.setImageResource(image[position]);
        holder.tag1.setText(tag1[position]);
        holder.tag2.setText(tag2[position]);
    }

    @Override
    public int getItemCount() {
        return image.length==0?0:image.length;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tag1;
        private TextView tag2;
        private TextView sceneName;
        private ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.trip_recyclerview_item_image);
            sceneName = (TextView) itemView.findViewById(R.id.scene_name);
            tag1 = (TextView) itemView.findViewById(R.id.tag1);
            tag2 = (TextView) itemView.findViewById(R.id.tag2);
        }
    }

}

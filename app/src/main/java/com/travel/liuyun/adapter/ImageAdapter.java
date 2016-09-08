package com.travel.liuyun.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.facebook.drawee.view.SimpleDraweeView;
import com.travel.liuyun.R;

import java.io.File;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import droidninja.filepicker.utils.image.FrescoFactory;

/**
 * Created by droidNinja on 29/07/16.
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.FileViewHolder> {

    private ArrayList<String> paths;
    private int imageSize;
    private Context ctx;

    public ImageAdapter(Context context, ArrayList<String> paths) {
        this.ctx = context;
        this.paths = paths;
        setColumnNumber(context,3);
    }

    public void addData(ArrayList<String> path) {
        paths = path;
        notifyDataSetChanged();
        Log.e("lgz", "addData: ");
    }

    private void setColumnNumber(Context context, int columnNum) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        int widthPixels = metrics.widthPixels;
        imageSize = widthPixels / columnNum;
    }

    @Override
    public FileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);

        return new FileViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FileViewHolder holder, int position) {
        String path = paths.get(position);
        Log.e("lgz", "paths: "+paths.size() );
        FrescoFactory.getLoader().showImage(holder.imageView, Uri.fromFile(new File(path)), FrescoFactory.newOption(imageSize,imageSize));
//        Glide.with(ctx).load(new File(path)).placeholder(R.mipmap.image_placeholder).crossFade().into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return paths == null ? 0 : paths.size();
    }

    public static class FileViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.iv_photo)
        SimpleDraweeView imageView;

        public FileViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

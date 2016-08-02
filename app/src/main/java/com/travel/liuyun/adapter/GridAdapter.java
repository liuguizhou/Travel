package com.travel.liuyun.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.travel.liuyun.R;
import com.travel.liuyun.utils.ViewFindUtils;

/**
 * Created by liuguizhou on 2016/5/1.
 */
public class GridAdapter extends BaseAdapter {

    private int[] images;

    private String[] str;

    private Context ctx;

    public GridAdapter(Context ctx, String[] str, int[] images) {
        this.ctx = ctx;
        this.str = str;
        this.images = images;
    }

    @Override
    public int getCount() {
        return images.length != 0 ? images.length : 0;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(ctx).inflate(R.layout.item_text_image, null);
            viewHolder.imageView = ViewFindUtils.find(convertView, R.id.item_image);
            viewHolder.text = ViewFindUtils.find(convertView, R.id.item_text);
            convertView.setTag(viewHolder);
        }else {
           viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.imageView.setImageResource(images[position]);
        viewHolder.text.setText(str[position]);

        return convertView;
    }


    class ViewHolder {
        ImageView imageView;
        TextView text;
    }
}

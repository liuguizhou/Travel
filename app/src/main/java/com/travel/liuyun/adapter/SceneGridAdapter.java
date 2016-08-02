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
public class SceneGridAdapter extends BaseAdapter {

    private int[] images = {R.mipmap.grid_item1,R.mipmap.grid_item2,R.mipmap.grid_item3,R.mipmap.grid_item4};

    private String[] str = {"大明湖风景区","西湖风景区","雷恩寺","八里河景区"};

    private Context ctx;

    public SceneGridAdapter(Context ctx) {
        this.ctx = ctx;
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
            convertView = LayoutInflater.from(ctx).inflate(R.layout.item_grid_image, null);
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

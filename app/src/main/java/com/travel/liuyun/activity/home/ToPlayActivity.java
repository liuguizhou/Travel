package com.travel.liuyun.activity.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.travel.liuyun.R;
import com.travel.liuyun.activity.BaseActivity;
import com.travel.liuyun.utils.ViewFindUtils;

/**
 * Created by liuguizhou on 2016/5/2.
 */
public class ToPlayActivity extends BaseActivity {

    private ImageView back;

    private GridView gridView;

    private int images[] = {R.mipmap.grid_item1, R.mipmap.grid_item2, R.mipmap.grid_item3,
            R.mipmap.grid_item4, R.mipmap.grid_item1, R.mipmap.grid_item2,
            R.mipmap.grid_item3, R.mipmap.grid_item4, R.mipmap.grid_item1, R.mipmap.grid_item2,};

    private void initListener() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.where_play_activity;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        gridView.setAdapter(new GridViewAdapter(this));
        initListener();
    }

    @Override
    protected void initView() {
        back = (ImageView) findViewById(R.id.image_back);
        gridView = (GridView) findViewById(R.id.gridView);
    }

    class GridViewAdapter extends BaseAdapter {

        private Context ctx;

        public GridViewAdapter(Context ctx) {
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
                convertView = LayoutInflater.from(ctx).inflate(R.layout.toplay_item_grid, null);
                viewHolder.imageView = ViewFindUtils.find(convertView, R.id.grid_item_image);
                viewHolder.text = ViewFindUtils.find(convertView, R.id.scene_name);
                viewHolder.map = ViewFindUtils.find(convertView, R.id.map_address);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.imageView.setImageResource(images[position]);
            viewHolder.text.setText("大洞山风景区");
            viewHolder.map.setText("江苏省徐州市贾汪区");

            return convertView;
        }


        class ViewHolder {
            ImageView imageView;
            TextView map;
            TextView text;
        }
    }
}

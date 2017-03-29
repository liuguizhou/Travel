package com.travel.liuyun.activity.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.travel.liuyun.R;
import com.travel.liuyun.activity.BaseActivity;
import com.travel.liuyun.utils.ViewFindUtils;

/**
 * Created by liuguizhou on 2016/5/2.
 */
public class ToLiveActivity extends BaseActivity {

    private TextView title;

    private ImageView back;

    private ListView listView;

    private int images[] = {R.mipmap.house_item1, R.mipmap.house_item2, R.mipmap.house_item3, R.mipmap.house_item4,
            R.mipmap.house_item1, R.mipmap.house_item2, R.mipmap.house_item3, R.mipmap.house_item4};

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
        return R.layout.where_live_activity;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        title.setText("住哪儿");
        listView.setAdapter(new ListViewAdapter(this));
        initListener();
    }

    @Override
    protected void initView() {
        title = (TextView) findViewById(R.id.title_text);
        back = (ImageView) findViewById(R.id.image_back);
        listView = (ListView) findViewById(R.id.listview);
    }

    class ListViewAdapter extends BaseAdapter {

        private Context ctx;

        public ListViewAdapter(Context ctx) {
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
                convertView = LayoutInflater.from(ctx).inflate(R.layout.tolive_item, null);
                viewHolder.imageView = ViewFindUtils.find(convertView, R.id.image);
                viewHolder.map = ViewFindUtils.find(convertView, R.id.map_address);
                viewHolder.hotel = ViewFindUtils.find(convertView, R.id.hotel);
                viewHolder.price = ViewFindUtils.find(convertView, R.id.price);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.imageView.setImageResource(images[position]);
            viewHolder.map.setText("江苏省徐州市贾汪区大洞山路122号");
            viewHolder.hotel.setText("贾汪大酒店");
            viewHolder.price.setText("￥120元起");

            return convertView;
        }


        class ViewHolder {
            ImageView imageView;
            TextView map;
            TextView hotel;
            TextView price;
        }
    }
}

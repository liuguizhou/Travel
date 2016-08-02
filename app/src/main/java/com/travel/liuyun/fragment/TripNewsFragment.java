package com.travel.liuyun.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.travel.liuyun.R;
import com.travel.liuyun.utils.ViewFindUtils;

/**
 * Created by liuguizhou on 2016/5/4.
 */
public class TripNewsFragment extends BaseFragment {

    private View rootView;

    private ListView listView;

    private int images[] = {R.mipmap.house_item1, R.mipmap.house_item2, R.mipmap.house_item3, R.mipmap.house_item4,
            R.mipmap.house_item1, R.mipmap.house_item2, R.mipmap.house_item3, R.mipmap.house_item4};

    public static TripNewsFragment getInstance() {
        TripNewsFragment fragment = new TripNewsFragment();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        listView = (ListView) view.findViewById(R.id.listview);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initData();
        initListener();

    }

    private void initData() {
        listView.setAdapter(new ListViewAdapter(getActivity()));
    }

    private void initListener() {

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
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
                convertView = LayoutInflater.from(ctx).inflate(R.layout.trip_list_item, null);
                viewHolder.imageView = ViewFindUtils.find(convertView, R.id.image);
                viewHolder.title = ViewFindUtils.find(convertView, R.id.title);
                viewHolder.content = ViewFindUtils.find(convertView, R.id.content);
                viewHolder.date = ViewFindUtils.find(convertView, R.id.date);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.imageView.setImageResource(images[position]);
            viewHolder.title.setText("贾汪全面开启全区域的旅游规划发展战略新时代");
            viewHolder.content.setText("贾汪全面开启全区域的旅游规划发展战略新时代");
            viewHolder.date.setText("2016/5/4");

            return convertView;
        }


        class ViewHolder {
            ImageView imageView;
            TextView title;
            TextView content;
            TextView date;
        }
    }
}

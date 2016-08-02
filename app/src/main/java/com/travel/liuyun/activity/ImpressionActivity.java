package com.travel.liuyun.activity;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.travel.liuyun.R;
import com.travel.liuyun.adapter.JiaWangAdapter;

/**
 * Created by liuguizhou on 2016/5/2.
 */
public class ImpressionActivity extends BaseActivity {

    private TextView title;

    private ImageView back;

    private ImageView search;

    private RecyclerView recyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.impress_jiawang_activity;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initView() {
        title = (TextView) findViewById(R.id.title_text);
        back = (ImageView) findViewById(R.id.image_back);
        search = (ImageView) findViewById(R.id.image_search);
        recyclerView = (RecyclerView) findViewById(R.id.impression_recyclerview);
    }


    private void initData() {
        title.setText("印象贾汪");
        search.setVisibility(View.INVISIBLE);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new JiaWangAdapter(this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        initListener();
    }

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
}

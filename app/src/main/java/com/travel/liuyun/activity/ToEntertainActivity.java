package com.travel.liuyun.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.travel.liuyun.R;
import com.travel.liuyun.adapter.SlideAdapter;
import com.travel.liuyun.bean.ItemBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuguizhou on 2016/5/2.
 */
public class ToEntertainActivity extends BaseActivity implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private SlideAdapter mSlideAdapter;
    private List<ItemBean> mItemBeans = new ArrayList<>();
    private TextView mRightTV;

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
        return R.layout.to_entertain_layout;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        initBean();
        mSlideAdapter = new SlideAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mSlideAdapter);
        mSlideAdapter.setItemBeans(mItemBeans);
    }

    @Override
    protected void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.rcv_root);
        mRightTV = (TextView) findViewById(R.id.right_tv);
        initListener();
    }

    private void initListener() {
        mRightTV.setOnClickListener(this);
    }

    private void initBean() {
        for (int x = 0; x < 30; x++) {
            mItemBeans.add(new ItemBean());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.right_tv:
                editItems();
                break;
        }
    }

    private void editItems() {
        if ("编辑".equals(mRightTV.getText().toString())) {
            mRightTV.setText("取消");
            mSlideAdapter.openItemAnimation();
        } else if ("取消".equals(mRightTV.getText().toString())) {
            mRightTV.setText("编辑");
            mSlideAdapter.closeItemAnimation();
        }
    }
}

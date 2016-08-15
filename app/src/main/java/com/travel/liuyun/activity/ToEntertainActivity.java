package com.travel.liuyun.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.travel.liuyun.R;
import com.travel.liuyun.adapter.SlideAdapter;
import com.travel.liuyun.bean.ItemBean;
import com.travel.liuyun.loading.EndlessRecyclerOnScrollListener;
import com.travel.liuyun.loading.LoadingFooter;

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
    private View view;
    private int lastPosition = 0;
    private int size = 8;
    private LoadingFooter mLoadingFooter;

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
        mRecyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mSlideAdapter = new SlideAdapter();
        mRecyclerView.setAdapter(mSlideAdapter);
        mRecyclerView.addOnScrollListener(mOnScrollListener);
        if (mLoadingFooter == null) {
            mLoadingFooter = new LoadingFooter(this);
            mSlideAdapter.setFooterView(mLoadingFooter);
        }
        getDataTask();
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
        for (int x = 0; x < 40; x++) {
            mItemBeans.add(new ItemBean("音乐"+x));
        }
    }

    private EndlessRecyclerOnScrollListener mOnScrollListener = new EndlessRecyclerOnScrollListener(2) {
        @Override
        public void onLoadMore(View view) {
            super.onLoadMore(view);
            getDataTask(false);
        }
    };

    public void getDataTask() {
        getDataTask(true);
    }

    public void getDataTask(final boolean isClear) {
        if (mLoadingFooter.getState() == LoadingFooter.State.Loading || (!isClear && mLoadingFooter.getState() == LoadingFooter.State.TheEnd)) {
            return;
        }
        mLoadingFooter.setState(LoadingFooter.State.Loading);

        mRecyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSlideAdapter.setFooterView(mLoadingFooter);
                if (isClear) {
                    mSlideAdapter.clear();
                }
                List<ItemBean> result = getData();
                if (result == null || result.size() < size) {
                    mLoadingFooter.setState(LoadingFooter.State.TheEnd, false);
                    mSlideAdapter.removeFooterView();
                } else {
                    mLoadingFooter.setState(LoadingFooter.State.Normal);
                }
                if (result != null && result.size() > 0) {
                    mSlideAdapter.addAll(result);
                    lastPosition = lastPosition + result.size();
                }
            }
        }, 2000);
    }

    public List<ItemBean> getData() {
        try {
            int end = (lastPosition + size) > mItemBeans.size() ? mItemBeans.size() : (lastPosition + size);
            return mItemBeans.subList(lastPosition, end);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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

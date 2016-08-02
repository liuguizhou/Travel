package com.travel.liuyun.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.travel.liuyun.R;
import com.travel.liuyun.adapter.TripRvAdapter;
import com.travel.liuyun.utils.ViewFindUtils;

/**
 * Created by liuguizhou on 2016/5/1.
 */
public class EnjoyFragment extends BaseFragment {

    private View rootView;

    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        recyclerView = ViewFindUtils.find(view, R.id.trip_recyclerview);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.enjoy_fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initData();
        initListener();

    }

    private void initData() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new TripRvAdapter(getActivity()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
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
}

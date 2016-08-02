package com.travel.liuyun.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.travel.liuyun.R;

/**
 * Created by liuguizhou on 2016/5/1.
 */
public class SceneFragment extends BaseFragment {
    private View rootView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.scene_fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initData();
        initListener();

    }

    private void initData() {

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

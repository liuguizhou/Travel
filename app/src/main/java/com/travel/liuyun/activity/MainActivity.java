package com.travel.liuyun.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.travel.liuyun.R;
import com.travel.liuyun.callback.OnActivityResultPickListener;
import com.travel.liuyun.fragment.EnjoyFragment;
import com.travel.liuyun.fragment.HomeFragment;
import com.travel.liuyun.fragment.PersonalFragment;
import com.travel.liuyun.fragment.SceneFragment;

public class MainActivity extends BaseActivity {

    private LayoutInflater layoutInflater;

    private FragmentTabHost mTabHost;

    private OnActivityResultPickListener listener;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initView() {
        layoutInflater = LayoutInflater.from(mContext);
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.main_content);
        mTabHost.getTabWidget().setDividerDrawable(null);
        TabHost.TabSpec tabSpec =
                mTabHost.newTabSpec(getString(R.string.main_page))
                        .setIndicator(getTabItemView(R.drawable.main_page_selector, R.string.main_page));
        mTabHost.addTab(tabSpec, HomeFragment.class, null);
        tabSpec =
                mTabHost.newTabSpec(getString(R.string.scene_travel))
                        .setIndicator(getTabItemView(R.drawable.all_scene_selector, R.string.scene_travel));
        mTabHost.addTab(tabSpec, SceneFragment.class, null);

        tabSpec =
                mTabHost.newTabSpec(getString(R.string.suggest_path))
                        .setIndicator(getTabItemView(R.drawable.enjoy_trip_selector, R.string.suggest_path));
        mTabHost.addTab(tabSpec, EnjoyFragment.class, null);
        tabSpec =
                mTabHost.newTabSpec(getString(R.string.personal))
                        .setIndicator(getTabItemView(R.drawable.personal_selector, R.string.personal));
        mTabHost.addTab(tabSpec, PersonalFragment.class, null);
    }


    /**
     * 给Tab按钮设置图标和文字
     */
    private View getTabItemView(int imgResId, int textId) {
        View view = layoutInflater.inflate(R.layout.tab_item, null);

        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        imageView.setImageResource(imgResId);

        TextView textView = (TextView) view.findViewById(R.id.textview);
        textView.setText(textId);
        return view;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("lgz", "MainActivity,onStop: ");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("lgz", "MainActivity,onDestroy: ");
    }
}

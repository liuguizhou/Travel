package com.travel.liuyun.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.content.ContextCompat;
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
import com.travel.liuyun.utils.AppInfo;

public class MainActivity extends BaseActivity {

    private LayoutInflater layoutInflater;

    private FragmentTabHost mTabHost;

    private OnActivityResultPickListener listener;

    private static final int READ_PHONE_STATE_CODE = 123;


    @Override
    protected int getLayoutId() {
        requestPhoneStatePermission();
        return R.layout.activity_main;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    private void requestPhoneStatePermission() {
//        //权限判断
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE},
                    READ_PHONE_STATE_CODE);
        } else {
            SceneFragment.DEVICEID = AppInfo.getDeviceUUID();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == READ_PHONE_STATE_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
                SceneFragment.DEVICEID = AppInfo.getDeviceUUID();
            } else {
                SceneFragment.DEVICEID = AppInfo.getAndroidID();
            }
        }
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

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
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

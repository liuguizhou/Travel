package com.travel.liuyun.activity.person;

import android.os.Bundle;
import android.view.View;

import com.travel.liuyun.R;
import com.travel.liuyun.activity.BaseActivity;
import com.travel.liuyun.utils.TipUtils;
import com.travel.liuyun.widget.CustomTitleBar;

import butterknife.BindView;

/**
 * Created by liuguizhou on 2017/3/29.
 */

public class CustomViewActivity extends BaseActivity {
    @BindView(R.id.title_bar)
    CustomTitleBar titleBar;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_custom_view;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        titleBar.setTitleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.title_bar_left:
                        onBackPressed();
                        break;
                    case R.id.title_bar_right:
                        TipUtils.showToast(CustomViewActivity.this,"我是右侧按钮");
                        break;
                }

            }
        });
    }

    @Override
    protected void initView() {

    }

}

package com.travel.liuyun.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.travel.liuyun.R;

/**
 * Created by liuguizhou on 2016/12/5.
 */

public class DialogActivity extends Activity implements View.OnClickListener {

    private Button but_ok;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        initView();
    }

    private void initView() {
        but_ok = (Button) findViewById(R.id.but_ok);

        but_ok.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_ok:
                this.finish();
                break;
        }
    }
}

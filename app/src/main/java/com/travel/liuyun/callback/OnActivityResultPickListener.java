package com.travel.liuyun.callback;

import android.content.Intent;

/**
 * Created by liuguizhou on 2016/9/6.
 */

public interface OnActivityResultPickListener {

    void onActivityResultPicker(int requestCode, int resultCode, Intent data);
}

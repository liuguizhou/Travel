package com.travel.liuyun.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by liuguizhou on 2016/7/26.
 */
public class TipUtils {

    private static Toast toast;

    public static void showToast(Context context, String content) {
        if (toast == null) { //这样处理，不管我们触发多少次Toast调用，都只会持续一次Toast显示的时长
            toast = Toast.makeText(context,
                    content,
                    Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }
}

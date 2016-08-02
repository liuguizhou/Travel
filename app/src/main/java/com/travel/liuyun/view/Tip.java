
package com.travel.liuyun.view;

import android.content.Context;
import android.os.Build;

public class Tip {
    private static MyToast tipToast;
    private static LoadingDialog dialog;

    public static void showTip(Context mContext, String msg) {
        showTip(mContext, msg, -1);
    }

    public static void showTip(Context mContext, int resId) {
        showTip(mContext, mContext.getString(resId), -1);
    }

    public static void showTip(Context mContext, int resId, int iconResId) {
        showTip(mContext, mContext.getString(resId), iconResId);
    }

    public static void showTip(Context mContext, String msg, int iconResId) {
        if (tipToast != null) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                tipToast.cancel();
            }
        } else {
            tipToast = MyToast.makeText(mContext, msg, MyToast.LENGTH_SHORT);
        }
        tipToast.setIcon(iconResId);
        tipToast.setText(msg);
        tipToast.show();
    }

    public static void showLoading(Context mContext, int resId) {
        showLoading(mContext, mContext.getString(resId));
    }

    public static void showLoading(Context mContext, String msg) {
        dialog = new LoadingDialog(mContext, msg);
        dialog.show();
    }

    public static void hideLoading() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            dialog = null;
        }
    }
}

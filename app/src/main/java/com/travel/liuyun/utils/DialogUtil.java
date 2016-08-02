package com.travel.liuyun.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.travel.liuyun.R;
import com.travel.liuyun.adapter.DialogMoreButtonsAdapter;
import com.travel.liuyun.bean.Callback;
import com.travel.liuyun.bean.DialogButtonItem;

import java.util.ArrayList;

/**
 * Created by tan_ on 14-10-14.
 */
public class DialogUtil {
    public static Dialog dialogMoreButtons;

    public static void dialogMoreButtons(final Activity activity, ArrayList<DialogButtonItem> items, final Callback<DialogButtonItem> callback) {
        dialogMoreButtons(activity, null, items, callback, null);
    }

    public static void dialogMoreButtons(final Activity activity, ArrayList<DialogButtonItem> items, final Callback<DialogButtonItem> callback, DialogInterface.OnCancelListener cancelListener) {
        dialogMoreButtons(activity, null, items, callback, cancelListener);
    }

    public static void dialogMoreButtons(final Activity activity, String title, ArrayList<DialogButtonItem> items, final Callback<DialogButtonItem> callback) {
        dialogMoreButtons(activity, title, items, callback, null);
    }

    public static void dialogMoreButtons(final Activity activity, String title, ArrayList<DialogButtonItem> items, final Callback<DialogButtonItem> callback, DialogInterface.OnCancelListener cancelListener) {
        dialogMoreButtons = generateDialog(activity, R.layout.dialog_more_buttons, cancelListener);
        transparentDialog(dialogMoreButtons, 1.0f, true);
        dialogMoreButtons.setCanceledOnTouchOutside(true);
        dialogMoreButtons.show();
        TextView titleView = (TextView) dialogMoreButtons.findViewById(R.id.dialog_button_title);
        ListView listView = (ListView) dialogMoreButtons.findViewById(R.id.dialog_button_list);
        if (TextUtils.isEmpty(title)) titleView.setVisibility(View.GONE);
        else {
            titleView.setText(title);
            titleView.setVisibility(View.VISIBLE);
        }
        final DialogMoreButtonsAdapter buttonsAdapter = new DialogMoreButtonsAdapter(activity, items);
        listView.setAdapter(buttonsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DialogButtonItem item = (DialogButtonItem) buttonsAdapter.getItem(i);
                callback.call(item);
                if (null != dialogMoreButtons) dialogMoreButtons.dismiss();
            }
        });
    }

    public static void transparentDialog(Dialog dialog, float scale) {
        transparentDialog(dialog, scale, false);
    }

    public static void transparentDialog(Dialog dialog, float scale, boolean bottom) {
        dialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setWindowAnimations(R.style.dialogWindowAnim);
        WindowManager w = dialog.getWindow().getWindowManager();
        Display d = w.getDefaultDisplay();
        WindowManager.LayoutParams p = dialog.getWindow().getAttributes();
        if (bottom) dialogWindow.setGravity(Gravity.BOTTOM);
        p.width = (int) (d.getWidth() * scale);

        dialog.getWindow().setAttributes(p);
    }

    public static Dialog generateDialog(Activity activity, int layout, DialogInterface.OnCancelListener cancelListener) {
        Dialog dialog = new Dialog(activity);
        dialog.setContentView(layout);
        dialog.setCancelable(false);
        if (null != cancelListener) {
            dialog.setOnCancelListener(cancelListener);
        }
        return dialog;
    }
}

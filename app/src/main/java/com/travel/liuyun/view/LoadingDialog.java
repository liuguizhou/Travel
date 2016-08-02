
package com.travel.liuyun.view;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.travel.liuyun.R;


/**
 * 加载中Dialog
 * 
 * @author xm
 */
public class LoadingDialog extends AlertDialog {
    private TextView loadingMsg;
    private String message;

    public LoadingDialog(Context context, String message) {
        super(context);
        this.message = message;
        this.setCancelable(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.view_loading);
        loadingMsg = (TextView) findViewById(R.id.tips_loading_msg);
        loadingMsg.setText(this.message);
    }

    public void setText(String message) {
        this.message = message;
        loadingMsg.setText(this.message);
    }

    public void setText(int resId) {
        setText(getContext().getResources().getString(resId));
    }
}

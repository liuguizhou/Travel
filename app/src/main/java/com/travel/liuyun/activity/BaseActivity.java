package com.travel.liuyun.activity;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by liuguizhou on 2016/5/2.
 */
public abstract class BaseActivity extends AppCompatActivity {

    public Activity mContext;

    public Typeface typeface;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
       /* if (typeface == null) {
            typeface = Typeface.createFromAsset(getAssets(), "fonts/custom.ttf");
        }
        LayoutInflaterCompat.setFactory(LayoutInflater.from(this), new LayoutInflaterFactory() {
            @Override
            public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
                AppCompatDelegate delegate = getDelegate();
                View view = delegate.createView(parent, name, context, attrs);

                if (view != null && (view instanceof TextView)) {
                    ((TextView) view).setTypeface(typeface);
                    Log.e("lgz", "onCreateView: ");
                }
                return view;
            }
        });*/
        setContentView(getLayoutId());
        onPreInitView();
        initView();
        initData(savedInstanceState);
    }

    protected void onPreInitView() {
    }

    protected abstract int getLayoutId();

    protected abstract void initData(Bundle savedInstanceState);

    protected abstract void initView();

}

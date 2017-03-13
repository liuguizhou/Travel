package com.travel.liuyun.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.travel.liuyun.R;
import com.travel.liuyun.bean.Banner;
import com.travel.liuyun.okhttp.LoadCallBack;
import com.travel.liuyun.okhttp.OkHttpManager;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by liuguizhou on 2016/5/2.
 */
public class ImpressionActivity extends BaseActivity implements View.OnClickListener {

    private TextView title;

    private ImageView back;

    private ImageView search;

    private RecyclerView recyclerView;

    private SliderLayout mSliderLayout;

    private List<Banner> mBanner;

    private Button btn_dialog;

    private Dialog mCameraDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.impress_jiawang_activity;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        initListener();
        requestAdData();
    }

    @Override
    protected void initView() {
        title = (TextView) findViewById(R.id.title_text);
        back = (ImageView) findViewById(R.id.image_back);
        mSliderLayout = (SliderLayout) findViewById(R.id.slider);
        search = (ImageView) findViewById(R.id.image_search);
        btn_dialog = (Button) findViewById(R.id.btn_dialog);
        btn_dialog.setOnClickListener(this);
    }

    private void requestAdData() {

        final String url = "http://112.124.22.238:8081/course_api/banner/query?type=1";

        OkHttpManager.getInstance().getRequest(url, new LoadCallBack<List<Banner>>(this) {

            @Override
            protected void onSuccess(Call call, Response response, List<Banner> banners) {
                mBanner = banners;
                initSlider();
            }

            @Override
            protected void onEror(Call call, int statusCode, Exception e) {

            }
        });
    }

    private void initSlider() {
        if (mBanner != null) {
            for (Banner banner : mBanner) {
                TextSliderView textSliderView = new TextSliderView(this);
                textSliderView.image(banner.getImgUrl());
                textSliderView.description(banner.getName());
                textSliderView.setScaleType(BaseSliderView.ScaleType.Fit);
                mSliderLayout.addSlider(textSliderView);
            }
        }
        mSliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mSliderLayout.setCustomAnimation(new DescriptionAnimation());
        mSliderLayout.setPresetTransformer(SliderLayout.Transformer.RotateUp);
        mSliderLayout.setDuration(3000);


    }


//    private void initData() {
//        title.setText("印象贾汪");
//        search.setVisibility(View.INVISIBLE);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setAdapter(new JiaWangAdapter(this));
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        initListener();
//    }

    private void initListener() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSliderLayout.stopAutoCycle();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_dialog:
                showDialog();
                break;
            case R.id.btn_cancel:
                mCameraDialog.dismiss();
                break;
            default:
                break;
        }
    }

    private void showDialog() {
        mCameraDialog = new Dialog(this, R.style.my_dialog);
        LinearLayout root = (LinearLayout) LayoutInflater.from(this).inflate(
                R.layout.layout_camera_control, null);
        root.findViewById(R.id.btn_open_camera).setOnClickListener(this);
        root.findViewById(R.id.btn_choose_img).setOnClickListener(this);
        root.findViewById(R.id.btn_cancel).setOnClickListener(this);
        mCameraDialog.setContentView(root);
        Window dialogWindow = mCameraDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
//        dialogWindow.setWindowAnimations(R.style.dialogstyle); // 添加动画
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = -20; // 新位置Y坐标
        lp.width = (int) getResources().getDisplayMetrics().widthPixels; // 宽度
//      lp.height = WindowManager.LayoutParams.WRAP_CONTENT; // 高度
//      lp.alpha = 9f; // 透明度
        root.measure(0, 0);
        lp.height = root.getMeasuredHeight();
        lp.alpha = 9f; // 透明度
        dialogWindow.setAttributes(lp);
        mCameraDialog.show();
    }
}

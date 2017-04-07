package com.travel.liuyun.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.travel.liuyun.R;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Eric on 2017/1/21.
 */

public class SplashActivity extends AppCompatActivity {

    private static final int ANIMATION_DURATION = 2000;
    private static final float SCALE_END = 1.13F;

    private static final int[] SPLASH_ARRAY = {
          R.drawable.splash_one ,R.drawable.splash_two, R.drawable.splash_three,R.drawable.splash_four,R.drawable.splash_five,
    };
    @BindView(R.id.iv_splash)
    ImageView ivSplash;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        Random r = new Random(SystemClock.elapsedRealtime());
        ivSplash.setImageResource(SPLASH_ARRAY[r.nextInt(SPLASH_ARRAY.length)]);
        animateImage();
//        finishActivity();
    }

    private void animateImage() {
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(ivSplash, "scaleX", 1f, SCALE_END);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(ivSplash, "scaleY", 1f, SCALE_END);

        AnimatorSet set = new AnimatorSet();

        set.setDuration(ANIMATION_DURATION).play(animatorX).with(animatorY);
        set.start();

        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                MainActivity.startActivity(SplashActivity.this);
                SplashActivity.this.finish();
            }
        });
    }

    private void finishActivity() {
        Observable.timer(2000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                        overridePendingTransition(0, android.R.anim.fade_out);
                        finish();
                    }
                });
    }
}

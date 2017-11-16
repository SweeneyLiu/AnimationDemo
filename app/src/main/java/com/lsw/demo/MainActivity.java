package com.lsw.demo;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Button mButton;
    int width = 0;
    ValueAnimator valueAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = (Button)findViewById(R.id.button);
        mButton.post(new Runnable() {
            @Override
            public void run() {
//                width = mButton.getWidth();
                width = mButton.getMeasuredWidth();
                Log.i(TAG, "onCreate1: "+width);
                setAnimation(width);
            }
        });


    }

    private void setAnimation(int width){
        valueAnimator = ValueAnimator.ofInt(width,width*2);
        valueAnimator.setDuration(2000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                int currentValue = (Integer) animator.getAnimatedValue();
                mButton.getLayoutParams().width = currentValue;
                mButton.requestLayout();
            }
        });
        valueAnimator.start();
    }

}

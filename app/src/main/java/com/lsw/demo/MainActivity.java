package com.lsw.demo;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "lsw";
    private Button mButton;
    float widthF;
    int width = 0;
    ValueAnimator valueAnimator;
    ObjectAnimator mObjectAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = (Button)findViewById(R.id.button);
/*        Log.i(TAG, "onCreate: "+mButton.getLayoutParams().width);
        mButton.post(new Runnable() {
            @Override
            public void run() {
//                width = mButton.getWidth();
//                width = mButton.getMeasuredWidth();
//                Log.i(TAG, "onCreate1: "+width);
//                setAnimation(width);

                widthF = mButton.getMeasuredWidth();
                Log.i(TAG, "onCreate1: "+widthF);
                setAnimation(widthF);
            }
        });*/

//        setXMLAnimation();

        setObjectAnimation();

//        setXMLObjectAnimation();


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
                Log.i(TAG, "onAnimationUpdate: int："+currentValue);
            }
        });
        valueAnimator.start();
    }

    private void setAnimation(float width){
        valueAnimator = ValueAnimator.ofFloat(width,width*2);
        valueAnimator.setDuration(2000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                float currentValue = (Float) animator.getAnimatedValue();
                mButton.getLayoutParams().width = (int)currentValue;
                mButton.requestLayout();
                Log.i(TAG, "onAnimationUpdate: float："+currentValue);
            }
        });
        valueAnimator.start();
    }

    private void setXMLAnimation(){
        final ValueAnimator animator = (ValueAnimator) AnimatorInflater.loadAnimator(this,R.animator.set_animation);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentValue = (float) animator.getAnimatedValue();
                mButton.getLayoutParams().width = (int)currentValue;
                mButton.requestLayout();
                Log.i(TAG, "onAnimationUpdate: xml："+currentValue);
            }
        });
        animator.setTarget(mButton);
        animator.start();
    }


    private void setObjectAnimation(){
/*        mObjectAnimator = ObjectAnimator.ofFloat(mButton,"scaleX",1f,2f);
        mObjectAnimator.setDuration(3000);
        mObjectAnimator.setStartDelay(500);
        mObjectAnimator.setRepeatCount(1);
        mObjectAnimator.setRepeatMode(ValueAnimator.REVERSE);
        mObjectAnimator.start();*/

        ObjectAnimator animator = ObjectAnimator.ofFloat(mButton, "alpha", 1f, 0f, 1f);
        animator.setDuration(5000);
        animator.start();

    }


    private void setXMLObjectAnimation() {
        Animator animator = AnimatorInflater.loadAnimator(this, R.animator.set_animation2);
        animator.setTarget(mButton);
        animator.start();
    }

}

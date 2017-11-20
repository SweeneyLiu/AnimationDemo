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
    private ColorChangeView mColorChangeView;
    float widthF;
    int width = 0;
    ValueAnimator valueAnimator;
    ObjectAnimator mObjectAnimator;
    private ViewWrapper wrapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = (Button)findViewById(R.id.button);
        mColorChangeView = (ColorChangeView)findViewById(R.id.colorView);
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

//        setObjectAnimation();

//        setXMLObjectAnimation();

//        setColorViewAnimation();

        // 创建包装类,并传入动画作用的对象
        wrapper = new ViewWrapper(mButton);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator.ofInt(wrapper, "width", 500).setDuration(3000).start();
                // 设置动画的对象是包装类的对象
            }
        });

    }

    private void setColorViewAnimation() {
        ObjectAnimator anim = ObjectAnimator.ofObject(mColorChangeView, "color", new ColorEvaluator(), "#0000FF", "#00FF00");
        // 设置自定义View对象、背景颜色属性值 & 颜色估值器
        // 本质逻辑：
        // 步骤1：根据颜色估值器不断 改变 值
        // 步骤2：调用set（）设置背景颜色的属性值（实际上是通过画笔进行颜色设置）
        // 步骤3：调用invalidate()刷新视图，即调用onDraw（）重新绘制，从而实现动画效果

        anim.setDuration(8000);
        anim.start();
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
        //缩放
/*        mObjectAnimator = ObjectAnimator.ofFloat(mButton,"scaleX",1f,2f);
        mObjectAnimator.setDuration(3000);
        mObjectAnimator.setStartDelay(500);
        mObjectAnimator.setRepeatCount(1);
        mObjectAnimator.setRepeatMode(ValueAnimator.REVERSE);
        mObjectAnimator.start();*/

        //透明度
        /*ObjectAnimator animator = ObjectAnimator.ofFloat(mButton, "alpha", 1f, 0f, 1f);
        animator.setDuration(5000);
        animator.start();*/

        //旋转
        /*ObjectAnimator animator = ObjectAnimator.ofFloat(mButton, "rotation", 0f, 180f);
        animator.setRepeatCount(1);
        animator.setDuration(5000);
        animator.start();*/

        //平移
        float curTranslationX = mButton.getTranslationX();
        ObjectAnimator animator = ObjectAnimator.ofFloat(mButton, "translationX", curTranslationX, 300, curTranslationX);
        animator.setDuration(5000);
        animator.start();

    }


    private void setXMLObjectAnimation() {
        Animator animator = AnimatorInflater.loadAnimator(this, R.animator.set_animation2);
        animator.setTarget(mButton);
        animator.start();
    }


    // 提供ViewWrapper类,用于包装View对象
    // 本例:包装Button对象
    private static class ViewWrapper {
        private View mTarget;

        // 构造方法:传入需要包装的对象
        public ViewWrapper(View target) {
            mTarget = target;
        }

        // 为宽度设置get（） & set（）
        public int getWidth() {
            return mTarget.getLayoutParams().width;
        }

        public void setWidth(int width) {
            mTarget.getLayoutParams().width = width;
            mTarget.requestLayout();
        }

    }

}

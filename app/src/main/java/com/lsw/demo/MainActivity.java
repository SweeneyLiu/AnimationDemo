package com.lsw.demo;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.BounceInterpolator;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "lsw";
    private Button mButton;
    private Button button2;
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
        button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });
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
        /*wrapper = new ViewWrapper(mButton);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator.ofInt(wrapper, "width", 500).setDuration(3000).start();
                // 设置动画的对象是包装类的对象
            }
        });*/

//        setMultiAnimator();

//        setMultiXmlAnimator();

        setViewPropertyAnimator();

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

        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                //动画开始时执行
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                //动画重复时执行
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                //动画取消时执行
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                //动画结束时执行
            }
        });


        animator.addListener(new AnimatorListenerAdapter() {
            // 向addListener()方法中传入适配器对象AnimatorListenerAdapter()
            // 由于AnimatorListenerAdapter中已经实现好每个接口
            // 所以这里不实现全部方法也不会报错
            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                super.onAnimationRepeat(animation);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                // 如想只想监听动画开始时刻，就只需要单独重写该方法就可以
            }

            @Override
            public void onAnimationPause(Animator animation) {
                super.onAnimationPause(animation);
            }

            @Override
            public void onAnimationResume(Animator animation) {
                super.onAnimationResume(animation);
            }
        });

    }


    private void setXMLObjectAnimation() {
        Animator animator = AnimatorInflater.loadAnimator(this, R.animator.set_animation2);
        animator.setTarget(mButton);
        animator.start();
    }


    private void setMultiAnimator(){

        float curTranslationX = mButton.getTranslationX();
        // 步骤1：设置需要组合的动画效果
        ObjectAnimator translation = ObjectAnimator.ofFloat(mButton, "translationX", curTranslationX, 300,curTranslationX);
        // 平移动画
        ObjectAnimator rotate = ObjectAnimator.ofFloat(mButton, "rotation", 0f, 360f);
        // 旋转动画
        ObjectAnimator alpha = ObjectAnimator.ofFloat(mButton, "alpha", 1f, 0f, 1f);
        // 透明度动画

        // 步骤2：创建组合动画的对象
        AnimatorSet animSet = new AnimatorSet();

        // 步骤3：根据需求组合动画
        animSet.play(translation).with(rotate).before(alpha);
        animSet.setDuration(5000);

        // 步骤4：启动动画
        animSet.start();

    }

    private void setMultiXmlAnimator(){
        AnimatorSet animator = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.multi_set_animation);
        // 创建组合动画对象  &  加载XML动画
        animator.setTarget(mButton);
        // 设置动画作用对象
        animator.start();
        // 启动动画
    }


    private void setViewPropertyAnimator(){
        mButton.animate().alpha(0f);
        // 单个动画设置:将按钮变成透明状态
        mButton.animate().alpha(0f).setDuration(5000).setInterpolator(new BounceInterpolator());
        // 单个动画效果设置 & 参数设置
        mButton.animate().alpha(0f).x(500).y(500).alpha(1f);
        // 组合动画:将按钮变成透明状态再移动到(500,500)处
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

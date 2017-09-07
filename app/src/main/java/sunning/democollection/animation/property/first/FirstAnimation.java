package sunning.democollection.animation.property.first;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import sunning.democollection.BaseActivity;
import sunning.democollection.R;

/**
 * Created by sunning on 2017/5/18.
 */

public class FirstAnimation extends BaseActivity {
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.firsr_animation);

        ImageView iv = (ImageView) findViewById(R.id.btn_animation);
        Button btn = (Button) findViewById(R.id.btn_animation_button);
        btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                return false;
            }
        });
        iv.setOnClickListener(v -> {
//            ObjectAnimator animator = ObjectAnimator.ofFloat(iv, "haha", 0f, 200f);
//            animator.setDuration(5000);
//            animator.addUpdateListener(animation -> {
//                // animation.getAnimatedFraction 得到百分比
//                // animation.getAnimatedValue() 得到值
//                float value = (float) animation.getAnimatedValue();
//                iv.setAlpha(value);
//                iv.setTranslationX(value);
//                iv.setScaleX(value);
//            });
//            animator.start();

//            PropertyValuesHolder valuesHolder1 = PropertyValuesHolder.ofFloat("alpha", 1f, 0.5f,1f);
//            PropertyValuesHolder valuesHolder2 = PropertyValuesHolder.ofFloat("scaleX", 1f,0.5f, 1f);
//            PropertyValuesHolder valuesHolder3 = PropertyValuesHolder.ofFloat("translationX", 0f,200f);
//            ObjectAnimator.ofPropertyValuesHolder(iv, valuesHolder1, valuesHolder2,valuesHolder3).start();

            //4.差值器 直线抛物线效果
            /**
             * X 匀速
             * Y 加速
             * y = 1/2 *g * t*t
             */

            ValueAnimator valueAnimator = new ValueAnimator();
            valueAnimator.setDuration(4000);
            valueAnimator.setObjectValues(new PointF(0, 0));
            valueAnimator.setEvaluator(new TypeEvaluator<PointF>() {
                @Override
                public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
                    //拿到每一个时间点的坐标
                    PointF pointF = new PointF();
                    pointF.x = 100f * (fraction * 4);//初始速度*百分比  x = vt
//                    pointF.y = 0.5f * 9.8f * (fraction) * 4 * (fraction * 4);
                    pointF.y = 0.5f * 150f * (fraction) * 4 * (fraction * 4);
                    return pointF;
                }
            });
            valueAnimator.addUpdateListener(animation -> {
                PointF pointF = (PointF) animation.getAnimatedValue();
                //得到此时间点的坐标
                iv.setX(pointF.x);
                iv.setY(pointF.y);
            });
//            valueAnimator.setInterpolator(new AccelerateInterpolator(5));//加速器
//            valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());//先加速在减速
//            valueAnimator.setInterpolator(new AnticipateInterpolator(8));//荡秋千
            valueAnimator.setInterpolator(new OvershootInterpolator(8));//回弹效果
            valueAnimator.start();
        });
    }
}

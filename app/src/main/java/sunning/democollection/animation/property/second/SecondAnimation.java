package sunning.democollection.animation.property.second;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import sunning.democollection.BaseActivity;
import sunning.democollection.R;

/**
 * Created by sunning on 2017/5/18.
 */


public class SecondAnimation extends BaseActivity {

    private View first_View;
    private View second_View;
    private Button bt;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.second_layout);
        first_View = findViewById(R.id.first);
        second_View = findViewById(R.id.second);
        bt = (Button) findViewById(R.id.bt);
    }

    public void startFirstAnimation(View view) {
        viewAnimation(false);
    }

    public void startSecondAnimation(View view) {
        //secondView 平移动画
        viewAnimation(true);
    }

    private void viewAnimation(boolean isOpen) {
        if (!isOpen) {
            //FirstView  翻转,透明度, 缩放

            ObjectAnimator firstRotationAnim = ObjectAnimator.ofFloat(first_View, "rotationX", 0f, 25f);
            firstRotationAnim.setDuration(300);

            //2.透明度
            ObjectAnimator firstAlphaAnim = ObjectAnimator.ofFloat(first_View, "alpha", 1f, 0.5f);
            firstAlphaAnim.setDuration(200);

            //3.缩放动画
            ObjectAnimator firstScaleXAnim = ObjectAnimator.ofFloat(first_View, "scaleX", 1f, 0.8f);
            firstScaleXAnim.setDuration(300);
            ObjectAnimator firstScaleYAnim = ObjectAnimator.ofFloat(first_View, "scaleY", 1f, 0.8f);
            firstScaleYAnim.setDuration(300);

            //改正向旋转设置监听，执行完毕后再执行反向旋转
            ObjectAnimator firstResumeRotationAnim = ObjectAnimator.ofFloat(first_View, "rotationX", 25f, 0f);
            firstResumeRotationAnim.setDuration(200);
            firstResumeRotationAnim.setStartDelay(200);//延迟执行

            //由于缩放造成了离顶部有一段距离，需要平移上去
            ObjectAnimator firstTranslationAnim = ObjectAnimator.ofFloat(first_View, "translationY", 0f, -0.1f * first_View.getHeight());
            firstTranslationAnim.setDuration(200);

            ObjectAnimator secondTranslationAnim = ObjectAnimator.ofFloat(second_View, "translationY", second_View.getHeight(), 0f);
            firstTranslationAnim.setDuration(200);
            secondTranslationAnim.addUpdateListener(animation -> second_View.setVisibility(View.VISIBLE));


            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(firstRotationAnim, firstAlphaAnim, firstScaleXAnim, firstScaleYAnim, firstResumeRotationAnim, firstTranslationAnim, secondTranslationAnim);
            animatorSet.start();
        } else {
            //FirstView  翻转,透明度, 缩放
            ObjectAnimator firstRotationAnim = ObjectAnimator.ofFloat(first_View, "rotationX", 0,25f);
            firstRotationAnim.setDuration(300);

            //2.透明度
            ObjectAnimator firstAlphaAnim = ObjectAnimator.ofFloat(first_View, "alpha", 0.5f, 1);
            firstAlphaAnim.setDuration(200);

            //3.缩放动画
            ObjectAnimator firstScaleXAnim = ObjectAnimator.ofFloat(first_View, "scaleX", 0.8f, 1f);
            firstScaleXAnim.setDuration(300);
            ObjectAnimator firstScaleYAnim = ObjectAnimator.ofFloat(first_View, "scaleY", 0.8f, 1f);
            firstScaleYAnim.setDuration(300);

            //改正向旋转设置监听，执行完毕后再执行反向旋转
            ObjectAnimator firstResumeRotationAnim = ObjectAnimator.ofFloat(first_View, "rotationX", 25f, 0);
            firstResumeRotationAnim.setDuration(200);
            firstResumeRotationAnim.setStartDelay(200);//延迟执行

            //由于缩放造成了离顶部有一段距离，需要平移上去
            ObjectAnimator firstTranslationAnim = ObjectAnimator.ofFloat(first_View, "translationY", -0.1f * first_View.getHeight(), 0f);
            firstTranslationAnim.setDuration(200);

            ObjectAnimator secondTranslationAnim = ObjectAnimator.ofFloat(second_View, "translationY", 0f, second_View.getHeight());
            firstTranslationAnim.setDuration(200);
            secondTranslationAnim.addUpdateListener(animation -> second_View.setVisibility(View.GONE));


            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(firstRotationAnim, firstAlphaAnim, firstScaleXAnim, firstScaleYAnim, firstResumeRotationAnim, firstTranslationAnim, secondTranslationAnim);
            animatorSet.start();
        }


    }

    private class AnimationParams {

        private boolean isOpen;

        private float rotationXFrom, rotationXTo;
        private float alphaFrom, alphaTo;
        private float scaleXFrom, scaleXTo;
        private float scaleYFrom, scaleYTo;
        private float translationYFrom, translationYTo;


        public AnimationParams(boolean isOpen) {
            this.isOpen = isOpen;
        }


    }

}

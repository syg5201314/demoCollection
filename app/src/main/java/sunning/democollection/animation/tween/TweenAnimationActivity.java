package sunning.democollection.animation.tween;
/*
 * Created by sunning on 2016/12/2.
 */

import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;

import sunning.democollection.BaseActivity;
import sunning.democollection.R;

public class TweenAnimationActivity extends BaseActivity {

    private Button btn;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.property_layout);
        btn = (Button) findViewById(R.id.btn_animation);
        findViewById(R.id.xuanzhuan_btn).setOnClickListener(v -> startRotateAnimation());
        findViewById(R.id.touming_btn).setOnClickListener(v -> startAlphaAnimation());
        findViewById(R.id.soufang_btn).setOnClickListener(v -> startScaleAnimation());
        findViewById(R.id.yidong_btn).setOnClickListener(v -> startTranslateAnimation());

    }

    private void startScaleAnimation() {
        Animation animation = new ScaleAnimation(1.0f, 5.0f, 1.0f, 10.0f);
        animation.setDuration(4000);
        animation.setFillAfter(true);
        btn.startAnimation(animation);
    }

    private void startAlphaAnimation() {
        Animation animation = new AlphaAnimation(0.1f, 1.0f);
        animation.setDuration(4000);
        animation.setFillAfter(true);
        btn.startAnimation(animation);
    }

    private void startTranslateAnimation() {
        Animation animation = new TranslateAnimation(1.0f, 1.0f, 200f, 200f);
        animation.setDuration(4000);
        animation.setFillAfter(true);
        btn.startAnimation(animation);
    }

    private void startRotateAnimation() {
        RotateAnimation animation = new RotateAnimation(0.f, 360f, 0.5f, 0.5f);
        animation.setDuration(4000);
        animation.setFillAfter(true);
        btn.startAnimation(animation);
    }
}

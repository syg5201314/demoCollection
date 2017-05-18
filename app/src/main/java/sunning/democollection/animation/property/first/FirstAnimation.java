package sunning.democollection.animation.property.first;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import sunning.democollection.BaseActivity;
import sunning.democollection.R;

/**
 * Created by sunning on 2017/5/18.
 */

public class FirstAnimation extends BaseActivity {
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.firsr_animation);

        ImageView iv = (ImageView) findViewById(R.id.btn_animation);
        ObjectAnimator animator = ObjectAnimator.ofFloat(iv, "haha", 1f, 200f);
        animator.addUpdateListener(animation -> {
            // animation.getAnimatedFraction 得到百分比
            // animation.getAnimatedValue() 得到值
            Log.e("addUpdateListener", animation.getAnimatedFraction() + "");
            if (iv != null) {
                iv.setImageAlpha((int) animation.getAnimatedFraction());
            }
        });
    }
}

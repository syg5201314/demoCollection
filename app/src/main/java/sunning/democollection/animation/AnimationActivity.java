package sunning.democollection.animation;

import sunning.democollection.FrameActivity;
import sunning.democollection.R;

/**
 * Created by sunning on 15/11/19.
 * 动画( Animation)
 * 二级 Activity
 */
public class AnimationActivity extends FrameActivity {

    @Override
    protected String[] getSecondLevel() {
        return getResources().getStringArray(R.array.animation);
    }
}

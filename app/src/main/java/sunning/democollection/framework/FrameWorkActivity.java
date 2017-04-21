package sunning.democollection.framework;
/*
 * Created by sunning on 2016/11/25.
 */

import sunning.democollection.FrameActivity;

public class FrameWorkActivity extends FrameActivity{
    @Override
    protected String[] getSecondLevel() {
        return new String[]{".framework.mvp.TweenAnimationActivity"};
    }
}

package sunning.democollection.animation.property;
/*
 * Created by sunning on 2016/12/2.
 */

import android.os.Bundle;

import sunning.democollection.FrameActivity;

public class PropertyAnimationActivity extends FrameActivity {
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
    }

    @Override
    protected String[] getSecondLevel() {
        return new String[]{".animation.property.first.FirstAnimation",
                ".animation.property.second.SecondAnimation"};
    }
}

package sunning.democollection.theme;

import android.os.Bundle;

import sunning.democollection.FrameActivity;

/**
 * Created by sunning on 15/11/19.
 * 主题(Theme)
 * 二级 Activity
 */
public class ThemeActivity extends FrameActivity{

    @Override
    protected String[] getSecondLevel() {
        return new String[0];
    }

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
    }
}

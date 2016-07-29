package sunning.democollection.draw;

import sunning.democollection.FrameActivity;
import sunning.democollection.R;

/**
 * Created by sunning on 15/11/19.
 * 绘图
 */
public class DrawActivity extends FrameActivity{
    @Override
    protected String[] getSecondLevel() {
        return getResources().getStringArray(R.array.Draw);
    }
}

package sunning.democollection.view;

import sunning.democollection.FrameActivity;
import sunning.democollection.R;

/**
 * Created by sunning on 15/11/27.
 */
public class ViewActivity extends FrameActivity{

    @Override
    protected String[] getSecondLevel() {
        return getResources().getStringArray(R.array.customView);
    }
}

package sunning.democollection.design;

import android.widget.Toast;

import sunning.democollection.FrameActivity;
import sunning.democollection.R;

/**
 * Created by sunning on 15/12/21.
 */
public class DesignActivity extends FrameActivity{
    @Override
    protected String[] getSecondLevel() {
        return getResources().getStringArray(R.array.Design);
    }
}

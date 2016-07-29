package sunning.democollection.learn._0306.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import sunning.democollection.R;

/**
 * Created by sunning on 16/3/6.
 */
public class ScrollerDemoView extends LinearLayout{

    private LinearLayout root;

    public ScrollerDemoView(Context context) {
        super(context);
    }

    public ScrollerDemoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollerDemoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}

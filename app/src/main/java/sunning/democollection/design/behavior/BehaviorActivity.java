package sunning.democollection.design.behavior;
/*
 * Created by sunning on 2017/4/21.
 */

import android.os.Bundle;
import android.view.MotionEvent;

import sunning.democollection.BaseActivity;
import sunning.democollection.R;

public class BehaviorActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.behavior_layout);
        findViewById(R.id.btn).setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_MOVE:
                    v.setX(event.getRawX() - v.getWidth() / 2);
                    v.setY(event.getRawY() - v.getHeight() / 2);
                    break;
            }
            return true;
        });

    }
}

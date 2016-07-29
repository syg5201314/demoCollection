package sunning.democollection.learn._0306.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

/**
 * Created by sunning on 16/3/6.
 */
public class OnEventButton extends Button{
    public OnEventButton(Context context) {
        super(context);
    }

    public OnEventButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public OnEventButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        boolean result = super.dispatchTouchEvent(event);
        Log.e("==result","result===="+result);
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                return false;
        }
        return super.onTouchEvent(event);
    }
}

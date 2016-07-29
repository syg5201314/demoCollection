package sunning.democollection.custom.coordinate;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * Created by sunning on 16/7/27.
 */
public class CoordinateViewGroup extends FrameLayout {

    private static final String TAG = "CoordinateViewGroup";

    public CoordinateViewGroup(Context context) {
        super(context);
    }

    public CoordinateViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CoordinateViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        String tag = getTag().toString();
        Log.e(TAG, "onTouchEvent" + tag);
//        if(tag.equals("root3")){
//            return true;
//        }
//        Log.e(TAG, "=============START=============");
////        if(tag.equals("root3")){
//        Log.e(tag + "getLeft", String.valueOf(getLeft()));
//        Log.e(tag + "getTop", String.valueOf(getTop()));
//        Log.e(tag + "getRight", String.valueOf(getRight()));
//        Log.e(tag + "getBottom", String.valueOf(getBottom()));
//        Log.e(tag + "getX", String.valueOf(getX()));
//        Log.e(tag + "getY", String.valueOf(getY()));
////        }
//        Log.e(TAG, "=============END=============");

        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        String tag = getTag().toString();
        Log.e(TAG, "onInterceptTouchEvent" + tag);
        if (tag.equals("root2")) {
            Log.e(TAG, "=============ev.getX()=============" + ev.getX());
            Log.e(TAG, "=============ev.getY()=============" + ev.getY());
            switch (ev.getActionMasked()){
                case MotionEvent.ACTION_DOWN:
                    Log.e(TAG, "=============DOWN=============");
                    break;
                case MotionEvent.ACTION_MOVE:
                    Log.e(TAG, ev.getX()+"=============MOVE============="+ev.getY());
                    break;
            }
        }
        return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        String tag = getTag().toString();
//        Log.e(TAG, "dispatchTouchEvent" + tag);
        return super.dispatchTouchEvent(ev);
    }
}

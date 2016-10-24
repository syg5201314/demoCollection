package sunning.democollection.custom.coordinate;

import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;

import sunning.democollection.BaseActivity;
import sunning.democollection.R;

/**
 * Created by sunning on 16/7/27.
 * 学习AndroidView坐标系和事件
 */
public class CoordinateActivity extends BaseActivity implements View.OnTouchListener {

    private static final String TAG = "CoordinateActivity";
    private CoordinateViewGroup root1, root2, root3;

    private int _xDelta;
    private int _yDelta;
    private int mTouchSlop;
    /* Initial touch point */
    private Point mInitialTouch;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.coordinate);
        root1 = (CoordinateViewGroup) findViewById(R.id.root_1);
        root2 = (CoordinateViewGroup) findViewById(R.id.root_2);
        root3 = (CoordinateViewGroup) findViewById(R.id.root_3);
        imageView = (ImageView) findViewById(R.id.image);
        root2.setOnTouchListener(this);
        root3.setOnTouchListener(this);
        root1.setTag("root1");
        root2.setTag("root2");
        root3.setTag("root3");
        imageView.setOnTouchListener(this);
        mInitialTouch = new Point();


        getWidthAndHeight();
        getViewContentHeight();
    }

    private void getViewContentHeight() {
        Rect rect = new Rect();
        getWindow().findViewById(Window.ID_ANDROID_CONTENT).getDrawingRect(rect);
        Log.e("content-height", "getViewContentHeight: " + rect.toString());

    }

    private void getWidthAndHeight() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int widthPixels = metrics.widthPixels;
        int heightPixels = metrics.heightPixels;
        Log.e("width-height", "getWidthAndHeight: " + widthPixels + heightPixels);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
//        Log.e(TAG, "onTouch" + v.getTag().toString());
        final int X = (int) event.getRawX();
        final int Y = (int) event.getRawY();
        if (v.getId() == imageView.getId()) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                mInitialTouch.set((int) event.getX(), (int) event.getY());
                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) v.getLayoutParams();
                _xDelta = X - params.leftMargin;
                _yDelta = Y - params.topMargin;
                //Must declare interest to get more events
                return true;
            } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
//                switch (v.getId()) {

//                    case R.id.root_2:
//                        Log.i(TAG, String.format("Top Move: %.1f,%.1f", event.getX(), event.getY()));
//                        break;
//                    case R.id.root_3:
//                        if (Math.abs(event.getX() - mInitialTouch.x) > mTouchSlop
//                                || Math.abs(event.getY() - mInitialTouch.y) > mTouchSlop) {
//                            Log.i(TAG, String.format("Bottom Move: %.1f,%.1f", event.getX(), event.getY()));
//                        }
//                        break;
//                    default:
//                        break;
//                }
//                imageView.scrollTo(-(int) event.getX(), -(int) event.getY());
                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) v.getLayoutParams();
                params.leftMargin = X - _xDelta;
                params.topMargin = Y - _yDelta;
//                params.rightMargin = -250;
//                params.bottomMargin = -250;
                v.setLayoutParams(params);
            }
        }
        root3.invalidate();
        //Don't interefere when not necessary
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, "Activity - - - - - - - -onTouchEvent");
        return super.onTouchEvent(event);
    }
}

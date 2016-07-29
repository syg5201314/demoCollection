package sunning.democollection.learn._0306.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ScrollView;
import android.widget.Scroller;

/**
 * Created by sunning on 16/3/7.
 */
public class ScrollerView extends ScrollView {

    private float initXPoint, initYPoint;

    private float offsetX, offsetY;

    private Scroller scroller;

    private ViewConfiguration configuration;

    private View mLeftView;
    private View mRightView;


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() != 2) {
            throw new RuntimeException("Only need two child view! Please check you xml file!");
        }

        mLeftView = getChildAt(0);
        mRightView = getChildAt(1);
    }

    public ScrollerView(Context context) {
        super(context);
        init();
    }

    public ScrollerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ScrollerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        scroller = new Scroller(getContext(), null, true);
        configuration = ViewConfiguration.get(getContext());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                initXPoint = event.getX();
                initYPoint = event.getY();
                Log.e("Point", "initXPoint=" + initXPoint + "====" + "initYPoint" + initYPoint);
                return true;
            case MotionEvent.ACTION_MOVE:
                offsetX = event.getX() - initXPoint;
                offsetY = event.getY() - initYPoint;
//                Log.e("Point", "offsetX=" + offsetX + "====" + "offsetY" + offsetY);
                if (Math.abs(offsetX) - Math.abs(offsetY) > configuration.getScaledTouchSlop()) {
                    int offset = (int) -offsetY;
                    Log.e("Point","offset="+offset+"getScrollX()="+getScrollX()+"mRightView.getWidth()="+mRightView.getWidth());
                    if (getScrollX() + offset > mRightView.getWidth() || getScrollX() + offset < 0) {
                        return true;
                    }
                    this.scrollBy(offset, 0);
                    initXPoint = event.getX();
                    initYPoint = event.getY();
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()) {
            this.scrollTo(scroller.getCurrX(), scroller.getCurrY());
            postInvalidate();
        }
    }
}

package sunning.democollection.custom.learn;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by sunning on 16/8/2.
 */
public class HorizontalView extends ViewGroup {

    private int lastX = 0;

    int lastTouchX = 0, lastTouchY = 0;

    private Scroller scroller;

    private int currentIndex;

    private int childWidth = 0;

    private VelocityTracker tracker;


    public HorizontalView(Context context) {
        super(context);
    }

    public HorizontalView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        scroller = new Scroller(getContext());
    }

    public HorizontalView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        int left = 0;
        for (int i = 0; i < count; i++) {
            View childView = getChildAt(i);
            childWidth = childView.getMeasuredWidth();
            childView.layout(left, 0, left + childView.getMeasuredWidth(), childView.getMeasuredHeight());
            left += childView.getMeasuredWidth();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View childView = getChildAt(i);
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
            if (heightMode == MeasureSpec.AT_MOST && widthMode == MeasureSpec.AT_MOST) {
                int childWidth = childView.getMeasuredWidth();
                int childHeight = childView.getMeasuredHeight();
                setMeasuredDimension(childWidth * count, childHeight);
            } else if (widthMode == MeasureSpec.AT_MOST) {
                int childWidth = childView.getMeasuredWidth();
                setMeasuredDimension(childWidth * count, heightSize);
            } else {
                setMeasuredDimension(widthSize, heightSize);
            }
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercept = false;
        int touchX = (int) ev.getX();
        int touchY = (int) ev.getY();

        switch (ev.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                intercept = false;
                if (!scroller.isFinished()) {
                    scroller.abortAnimation();
                    intercept = true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = touchX - lastTouchX;
                int deltaY = touchY - lastTouchY;
//                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                if (Math.abs(deltaX) - Math.abs(deltaY) > 0) {
                    intercept = true;
                }
                break;
        }
        lastTouchX = touchX;
        lastTouchY = touchY;
        lastX = touchX; //这里赋值是为了第一次点击的时候lastX没有赋值，导致图片会随手指乱动
        return intercept;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - lastX;
                scrollBy(-deltaX, 0);
                if (tracker == null) {
                    tracker = VelocityTracker.obtain();//获得VelocityTracker类实例
                    tracker.addMovement(event);
                }
                break;
            case MotionEvent.ACTION_UP:
                int distance = getScrollX() - currentIndex * childWidth;
                if (Math.abs(distance) > childWidth / 2) {
                    if (distance > 0) {
                        currentIndex++;
                    } else {
                        currentIndex--;
                    }
                } else {
                    tracker.computeCurrentVelocity(1000);
                    float flingX = tracker.getXVelocity();
                    if (Math.abs(flingX) > 50) {
                        if (flingX > 0) { //大于0切换上一个页面
                            currentIndex--;
                        } else { //小于0切换到下一个页面（子View）
                            currentIndex++;
                        }
                    }
                }
                currentIndex = currentIndex < 0 ? currentIndex = 0 : currentIndex > getChildCount() - 1 ? getChildCount() - 1 : currentIndex;
                smoothScrollTo(currentIndex * childWidth, 0);
                tracker.clear();
                tracker.recycle();
                tracker = null;
                break;
        }
        lastX = x;
        return super.onTouchEvent(event);
    }

    /**
     * 先执行smoothScrollTo 根据从X,Y TO X,Y，
     * 果动画没结束，会call computeScroll 然后进到scrollTo中 scroller的currY就是 startScroll的第4个参数 就是TO哪。
     *
     * @param destX
     * @param destY
     */
    public void smoothScrollTo(int destX, int destY) {
        scroller.startScroll(getScrollX(), getScrollY(), destX - getScrollX(), destY, 1000);
        invalidate();
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            postInvalidate();
        }
    }
}

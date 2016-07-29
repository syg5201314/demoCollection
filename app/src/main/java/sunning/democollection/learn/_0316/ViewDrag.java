package sunning.democollection.learn._0316;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by sunning on 16/3/16.
 */
public class ViewDrag extends ViewGroup {

    private ViewDragHelper viewDragHelper;

    private View mContentView, mDrawerView;

    private boolean mIsOpen;

    private int mCurTop = 0;


    public ViewDrag(Context context) {
        super(context);
        init();
    }

    public ViewDrag(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ViewDrag(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        viewDragHelper = ViewDragHelper.create(this, 1.0F, new ViewDragCallBack());
        viewDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_TOP);
    }

    class ViewDragCallBack extends ViewDragHelper.Callback {

        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return child == mDrawerView;
        }

        @Override
        public void onEdgeDragStarted(int edgeFlags, int pointerId) {
            viewDragHelper.captureChildView(mDrawerView, pointerId);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
            MarginLayoutParams params = (MarginLayoutParams) mContentView.getLayoutParams();
            mContentView.layout(params.leftMargin, params.topMargin, mContentView.getMeasuredWidth() + params.rightMargin, mContentView.getMeasuredHeight() + params.bottomMargin);
            params = (MarginLayoutParams) mDrawerView.getLayoutParams();
            mDrawerView.layout(params.leftMargin, mCurTop + params.topMargin,
                    mDrawerView.getMeasuredWidth() + params.leftMargin,
                    mCurTop + mDrawerView.getMeasuredHeight() + params.topMargin);

        }
    }

    @Override
    public void computeScroll() {
        if (viewDragHelper.continueSettling(true)) {
            invalidate();
        }
    }

    public void closeDrawer() {
        if (mIsOpen) {
            viewDragHelper.smoothSlideViewTo(mDrawerView, mDrawerView.getLeft(), -mDrawerView.getHeight());
            invalidate();
        }
    }

    public void openDrawer() {
        if (!mIsOpen) {
            viewDragHelper.smoothSlideViewTo(mDrawerView, mDrawerView.getLeft(), 0);
            invalidate();
        }
    }

    //Step3：重写onInterceptTouchEvent回调ViewDragHelper中对应的方法.
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return viewDragHelper.shouldInterceptTouchEvent(ev);
    }

    //Step3：重写onTouchEvent回调ViewDragHelper中对应的方法.
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        viewDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int measureHeight = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(measureWidth, measureHeight);

        mContentView = getChildAt(0);
        mDrawerView = getChildAt(1);

        MarginLayoutParams params = (MarginLayoutParams) mContentView.getLayoutParams();
        int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(measureWidth - (params.leftMargin + params.rightMargin), MeasureSpec.EXACTLY);
        int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(measureHeight - (params.topMargin + params.bottomMargin), MeasureSpec.EXACTLY);
        mContentView.measure(childWidthMeasureSpec, childHeightMeasureSpec);
        mDrawerView.measure(widthMeasureSpec, heightMeasureSpec);
    }


    public boolean isDrawerOpened() {
        return mIsOpen;
    }
}

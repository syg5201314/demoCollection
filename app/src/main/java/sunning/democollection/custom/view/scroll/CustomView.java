package sunning.democollection.custom.view.scroll;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

import sunning.democollection.BaseActivity;

/**
 * Created by sunning on 16/8/10.
 */

public class CustomView extends View {

    private int[] interval = new int[2];

    private Scroller scroller;
    private Paint mScalePaint;
    private Paint textPaint;
    private PointF lastPoint;
    private int mWidth;

    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        interval[0] = 10;
        interval[1] = 50;
        scroller = new Scroller(getContext());
        mScalePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mScalePaint.setColor(Color.WHITE);
        mScalePaint.setStrokeWidth(2.f);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(50.f);
        lastPoint = new PointF();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float longLine = getMeasuredHeight() / 10 * 3;
        float middleLine = longLine * .5f;
        float normalLine = longLine * .3f;
        float linePadding = longLine;
        float paddingLeft = 0;
        for (int i = interval[0]; i <= interval[1]; i++) {
            paddingLeft = paddingLeft + linePadding;
            if (i % 10 == 0) {
                //画长线
                canvas.drawLine(paddingLeft, getTop(), paddingLeft, getTop() + linePadding, mScalePaint);
                canvas.drawText(String.valueOf(i), paddingLeft, getTop() + linePadding + 40.f, textPaint);
            } else if (i % 5 == 0) {
                //画中线
                canvas.drawLine(paddingLeft, getTop(), paddingLeft, getTop() + middleLine, mScalePaint);
            } else {
                //普通线
                canvas.drawLine(paddingLeft, getTop(), paddingLeft, getTop() + normalLine, mScalePaint);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        PointF currPoint = new PointF(event.getX(), event.getY());
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_MOVE:
                int deltaX = (int) (currPoint.x - lastPoint.x);
                smoothScrollTo(deltaX);
                lastPoint.set(currPoint);
                break;
        }
        return true;
    }

    public void smoothScrollTo(int destX) {
        BaseActivity.log(this, destX + "====");
        scroller.startScroll(scroller.getCurrX() + mWidth, 0, -destX, 0, 1000);
        invalidate();
    }


    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.getCurrX(), 0);
            postInvalidate();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getWidth();
    }
}
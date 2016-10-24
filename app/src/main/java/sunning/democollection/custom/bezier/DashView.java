package sunning.democollection.custom.bezier;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by sunning on 16/8/5.
 */

public class DashView extends View {

    private Paint mDashPaint;

    private int mlineHeight;

    public DashView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), mlineHeight);
//    }

    private void init() {
        setLayerType(LAYER_TYPE_SOFTWARE, null);

        mDashPaint = new Paint();
        mDashPaint.setColor(Color.RED);
        mDashPaint.setAntiAlias(true);
        mDashPaint.setStyle(Paint.Style.STROKE);
        mDashPaint.setPathEffect(new DashPathEffect(new float[]{8, 4}, 0));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.e("onDraw",getMeasuredHeight()+"====");
        float mWidth = getMeasuredWidth();
        float mHeight = getMeasuredHeight();


        float xPos = 25.f / 450.f * mWidth;
        float yPos = 352.f / 525.f * mHeight;

        float endX = xPos + (450.f - 50f) / 450f * mWidth;
        float endY = yPos;
        canvas.drawLine(xPos, yPos, endX, getMeasuredHeight(), mDashPaint);


    }
}

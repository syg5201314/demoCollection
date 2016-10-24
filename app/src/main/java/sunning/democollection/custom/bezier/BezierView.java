package sunning.democollection.custom.bezier;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * Created by sunning on 16/8/3.
 */

public class BezierView extends View {

    private Paint mPaint;
    private Path mPath;
    private Point startPoint;
    private Point endPoint;
    // 辅助点
    private Point assistPoint;


    public BezierView(Context context) {
        super(context);
    }

    public BezierView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        mPaint = new Paint();
        mPath = new Path();
        startPoint = new Point(300, 600);
        endPoint = new Point(900, 600);
        assistPoint = new Point(600, 900);
        mPaint.setAntiAlias(true);//抗锯齿
        mPaint.setDither(true);//防抖动
    }

    public BezierView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(20f);
        canvas.drawText("这是我画出来的哦",100,100,mPaint);
        /**
         * draw二次的贝塞尔曲线
         */
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(2f);
        mPaint.setStyle(Paint.Style.STROKE);
        mPath.reset();//重置路径
        mPath.moveTo(startPoint.x,startPoint.y);
        mPath.quadTo(assistPoint.x,assistPoint.y,endPoint.x,endPoint.y);

        canvas.drawPath(mPath,mPaint);
//        canvas.drawPoint(assistPoint.x, assistPoint.y, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                assistPoint.x = (int) event.getX();
                assistPoint.y = (int) event.getY();
                Log.i(TAG, "assistPoint.x = " + assistPoint.x);
                Log.i(TAG, "assistPoint.Y = " + assistPoint.y);
                invalidate();
                break;
        }
        return true;
    }
    //todo 未完-待续 例子里还有一个 天气预报的2次3次混合的 还没敲进去,有时间继续,现在要去做QQ健康的View

}

package sunning.democollection.custom.health;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.design.widget.Snackbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import sunning.democollection.R;

/**
 * Created by sunning on 16/8/3.
 */

public class QQHealthView extends View {

    int mWidth, mHeight;//全局的View宽和高

    private float mRatio;//宽高比
    private int mBackgroundCorner;//背景四角的角度

    /**
     * 颜色
     */
    private int mThemeColor;
    private int mUpBackgroundColor;
    private int mAverageStep;//平均步数

    /**
     * 各种画笔
     */
    private Paint mBackgroundPaint;//背景画笔
    private Paint mArcPaint;//最上面弧线的画笔
    private Paint mTextPaint;//文字画笔
    private Paint mDashLinePaint;//虚线的画笔
    private Paint mBarPaint;//竖条的画笔
    private Paint mAvatarPaint;//

    private RectF mArcRect;//圆弧的矩形
    private int mArcCenterX;//圆弧中心点X
    private int mArcCenterY;//中心点Y

    private int step = 25;
    private float percent = 0.5f;


    private int[] mSteps;//记录行走步数


    public QQHealthView(Context context) {
        super(context);
    }

    public QQHealthView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public QQHealthView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initView() {
        setLayerType(LAYER_TYPE_SOFTWARE, null);//关闭硬件加速
        mRatio = 450.f / 525.0f;//宽高比
        mBackgroundCorner = 20;
        int mDefaultThemeColor = Color.parseColor("#2EC3FD");//蓝色
        int mDefaultUpBackgroundColor = Color.WHITE;
        mThemeColor = mDefaultThemeColor;
        mUpBackgroundColor = mDefaultUpBackgroundColor;
        mSteps = new int[]{10050, 15280, 8900, 9200, 6500, 5660, 9450};
        calculateStep();
        initPaints();
        AnimatorSet animatorSet = new AnimatorSet();

        //步数的动画
        ValueAnimator stepAnimator = ValueAnimator.ofInt(0, mSteps[mSteps.length - 1]);
        stepAnimator.addUpdateListener(animation -> {
            step = (int) animation.getAnimatedValue();
            invalidate();
        });
        //  stepAnimator.setDuration(1000);
        //stepAnimator.start();

        //圆环动画
        ValueAnimator percentAnimator = ValueAnimator.ofFloat(0, 1);
        percentAnimator.addUpdateListener(animation -> {
            percent = (float) animation.getAnimatedValue();
            invalidate();
        });

        // percentAnimator.setDuration(1000);
        // percentAnimator.start();
        animatorSet.setDuration(1000);
        animatorSet.playTogether(stepAnimator, percentAnimator);
        animatorSet.start();
    }

    private void initPaints() {
        mBackgroundPaint = new Paint();
        mBackgroundPaint.setAntiAlias(true);
        mBackgroundPaint.setColor(mThemeColor);

        //初始化圆弧画笔
        mArcPaint = new Paint();
        mArcPaint.setAntiAlias(true);
        mArcPaint.setColor(mThemeColor);
        mArcPaint.setStyle(Paint.Style.STROKE);
        mArcPaint.setDither(true);
        mArcPaint.setStrokeJoin(Paint.Join.ROUND);//画笔连接处是圆润的
        mArcPaint.setStrokeCap(Paint.Cap.ROUND);//画笔起始处是圆润的
        mArcPaint.setPathEffect(new CornerPathEffect(10));
        //文字画笔
        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);

        //虚线画笔
        mDashLinePaint = new Paint();
        mDashLinePaint.setAntiAlias(true);
        mDashLinePaint.setColor(Color.parseColor("#C1C1C1"));
        mDashLinePaint.setStyle(Paint.Style.STROKE);
        mDashLinePaint.setPathEffect(new DashPathEffect(new float[]{8, 4}, 0));

        //竖条画笔
        mBarPaint = new Paint();
        mBarPaint.setColor(mThemeColor);
        mBarPaint.setAntiAlias(true);
        mBarPaint.setStrokeCap(Paint.Cap.ROUND);

        //头像画笔
        mAvatarPaint = new Paint();
        mAvatarPaint.setAntiAlias(true);
    }

    /**
     * 计算步数
     */
    private void calculateStep() {
        int mTotalSteps = 0;//总步数
        int mMaxStep = 0;//最大步数
        mAverageStep = 0;//平均
        for (int i = 0; i < mSteps.length; i++) {
            mTotalSteps += mSteps[i];
            if (mMaxStep < mSteps[i])
                mMaxStep = mSteps[i];
        }
        mAverageStep = (int) (mTotalSteps * 1.f / mSteps.length);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mArcCenterX = (int) (mWidth / 2.f);// 1080 / 2 == 540.0f
        mArcCenterY = (int) (160.f / 525.f * mHeight);//384
        Log.e("onSizeChanged", "mArcCenterX: " + mArcCenterX + "   mArcCenterY:   " + mArcCenterY);

        //todo question ..为什么要用450和125当做基准参数
        mArcRect = new RectF();
        mArcRect.left = mArcCenterX - 125.f / 450.f * mWidth;
        mArcRect.top = mArcCenterY - 125.f / 525.f * mHeight;
        mArcRect.right = mArcCenterX + 125.f / 450.f * mWidth;
        mArcRect.bottom = mArcCenterY + 125.f / 525.f * mHeight;
        //mArcRect = [240.0,84.0][840.0,684.0]
        Log.e("RectF", "mArcRect: " + mArcRect.toShortString());

        float mArcWidth = 20.f / 450.f * mWidth;
        float mBarWidth = 16.f / 450.f * mWidth;

        //设置画笔宽度
        mArcPaint.setStrokeWidth(mArcWidth);
        mBarPaint.setStrokeWidth(mBarWidth);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        mBackgroundPaint.setColor(mThemeColor);
        drawBelowBackground(0, 0, mWidth, mHeight, mBackgroundCorner, canvas, mBackgroundPaint);
        mBackgroundPaint.setColor(mUpBackgroundColor);
        drawUpBackground(0, 0, mWidth, mHeight, mBackgroundCorner, canvas, mBackgroundPaint);
        canvas.drawArc(mArcRect, 120, 300, false, mArcPaint);//120,300 第一个参数为开始角度,第二个参数原的周长
        //画圆内文字
        drawArcAndText(mArcCenterX, mArcCenterY, canvas, mTextPaint);
        //绘制圆弧下方的文字及虚线
        drawOutArcText(canvas, mTextPaint);
        //绘制下面的线条
        drawBars(canvas, mTextPaint);
        drawBottom(canvas, mTextPaint);
    }

    private void drawBottom(Canvas canvas, Paint mTextPaint) {
        float xPos = 100.f / 450 * mWidth;
        float yPos = (mHeight - mWidth) / 2 + mWidth + 20.f / 450.f * mWidth / 2;
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setTextSize(20.f / 450.f * mWidth);
        mTextPaint.setTextAlign(Paint.Align.LEFT);//绘制文字焦点在左边
        canvas.drawText("Sunning今日获得冠军", xPos, yPos, mTextPaint);

        Bitmap bitmap = BitmapFactory.decodeResource(getContext().getResources(), R.mipmap.ic_launcher);
        RectF dst = new RectF();
        int rectWidth = (int) (30.f / 525.f * mWidth);
        dst.left = xPos - 40.f / 450.f * mWidth;
        dst.top = (mHeight - mWidth) / 2 + mWidth - rectWidth / 2.f;
        dst.bottom = (int) ((mHeight - mWidth) / 2.f + mWidth + rectWidth / 2.f);
        dst.right = (int) (xPos - 10.f / 450 * mWidth);
        bitmap = toRoundBitmap(bitmap);
        canvas.drawBitmap(bitmap, null, dst, mAvatarPaint);

        xPos = 425.f / 450.f * mWidth;
        mTextPaint.setTextAlign(Paint.Align.RIGHT);
        mTextPaint.setTextSize(15.f / 450.f * mWidth);
        canvas.drawText("查看 >", xPos, yPos, mTextPaint);

    }

    /**
     * 将原始图片转成为圆形图片
     *
     * @param bitmap
     * @return
     */
    private Bitmap toRoundBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int r;
        if (width > height) r = height;
        else r = width;
        Bitmap background = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(background);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        RectF rect = new RectF(0, 0, r, r);
        BitmapShader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        paint.setShader(shader);
        canvas.drawRoundRect(rect, r / 2, r / 2, paint);
        return background;
    }

    private void drawBars(Canvas canvas, Paint mTextPaint) {
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setTextSize(10.f / 450.f * mWidth);
        float startY = 388.f / 525.f * mHeight;
        for (int i = 0; i < mSteps.length; i++) {
            float barHeight = mSteps[i] * 1.f / mAverageStep * 30.f / 525.f * mHeight;
            float startX = 55.f / 450.f * mWidth + i * (57.f / 450.f * mWidth);
            float stopX = startX;
            float stopY = startY - barHeight;
            if (mSteps[i] < mAverageStep) mBarPaint.setColor(Color.parseColor("#C1C1C1"));
            else mBarPaint.setColor(mThemeColor);
            canvas.drawLine(startX, startY, stopX, stopY, mBarPaint);
        }

    }

    private void drawOutArcText(Canvas canvas, Paint mTextPaint) {
        float xPos = 25.f / 450.f * mWidth;
        float yPos = 320.f / 525.f * mHeight;
        mTextPaint.setTextAlign(Paint.Align.LEFT);//绘制文字焦点在左边
        mTextPaint.setColor(Color.parseColor("#C1C1C1"));
        mTextPaint.setTextSize(12.f / 450.f * mWidth);
        canvas.drawText("最近七天", xPos, yPos, mTextPaint);

        xPos = (int) ((450.f - 25.f) / 450.f * mWidth);
        yPos = (int) (320.f / 525.f * mHeight);
        mTextPaint.setTextAlign(Paint.Align.RIGHT);
        mTextPaint.setColor(Color.parseColor("#C1C1C1"));
        mTextPaint.setTextSize(12.f / 450.f * mWidth);
        canvas.drawText("平均" + mAverageStep + "步/天", xPos, yPos, mTextPaint);

        xPos = 25.f / 450.f * mWidth;
        yPos = 352.f / 525.f * mHeight;

        float endX = xPos + (450.f - 50f) / 450f * mWidth;
        float endY = yPos;
        canvas.drawLine(xPos, yPos, endX, endY, mDashLinePaint);
    }

    private void drawArcAndText(int mArcCenterX, int mArcCenterY, Canvas canvas, Paint mTextPaint) {
        float xPos = mArcCenterX;
        float yPos = mArcCenterY - 45.f / 525.f * mHeight;
        canvas.drawArc(mArcRect, 120, 300 * percent, false, mArcPaint);
        mTextPaint.setTextAlign(Paint.Align.CENTER);//绘制文字在基准点中央
        mTextPaint.setTextSize(15.f / 450.f * mWidth);
        mTextPaint.setColor(Color.parseColor("#C1C1C1"));
        canvas.drawText("截止22:44分您已走", xPos, yPos, mTextPaint);

        mTextPaint.setTextSize(42.f / 450.f * mWidth);
        mTextPaint.setColor(mThemeColor);

        canvas.drawText(step + "", mArcCenterX, mArcCenterY, mTextPaint);

        mTextPaint.setColor(Color.parseColor("#C1C1C1"));
        mTextPaint.setTextSize(13.f / 450.f * mWidth);
        yPos = mArcCenterY + 50.f / 525.f * mHeight;
        canvas.drawText("好友平均步数7800", xPos, yPos, mTextPaint);

        xPos = mArcCenterX - 35.f / 450.f * mWidth;
        yPos = mArcCenterY + 120.f / 525.f * mHeight;
        canvas.drawText("第", xPos, yPos, mTextPaint);

        xPos = (int) (mArcCenterX + 35.f / 450.f * mWidth);
        canvas.drawText("名", xPos, yPos, mTextPaint);
        mTextPaint.setColor(mThemeColor);
        mTextPaint.setTextSize(24.f / 450.f * mWidth);
        canvas.drawText("10", mArcCenterX, yPos, mTextPaint);
    }

    private void drawUpBackground(int left, int top, int right, int bottom, int radius, Canvas canvas, Paint mBackgroundPaint) {
        Path path = new Path();
        path.moveTo(left, top);
        //右上圆角
        path.lineTo(right - radius, top);
        path.quadTo(right, top, right, top + radius);
        //右下直角
        path.lineTo(right, bottom - 120.f);
        //左下直角
        path.lineTo(left, bottom - 120.f);
        //左上圆角
        path.lineTo(left, top + radius);
        path.quadTo(left, top, left + radius, top);

        canvas.drawPath(path, mBackgroundPaint);
    }

    /**
     * 绘制最底层背景
     *
     * @param left
     * @param top
     * @param right
     * @param bottom
     * @param radius
     * @param canvas
     * @param paint
     */
    private void drawBelowBackground(int left, int top, int right, int bottom, int radius, Canvas canvas, Paint paint) {
        Log.e("drawBelowBackground", "l= " + left + ",r= " + right + ",t= " + top + ",b= " + bottom);
        //0,0,1080,1260
        Log.e("radius", "radius = " + radius);//20
        Path path = new Path();
        path.moveTo(left, top);
        //画右上角圆弧
        path.lineTo(right - radius, top);//1060,0
        path.quadTo(right, top, right, top + radius);//(1080,0)-->(1080,20)
//        //画右下角圆弧
        path.lineTo(right, bottom - radius);//(1080,1240)
        path.quadTo(right, bottom, right - radius, bottom);//(1080,1260)-->(1060,1260)
//        //画左下角圆弧
        path.lineTo(left + radius, bottom);//(20,1260)
        path.quadTo(left, bottom, left, bottom - radius);
//        //画左上角圆弧
        path.lineTo(left, top + radius);//(0,20)
        path.quadTo(left, top, left + radius, top);
        canvas.drawPath(path, paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int defaultWidth = Integer.MAX_VALUE;
        int width, height;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY || widthMode == MeasureSpec.AT_MOST) {
            width = widthSize;
        } else {
            width = defaultWidth;//显示满
        }
        int defaultHeight = (int) (width * 1.f / mRatio);
        height = defaultHeight;
        setMeasuredDimension(width, height);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        RectF rectF = new RectF();
        rectF.top = mWidth;
        rectF.left = 380.f / 450.f * mWidth;
        rectF.right = mWidth;
        rectF.bottom = mHeight;
        Log.e("onTouchEvent", "===" + rectF.toShortString() + event.getX() + " , ==== , " + event.getY());
        if (rectF.contains(event.getX(), event.getY())) {//当前点击的坐标在右下角的范围内
            //在这里可以做点击事件的监听
            Snackbar.make(this, "Click", Snackbar.LENGTH_SHORT).show();
            return true;
        } else {
            return super.onTouchEvent(event);
        }
    }
}

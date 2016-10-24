package sunning.democollection.custom.learn;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by sunning on 16/8/2.
 */
public class MeasureViewGroup extends ViewGroup {
    public MeasureViewGroup(Context context) {
        super(context);
    }

    public MeasureViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MeasureViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        /**
         * 四个角各一个View的onLayout
         */
//        MarginLayoutParams cParams;
//
//        int size = getChildCount();
//        for (int i = 0; i < size; i++) {
//            View view = getChildAt(i);
//            int cHeight = view.getMeasuredHeight();
//            int cWidth = view.getMeasuredWidth();
//            cParams = (MarginLayoutParams) view.getLayoutParams();
//            int cl = 0, ct = 0, cr = 0, cb = 0;
//            switch (i){
//                case 0:
//                    cl = cParams.leftMargin;
//                    ct = cParams.topMargin;
//                    break;
//                case 1:
//                    cl = getWidth() - cWidth - cParams.leftMargin - cParams.rightMargin;
//                    ct = cParams.topMargin;
//                    break;
//                case 2:
//                    cl = cParams.leftMargin;
//                    ct = getHeight() - cHeight - cParams.topMargin - cParams.bottomMargin;
//                    break;
//                case 3:
//                    cl = getWidth() - cWidth - cParams.leftMargin - cParams.rightMargin;
//                    ct = getHeight() - cHeight - cParams.topMargin - cParams.bottomMargin;
//                    break;
//            }
//            cr = cl + cWidth;
//            cb = ct + cHeight;
//            view.layout(cl,ct,cr,cb);
//        }

        /**
         * 自动换行的onLayout
         */
        int mViewGroupWidth  = getMeasuredWidth();  //当前ViewGroup的总宽度

        int mPainterPosX = l;  //当前绘图光标横坐标位置
        int mPainterPosY = t;  //当前绘图光标纵坐标位置

        int childCount = getChildCount();
        for ( int i = 0; i < childCount; i++ ) {

            View childView = getChildAt(i);

            int width = childView.getMeasuredWidth();
            int height = childView.getMeasuredHeight();

            //如果剩余的空间不够，则移到下一行开始位置
            if (mPainterPosX + width > mViewGroupWidth) {
                mPainterPosX = l;
                mPainterPosY += height;
            }

            //执行ChildView的绘制
            childView.layout(mPainterPosX, mPainterPosY, mPainterPosX + width, mPainterPosY + height);

            //记录当前已经绘制到的横坐标位置
            mPainterPosX += width;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        // 计算出所有的childView的宽和高
//        measureChildren(widthMeasureSpec, heightMeasureSpec);

//        for (int i = 0; i < getChildCount(); i++) {
//            measureChild(getChildAt(i), widthMeasureSpec, heightMeasureSpec);
//        }
//        setMeasuredDimension(widthSize, heightSize);


        int width, height;
        int count = getChildCount();
        int cWidth, cHeight;
        MarginLayoutParams params;

        // 用于计算左边两个childView的高度
        int lHeight = 0;
        // 用于计算右边两个childView的高度，最终高度取二者之间大值
        int rHeight = 0;

        // 用于计算上边两个childView的宽度
        int tWidth = 0;
        // 用于计算下面两个childiew的宽度，最终宽度取二者之间大值
        int bWidth = 0;
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        for (int i = 0; i < count; i++) {
            View childView = getChildAt(i);
            cWidth = childView.getMeasuredWidth();
            cHeight = childView.getMeasuredHeight();
            params = (MarginLayoutParams) childView.getLayoutParams();

            if (i == 0 || i == 1) {
                tWidth += cWidth + params.leftMargin + params.rightMargin;
            }
            if (i == 2 || i == 3) {
                bWidth += cWidth + params.leftMargin + params.rightMargin;
            }

            if (i == 0 || i == 2) {
                lHeight += cHeight + params.topMargin + params.bottomMargin;
            }

            if (i == 1 || i == 3) {
                rHeight += cHeight + params.topMargin + params.bottomMargin;
            }

            width = Math.max(tWidth, bWidth);
            height = Math.max(lHeight, rHeight);

            setMeasuredDimension((widthMode == MeasureSpec.EXACTLY) ? widthSize : width, (heightMode == MeasureSpec.EXACTLY) ? heightSize : height);
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }
}

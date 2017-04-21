package sunning.democollection.demo.elemedetail;/*
 * Created by sunning on 2017/1/5.
 */

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class ZoomHeaderViewPager extends ViewPager {
    public boolean canScroll;

    public ZoomHeaderViewPager(Context context) {
        super(context);
    }

    public ZoomHeaderViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        setPageTransformer(true, new ZoomOutPageTransformer());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return canScroll && super.onTouchEvent(event);
    }

    @Override
    protected int getChildDrawingOrder(int childCount, int i) {
        int position = getCurrentItem();
        if (position < 0) {
            return i;
        } else {
            if (i == childCount - 1) {//这是最后一个需要刷新的item
                if (position > i) {
                    position = i;
                }
                return position;
            }
            if (i == position) {//这是原本要在最后一个刷新的item
                return childCount - 1;
            }
        }
        return i;
    }
}

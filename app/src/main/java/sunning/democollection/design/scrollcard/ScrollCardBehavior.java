package sunning.democollection.design.scrollcard;
/*
 * Created by sunning on 2017/4/25.
 */

import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.view.View;

class ScrollCardBehavior extends CoordinatorLayout.Behavior<SlidingCardView> {

    private int mInitHeight;

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, SlidingCardView child, View target, int dx, int dy, int[] consumed) {
        if (child.getTop() > mInitHeight) {
            int minOffset = mInitHeight;
            int maxOffset = mInitHeight + child.getHeight() - child.getHeadHeight();
            consumed[1] = scroll(child, dy, minOffset, maxOffset);
            shiftScroll(consumed[1], coordinatorLayout, child);
        }
    }


    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, SlidingCardView child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        int minOffset = mInitHeight;
        int maxOffset = mInitHeight + child.getHeight() - child.getHeadHeight();
        int scrollY = scroll(child, dyUnconsumed, minOffset, maxOffset);
        shiftScroll(scrollY, coordinatorLayout, child);
    }


    private void shiftScroll(int scrollY, CoordinatorLayout parent, SlidingCardView child) {
        if(scrollY == 0){
            return;
        }
        if(scrollY > 0 ){
            SlidingCardView current = child;
            SlidingCardView card = getPreView(parent, current);
            while(card != null){
                int delta = calcOtherOffset(card, current);
                if (delta > 0) {
                    card.offsetTopAndBottom(-delta);
                }
                current = card;
                card = getPreView(parent, current);
            }
        }else{
            SlidingCardView current = child;
            SlidingCardView card = getNextView(parent, current);
            while (card != null) {
                int delta = calcOtherOffset(current, card);
                if (delta > 0) {
                    card.offsetTopAndBottom(delta);
                }
                current = card;
                card = getNextView(parent, current);
            }
        }
    }

    private int calcOtherOffset(SlidingCardView above, SlidingCardView below) {
        return above.getTop() + above.getHeadHeight() - below.getTop();
    }


    private int scroll(SlidingCardView child, int dyConsumed, int minOffset, int maxOffset) {
        int initOffset = child.getTop();
        int delta = clamp(initOffset - dyConsumed, minOffset, maxOffset) - initOffset;
        child.offsetTopAndBottom(delta);
        return -delta;
    }

    private int clamp(int initOffset, int minOffset, int maxOffset) {
        if (initOffset > maxOffset) {
            return maxOffset;
        } else if (initOffset < minOffset) {
            return minOffset;
        } else {
            return initOffset;
        }
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, SlidingCardView child, View directTargetChild, View target, int nestedScrollAxes) {
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0 && child == directTargetChild;
    }

    @Override
    public boolean onMeasureChild(CoordinatorLayout parent, SlidingCardView child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
        int offset = getChildHeightOffset(parent, child);
        int height = View.MeasureSpec.getSize(parentHeightMeasureSpec) - heightUsed - offset;
        child.measure(parentWidthMeasureSpec, View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY));
        return true;
    }

    private int getChildHeightOffset(CoordinatorLayout parent, SlidingCardView child) {
        int offset = 0;
        int count = parent.getChildCount();
        for (int i = 0; i < count; i++) {
            View loopView = parent.getChildAt(i);
            if (loopView instanceof SlidingCardView && loopView != child) {
                SlidingCardView cardView = (SlidingCardView) loopView;
                offset += cardView.getHeadHeight();
            }
        }
        return offset;
    }

    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, SlidingCardView child, int layoutDirection) {
        parent.onLayoutChild(child, layoutDirection);
        SlidingCardView cardView = getPreView(parent, child);
        if (cardView != null) {
            int offset = cardView.getTop() + cardView.getHeadHeight();
            child.offsetTopAndBottom(offset);
        }
        mInitHeight = child.getTop();
        return true;
    }

    private SlidingCardView getPreView(CoordinatorLayout parent, SlidingCardView child) {
        int index = parent.indexOfChild(child);
        for (int i = index - 1; i >= 0; i--) {
            View cardView = parent.getChildAt(i);
            if (cardView instanceof SlidingCardView) {
                return (SlidingCardView) cardView;
            }
        }
        return null;
    }

    private SlidingCardView getNextView(CoordinatorLayout parent, SlidingCardView child) {
        int index = parent.indexOfChild(child);
        int size = parent.getChildCount();
        for (int i = index + 1; i < size; i++) {
            View cardView = parent.getChildAt(i);
            if (cardView instanceof SlidingCardView) {
                return (SlidingCardView) cardView;
            }
        }
        return null;
    }
}

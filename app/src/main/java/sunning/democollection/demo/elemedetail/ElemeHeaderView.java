package sunning.democollection.demo.elemedetail;
/*
 * Created by sunning on 2017/1/5.
 */

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import sunning.democollection.R;

import static android.R.attr.y;

public class ElemeHeaderView extends LinearLayout {

    private static final long ANIMATE_LENGTH = 300;
    private float mTouchSlop;
    private ZoomHeaderViewPager mViewPager;
    private float fitstY;
    private TextView mCloseTxt;
    private float iDownY = 0;
    private float mMaxY;
    private RecyclerView mRecyclerView;
    private boolean isExpand;
    private RelativeLayout mBottomView;
    private float bottomY;


    public ElemeHeaderView(Context context) {
        super(context);
    }

    public ElemeHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mViewPager = (ZoomHeaderViewPager) getChildAt(1);
        fitstY = getY();
        mCloseTxt = (TextView) findViewById(R.id.tv_close);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getActionMasked();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                return true;
            case MotionEvent.ACTION_UP:
                float upY = event.getY() - iDownY;
                float currentY = getY();
                if (upY + currentY > 190) {
                    finish();
                } else if (currentY + upY > -getHeight() / 4 && currentY + upY < 190) {
                    restore(upY + currentY);
                } else if (upY + currentY < -getHeight() / 4) {
                    if (upY + currentY < mMaxY) {
                        expand(mMaxY);
                    } else {
                        expand(upY + currentY);
                    }
                }
                return true;
            case MotionEvent.ACTION_MOVE:
                float moveY = event.getY() - iDownY;
                float currentMoveY = getY();
                if (currentMoveY + moveY < 0 && (currentMoveY + moveY) > getHeight() / 2) {
                    //向上移动
                    pageUp(moveY, currentMoveY);
                }
                if (currentMoveY + moveY > 0 && currentMoveY + moveY < 800) {
                    pageDown(moveY, currentMoveY);
                    return true;
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    private void expand(float mMaxY) {
        mRecyclerView.scrollToPosition(0);
        ValueAnimator va = ValueAnimator.ofFloat(mMaxY, -getHeight() / 3);
        va.addUpdateListener(animation -> {
            float y1 = (float) animation.getAnimatedValue();
            mViewPager.canScroll = false;
            setTranslationY(y1);
            isExpand = true;
        });

        va.setInterpolator(new DecelerateInterpolator());
        va.setDuration(ANIMATE_LENGTH);
        va.start();
        //允许滑动
        ((CtrlLinearLayoutManager) mRecyclerView.getLayoutManager()).setScrollEnabled(true);

        //底部上移

        ValueAnimator bottomAnimate = ValueAnimator.ofFloat(mBottomView.getY(), bottomY);
        bottomAnimate.addUpdateListener(animation -> mBottomView.setY((Float) animation.getAnimatedValue()));

        bottomAnimate.start();
    }

    public void restore(float v) {
        mCloseTxt.setAlpha(0f);
        if (v > fitstY) {
            ValueAnimator closeVA = ValueAnimator.ofFloat(1, 0);
            closeVA.addUpdateListener(animation -> mCloseTxt.setAlpha((Float) animation.getAnimatedValue()));
            closeVA.setDuration(ANIMATE_LENGTH);
            closeVA.start();
        }
        mRecyclerView.scrollToPosition(0);
        ValueAnimator restoreVA = ValueAnimator.ofFloat(y, fitstY);
        restoreVA.setInterpolator(new DecelerateInterpolator());
        restoreVA.addUpdateListener(animation -> {
            float value = (float) animation.getAnimatedValue();
            setTranslationY(value);
            isExpand = false;
            mViewPager.canScroll = true;
        });
        restoreVA.setDuration(ANIMATE_LENGTH);
        restoreVA.start();
        ((CtrlLinearLayoutManager) mRecyclerView.getLayoutManager()).setScrollEnabled(false);

        //底部隐藏
        ValueAnimator bottomAnimator = ValueAnimator.ofFloat(mBottomView.getY(),bottomY + mBottomView.getHeight());
        bottomAnimator.addUpdateListener(animation -> mBottomView.setY((Float) animation.getAnimatedValue()));
        bottomAnimator.start();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getActionMasked();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                iDownY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_UP:
                int moveY = (int) ev.getY();
                if (Math.abs(moveY - iDownY) > mTouchSlop) {
                    return true;
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    private void finish() {
        TranslateAnimation finishTa = new TranslateAnimation(0, 0, 0, 1000);
        finishTa.setDuration(ANIMATE_LENGTH);
        finishTa.setFillAfter(true);
        finishTa.setAnimationListener(new Animation.AnimationListener() {
            @Override public void onAnimationStart(Animation animation) {

            }

            @Override public void onAnimationEnd(Animation animation) {
                ((Activity) getContext()).finish();
            }

            @Override public void onAnimationRepeat(Animation animation) {

            }
        });

        startAnimation(finishTa);
    }

    private void pageUp(float moveY, float currentY) {
        mMaxY = moveY + currentY;
        setTranslationY(mMaxY);
        mCloseTxt.setAlpha(0);
    }

    private void pageDown(float moveY, float currentY) {
        int position = mViewPager.getCurrentItem();
        View view = mViewPager.getChildAt(position);
        view.setTranslationY((currentY + moveY) / 4);//为什么除4
        mCloseTxt.setAlpha(view.getY() / 76);
    }

    public ZoomHeaderViewPager getViewPager() {
        return mViewPager;
    }

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.mRecyclerView = recyclerView;
    }

    public RelativeLayout getBottomView() {
        return mBottomView;
    }

    public void setBottomView(RelativeLayout mBottomView, int bottomY) {
        this.mBottomView = mBottomView;
        this.bottomY = bottomY;
    }

    public boolean isExpand() {
        return isExpand;
    }
}

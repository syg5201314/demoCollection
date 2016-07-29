package sunning.democollection.learn._0301.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;

/**
 * Created by sunning on 16/3/1.
 */
public class EventView extends View{

    //加速度工具类
    private VelocityTracker velocity;

    int mMaxVelocity;

    public static final String TAG = "EventView";
    private int mPointerId;

    public EventView(Context context) {
        super(context);
    }

    public EventView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EventView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        velocity = obtainVelocity();
        velocity.addMovement(event);
        mMaxVelocity = ViewConfiguration.get(getContext()).getMaximumFlingVelocity();
        int action = event.getAction() & event.ACTION_MASK;
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mPointerId = event.getPointerId(0);
                break;
            case MotionEvent.ACTION_MOVE:
                //1秒内的滑动像素数
                //比如当前时间间隔设置为1000毫秒，在一秒内从左向右滑动100像素，那么水平速度就是100.
                //速度 = (终点-起点)/时间段
                velocity.computeCurrentVelocity(100,mMaxVelocity);
                int xVelocity = (int) velocity.getXVelocity(mPointerId);
                int yVelocity = (int) velocity.getYVelocity(mPointerId);
                recodeInfo(xVelocity,yVelocity);
                break;
            case MotionEvent.ACTION_UP:
                velocity.clear();
                velocity.recycle();
                break;
        }
        return super.onTouchEvent(event);
    }


    private void recodeInfo(float velocityX, float velocityY){
        Log.e(TAG,"yVelocitx= "+ velocityX);
        Log.e(TAG,"xVelocity= "+ velocityY);

    }

    private VelocityTracker obtainVelocity() {
        if(velocity == null){
            velocity = VelocityTracker.obtain();
        }
        return velocity;
    }
}

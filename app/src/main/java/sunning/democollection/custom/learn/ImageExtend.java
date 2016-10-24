package sunning.democollection.custom.learn;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.ImageView;

/**
 * Created by sunning on 16/8/2.
 */
public class ImageExtend extends ImageView{

    private DisplayMetrics metrics;

    public ImageExtend(Context context) {
        super(context);
    }

    public ImageExtend(Context context, AttributeSet attrs) {
        super(context, attrs);
        WindowManager manager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        metrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metrics);
    }

    public ImageExtend(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        if(widthMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(metrics.widthPixels,MeasureSpec.getSize(heightMeasureSpec));
            return;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}

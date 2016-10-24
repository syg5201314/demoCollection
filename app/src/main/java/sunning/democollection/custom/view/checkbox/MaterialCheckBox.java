package sunning.democollection.custom.view.checkbox;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Checkable;

/**
 * Created by sunning on 16/8/8.
 */

public abstract class MaterialCheckBox extends View implements Checkable {

    private boolean mChecked;
    private final static int DEFAULT_SIZE = 40;

    public MaterialCheckBox(Context context) {
        super(context);
    }

    public MaterialCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MaterialCheckBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setChecked(boolean checked) {
        this.mChecked = checked;
    }

    @Override
    public boolean isChecked() {
        return mChecked;
    }

    @Override
    public void toggle() {
        setChecked(!mChecked);
    }

    public int dp2px(float value) {
        final float scale = getContext().getResources().getDisplayMetrics().densityDpi;
        return (int) (value * (scale / 160) + 0.5f);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int width = widthSize, height = heightSize;

        if (widthMode == MeasureSpec.AT_MOST) {
            width = dp2px(DEFAULT_SIZE);
        }
        if (heightMode == MeasureSpec.AT_MOST) {
            height = dp2px(DEFAULT_SIZE);
        }
        setMeasuredDimension(width, height);

    }
}

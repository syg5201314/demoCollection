package sunning.democollection.custom.view.parent;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import sunning.democollection.BaseActivity;

/**
 * Created by sunning on 16/1/11.
 */
public abstract class ViewParentDemo extends BaseActivity {

    private View mRootView;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
//        if (mRootView != null) {
        mRootView = getView();
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }

//        } else {

//        }
        setContentView(mRootView);
        Log.e("===",findViewById(android.R.id.content)+"=");
    }

    public abstract View getView();
}

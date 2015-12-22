package sunning.democollection.design.snackbar;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import sunning.democollection.BaseActivity;
import sunning.democollection.FrameActivity;
import sunning.democollection.R;

/**
 * Created by sunning on 15/12/21.
 */
public class SnackbarActivity extends BaseActivity{
    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
    }

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        FrameLayout root = (FrameLayout) findViewById(android.R.id.content);
        WindowManager mWm = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
        Button view = new Button(this);
//        view.setText("window manager test!");
//        WindowManager.LayoutParams mParams = new WindowManager.LayoutParams();
////        mParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
//        mParams.width = 300;
//        mParams.height = 100;
//        mWm.addView(view, mParams);


//        view = new Button(this);
        view.setText("start");
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,200);
        final Button finalView = view;
        view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Snackbar.make(finalView, "Snackbar comes out", Snackbar.LENGTH_LONG)
                        .setAction("Action", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(
                                        SnackbarActivity.this,
                                        "Toast comes out",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });
        params.gravity = Gravity.CENTER;
        root.addView(view , params );
    }
}

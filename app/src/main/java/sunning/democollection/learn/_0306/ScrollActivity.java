package sunning.democollection.learn._0306;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.LinearLayout;

import sunning.democollection.BaseActivity;
import sunning.democollection.R;
import sunning.democollection.learn._0306.view.OnEventButton;

/**
 * Created by sunning on 16/3/6.
 */
public class ScrollActivity extends BaseActivity implements View.OnClickListener{

    private LinearLayout root;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.scroll);
        init();
    }

    private void init() {
        OnEventButton x = (OnEventButton) findViewById(R.id.scroll_x);
        Button y = (Button) findViewById(R.id.scroll_y);
        root = (LinearLayout) findViewById(R.id.scrollView_root);
        x.setOnClickListener(this);
        y.setOnClickListener(this);
        x.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction() & MotionEvent.ACTION_MASK){
                    case MotionEvent.ACTION_DOWN:
                        Log.e("===","MotionEvent.ACTION_DOWN");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.e("===","MotionEvent.ACTION_MOVE");
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.scroll_x:
                root.scrollTo(-10,-10);
                break;
            case R.id.scroll_y:
                root.scrollBy(-20,-20);
                break;
        }
        Log.e("=====",root.getScrollX()+","+root.getScrollY());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
//        return false;
    }
}

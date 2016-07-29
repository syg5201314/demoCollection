package sunning.democollection.custom.view.timer;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import sunning.democollection.BaseActivity;
import sunning.democollection.custom.view.timer.view.TimerTextView;
import sunning.global.common.ButtonFactory;

/**
 * Created by sunning on 15/12/23.
 */
public class TimerActivity extends BaseActivity{
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        crateView();
    }

    private void crateView() {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(params);
        setContentView(linearLayout);
        ButtonFactory startBtn = new ButtonFactory(this);
        ButtonFactory endBtn = new ButtonFactory(this);
        final TimerTextView ttv = new TimerTextView(this,null);
        ttv.setTimes(new long[]{0l,10l,30l,15l});
        View startView = startBtn.create("开始", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ttv.isRun()){
                    ttv.beginRun();
                }
            }
        });
        View endView = endBtn.create("结束", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ttv.isRun()){
                    ttv.stopRun();
                }
            }
        });

        linearLayout.addView(startView);
        linearLayout.addView(endView);
        linearLayout.addView(ttv);
    }
}

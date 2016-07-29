package sunning.democollection.custom.view.smoothcb;

import android.os.Bundle;
import android.util.Log;

import sunning.democollection.BaseActivity;
import sunning.democollection.R;

/**
 * Created by sunning on 16/2/19.
 */
public class SmoothActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.smooth_checkbox_layout);

        final SmoothCheckBox scb = (SmoothCheckBox) findViewById(R.id.scb);
        scb.setOnCheckedChangeListener(new SmoothCheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SmoothCheckBox checkBox, boolean isChecked) {
                Log.d("SmoothCheckBox", String.valueOf(isChecked));
            }
        });
    }
}

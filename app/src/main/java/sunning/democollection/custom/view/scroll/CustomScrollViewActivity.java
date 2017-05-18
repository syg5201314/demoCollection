package sunning.democollection.custom.view.scroll;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import sunning.democollection.BaseActivity;
import sunning.democollection.R;

/**
 * Created by sunning on 16/8/10.
 */

public class CustomScrollViewActivity extends BaseActivity{
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.scroll_view);
        View view = findViewById(R.id.demo_id);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
        Log.e("sss", params.toString());
    }
}

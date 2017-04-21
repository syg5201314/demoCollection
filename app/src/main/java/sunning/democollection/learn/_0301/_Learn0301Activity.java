package sunning.democollection.learn._0301;

import android.os.Bundle;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import sunning.democollection.BaseActivity;
import sunning.democollection.R;

/**
 * Created by sunning on 16/3/1.
 * 今天主要研究View的事件体系
 */
public class _Learn0301Activity extends BaseActivity{

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.learn_0301);
        TextView t = new TextView(this);
        t.setOnClickListener(v -> {

        });
        List features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");

    }
}

package sunning.democollection.custom.view.checkbox;

import android.os.Bundle;

import sunning.democollection.BaseActivity;
import sunning.democollection.R;

/**
 * Created by sunning on 16/8/8.
 */

public class CheckBoxActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.check_box);
        CircleCheckBox checkBox = (CircleCheckBox) findViewById(R.id.check);
        checkBox.setOnClickListener(view -> checkBox.toggle());
    }
}

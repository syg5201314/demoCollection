package sunning.democollection.learn._0429;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import javax.inject.Inject;

import sunning.democollection.AndroidApplication;
import sunning.democollection.BaseActivity;
import sunning.democollection.animation.sandbox.Navigator;
import sunning.global.common.ButtonFactory;

/**
 * Created by sunning on 16/4/29.
 * Dagger2最后一个demo 一定搞明白！
 */
public class Dagger2FinalDemo extends BaseActivity {

    @Inject
    public Navigator navigator;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        ((AndroidApplication) getApplication()).getApplicationComponent().inject(this);
        test();
    }

    private void test() {
        ButtonFactory factory = new ButtonFactory(this);
        View view = factory.create(navigator.toString(), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Dagger2FinalDemo.this, "点击按钮了", Toast.LENGTH_SHORT).show();
            }
        });
        setContentView(view);
    }
}

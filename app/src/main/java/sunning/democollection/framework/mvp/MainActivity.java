package sunning.democollection.framework.mvp;
/*
 * Created by sunning on 2016/11/25.
 */

import android.os.Bundle;
import android.util.Log;

import javax.inject.Inject;

import sunning.democollection.BaseActivity;
import sunning.democollection.R;

public class MainActivity extends BaseActivity {

    @Inject
    LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.test);

        LoginFragment fragment = LoginFragment.newInstance();
        DaggerLoginPresenterComponent.builder().presenterModule(new PresenterModule(fragment)).build().inject(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.contentFrame, fragment).commit();
        Log.e("sss", presenter.toString());

    }
}

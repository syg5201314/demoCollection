package sunning.democollection.learn._0506mvp_dagger2.module;

import dagger.Component;
import sunning.democollection.learn._0506mvp_dagger2.LoginContract;
import sunning.democollection.learn._0506mvp_dagger2.MvpActivity;

/**
 * Created by sunning on 16/5/6.
 */
@Component(modules = LoginModule.class)
public interface LoginComponent {

    void inject(MvpActivity mvpActivity);

    LoginContract.Present getPresent();

}

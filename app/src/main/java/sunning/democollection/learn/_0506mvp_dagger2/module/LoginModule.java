package sunning.democollection.learn._0506mvp_dagger2.module;

import dagger.Module;
import dagger.Provides;
import sunning.democollection.learn._0506mvp_dagger2.LoginContract;
import sunning.democollection.learn._0506mvp_dagger2.LoginPresenter;

/**
 * Created by sunning on 16/5/6.
 */
@Module
public class LoginModule {

    private LoginContract.View view;

    public LoginModule(LoginContract.View view) {
        this.view = view;
    }

    @Provides
    public LoginContract.Present getPresent() {
        return new LoginPresenter(view);
    }
}

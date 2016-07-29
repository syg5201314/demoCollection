package sunning.democollection.learn._0506mvp_dagger2.module;

import dagger.Module;
import dagger.Provides;
import sunning.democollection.learn._0506mvp_dagger2.LoginContract;
import sunning.democollection.learn._0506mvp_dagger2.MvpActivity;

/**
 * Created by sunning on 16/5/8.
 */
@Module
public class LoginViewModule {

    @Provides
    public LoginContract.View getView(MvpActivity mvpActivity) {
        return mvpActivity;
    }
}

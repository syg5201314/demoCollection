package sunning.democollection.framework.mvp;
/*
 * Created by sunning on 2016/12/1.
 */

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    private LoginContract.View view;

    public PresenterModule(LoginContract.View view) {
        this.view = view;
    }


    @Provides
    public LoginContract.View getName() {
        return this.view;
    }


}

package sunning.democollection.learn._0429;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import sunning.democollection.AndroidApplication;
import sunning.democollection.animation.sandbox.Navigator;

/**
 * Created by sunning on 16/4/29.
 */
@Module
public class ApplicationModule {

    private AndroidApplication androidApplication;

    public ApplicationModule(AndroidApplication androidApplication) {
        this.androidApplication = androidApplication;
    }


    @Provides
    @Singleton
    public Context provideAndroidApplication(){
        return this.androidApplication;
    }

    @Provides
    @Singleton
    public Navigator provideNavigator(){
        return new Navigator();
    }

    @Provides
    @Singleton
    UserRepository provideUserRepository(UserRepository userDataRepository) {
        return userDataRepository;
    }
}

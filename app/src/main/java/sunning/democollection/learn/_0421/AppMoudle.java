package sunning.democollection.learn._0421;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by sunning on 16/4/25.
 */
@Module
public class AppMoudle {

    private Application application;

    public AppMoudle(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return application;
    }


}

package sunning.democollection.learn._0429;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by sunning on 16/4/29.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(Dagger2FinalDemo baseActivity);

    Context context();

}

package sunning.democollection.learn._0421;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by sunning on 16/4/25.
 */
@Singleton
@Component(modules = AppMoudle.class)
public interface AppComponent {
    Context context();  // 提供Applicaiton的Context
//    Supplier


}

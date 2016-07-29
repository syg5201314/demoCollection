package sunning.democollection.learn._0331.component;

import dagger.Component;
import sunning.democollection.learn._0331.UserActivity;
import sunning.democollection.learn._0331.module.ShoppingCartModule;

/**
 * Created by sunning on 16/3/31.
 */
@Component(dependencies = ActivityComponent.class, modules = ShoppingCartModule.class)
public interface ShoppingCartComponent {
    void inject(UserActivity userActivity);
}

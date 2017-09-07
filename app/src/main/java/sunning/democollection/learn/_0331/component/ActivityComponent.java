package sunning.democollection.learn._0331.component;

import dagger.Component;
import sunning.democollection.learn._0331.UserActivity;
import sunning.democollection.learn._0331.bean.User;
import sunning.democollection.learn._0331.module.UserModule;

/**
 * Created by sunning on 16/3/31.
 */
@Component(modules = UserModule.class)
public interface ActivityComponent {
    void inject(UserActivity activity);
    User getUser();
}

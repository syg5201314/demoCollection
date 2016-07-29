package sunning.democollection.learn._0331.module;

import dagger.Module;
import dagger.Provides;
import sunning.democollection.learn._0331.bean.User;

/**
 * Created by sunning on 16/3/31.
 */
@Module
public class UserModule {
    @Provides
    User provideUserModel(){
        User user = new User();
        user.userName = "大猴子";
        user.pwd = "大猴子520";
        return user;
    }
}

package sunning.democollection.learn._0330.module;

import dagger.Module;
import dagger.Provides;
import sunning.democollection.learn._0330.module.bean.BasePerson;

/**
 * Created by sunning on 16/3/30.
 */
@Module
public class PersonModule {
    @Provides
    BasePerson provideBasePerson(){
        return new BasePerson();
    }
}

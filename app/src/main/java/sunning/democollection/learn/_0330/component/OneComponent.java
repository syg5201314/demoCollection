package sunning.democollection.learn._0330.component;

import dagger.Component;
import sunning.democollection.learn._0330.module.bean.BasePerson;
import sunning.democollection.learn._0330.module.PersonModule;

/**
 * Created by sunning on 16/3/30.
 */
@Component(modules = PersonModule.class)
public interface OneComponent {
    BasePerson basePerson();
}

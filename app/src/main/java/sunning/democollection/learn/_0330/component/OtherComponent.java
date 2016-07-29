package sunning.democollection.learn._0330.component;

import dagger.Component;
import sunning.democollection.learn._0330.LearnDagger2;
import sunning.democollection.learn._0330.module.StudentModule;

/**
 * Created by sunning on 16/3/30.
 */
@Component(dependencies = OneComponent.class,modules = StudentModule.class)
public interface OtherComponent {
    void inject(LearnDagger2 learnDagger2);
}

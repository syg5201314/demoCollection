package sunning.democollection.learn._0330.module;

import dagger.Module;
import dagger.Provides;
import sunning.democollection.learn._0330.module.bean.StudentPerson;

/**
 * Created by sunning on 16/3/30.
 */
@Module
public class StudentModule {
    @Provides
    StudentPerson provideStudentPerson(){
        return new StudentPerson();
    }
}

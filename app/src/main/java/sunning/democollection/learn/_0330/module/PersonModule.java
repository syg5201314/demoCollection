package sunning.democollection.learn._0330.module;

import dagger.Module;
import dagger.Provides;

/**
 * Created by sunning on 16/3/30.
 */
@Module
public class PersonModule {

    public int age;
    public String name;

    public PersonModule(int age, String name) {
        this.age = age;
        this.name = name;
    }

//    @Provides
//    BasePerson provideBasePerson(int age, String name) {
//        return new BasePerson(age, name);
//    }

    @Provides
    public int getAge() {
        return age;
    }

    @Provides
    public String getName() {
        return name;
    }
}

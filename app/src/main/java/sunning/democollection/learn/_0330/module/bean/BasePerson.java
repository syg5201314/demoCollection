package sunning.democollection.learn._0330.module.bean;

import javax.inject.Inject;

/**
 * Created by sunning on 16/3/30.
 */
public class BasePerson {
    public int age;
    public String name;

    @Inject
    public BasePerson(int age, String name) {
        this.age = age;
        this.name = name;
    }
}

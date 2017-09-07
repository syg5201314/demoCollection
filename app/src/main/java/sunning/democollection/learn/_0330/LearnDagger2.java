package sunning.democollection.learn._0330;

import android.os.Bundle;

import sunning.democollection.BaseActivity;
import sunning.democollection.learn._0330.module.bean.BasePerson;

/**
 * Created by sunning on 16/3/30.
 */
public class LearnDagger2 extends BaseActivity {


    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        BasePerson p = new BasePerson(2, "sunning");
//        BasePerson basePerson = DaggerOneComponent.builder().personModule(new PersonModule(444, "Dagger2")).build().basePerson();
//        Log.e("LearnDagger2", "new: " + p.age);
//        Log.e("LearnDagger2", "dagger2: " + basePerson.age);
    }
}

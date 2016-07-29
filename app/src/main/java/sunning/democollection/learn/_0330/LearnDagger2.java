package sunning.democollection.learn._0330;

import android.os.Bundle;

import sunning.democollection.BaseActivity;
import sunning.democollection.learn._0330.component.DaggerOneComponent;
import sunning.democollection.learn._0330.component.DaggerOtherComponent;
import sunning.democollection.learn._0330.component.OneComponent;
import sunning.democollection.learn._0330.component.OtherComponent;
import sunning.democollection.learn._0330.module.PersonModule;
import sunning.democollection.learn._0330.module.StudentModule;

/**
 * Created by sunning on 16/3/30.
 */
public class LearnDagger2 extends BaseActivity {
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        OneComponent build = DaggerOneComponent
                .builder()
                .personModule(new PersonModule())
                .build();

        OtherComponent build1 = DaggerOtherComponent
                .builder()
                .oneComponent(build)
                .studentModule(new StudentModule())
                .build();
        build1.inject(this);
    }
}

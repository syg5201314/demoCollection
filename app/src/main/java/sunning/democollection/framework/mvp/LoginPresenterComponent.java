package sunning.democollection.framework.mvp;
/*
 * Created by sunning on 2016/12/1.
 */

import dagger.Component;

@Component(modules = PresenterModule.class)
public interface LoginPresenterComponent {
    void inject(MainActivity activity);
}

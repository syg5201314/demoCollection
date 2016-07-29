package sunning.democollection.learn._0429;

import dagger.Component;
import sunning.democollection.learn._0331.module.UserModule;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, UserModule.class})
public interface UserComponent extends ActivityComponent {
  void inject(UserListFragment userListFragment);

  void inject(UserDetailsFragment userDetailsFragment);
}
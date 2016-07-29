package sunning.democollection.learn._0429;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
  private final Activity activity;
 
  public ActivityModule(Activity activity) {
    this.activity = activity;
  }
 
  @Provides
  @PerActivity
  Activity activity() {
    return this.activity;
  }
}
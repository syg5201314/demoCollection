package sunning.democollection.learn._0318;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import sunning.democollection.BaseActivity;
import sunning.democollection.R;
import sunning.democollection.databinding.BindingLayoutBinding;
import sunning.democollection.entity.User;

/**
 * Created by sunning on 16/3/18.
 */
public class DataBindingActivity extends BaseActivity{
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        BindingLayoutBinding bindingLayoutBinding = DataBindingUtil.setContentView(this, R.layout.binding_layout);
        User user = new User();
        user.setName("大猴子");
        user.setNickName("大猴子超级美丽");
        bindingLayoutBinding.setUser(user);
    }
}

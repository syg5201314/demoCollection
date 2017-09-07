package sunning.democollection.learn._0331;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import javax.inject.Inject;

import sunning.democollection.BaseActivity;
import sunning.democollection.R;
import sunning.democollection.learn._0331.bean.ShoppingCart;
import sunning.democollection.learn._0331.bean.User;
import sunning.democollection.learn._0331.component.ActivityComponent;

//import sunning.democollection.learn._0331.component.DaggerActivityComponent;
//import sunning.democollection.learn._0331.component.DaggerShoppingCartComponent;

/**
 * Created by sunning on 16/3/31.
 */
public class UserActivity extends BaseActivity{

    @Inject User user;
    @Inject ShoppingCart shoppingCart;

    private ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.dagger_layout);
//        mActivityComponent = DaggerActivityComponent.builder().userModule(new UserModule()).build();
//        ShoppingCartComponent build = DaggerShoppingCartComponent.builder().activityComponent(mActivityComponent).shoppingCartModule(new ShoppingCartModule()).build();
        User user = mActivityComponent.getUser();
//        build.inject(this);
//        mActivityComponent.inject(this);
        ((TextView)findViewById(R.id.name)).setText(user.userName);
        ((TextView)findViewById(R.id.pwd)).setText(user.pwd);
        Log.e("user.userName",user.userName);
        Log.e("user.user.pwd",user.pwd);
        Log.e("user.user.pwd",shoppingCart.productName+"-===="+shoppingCart.count+"==");
    }

}

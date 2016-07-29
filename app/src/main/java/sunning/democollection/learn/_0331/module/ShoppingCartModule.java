package sunning.democollection.learn._0331.module;

import dagger.Module;
import dagger.Provides;
import sunning.democollection.learn._0331.bean.ShoppingCart;

/**
 * Created by sunning on 16/3/31.
 */
@Module
public class ShoppingCartModule {
    @Provides ShoppingCart providerShoppingCart(){
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.count = 100;
        shoppingCart.productName="iphone100 1024T 24K纯金镶钻版";
        return shoppingCart;
    }
}

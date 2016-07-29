package sunning.democollection.learn._0506mvp_dagger2;

import sunning.democollection.learn._0506mvp_dagger2.base.BasePresenter;
import sunning.democollection.learn._0506mvp_dagger2.base.BaseView;

public class LoginContract {
    public interface View extends BaseView<Present> {
        void loginError(String msg);

        void loginSuccess();

        String getAccount();

        String getPassword();
    }

    public interface Present extends BasePresenter {
        void login();
    }

}
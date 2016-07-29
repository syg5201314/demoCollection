package sunning.democollection.learn._0506mvp.login;

import sunning.democollection.learn._0506mvp.base.BasePresenter;
import sunning.democollection.learn._0506mvp.base.BaseView;

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
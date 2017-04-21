package sunning.democollection.framework.mvp;
/*
 * Created by sunning on 2016/11/30.
 */

public interface LoginContract {

    interface Presenter extends BasePresenter {

        void startLogin(String userName, String pwd);

        void loginSuccess();

        void loginFail();
    }

    interface View extends BaseView<Presenter>{

        void showProgress();

        void dismissProgress();

        void startActivity();

    }
}

package sunning.democollection.framework.mvp;
/*
 * Created by sunning on 2016/11/30.
 */

import android.util.Log;

import javax.inject.Inject;

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View loginView;

    @Inject
    public LoginPresenter(LoginContract.View loginView) {
        this.loginView = loginView;
        loginView.setPresenter(this);
    }

    @Override
    public void startLogin(String userName, String pwd) {
        Log.e("hahaha","开始登录======");
        Log.e("hahaha","用户名===" + userName);
        Log.e("hahaha","密码===" + pwd);
        loginSuccess();
    }

    @Override
    public void loginSuccess() {
        Log.e("hahaha","登录成功");
        loginView.startActivity();
    }

    @Override
    public void loginFail() {

    }

    @Override
    public void start() {
    }
}

package sunning.democollection.learn._0506mvp_dagger2;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import javax.inject.Inject;

import sunning.democollection.BaseActivity;
import sunning.democollection.R;
import sunning.democollection.databinding.MvpMainBinding;
import sunning.democollection.learn._0506mvp_dagger2.module.DaggerLoginComponent;
import sunning.democollection.learn._0506mvp_dagger2.module.LoginModule;

/**
 * Created by sunning on 16/5/6.
 */
public class MvpActivity extends BaseActivity implements View.OnClickListener, LoginContract.View {

    private MvpMainBinding binding;
    @Inject
    public LoginContract.Present mPresent;


    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        binding = DataBindingUtil.setContentView(this, R.layout.mvp_main);
        DaggerLoginComponent.builder().loginModule(new LoginModule(this)).build().inject(this);
        initView();
    }

    private void initView() {
        binding.startLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        mPresent.login();
    }

    @Override
    public void loginError(String msg) {
        Toast.makeText(MvpActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginSuccess() {
        Toast.makeText(MvpActivity.this, "登录成功", Toast.LENGTH_SHORT).show();

    }

    @Override
    public String getAccount() {
        return binding.userName.getText().toString();
    }

    @Override
    public String getPassword() {
        return binding.passwd.getText().toString();
    }

    @Override
    public void setPresenter(LoginContract.Present presenter) {

    }
}

package sunning.democollection.learn._0506mvp;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import sunning.democollection.BaseActivity;
import sunning.democollection.R;
import sunning.democollection.databinding.MvpMainBinding;
import sunning.democollection.learn._0506mvp.login.LoginContract;

/**
 * Created by sunning on 16/5/6.
 */
public class MvpActivity extends BaseActivity implements View.OnClickListener , LoginContract.View{

    private MvpMainBinding binding;
    private LoginContract.Present mPresent;


    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        binding = DataBindingUtil.setContentView(this, R.layout.mvp_main);
        initView();
    }

    private void initView() {
        mPresent = new LoginPresenter(this);
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

package sunning.democollection.framework.mvp;
/*
 * Created by sunning on 2016/11/30.
 */

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sunning.democollection.R;
import sunning.democollection.databinding.MvpMainBinding;
import sunning.democollection.learn._0316.TestActivity;

public class LoginFragment extends Fragment implements LoginContract.View {

    private MvpMainBinding binging;

    private LoginContract.Presenter mPersenter;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binging.startLogin.setOnClickListener(v -> {
            mPersenter.startLogin(binging.userName.getText().toString(), binging.userName.getText().toString());
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binging = DataBindingUtil.inflate(inflater, R.layout.mvp_main, container,false);
        return binging.getRoot();
    }

    @Override
    public void showProgress() {
        Log.e("hahaha", "loading。。。。开始转转转");
    }

    @Override
    public void onResume() {
        super.onResume();
        mPersenter.start();
    }


    @Override
    public void dismissProgress() {
        Log.e("hahaha", "loading。。。。停停停停停");
    }

    @Override
    public void startActivity() {
        Intent intent = new Intent(getContext(), TestActivity.class);
        getContext().startActivity(intent);
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        this.mPersenter = presenter;
    }
}

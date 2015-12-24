package thenextapp.mvpdemo.login;

import android.os.*;

import thenextapp.mvpdemo.api.Api;
import thenextapp.mvpdemo.base.BasePresenter;
import thenextapp.mvpdemo.model.User;

/**
 * Created by Sandy on 12/25/15.
 */
public class LoginPresenter extends BasePresenter<ILoginView> {

    @Override
    public void attachView(ILoginView iLoginView) {
        super.attachView(iLoginView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    public void login(final User user) {
        getMvpView().showLoading();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean success =  Api.requestCheckLogin(user);

                if (success) {
                    getMvpView().showSuccessLoginUI();
                } else {
                    getMvpView().showErrorLoginUI();
                }

                getMvpView().hideLoading();
            }
        }, 6000);
    }

}

package thenextapp.mvpdemo.login;

import thenextapp.mvpdemo.base.MvpView;

/**
 * Created by Sandy on 12/25/15.
 */
public interface ILoginView extends MvpView {
    public void showLoading();
    public void hideLoading();
    public void showSuccessLoginUI();
    public void showErrorLoginUI();
}

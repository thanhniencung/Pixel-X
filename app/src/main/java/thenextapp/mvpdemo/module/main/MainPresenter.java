package thenextapp.mvpdemo.module.main;

import android.app.Activity;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import thenextapp.mvpdemo.BuildConfig;
import thenextapp.mvpdemo.GroovyApplication;
import thenextapp.mvpdemo.R;
import thenextapp.mvpdemo.data.model.Photo;
import thenextapp.mvpdemo.data.model.PhotoDetail;
import thenextapp.mvpdemo.module.base.BaseActivity;
import thenextapp.mvpdemo.module.base.BasePresenter;
import thenextapp.mvpdemo.data.api.PhotoApi;
import thenextapp.mvpdemo.data.local.SharedPreferenceConfig;
import thenextapp.mvpdemo.data.local.SharedPreferenceHelper;
import thenextapp.mvpdemo.data.model.PhotoList;
import thenextapp.mvpdemo.uitil.LogUtils;
import thenextapp.mvpdemo.uitil.StringUtils;
import thenextapp.pxsdk.FiveHundredException;
import thenextapp.pxsdk.auth.AccessToken;
import thenextapp.pxsdk.tasks.XAuth500pxTask;

/**
 * Created by Sandy on 12/26/15.
 */
public class MainPresenter extends BasePresenter<IMainView> implements XAuth500pxTask.Delegate {

    private static final String TAG = "MainPresenter";

    @Inject
    PhotoApi photoApi;

    private Activity activity;

    public MainPresenter(Activity activity) {
        this.activity = activity;
        ((GroovyApplication) activity.getApplication()).getAppComponent().inject(this);
    }

    @Override
    public void attachView(IMainView iMainView) {
        super.attachView(iMainView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    public void login() {
        if (shouldRequestToeken()) {
            XAuth500pxTask loginTask = new XAuth500pxTask(this);
            loginTask.execute(BuildConfig.CONSUMER_KEY, BuildConfig.CONSUMER_KEY_SECRET,
                    BuildConfig.USERNAME, BuildConfig.PASSWORD);
        }
    }

    public void getPhotos(int page, String feature, boolean isLoadMore) {
        LogUtils.info(TAG, page + "");

        if (isLoadMore) {
            getMvpView().loadMore();
        } else {
            getMvpView().showLoadingData();
        }
        photoApi.getFeaturePhotos(page, feature)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PhotoList>() {
                    @Override
                    public void onCompleted() {
                        getMvpView().hideLoadingData();
                    }

                    @Override
                    public void onError(Throwable e) {
                        getMvpView().showError(e.getMessage());
                    }

                    @Override
                    public void onNext(PhotoList photoList) {
                        getMvpView().showPhotoList(photoList);
                    }
                });
    }

    private boolean shouldRequestToeken() {
        String token = SharedPreferenceHelper.getSharedPreferenceString(activity, SharedPreferenceConfig.TOKEN, "");
        String tokenSecret = SharedPreferenceHelper.getSharedPreferenceString(activity, SharedPreferenceConfig.TOKEN_SECRET, "");

        if (StringUtils.isEmpty(token) || StringUtils.isEmpty(tokenSecret))
            return true;

        return false;
    }

    @Override
    public void onSuccess(AccessToken result) {
        if (result != null && !StringUtils.isEmpty(result.getToken()) &&
                !StringUtils.isEmpty(result.getTokenSecret())) {
            SharedPreferenceHelper.setSharedPreferenceString(activity, SharedPreferenceConfig.TOKEN, result.getToken());
            SharedPreferenceHelper.setSharedPreferenceString(activity, SharedPreferenceConfig.TOKEN_SECRET, result.getTokenSecret());
        }
    }

    @Override
    public void onFail(FiveHundredException e) {
        getMvpView().showError(e.getMessage());
    }
}

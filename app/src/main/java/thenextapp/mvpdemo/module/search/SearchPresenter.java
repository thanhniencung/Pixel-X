package thenextapp.mvpdemo.module.search;

import android.app.Activity;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import thenextapp.mvpdemo.GroovyApplication;
import thenextapp.mvpdemo.data.api.PhotoApi;
import thenextapp.mvpdemo.data.model.PhotoList;
import thenextapp.mvpdemo.module.base.BasePresenter;
import thenextapp.mvpdemo.module.photo.IPhotoView;

/**
 * Created by Sandy on 12/26/15.
 */
public class SearchPresenter extends BasePresenter<ISearchView> {

    @Inject
    PhotoApi photoApi;

    private Activity activity;

    public SearchPresenter(Activity activity) {
        this.activity = activity;
        ((GroovyApplication) activity.getApplication()).getAppComponent().inject(this);
    }

    @Override
    public void attachView(ISearchView iSearchView) {
        super.attachView(iSearchView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    public void search(String keyword) {

        getMvpView().showLoadingData();

        photoApi.search(keyword)
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
                        if (photoList != null && photoList.photos.size() > 0) {
                            getMvpView().showResult(photoList.photos);
                        } else {
                            getMvpView().emptyResult();
                        }
                    }
                });
    }
}

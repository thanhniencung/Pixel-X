package thenextapp.mvpdemo.module.photo;

import android.app.Activity;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import thenextapp.mvpdemo.GroovyApplication;
import thenextapp.mvpdemo.R;
import thenextapp.mvpdemo.data.api.PhotoApi;
import thenextapp.mvpdemo.data.model.Photo;
import thenextapp.mvpdemo.data.model.PhotoDetail;
import thenextapp.mvpdemo.data.model.PhotoList;
import thenextapp.mvpdemo.module.base.BaseActivity;
import thenextapp.mvpdemo.module.base.BasePresenter;
import thenextapp.mvpdemo.module.main.IMainView;

/**
 * Created by Sandy on 12/26/15.
 */
public class PhotoPresenter extends BasePresenter<IPhotoView> {

    @Inject
    PhotoApi photoApi;

    private Activity activity;

    public PhotoPresenter(Activity activity) {
        this.activity = activity;
        ((GroovyApplication) activity.getApplication()).getAppComponent().inject(this);
    }

    @Override
    public void attachView(IPhotoView iPhotoView) {
        super.attachView(iPhotoView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    public void getPhotoById(String id) {
        getMvpView().loadingImage();
        photoApi.getPhotoById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<PhotoDetail>() {
                    @Override
                    public void call(PhotoDetail photoDetail) {
                        if (photoDetail != null) {
                            getMvpView().imageLoaded(photoDetail.photo);
                        } else {
                            getMvpView().errorLoadingImage(activity.getString(R.string.empty_result));
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        getMvpView().errorLoadingImage(throwable.getMessage());
                    }
                });
    }

}

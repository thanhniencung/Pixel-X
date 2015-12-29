package thenextapp.mvpdemo.module.main;

import java.util.List;

import thenextapp.mvpdemo.data.model.Photo;
import thenextapp.mvpdemo.module.base.MvpView;
import thenextapp.mvpdemo.data.model.PhotoList;

/**
 * Created by Sandy on 12/26/15.
 */
public interface IMainView extends MvpView {
    public void showLoadingData();
    public void hideLoadingData();
    public void showError(String e);
    public void showPhotoList(PhotoList photo);
    public void loadMore();
}

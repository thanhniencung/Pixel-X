package thenextapp.mvpdemo.module.photo;

import thenextapp.mvpdemo.data.model.Photo;
import thenextapp.mvpdemo.module.base.MvpView;

/**
 * Created by Sandy on 12/26/15.
 */
public interface IPhotoView extends MvpView {
    public void loadingImage();
    public void imageLoaded(Photo photo);
    public void errorLoadingImage(String e);
}

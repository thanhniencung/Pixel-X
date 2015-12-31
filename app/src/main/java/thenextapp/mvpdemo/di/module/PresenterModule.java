package thenextapp.mvpdemo.di.module;

import android.app.Activity;
import android.content.Context;

import java.lang.ref.WeakReference;

import dagger.Module;
import dagger.Provides;
import thenextapp.mvpdemo.di.scope.PerActivity;
import thenextapp.mvpdemo.module.main.MainPresenter;
import thenextapp.mvpdemo.module.photo.PhotoPresenter;
import thenextapp.mvpdemo.module.search.SearchPresenter;

/**
 * Created by Sandy on 12/31/15.
 */

@Module
public class PresenterModule {

    @Provides
    @PerActivity
    MainPresenter providesMainPresenter(Activity activity) {
        return new MainPresenter(activity);
    }

    @Provides
    @PerActivity
    PhotoPresenter providesPhotoPresenter(Activity activity) {
        return new PhotoPresenter(activity);
    }

    @Provides
    @PerActivity
    SearchPresenter providesSearchPresenter(Activity activity) {
        return new SearchPresenter(activity);
    }

}

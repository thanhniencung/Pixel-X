package thenextapp.mvpdemo.di.component;

import javax.inject.Singleton;

import dagger.Component;
import thenextapp.mvpdemo.di.module.ApplicationModule;
import thenextapp.mvpdemo.di.module.PixelApiModule;
import thenextapp.mvpdemo.module.base.BasePresenter;
import thenextapp.mvpdemo.module.main.MainActivity;
import thenextapp.mvpdemo.module.main.MainPresenter;
import thenextapp.mvpdemo.module.photo.PhotoPresenter;
import thenextapp.mvpdemo.module.search.SearchPresenter;

/**
 * Created by Sandy on 12/26/15.
 */
@Singleton
@Component(
        modules = {
                ApplicationModule.class,
                PixelApiModule.class
        }
)
public interface ApplicationComponent  {
    void inject(MainPresenter mainPresenter);
    void inject(PhotoPresenter photoPresenter);
    void inject(SearchPresenter searchPresenter);
}
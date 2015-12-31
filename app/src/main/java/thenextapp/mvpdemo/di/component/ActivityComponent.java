package thenextapp.mvpdemo.di.component;

import android.support.design.widget.TabLayout;

import dagger.Component;
import dagger.Module;
import thenextapp.mvpdemo.di.module.ActivityModule;
import thenextapp.mvpdemo.di.module.PresenterModule;
import thenextapp.mvpdemo.di.scope.PerActivity;
import thenextapp.mvpdemo.module.main.MainActivity;
import thenextapp.mvpdemo.module.photo.PhotoActivity;
import thenextapp.mvpdemo.module.search.SearchActivity;

/**
 * Created by Sandy on 12/31/15.
 */
@PerActivity
@Component(
        dependencies = {
          ApplicationComponent.class
        },
        modules = {
           ActivityModule.class,
           PresenterModule.class
        }
)
public interface ActivityComponent {
    void inject(MainActivity mainActivity);
    void inject(PhotoActivity photoActivity);
    void inject(SearchActivity searchActivity);
}

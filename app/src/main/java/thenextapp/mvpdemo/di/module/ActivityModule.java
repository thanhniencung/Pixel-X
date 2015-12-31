package thenextapp.mvpdemo.di.module;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;
import thenextapp.mvpdemo.di.scope.PerActivity;

/**
 * Created by Sandy on 12/31/15.
 */
@Module
public class ActivityModule {
    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    Activity activity() {
        return activity;
    }
}
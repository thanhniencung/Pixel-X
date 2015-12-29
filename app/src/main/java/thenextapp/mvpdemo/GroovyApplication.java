package thenextapp.mvpdemo;

import android.app.Application;

import thenextapp.mvpdemo.di.component.ApplicationComponent;
import thenextapp.mvpdemo.di.component.DaggerApplicationComponent;
import thenextapp.mvpdemo.di.module.ApplicationModule;

/**
 * Created by Sandy on 12/26/15.
 */
public class GroovyApplication extends Application {

    private ApplicationComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initAppComponent();
    }

    private void initAppComponent() {
        appComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getAppComponent() {
        return appComponent;
    }

}

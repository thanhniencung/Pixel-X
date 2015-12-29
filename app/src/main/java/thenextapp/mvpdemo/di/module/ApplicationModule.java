package thenextapp.mvpdemo.di.module;

import android.app.Application;
import android.content.res.Resources;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import thenextapp.mvpdemo.GroovyApplication;

/**
 * Created by Sandy on 12/26/15.
 */
@Module
public class ApplicationModule {

    private final GroovyApplication app;

    public ApplicationModule(GroovyApplication application) {
        app = application;
    }

    @Provides
    @Singleton
    protected Application provideApplication() {
        return app;
    }

    @Provides
    @Singleton
    protected Resources provideResources() {
        return app.getResources();
    }
}
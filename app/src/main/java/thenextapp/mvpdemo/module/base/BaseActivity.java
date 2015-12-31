package thenextapp.mvpdemo.module.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import thenextapp.mvpdemo.GroovyApplication;
import thenextapp.mvpdemo.di.component.ActivityComponent;
import thenextapp.mvpdemo.di.component.DaggerActivityComponent;
import thenextapp.mvpdemo.di.module.ActivityModule;
import thenextapp.mvpdemo.di.module.PresenterModule;

/**
 * Created by Sandy on 12/25/15.
 */
public class BaseActivity extends AppCompatActivity {

    private ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public ActivityComponent getActivityComponent() {
        if (mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent.builder()
                    .applicationComponent(((GroovyApplication) getApplication()).getAppComponent())
                    .activityModule(new ActivityModule(this))
                    .presenterModule(new PresenterModule())
                    .build();
        }
        return mActivityComponent;
    }

}

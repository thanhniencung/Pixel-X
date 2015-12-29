package thenextapp.mvpdemo.module.welcome;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.os.*;
import butterknife.Bind;
import butterknife.ButterKnife;
import thenextapp.mvpdemo.R;
import thenextapp.mvpdemo.module.main.MainActivity;
import thenextapp.mvpdemo.widget.AnimatedGroovyLogoView;

/**
 * Created by Sandy on 12/26/15.
 */
public class WelcomeActivity extends Activity {

    @Bind(R.id.welcomeView)
    LinearLayout welcomeView;

    @Bind(R.id.animated_logo)
    AnimatedGroovyLogoView animatedGroovyLogoView;

    @Bind(R.id.logo_subtitle)
    ImageView subTitle;

    private float mInitialLogoOffset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        mInitialLogoOffset = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16,
                getResources().getDisplayMetrics());

        ButterKnife.bind(this);

        animatedGroovyLogoView.setOnStateChangeListener(new AnimatedGroovyLogoView.OnStateChangeListener() {
            @Override
            public void onStateChange(int state) {
                if (state == AnimatedGroovyLogoView.STATE_FILL_STARTED) {
                    subTitle.setAlpha(0.0f);
                    subTitle.setVisibility(View.VISIBLE);
                    subTitle.setTranslationY(-subTitle.getHeight());

                    AnimatorSet set = new AnimatorSet();
                    Interpolator interpolator = new OvershootInterpolator();
                    ObjectAnimator a1 = ObjectAnimator.ofFloat(animatedGroovyLogoView, View.TRANSLATION_Y, 0);
                    ObjectAnimator a2 = ObjectAnimator.ofFloat(subTitle,
                            View.TRANSLATION_Y, 0);
                    ObjectAnimator a3 = ObjectAnimator.ofFloat(subTitle, View.ALPHA, 1);
                    a1.setInterpolator(interpolator);
                    a2.setInterpolator(interpolator);
                    set.setDuration(500).playTogether(a1, a2, a3);
                    set.start();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }, 3000);
                }
            }
        });
        reset();
        start();
    }

    public void start() {
        animatedGroovyLogoView.start();
    }

    public void reset() {
        animatedGroovyLogoView.reset();
        animatedGroovyLogoView.setTranslationY(mInitialLogoOffset);
        subTitle.setVisibility(View.INVISIBLE);
    }
}

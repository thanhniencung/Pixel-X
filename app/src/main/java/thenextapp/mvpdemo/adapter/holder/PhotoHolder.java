package thenextapp.mvpdemo.adapter.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import thenextapp.mvpdemo.R;
import thenextapp.mvpdemo.widget.PhotoView;

/**
 * Created by Sandy on 12/27/15.
 */
public class PhotoHolder extends MainHolder {

    @Bind(R.id.photo)
    public PhotoView photo;

    public PhotoHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

}

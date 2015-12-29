package thenextapp.mvpdemo.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import thenextapp.mvpdemo.R;
import thenextapp.mvpdemo.adapter.holder.MainHolder;
import thenextapp.mvpdemo.adapter.holder.PhotoHolder;
import thenextapp.mvpdemo.data.model.Photo;
import thenextapp.mvpdemo.data.model.PhotoList;

/**
 * Created by Sandy on 12/27/15.
 */
public class PhotoAdapter extends RecyclerView.Adapter<MainHolder> {

    private Activity mActivity;
    private List<Photo> photoList;

    private MainHolder.ClickListener clickListener;

    public PhotoAdapter(Activity activity, List<Photo> photoList, MainHolder.ClickListener clickListener) {
        mActivity = activity;
        this.photoList = photoList;
        this.clickListener = clickListener;
    }

    public void addPhoto(Photo photo) {
        photoList.add(photo);
        notifyDataSetChanged();
    }

    public void setData(List<Photo> newPhotoList) {
        photoList.addAll(newPhotoList);
        notifyDataSetChanged();
    }

    @Override
    public PhotoHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());

        ViewGroup view = (ViewGroup) mInflater.inflate(R.layout.adapter_photo, parent, false);
        PhotoHolder photoHolder = new PhotoHolder(view);
        photoHolder.setClickListener(clickListener);
        return photoHolder;
    }

    @Override
    public void onBindViewHolder(MainHolder viewHolder, int position) {
        PhotoHolder photoHolder = (PhotoHolder) viewHolder;
        Picasso.with(mActivity)
                .load(photoList.get(position).image_url)
                .into(photoHolder.photo);
    }

    @Override
    public int getItemCount() {
        if (photoList != null) {
            return photoList.size();
        }
        return 0;
    }
}
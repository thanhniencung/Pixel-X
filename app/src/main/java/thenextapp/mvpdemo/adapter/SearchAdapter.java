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
import thenextapp.mvpdemo.adapter.holder.SearchHolder;
import thenextapp.mvpdemo.data.model.Photo;

/**
 * Created by Sandy on 12/29/15.
 */
public class SearchAdapter extends RecyclerView.Adapter<MainHolder> {

    private Activity mActivity;
    private List<Photo> photoList;

    private MainHolder.ClickListener clickListener;

    public SearchAdapter(Activity activity, List<Photo> photoList, MainHolder.ClickListener clickListener) {
        mActivity = activity;
        this.photoList = photoList;
        this.clickListener = clickListener;
    }

    public void setData(List<Photo> newPhotoList) {
        photoList.clear();
        photoList.addAll(newPhotoList);
        notifyDataSetChanged();
    }

    @Override
    public MainHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());

        ViewGroup view = (ViewGroup) mInflater.inflate(R.layout.adapter_search, parent, false);
        SearchHolder searchHolder = new SearchHolder(view);
        searchHolder.setClickListener(clickListener);
        return searchHolder;
    }

    @Override
    public void onBindViewHolder(MainHolder viewHolder, int position) {
        SearchHolder searchHolder = (SearchHolder) viewHolder;
        Picasso.with(mActivity)
                .load(photoList.get(position).image_url)
                .into(searchHolder.photo);
        searchHolder.title.setText(photoList.get(position).name);
    }

    @Override
    public int getItemCount() {
        if (photoList != null) {
            return photoList.size();
        }
        return 0;
    }
}

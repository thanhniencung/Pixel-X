package thenextapp.mvpdemo.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sandy on 12/26/15.
 */
public class PhotoList {
    @SerializedName("photos")
    public List<Photo> photos = new ArrayList<Photo>();
}

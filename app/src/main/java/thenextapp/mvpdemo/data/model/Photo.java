package thenextapp.mvpdemo.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Sandy on 12/26/15.
 */
public class Photo implements Parcelable {

    @SerializedName("image_url")
    public String image_url;

    @SerializedName("name")
    public String name;

    @SerializedName("url")
    public String url;

    @SerializedName("id")
    public String id;

    @SerializedName("description")
    public String description;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.image_url);
        dest.writeString(this.name);
        dest.writeString(this.url);
        dest.writeString(this.id);
        dest.writeString(this.description);
    }

    public Photo() {
    }

    private Photo(Parcel in) {
        this.image_url = in.readString();
        this.name = in.readString();
        this.url = in.readString();
        this.id = in.readString();
        this.description = in.readString();
    }

    public static final Creator<Photo> CREATOR = new Creator<Photo>() {
        public Photo createFromParcel(Parcel source) {
            return new Photo(source);
        }

        public Photo[] newArray(int size) {
            return new Photo[size];
        }
    };
}

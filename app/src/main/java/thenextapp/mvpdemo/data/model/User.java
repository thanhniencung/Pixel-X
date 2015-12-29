package thenextapp.mvpdemo.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Sandy on 12/25/15.
 */
public class User implements Parcelable {

    @SerializedName("firstname")
    public String firstname;

    @SerializedName("fullname")
    public String fullname;

    @SerializedName("userpic_url")
    public String userpic_url;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.firstname);
        dest.writeString(this.fullname);
        dest.writeString(this.userpic_url);
    }

    public User() {
    }

    private User(Parcel in) {
        this.firstname = in.readString();
        this.fullname = in.readString();
        this.userpic_url = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };
}

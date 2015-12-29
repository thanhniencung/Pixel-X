package thenextapp.mvpdemo.data.api;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;
import thenextapp.mvpdemo.BuildConfig;
import thenextapp.mvpdemo.data.model.Photo;
import thenextapp.mvpdemo.data.model.PhotoDetail;
import thenextapp.mvpdemo.data.model.PhotoList;
import thenextapp.mvpdemo.data.model.User;

/**
 * Created by Sandy on 12/25/15.
 */
public interface PhotoApi {

    @GET("/v1/photos?image_size=440&include_store=store_download&include_states=voted&consumer_key=rPv5iXqGa5QT5uQSNP8Y8Ja044qfUEN6pDCO2UFx")
    Observable<PhotoList> getFeaturePhotos(@Query("page") int page, @Query("feature") String feature);

    @GET("/v1/photos/{id}?image_size=1080&consumer_key=rPv5iXqGa5QT5uQSNP8Y8Ja044qfUEN6pDCO2UFx")
    Observable<PhotoDetail> getPhotoById(@Path("id") String id);

    @GET("/v1/photos/search?consumer_key=rPv5iXqGa5QT5uQSNP8Y8Ja044qfUEN6pDCO2UFx")
    Observable<PhotoList> search(@Query("term") String keyword);

}

package xyz.ibat.sloth.network.api;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
import xyz.ibat.sloth.domain.main.model.DataModel;

/**
 * Created by DongJr on 2017/1/13.
 */

public interface ApiService {

    @GET("data/{type}/10/{page}")
    Observable<DataModel> getAndroidData(@Path("type") String type, @Path("page") int page);

}

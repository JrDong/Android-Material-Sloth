package xyz.ibat.sloth.network.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import xyz.ibat.sloth.domain.main.model.HomeDataModel;

/**
 * Created by DongJr on 2017/1/13.
 */

public interface ApiService {

    @GET("{type}/10/{page}")
    Call<HomeDataModel> getAndroidData(@Path("type") String type, @Path("page") int page);



}

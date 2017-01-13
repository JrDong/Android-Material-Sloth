package xyz.ibat.sloth.network.api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import xyz.ibat.sloth.domain.main.model.HomeDataModel;

/**
 * Created by DongJr on 2017/1/13.
 */

public class RetrofitFactory {

    private static final String BASE_URL = "http://gank.io/api/";

    private Retrofit retrofit;

    private ApiService apiService;

    private RetrofitFactory() {

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    private static class SingletonHolder {
        private static final RetrofitFactory INSTANCE = new RetrofitFactory();
    }

    public static RetrofitFactory getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void getAndroidData(Subscriber<HomeDataModel> subscriber, String type, int pageIndex) {

        apiService.getAndroidData(type, pageIndex)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

    }


}

package xyz.ibat.sloth.domain.main.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.ibat.sloth.R;
import xyz.ibat.sloth.domain.main.adapter.HomeAdapter;
import xyz.ibat.sloth.domain.main.model.HomeDataModel;
import xyz.ibat.sloth.network.api.ApiService;
import xyz.ibat.sloth.utils.T;
import xyz.ibat.sloth.view.SlothRecycler;

/**
 * Created by DongJr on 2016/03/31
 */
public class AndroidFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {


    @Bind(R.id.recycler_one)
    SlothRecycler mRecyclerView;
    @Bind(R.id.refresh_one)
    SwipeRefreshLayout mRefresh;

    private int mPageIndex = 1;

    private HomeAdapter mAdapter;

    public static String TYPE_TAG = "TYPE_TAG";

    private String mType;

    private Retrofit retrofit;
    private Call<HomeDataModel> call;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mType = getArguments().getString(TYPE_TAG);

        mRefresh.setColorSchemeResources(android.R.color.holo_blue_bright);
        mRefresh.setOnRefreshListener(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mAdapter = new HomeAdapter(getContext());
        mRecyclerView.setAdapter(mAdapter);

        requestData();
    }

    private void requestData() {
        String baseUrl = "http://gank.io/api/data/";

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        call = apiService.getAndroidData(mType, mPageIndex);

        call.enqueue(new Callback<HomeDataModel>() {
            @Override
            public void onResponse(Call<HomeDataModel> call, Response<HomeDataModel> response) {
                if (mRefresh != null) {
                    mRefresh.setRefreshing(false);
                }

                if (mAdapter == null) {
                    mAdapter = new HomeAdapter(getContext(), response.body());
                    mRecyclerView.setAdapter(mAdapter);
                } else {
                    mAdapter.setData(response.body());
                }
            }

            @Override
            public void onFailure(Call<HomeDataModel> call, Throwable t) {
                if (mRefresh != null) {
                    mRefresh.setRefreshing(false);
                }
                T.show(t.getMessage());
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (call != null) {
            call.cancel();
        }
        ButterKnife.unbind(this);
    }

    @Override
    public void onRefresh() {
        mPageIndex = 1;
        requestData();
    }

}

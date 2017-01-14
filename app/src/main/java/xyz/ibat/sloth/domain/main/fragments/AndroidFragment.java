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
import retrofit2.Retrofit;
import rx.Subscriber;
import xyz.ibat.sloth.R;
import xyz.ibat.sloth.domain.main.adapter.HomeAdapter;
import xyz.ibat.sloth.domain.main.model.HomeDataModel;
import xyz.ibat.sloth.network.RetrofitFactory;
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
        RetrofitFactory.getInstance().getAndroidData(new Subscriber<HomeDataModel>() {
            @Override
            public void onCompleted() {
                if (mRefresh != null) {
                    mRefresh.setRefreshing(false);
                }
            }

            @Override
            public void onError(Throwable e) {
                T.show(e.getMessage());
            }

            @Override
            public void onNext(HomeDataModel model) {
                if (mAdapter == null) {
                    mAdapter = new HomeAdapter(getContext(), model);
                    mRecyclerView.setAdapter(mAdapter);
                } else {
                    mAdapter.setData(model);
                }
            }
        }, mType, mPageIndex);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onRefresh() {
        mPageIndex = 1;
        requestData();
    }

}

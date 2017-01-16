package xyz.ibat.sloth.domain.main.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;
import xyz.ibat.sloth.R;
import xyz.ibat.sloth.domain.main.model.DataModel;
import xyz.ibat.sloth.network.RetrofitFactory;
import xyz.ibat.sloth.utils.T;
import xyz.ibat.sloth.view.SlothRecycler;

/**
 * Created by DongJr on 2016/03/31
 */
public class DataFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.recycler_one)
    SlothRecycler mRecyclerView;
    @Bind(R.id.refresh_one)
    SwipeRefreshLayout mRefresh;


    private CommonAdapter mAdapter;
    private LoadMoreWrapper mLoadMoreWrapper;

    public static String TYPE_TAG = "TYPE_TAG";
    private int mPageIndex = 1;
    private String mType;

    private List<DataModel.ResultsBean> mResultsList = new ArrayList<>();

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

        initAdapter();

        requestData();
    }

    private void initAdapter() {
        mAdapter = new CommonAdapter<DataModel.ResultsBean>(getContext(), R.layout.item_fragment_home, mResultsList) {
            @Override
            protected void convert(ViewHolder holder, DataModel.ResultsBean resultsBean, int position) {
                holder.setText(R.id.tv_title, resultsBean.getDesc())
                        .setText(R.id.tv_author, resultsBean.getWho())
                        .setText(R.id.tv_date, resultsBean.getCreatedAt());
            }
        };
        mLoadMoreWrapper = new LoadMoreWrapper(mAdapter);
        mLoadMoreWrapper.setLoadMoreView(R.layout.default_loading);
        mLoadMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mPageIndex++;
                requestData();
            }
        });

        mLoadMoreWrapper.setShouldLoading(false);

        mRecyclerView.setAdapter(mLoadMoreWrapper);

    }

    private void requestData() {
        RetrofitFactory.getInstance().getAndroidData(new Subscriber<DataModel>() {
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
            public void onNext(DataModel model) {
                mLoadMoreWrapper.setShouldLoading(true);

                if (mPageIndex == 1) {
                    mResultsList.clear();
                }
                mResultsList.addAll(model.getResults());
                mLoadMoreWrapper.notifyDataSetChanged();

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

package xyz.ibat.sloth.domain.main.fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;
import xyz.ibat.sloth.R;
import xyz.ibat.sloth.base.adapter.NewLoadMoreWrapper;
import xyz.ibat.sloth.base.webview.WebActivity;
import xyz.ibat.sloth.domain.main.model.DataModel;
import xyz.ibat.sloth.network.RetrofitFactory;
import xyz.ibat.sloth.utils.T;
import xyz.ibat.sloth.view.SlothRecycler;
import xyz.ibat.sloth.view.behavior.FABScrollBehavior;

/**
 * Created by DongJr on 2016/03/31
 */
public class DataFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.recycler_one)
    SlothRecycler mRecyclerView;
    @Bind(R.id.refresh_one)
    SwipeRefreshLayout mRefresh;
    @Bind(R.id.fab)
    FloatingActionButton mFab;

    private CommonAdapter mAdapter;
    private NewLoadMoreWrapper mLoadMoreWrapper;

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
        Bundle arguments = getArguments();

        if (arguments != null) {
            mType = arguments.getString(TYPE_TAG);
        }

        mRefresh.setColorSchemeResources(android.R.color.holo_blue_bright);
        mRefresh.setOnRefreshListener(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        initAdapter();

        requestData();

        mRecyclerView.addOnScrollListener(new FABScrollBehavior(mFab));

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mResultsList.isEmpty()) {
                    Random random = new Random();
                    WebActivity.startActivity(getContext()
                            , mResultsList.get(random.nextInt(mResultsList.size())).getUrl()
                            , "随机看");
                }
            }
        });

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
        mLoadMoreWrapper = new NewLoadMoreWrapper(getContext(), mAdapter, mRecyclerView);
        mLoadMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mPageIndex++;
                requestData();
            }
        });

        mRecyclerView.setAdapter(mLoadMoreWrapper);

        mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                DataModel.ResultsBean resultsBean = mResultsList.get(position);
                WebActivity.startActivity(getContext()
                        , resultsBean.getUrl(), resultsBean.getDesc());
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });

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
                mLoadMoreWrapper.setLoadState(NewLoadMoreWrapper.LoadState.ERROR);
                T.show(e.getMessage());
            }

            @Override
            public void onNext(DataModel model) {
                if (mPageIndex == 1) {
                    mResultsList.clear();
                }
                List<DataModel.ResultsBean> results = model.getResults();
                if (results.size() < 10) {
                    mLoadMoreWrapper.setLoadState(NewLoadMoreWrapper.LoadState.NOMORE);
                } else {
                    mLoadMoreWrapper.setLoadState(NewLoadMoreWrapper.LoadState.LOAD);
                }
                mResultsList.addAll(results);
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

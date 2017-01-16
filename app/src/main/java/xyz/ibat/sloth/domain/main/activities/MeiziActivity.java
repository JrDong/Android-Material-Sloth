package xyz.ibat.sloth.domain.main.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;
import xyz.ibat.sloth.R;
import xyz.ibat.sloth.base.BaseActivity;
import xyz.ibat.sloth.base.adapter.NewLoadMoreWrapper;
import xyz.ibat.sloth.domain.main.model.DataModel;
import xyz.ibat.sloth.network.PicassoFactory;
import xyz.ibat.sloth.network.RetrofitFactory;
import xyz.ibat.sloth.utils.DensityUtil;
import xyz.ibat.sloth.utils.T;

import static xyz.ibat.sloth.R.id.meizi;

public class MeiziActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {


    @Bind(R.id.main_toolbar)
    Toolbar mainToolbar;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.refresh)
    SwipeRefreshLayout mRefresh;

    private CommonAdapter mAdapter;
    private NewLoadMoreWrapper mLoadMoreWrapper;
    private List<DataModel.ResultsBean> mResultsList = new ArrayList<>();

    int mPageIndex = 1;

    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, MeiziActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meizi);
        ButterKnife.bind(this);


        setSupportActionBar(mainToolbar);

        mainToolbar.setTitle("妹纸");
        mainToolbar.setNavigationIcon(R.mipmap.icon_arrow_back);
        mainToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mRefresh.setColorSchemeResources(android.R.color.holo_blue_bright);
        mRefresh.setOnRefreshListener(this);

        StaggeredGridLayoutManager sgm = new StaggeredGridLayoutManager
                (2, StaggeredGridLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(sgm);
        initAdapter();
        requestData();
    }

    private void initAdapter() {
        mAdapter = new CommonAdapter<DataModel.ResultsBean>(this, R.layout.item_meizi, mResultsList) {
            @Override
            protected void convert(ViewHolder holder, DataModel.ResultsBean resultsBean, int position) {
                ImageView view = (ImageView) holder.getView(meizi);
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = (int) (DensityUtil.dp2px(160) + Math.random() * 200f);

                PicassoFactory.load(resultsBean.getUrl(), view);
            }
        };
        mLoadMoreWrapper = new NewLoadMoreWrapper(this,mAdapter,recyclerView);
        mLoadMoreWrapper.setLoadMoreView(R.layout.default_loading);
        mLoadMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mPageIndex++;
                requestData();
            }
        });

        recyclerView.setAdapter(mLoadMoreWrapper);

    }


    private void requestData() {
        Subscriber<DataModel> subscriber = new Subscriber<DataModel>() {
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
            public void onNext(DataModel homeDataModel) {

                if (mPageIndex == 1) {
                    mResultsList.clear();
                }
                List<DataModel.ResultsBean> results = homeDataModel.getResults();
                if (results.size() < 10) {
                    mLoadMoreWrapper.setLoadState(NewLoadMoreWrapper.LoadState.NOMORE);
                } else {
                    mLoadMoreWrapper.setLoadState(NewLoadMoreWrapper.LoadState.LOAD);
                }
                mResultsList.addAll(results);
                mLoadMoreWrapper.notifyDataSetChanged();
            }
        };

        RetrofitFactory.getInstance().getAndroidData(subscriber, "福利", mPageIndex);
    }


    @Override
    public void onRefresh() {
        mPageIndex = 1;
        requestData();
    }
}

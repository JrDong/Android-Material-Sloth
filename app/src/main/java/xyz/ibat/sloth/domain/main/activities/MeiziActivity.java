package xyz.ibat.sloth.domain.main.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;
import xyz.ibat.sloth.R;
import xyz.ibat.sloth.base.BaseActivity;
import xyz.ibat.sloth.domain.main.adapter.HomeAdapter;
import xyz.ibat.sloth.domain.main.adapter.MeiziAdapter;
import xyz.ibat.sloth.domain.main.model.HomeDataModel;
import xyz.ibat.sloth.network.RetrofitFactory;
import xyz.ibat.sloth.utils.T;

public class MeiziActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {


    @Bind(R.id.main_toolbar)
    Toolbar mainToolbar;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.refresh)
    SwipeRefreshLayout mRefresh;

    private MeiziAdapter mAdapter;

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
        recyclerView.setHasFixedSize(true);
        mAdapter = new MeiziAdapter(this);
        recyclerView.setAdapter(mAdapter);

        requestData();
    }

    private void requestData() {
        Subscriber<HomeDataModel> subscriber = new Subscriber<HomeDataModel>() {
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
            public void onNext(HomeDataModel homeDataModel) {
                if (mAdapter == null) {
                    mAdapter = new MeiziAdapter(MeiziActivity.this, homeDataModel);
                    recyclerView.setAdapter(mAdapter);
                } else {
                    mAdapter.setData(homeDataModel);
                }
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

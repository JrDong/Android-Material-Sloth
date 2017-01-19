package xyz.ibat.sloth.domain.map;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;
import xyz.ibat.sloth.R;
import xyz.ibat.sloth.base.BaseActivity;
import xyz.ibat.sloth.base.BaseFragment;
import xyz.ibat.sloth.domain.main.adapter.MeiziAdapter;
import xyz.ibat.sloth.domain.main.model.DataModel;
import xyz.ibat.sloth.network.RetrofitFactory;
import xyz.ibat.sloth.utils.T;

/**
 * Created by DongJr on 2016/4/15.
 */
public class MeiziFragment extends BaseFragment {

    @Bind(R.id.main_toolbar)
    Toolbar mainToolbar;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.fab)
    FloatingActionButton fab;

    private MeiziAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_map, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mainToolbar.setTitle("妹纸");
        ((BaseActivity) getActivity()).setSupportActionBar(mainToolbar);

        StaggeredGridLayoutManager sgm = new StaggeredGridLayoutManager
                (2, StaggeredGridLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(sgm);

        requestData();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestData();
            }
        });

    }

    private void requestData() {
        Subscriber<DataModel> subscriber = new Subscriber<DataModel>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                T.show(e.getMessage());
            }

            @Override
            public void onNext(DataModel dataModel) {
                if (mAdapter == null) {
                    mAdapter = new MeiziAdapter(getContext(), dataModel);
                    recyclerView.setAdapter(mAdapter);
                } else {
                    mAdapter.setData(dataModel);
                }
            }
        };

        RetrofitFactory.getInstance().getRandomData(subscriber, "福利");
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

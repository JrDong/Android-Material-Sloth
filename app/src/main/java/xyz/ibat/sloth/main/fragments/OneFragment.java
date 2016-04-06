package xyz.ibat.sloth.main.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;

import butterknife.Bind;
import butterknife.ButterKnife;
import xyz.ibat.sloth.R;
import xyz.ibat.sloth.main.bean.OneBean;
import xyz.ibat.sloth.main.viewholder.OneViewHolder;
import xyz.ibat.sloth.view.RefreshLayout;
import xyz.ibat.sloth.view.SlothRecycler;

/**
 * Created by DongJr on 2016/03/31
 */
public class OneFragment extends Fragment {


    @Bind(R.id.recycler_one)
    SlothRecycler recyclerOne;
    @Bind(R.id.refresh_one)
    RefreshLayout refreshOne;

    private OneAdapter mOneAdapter;

    private LinkedList<OneBean> mList = new LinkedList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        initData();
        initRecycler();
        initRefresh();
    }

    private void initRefresh() {
        refreshOne.setColorSchemeResources(android.R.color.holo_purple, android.R.color.holo_blue_bright, android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        refreshOne.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(3000);
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    refreshOne.setRefreshing(false);
                                }
                            });
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
        recyclerOne.setOnLoadListener(new SlothRecycler.OnLoadListener() {
            @Override
            public void onLoad() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(3000);
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    initMoreData();
                                    mOneAdapter.notifyDataSetChanged();
                                }
                            });
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }

    private void initMoreData() {
        OneBean oneBean = new OneBean();
        oneBean.setTitle("我是更多数据RecyclerView标题");
        oneBean.setContent("我是更多数据RecyclerView内容我是RecyclerView内容");
        mList.addLast(oneBean);
    }

    private void initData() {
        for (int i = 0; i < 10; i++) {
            OneBean oneBean = new OneBean();
            oneBean.setTitle("我是RecyclerView标题" + i);
            oneBean.setContent("我是RecyclerView内容我是RecyclerView内容" + i);
            mList.add(oneBean);
        }
    }

    private void initRecycler() {
        recyclerOne.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mOneAdapter = new OneAdapter(getContext());
        recyclerOne.setAdapter(mOneAdapter);
    }

    class OneAdapter extends SlothRecycler.LoadMoreAdaper {

        public OneAdapter(Context context) {
            super(context, mList);
        }

        @Override
        public OneViewHolder onCreateHolder(ViewGroup parent, int viewType) {
            return OneViewHolder.newIntance(getContext(), parent);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof OneViewHolder) {
                ((OneViewHolder) holder).getView(mList.get(position));
            }
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

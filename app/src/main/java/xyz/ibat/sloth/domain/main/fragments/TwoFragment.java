package xyz.ibat.sloth.domain.main.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;

import butterknife.Bind;
import butterknife.ButterKnife;
import xyz.ibat.sloth.R;
import xyz.ibat.sloth.domain.main.bean.OneBean;
import xyz.ibat.sloth.view.RefreshLayout;

/**
 * Created by DongJr on 2016/03/31
 */
public class TwoFragment extends Fragment {

    @Bind(R.id.listview_two)
    ListView listTwo;
    @Bind(R.id.refresh_two)
    RefreshLayout refreshTwo;
    private LinkedList<OneBean> mList = new LinkedList<>();
    private TwoAdaper mTwoAdaper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_two, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        initData();
        initList();
    }

    private void initData() {
        for (int i = 0; i < 10; i++) {
            OneBean oneBean = new OneBean();
            oneBean.setTitle("我是ListView标题" + i);
            oneBean.setContent("我是ListVie内容我是ListVie内容" + i);
            mList.add(oneBean);
        }
    }

    private void initMoreData() {
        OneBean oneBean = new OneBean();
        oneBean.setTitle("我是更多数据ListView标题");
        oneBean.setContent("我是更多数据ListVie内容我是ListVie内容");
        mList.addLast(oneBean);
    }


    private void initRefreshData() {
        OneBean oneBean = new OneBean();
        oneBean.setTitle("我是刷新数据ListView标题");
        oneBean.setContent("我是刷新数据ListVie内容我是ListVie内容");
        mList.addFirst(oneBean);
    }

    private void initList() {
        mTwoAdaper = new TwoAdaper();
        listTwo.setAdapter(mTwoAdaper);
        initRefresh();
        listTwo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getContext(),"点击了",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initRefresh() {
        refreshTwo.setColorSchemeResources(android.R.color.holo_purple, android.R.color.holo_blue_bright, android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        refreshTwo.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
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
                                    initRefreshData();
                                    mTwoAdaper.notifyDataSetChanged();
                                    refreshTwo.setRefreshing(false);
                                }
                            });
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
        refreshTwo.setOnLoadListener(new RefreshLayout.OnLoadListener() {
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
                                    mTwoAdaper.notifyDataSetChanged();
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    class TwoAdaper extends BaseAdapter {

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public OneBean getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(getContext()).inflate(
                        R.layout.item_recycler_one, parent, false);
                viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
                viewHolder.tvContent = (TextView) convertView.findViewById(R.id.tv_content);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.tvTitle.setText(getItem(position).getTitle());
            viewHolder.tvContent.setText(getItem(position).getContent());
            return convertView;
        }
    }

    class ViewHolder {
        TextView tvTitle;
        TextView tvContent;
    }
}

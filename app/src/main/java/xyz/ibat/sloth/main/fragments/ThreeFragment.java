package xyz.ibat.sloth.main.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import xyz.ibat.sloth.R;
import xyz.ibat.sloth.main.bean.ThreeBean;
import xyz.ibat.sloth.main.viewholder.ThreeViewHolder;
import xyz.ibat.sloth.view.SlothRecycler;

/**
 * Created by DongJr on 2016/03/31
 */
public class ThreeFragment extends Fragment {

    @Bind(R.id.recycler_three)
    SlothRecycler recyclerThree;
    @Bind(R.id.refresh_three)
    SwipeRefreshLayout refreshThree;

    private List<ThreeBean> mList = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_three, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        initData();
    }

    private void initData() {
        Random random = new Random();
        for (int i = 0;i<10;i++){
            ThreeBean threeBean = new ThreeBean();
            int color = Color.argb(random.nextInt(100),0,100,255);
            threeBean.setColor(color);
            mList.add(threeBean);
        }

        recyclerThree.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerThree.setAdapter(new ThreeAdapter());
    }

    class ThreeAdapter extends RecyclerView.Adapter<ThreeViewHolder>{

        @Override
        public ThreeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return ThreeViewHolder.newIntance(getContext(),parent);
        }

        @Override
        public void onBindViewHolder(ThreeViewHolder holder, int position) {
            holder.getView(mList.get(position));
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

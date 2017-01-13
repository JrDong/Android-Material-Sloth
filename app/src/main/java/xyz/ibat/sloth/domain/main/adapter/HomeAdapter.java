package xyz.ibat.sloth.domain.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import xyz.ibat.sloth.domain.main.model.HomeDataModel;

/**
 * Created by DongJr on 2017/1/13.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeHolder> {

    private Context mContext;

    private List<HomeDataModel.ResultsBean> mList = new ArrayList<>();

    public HomeAdapter(Context context) {
        mContext = context;
    }

    public HomeAdapter(Context context, HomeDataModel model) {
        mContext = context;
        List<HomeDataModel.ResultsBean> list = model.getResults();
        mList.addAll(list);
    }

    public void setData(HomeDataModel model) {
        mList.clear();
        mList.addAll(model.getResults());
        notifyDataSetChanged();
    }

    @Override
    public HomeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return HomeHolder.newIntance(mContext, parent);
    }

    @Override
    public void onBindViewHolder(HomeHolder holder, int position) {
        if (mList != null) {
            holder.getView(mList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}

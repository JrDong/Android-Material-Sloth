package xyz.ibat.sloth.domain.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import xyz.ibat.sloth.domain.main.model.HomeDataModel;

/**
 * Created by jerry on 2017/1/14.
 */

public class MeiziAdapter extends RecyclerView.Adapter<MeiziHolder>{

    private Context mContext;

    private List<HomeDataModel.ResultsBean> mList = new ArrayList<>();

    public MeiziAdapter(Context context) {
        mContext = context;
    }

    public MeiziAdapter(Context context, HomeDataModel model) {
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
    public MeiziHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return MeiziHolder.newIntance(mContext,parent);
    }

    @Override
    public void onBindViewHolder(MeiziHolder holder, int position) {
        holder.getView(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}

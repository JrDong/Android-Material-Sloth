package xyz.ibat.sloth.domain.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import xyz.ibat.sloth.domain.main.model.DataModel;

/**
 * Created by jerry on 2017/1/14.
 */

public class MeiziAdapter extends RecyclerView.Adapter<MeiziHolder>{

    private Context mContext;

    private List<DataModel.ResultsBean> mList = new ArrayList<>();

    public MeiziAdapter(Context context) {
        mContext = context;
    }

    public MeiziAdapter(Context context, DataModel model) {
        mContext = context;
        List<DataModel.ResultsBean> list = model.getResults();
        mList.addAll(list);
    }

    public void setData(DataModel model) {
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

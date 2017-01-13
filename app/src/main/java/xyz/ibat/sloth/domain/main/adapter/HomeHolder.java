package xyz.ibat.sloth.domain.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import xyz.ibat.sloth.R;
import xyz.ibat.sloth.domain.main.model.HomeDataModel;

/**
 * Created by DongJr on 2017/1/13.
 */

public class HomeHolder extends RecyclerView.ViewHolder {

    private TextView mTitle;
    private TextView mDate;
    private TextView mAuthor;

    public HomeHolder(View itemView) {
        super(itemView);
        mTitle = (TextView) itemView.findViewById(R.id.tv_title);
        mDate = (TextView) itemView.findViewById(R.id.tv_date);
        mAuthor = (TextView) itemView.findViewById(R.id.tv_author);
    }

    public static HomeHolder newIntance(Context context, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_fragment_home, parent, false);
        return new HomeHolder(view);
    }

    public void getView(HomeDataModel.ResultsBean bean) {
        mTitle.setText(bean.getDesc());
        mDate.setText(bean.getPublishedAt());
        mAuthor.setText(bean.getWho());
    }
}

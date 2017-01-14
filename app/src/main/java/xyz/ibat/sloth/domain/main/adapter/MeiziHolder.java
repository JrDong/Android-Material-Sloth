package xyz.ibat.sloth.domain.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import xyz.ibat.sloth.R;
import xyz.ibat.sloth.domain.main.model.HomeDataModel;
import xyz.ibat.sloth.network.PicassoFactory;
import xyz.ibat.sloth.utils.DensityUtil;

/**
 * Created by jerry on 2017/1/14.
 */

public class MeiziHolder extends RecyclerView.ViewHolder{

    private ImageView meizi;

    public MeiziHolder(View itemView) {
        super(itemView);
        meizi = (ImageView) itemView.findViewById(R.id.meizi);
    }

    public static MeiziHolder newIntance(Context context, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_meizi, parent, false);
        return new MeiziHolder(view);
    }

    public void getView(HomeDataModel.ResultsBean bean) {

        ViewGroup.LayoutParams layoutParams = meizi.getLayoutParams();
        layoutParams.height = (int) (DensityUtil.dp2px(160) + Math.random()*200f);
        PicassoFactory.load(bean.getUrl(),meizi);
    }
}

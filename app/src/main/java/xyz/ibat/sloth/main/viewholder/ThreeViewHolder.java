package xyz.ibat.sloth.main.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import xyz.ibat.sloth.R;
import xyz.ibat.sloth.main.bean.ThreeBean;

/**
 * Created by DongJr on 2016/04/07
 */
public class ThreeViewHolder extends RecyclerView.ViewHolder {

    ImageView ivCard;

    public ThreeViewHolder(View itemView) {
        super(itemView);
        ivCard = (ImageView) itemView.findViewById(R.id.iv_card);
    }

    public static ThreeViewHolder newIntance(Context context, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_card_view, parent, false);
        return new ThreeViewHolder(view);
    }

    public void getView(ThreeBean threeBean) {
        ivCard.setBackgroundColor(threeBean.getColor());
    }
}


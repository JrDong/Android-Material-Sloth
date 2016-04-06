package xyz.ibat.sloth.main.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import xyz.ibat.sloth.R;
import xyz.ibat.sloth.main.bean.OneBean;

/**
 * Created by DongJr on 2016/03/31
 */
public class OneViewHolder extends RecyclerView.ViewHolder {

    TextView tvTitle;
    TextView tvContent;

    public OneViewHolder(View itemView) {
        super(itemView);
        tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        tvContent = (TextView) itemView.findViewById(R.id.tv_content);
    }

    public static OneViewHolder newIntance(Context context, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_one, parent, false);
        return new OneViewHolder(view);
    }

    public void getView(OneBean oneBean) {
        tvTitle.setText(oneBean.getTitle());
        tvContent.setText(oneBean.getContent());
    }
}


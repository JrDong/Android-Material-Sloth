package xyz.ibat.sloth.base;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import xyz.ibat.sloth.R;

/**
 * Created by DongJr on 2016/4/12.
 */
public class TabsFragment extends BaseFragment implements View.OnClickListener{

    @Bind(R.id.fragment_tab)
    FrameLayout fragmentTab;
    @Bind(R.id.tab_group)
    RadioGroup tabGroup;
    @Bind(R.id.main_tab)
    RadioButton mainTab;
    @Bind(R.id.map_tab)
    RadioButton mapTab;
    @Bind(R.id.info_tab)
    RadioButton infoTab;
    @Bind(R.id.mine_tab)
    RadioButton mineTab;

    private FragmentFactory mFactory;
    private String mCurrentTag;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab, container, false);

        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        mainTab.setChecked(true);
        mFactory = FragmentFactory.getInstance();

        mainTab.setOnClickListener(this);
        mapTab.setOnClickListener(this);
        infoTab.setOnClickListener(this);
        mineTab.setOnClickListener(this);

        initFragment(FragmentFactory.MAIN_TAG);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_tab:
                initFragment(FragmentFactory.MAIN_TAG);
                break;
            case R.id.map_tab:
                initFragment(FragmentFactory.MAP_TAG);
                break;
            case R.id.info_tab:
                initFragment(FragmentFactory.INFO_TAG);
                break;
            case R.id.mine_tab:
                initFragment(FragmentFactory.MINE_TAG);
                break;
        }
    }
    private void initFragment(String tag){
        BaseFragment baseFragment = mFactory.getFragmentByTag(tag);
        if (baseFragment==null){
            throw new IllegalAccessError("tag is error!");
        }
        if (tag.equals(mCurrentTag)){
            return;
        }else {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            if(!TextUtils.isEmpty(mCurrentTag)) {
                transaction.hide(mFactory.getFragmentByTag(mCurrentTag));
            }
            if (!baseFragment.isAdded()){
                transaction.add(R.id.fragment_tab,baseFragment);
            }else {
                transaction.show(baseFragment);
            }
            transaction.commitAllowingStateLoss();
        }
        mCurrentTag = tag;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


}

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
import xyz.ibat.sloth.view.animation.FrameDrawable;

/**
 * Created by DongJr on 2016/4/12.
 */
public class TabsFragment extends BaseFragment implements View.OnClickListener {

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
        switch (v.getId()) {
            case R.id.main_tab:
                new FrameDrawable(getContext(), mainTab)
                        .addFrame(new int[]{R.mipmap.icon_mine_pressed
                                , R.mipmap.icon_map_pressed, R.mipmap.icon_info_pressed
                                , R.mipmap.icon_mine_pressed, R.mipmap.icon_main_pressed}, 100)
                        .setOneRepeat(true)
                        .show();

                initFragment(FragmentFactory.MAIN_TAG);
                break;
            case R.id.map_tab:
                new FrameDrawable(getContext(), mapTab)
                        .addFrame(new int[]{R.mipmap.icon_mine_pressed
                                , R.mipmap.icon_map_pressed, R.mipmap.icon_info_pressed
                                , R.mipmap.icon_mine_pressed, R.mipmap.icon_main_pressed}, 100)
                        .setOneRepeat(true)
                        .show();
                initFragment(FragmentFactory.MAP_TAG);
                break;
            case R.id.info_tab:
                new FrameDrawable(getContext(), infoTab)
                        .addFrame(new int[]{R.mipmap.icon_mine_pressed
                                , R.mipmap.icon_map_pressed, R.mipmap.icon_info_pressed
                                , R.mipmap.icon_mine_pressed, R.mipmap.icon_main_pressed}, 100)
                        .setOneRepeat(true)
                        .show();
                initFragment(FragmentFactory.INFO_TAG);
                break;
            case R.id.mine_tab:
                new FrameDrawable(getContext(), mineTab)
                        .addFrame(new int[]{R.mipmap.icon_mine_pressed
                                , R.mipmap.icon_map_pressed, R.mipmap.icon_info_pressed
                                , R.mipmap.icon_mine_pressed, R.mipmap.icon_main_pressed}, 100)
                        .setOneRepeat(true)
                        .show();
                initFragment(FragmentFactory.MINE_TAG);
                break;
        }
    }

    private void initFragment(String tag) {
        BaseFragment baseFragment = mFactory.getFragmentByTag(tag);
        if (baseFragment == null) {
            throw new IllegalAccessError("tag is error!");
        }
        if (tag.equals(mCurrentTag)) {
            return;
        } else {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            if (!TextUtils.isEmpty(mCurrentTag)) {
                transaction.hide(mFactory.getFragmentByTag(mCurrentTag));
            }
            if (!baseFragment.isAdded()) {
                transaction.add(R.id.fragment_tab, baseFragment);
            } else {
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

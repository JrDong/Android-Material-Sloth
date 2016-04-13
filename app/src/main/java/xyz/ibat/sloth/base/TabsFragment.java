package xyz.ibat.sloth.base;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.RadioButton;

import butterknife.Bind;
import butterknife.ButterKnife;
import xyz.ibat.sloth.R;
import xyz.ibat.sloth.main.fragments.MainFragment;

/**
 * Created by DongJr on 2016/4/12.
 */
public class TabsFragment extends BaseFragment {

    @Bind(R.id.fragment_tab)
    FrameLayout fragmentTab;
    @Bind(R.id.main_tab)
    RadioButton mainTab;
    @Bind(R.id.map_tab)
    RadioButton mapTab;
    @Bind(R.id.info_tab)
    RadioButton infoTab;
    @Bind(R.id.mine_tab)
    RadioButton mineTab;

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
        mainTab.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            }
        });
        initFragment();
    }

    private void initFragment() {
        FragmentManager manager = ((BaseActivity) getActivity()).getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_tab,new MainFragment());
        transaction.commitAllowingStateLoss();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

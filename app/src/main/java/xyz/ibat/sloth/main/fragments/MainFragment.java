package xyz.ibat.sloth.main.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import xyz.ibat.sloth.R;
import xyz.ibat.sloth.base.BaseActivity;
import xyz.ibat.sloth.base.BaseFragment;

/**
 * Created by DongJr on 2016/4/12.
 */
public class MainFragment extends BaseFragment {

    @Bind(R.id.tabs)
    TabLayout mTabs;
    @Bind(R.id.vp_main)
    ViewPager mViewPager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        initViewPager();
    }
    private void initViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(((BaseActivity)getActivity()).getSupportFragmentManager());
        adapter.addFragment(new OneFragment(), "ONE");
        adapter.addFragment(new TwoFragment(), "TWO");
        adapter.addFragment(new ThreeFragment(), "THREE");
        mViewPager.setAdapter(adapter);
        mTabs.setupWithViewPager(mViewPager);
        mTabs.setSelectedTabIndicatorColor(getResources().getColor(R.color.white));
        mTabs.setTabTextColors(getResources().getColor(R.color.white_66), getResources().getColor(R.color.white));
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

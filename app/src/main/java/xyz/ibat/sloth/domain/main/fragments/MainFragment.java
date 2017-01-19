package xyz.ibat.sloth.domain.main.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import xyz.ibat.sloth.R;
import xyz.ibat.sloth.base.BaseActivity;
import xyz.ibat.sloth.base.BaseFragment;
import xyz.ibat.sloth.domain.main.activities.DiscussionActivity;
import xyz.ibat.sloth.domain.main.activities.FriendActivity;
import xyz.ibat.sloth.domain.main.activities.MeiziActivity;
import xyz.ibat.sloth.domain.main.activities.MessageActivity;
import xyz.ibat.sloth.utils.SlothUtil;
import xyz.ibat.sloth.utils.T;

/**
 * Created by DongJr on 2016/4/12.
 */
public class MainFragment extends BaseFragment implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.tabs)
    TabLayout mTabs;
    @Bind(R.id.vp_main)
    ViewPager mViewPager;
    @Bind(R.id.main_toolbar)
    Toolbar mainToolbar;
    @Bind(R.id.navigation_main)
    NavigationView mNavigationView;
    @Bind(R.id.drawLayout)
    DrawerLayout mDrawerLayout;

    ActionBarDrawerToggle mDrawerToggle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_main, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        initToolbar();
        initDrawLayout();
        initViewPager();
    }

    private void initToolbar() {
        ((BaseActivity) getActivity()).setSupportActionBar(mainToolbar);
        mainToolbar.setNavigationIcon(R.mipmap.ic_toolbar_drawer);
//      mainToolbar.setLogo();
//      mainToolbar.setSubtitle();
        mainToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                String msg = "";
                switch (item.getItemId()) {
                    case R.id.action_search:
                        msg = "search";
                        break;
                    case R.id.action_about:
                        msg = "about";
                        break;
                    case R.id.action_settings:
                        msg = "setting";
                        break;
                }
                T.show(msg);
                return false;
            }
        });
    }


    private void initDrawLayout() {
        //DrawerLayout和Toolbar关联
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), mDrawerLayout, mainToolbar, R.string.app_name, R.string.app_name);
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mainToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
                    mDrawerLayout.closeDrawer(Gravity.LEFT);
                } else {
                    mDrawerLayout.openDrawer(Gravity.LEFT);
                }
            }
        });

        mNavigationView.setNavigationItemSelectedListener(this);
    }

    private void initViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter((getActivity()).getSupportFragmentManager());
        //Android
        DataFragment dataFragment = new DataFragment();
        Bundle bundle = new Bundle();
        bundle.putString(DataFragment.TYPE_TAG, "Android");
        dataFragment.setArguments(bundle);
        adapter.addFragment(dataFragment, "Android");
        //Ios
        DataFragment dataFragment1 = new DataFragment();
        Bundle bundle1 = new Bundle();
        bundle1.putString(DataFragment.TYPE_TAG, "iOS");
        dataFragment1.setArguments(bundle1);
        adapter.addFragment(dataFragment1, "IOS");
        //前端
        DataFragment dataFragment2 = new DataFragment();
        Bundle bundle2 = new Bundle();
        bundle2.putString(DataFragment.TYPE_TAG, "前端");
        dataFragment2.setArguments(bundle2);
        adapter.addFragment(dataFragment2, "前端");
        //拓展资源
        DataFragment dataFragment3 = new DataFragment();
        Bundle bundle3 = new Bundle();
        bundle3.putString(DataFragment.TYPE_TAG, "拓展资源");
        dataFragment3.setArguments(bundle3);
        adapter.addFragment(dataFragment3, "拓展资源");


        mViewPager.setAdapter(adapter);
        //必须在setAdapter之后调用
        mTabs.setupWithViewPager(mViewPager);
        //设置指示器颜色
        mTabs.setSelectedTabIndicatorColor(getResources().getColor(R.color.white));
        //tab的颜色,属性一,正常颜色;属性二,被选中的颜色.
        mTabs.setTabTextColors(getResources().getColor(R.color.white_66), getResources().getColor(R.color.white));
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                mDrawerLayout.closeDrawer(Gravity.LEFT);
                MeiziActivity.startActivity(getActivity());
                break;
            case R.id.nav_messages:
                SlothUtil.showToast("MESSAGE");
                mDrawerLayout.closeDrawer(Gravity.LEFT);
                startActivity(new Intent(getActivity(), MessageActivity.class));
                break;
            case R.id.nav_friends:
                SlothUtil.showToast("FRIENDS");
                mDrawerLayout.closeDrawer(Gravity.LEFT);
                startActivity(new Intent(getActivity(), FriendActivity.class));
                break;
            case R.id.nav_discussion:
                SlothUtil.showToast("DISCUSSION");
                mDrawerLayout.closeDrawer(Gravity.LEFT);
                startActivity(new Intent(getActivity(), DiscussionActivity.class));
                break;
            case R.id.sub_item1:
                SlothUtil.showToast("SUB_ITEM1");
                mDrawerLayout.closeDrawer(Gravity.LEFT);
                break;
            case R.id.sub_item2:
                SlothUtil.showToast("SUB_ITEM2");
                mDrawerLayout.closeDrawer(Gravity.LEFT);
                break;
        }
        return false;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
        mDrawerToggle.syncState();
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

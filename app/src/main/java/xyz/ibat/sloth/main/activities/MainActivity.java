package xyz.ibat.sloth.main.activities;

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
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import xyz.ibat.sloth.R;
import xyz.ibat.sloth.main.fragments.OneFragment;
import xyz.ibat.sloth.main.fragments.ThreeFragment;
import xyz.ibat.sloth.main.fragments.TwoFragment;
import xyz.ibat.sloth.utils.SlothUtil;

/**
 * create by DongJr 2016/03/31
 * 图片素材，参考 https://design.google.com/icons/
 */
public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.main_toolbar)
    Toolbar mainToolbar;
    @Bind(R.id.navigation_main)
    NavigationView mNavigationView;
    @Bind(R.id.drawLayout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.tabs)
    TabLayout mTabs;
    @Bind(R.id.vp_main)
    ViewPager mViewPager;

    ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initToolbar();
        initDrawLayout();
        initViewPager();
    }

    private void initViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new OneFragment(), "ONE");
        adapter.addFragment(new TwoFragment(), "TWO");
        adapter.addFragment(new ThreeFragment(), "THREE");
        mViewPager.setAdapter(adapter);
        mTabs.setupWithViewPager(mViewPager);
        mTabs.setSelectedTabIndicatorColor(getResources().getColor(R.color.white));
        mTabs.setTabTextColors(getResources().getColor(R.color.white_66), getResources().getColor(R.color.white));
    }

    private void initToolbar() {
        setSupportActionBar(mainToolbar);
        mainToolbar.setNavigationIcon(R.mipmap.ic_toolbar_drawer);
    }

    private void initDrawLayout() {
        //DrawerLayout和Toolbar关联
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mainToolbar, R.string.app_name, R.string.app_name);
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mainToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDrawerLayout.isDrawerOpen(Gravity.START)) {
                    mDrawerLayout.closeDrawer(Gravity.START);
                } else {
                    mDrawerLayout.openDrawer(Gravity.START);
                }
            }
        });

        mNavigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                SlothUtil.showToast("HOME");
                mDrawerLayout.closeDrawer(Gravity.START);
                break;
            case R.id.nav_messages:
                SlothUtil.showToast("MESSAGE");
                mDrawerLayout.closeDrawer(Gravity.START);
                break;
            case R.id.nav_friends:
                SlothUtil.showToast("FRIENDS");
                mDrawerLayout.closeDrawer(Gravity.START);
                break;
            case R.id.nav_discussion:
                SlothUtil.showToast("DISCUSSION");
                mDrawerLayout.closeDrawer(Gravity.START);
                break;
            case R.id.sub_item1:
                SlothUtil.showToast("SUB_ITEM1");
                mDrawerLayout.closeDrawer(Gravity.START);
                break;
            case R.id.sub_item2:
                SlothUtil.showToast("SUB_ITEM2");
                mDrawerLayout.closeDrawer(Gravity.START);
                break;
        }
        return false;
    }

    /**
     * Activity彻底运行起来之后的回调
     * Called when activity start-up is complete
     * (after onStart() and onRestoreInstanceState(Bundle) have been called).
     */
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        //箭头旋转动画的关键
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
}

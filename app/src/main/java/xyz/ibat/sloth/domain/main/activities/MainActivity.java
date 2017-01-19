package xyz.ibat.sloth.domain.main.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import butterknife.ButterKnife;
import xyz.ibat.sloth.R;
import xyz.ibat.sloth.base.BaseActivity;
import xyz.ibat.sloth.base.TabsFragment;

/**
 * create by DongJr 2016/03/31
 * 图片素材，参考 https://design.google.com/icons/
 * 子模块rooter实现: http://www.jianshu.com/p/7cb2cc9b726a?hmsr=toutiao.io&utm_medium=toutiao.io&utm_source=toutiao.io
 */
public class MainActivity extends BaseActivity  {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initFragment();
    }

    private void initFragment() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fl_root,new TabsFragment());
        transaction.commitAllowingStateLoss();
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
//        mDrawerToggle.syncState();
    }


}

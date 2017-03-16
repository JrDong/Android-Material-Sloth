package xyz.ibat.sloth.base;

import android.os.Bundle;

import java.util.HashMap;

import xyz.ibat.sloth.base.webview.WebFragment;
import xyz.ibat.sloth.domain.main.fragments.MainFragment;
import xyz.ibat.sloth.domain.map.MeiziFragment;
import xyz.ibat.sloth.domain.mine.MineFragment;

/**
 * Created by DongJr on 2016/4/15.
 */
public class FragmentFactory {

    public static final String MAIN_TAG = "main";
    public static final String MAP_TAG = "map";
    public static final String INFO_TAG = "info";
    public static final String MINE_TAG = "mine";

    private MainFragment mainFragment;
    private MeiziFragment mapFragment;
    private WebFragment infoFragment;
    private MineFragment mineFragment;


    private static FragmentFactory mFactory;
    private HashMap<String, BaseFragment> mHashMap = new HashMap<>();

    private FragmentFactory() {

    }

    public static FragmentFactory getInstance() {
        if (mFactory == null) {
            synchronized (FragmentFactory.class) {
                if (mFactory == null) {
                    mFactory = new FragmentFactory();
                }
            }
        }
        return mFactory;
    }

    public BaseFragment getFragmentByTag(String tag) {

        if (tag.equals(MAIN_TAG) && mainFragment == null) {
            mainFragment = new MainFragment();
            mHashMap.put(MAIN_TAG, mainFragment);
        }
        if (tag.equals(MAP_TAG) && mapFragment == null) {
            mapFragment = new MeiziFragment();
            mHashMap.put(MAP_TAG, mapFragment);
        }
        if (tag.equals(INFO_TAG) && infoFragment == null) {
            infoFragment = new WebFragment();
            Bundle bundle = new Bundle();
            bundle.putString(WebFragment.URL_TAG, "http://ibat.xyz");
            infoFragment.setArguments(bundle);
            mHashMap.put(INFO_TAG, infoFragment);
        }
        if (tag.equals(MINE_TAG) && mineFragment == null) {
            mineFragment = new MineFragment();
            mHashMap.put(MINE_TAG, mineFragment);
        }
        return mHashMap.get(tag);
    }

}

package xyz.ibat.sloth.view;

import android.support.design.widget.AppBarLayout;
import android.util.Log;

/**
 * Created by DongJr on 2017/1/5.
 */

public abstract class AppBarStateChangeListener implements AppBarLayout.OnOffsetChangedListener {

    public enum State {
        EXPANDED,
        COLLAPSED,
        IDLE
    }

    private State mCurrentState = State.IDLE;

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {


        float percent = (float) Math.abs(verticalOffset) / (float) appBarLayout.getTotalScrollRange();
        Log.e("dong", percent + "================" + verticalOffset + "=======" + appBarLayout.getTotalScrollRange());

        if (verticalOffset == 0) {
            onStateChanged(appBarLayout, State.EXPANDED, percent);
            mCurrentState = State.EXPANDED;
        } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
            onStateChanged(appBarLayout, State.COLLAPSED, percent);
            mCurrentState = State.COLLAPSED;
        } else {
            onStateChanged(appBarLayout, State.IDLE, percent);
            mCurrentState = State.IDLE;
        }

    }

    public abstract void onStateChanged(AppBarLayout appBarLayout, State state, float percent);
}

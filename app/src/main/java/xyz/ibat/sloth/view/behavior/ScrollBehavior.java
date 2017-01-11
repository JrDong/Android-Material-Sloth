package xyz.ibat.sloth.view.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by DongJr on 2016/5/4.
 */
public class ScrollBehavior extends CoordinatorLayout.Behavior {

    int offsetTotal = 0;

    public ScrollBehavior(Context context, AttributeSet attrs){
        super(context,attrs);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        //nestedScrollAxes滑动方向
        return true;
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        //滑动事件处理
        onScroll(child,dyConsumed);
        Log.e("behavior","=============dtConsumed======="+dyConsumed);
        Log.e("behavior","=============dtUnConsumed======="+dyUnconsumed);
    }

    private void onScroll(View child, int dyConsumed) {
        int old = offsetTotal;
        int top = offsetTotal - dyConsumed;
        top = Math.max(top, -child.getHeight());
        top = Math.min(top, 0);
        offsetTotal = top;
        if (old == offsetTotal){
            return;
        }
        int delta = offsetTotal-old;
        child.offsetTopAndBottom(delta);
//        int top = -dyConsumed;
//        top = Math.min(top,child.getHeight());
//        child.offsetTopAndBottom(top);
    }


    @Override
    public boolean onNestedFling(CoordinatorLayout coordinatorLayout, View child, View target, float velocityX, float velocityY, boolean consumed) {
        //当快速滑动
        return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed);
    }
}

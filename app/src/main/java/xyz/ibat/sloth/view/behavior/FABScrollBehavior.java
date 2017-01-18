package xyz.ibat.sloth.view.behavior;

import android.animation.Animator;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;

import xyz.ibat.sloth.utils.DensityUtil;

/**
 * Created by DongJr on 2016/5/4.
 */
public class FABScrollBehavior extends RecyclerView.OnScrollListener {

    int offsetTotal = 0;

    private static final Interpolator INTERPOLATOR = new AccelerateDecelerateInterpolator();

    private int mState;
    private int sinceDirectionChange;
    private int hideHeight = 0;

    private View innerView;

    public FABScrollBehavior(View innerView){
        this.innerView = innerView;
    }


    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        if (mState != RecyclerView.SCROLL_STATE_DRAGGING){
            return;
        }
        Log.e("behavior",dy+"===========dy===============");

        //dy>0 向上滑 else 向下滑
        if (dy > 0 && sinceDirectionChange < 0 || dy < 0 && sinceDirectionChange > 0) {
            innerView.animate().cancel();
            sinceDirectionChange = 0;
        }
        sinceDirectionChange += dy;
        if (sinceDirectionChange > DensityUtil.dp2px(46)) {
            hide(innerView);
        } else if (sinceDirectionChange < 0 ) {
            show(innerView);
        }
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        mState = newState;
    }

    private void hide(final View view) {
        hideHeight = view.getBottom();
        ViewPropertyAnimator animator = view.animate().translationY(hideHeight)
                .setInterpolator(INTERPOLATOR).setDuration(400);
        animator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
//                view.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                show(view);
            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        animator.start();
    }


    private void show(final View view) {
        ViewPropertyAnimator animator = view.animate().translationY(0)
                .setInterpolator(INTERPOLATOR).setDuration(400);
        animator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
//                view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                hide(view);
            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        animator.start();

    }
}

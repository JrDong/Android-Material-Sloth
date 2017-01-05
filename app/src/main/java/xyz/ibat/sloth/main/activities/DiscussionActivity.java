package xyz.ibat.sloth.main.activities;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import xyz.ibat.sloth.R;
import xyz.ibat.sloth.view.AppBarStateChangeListener;

/**
 * create by DongJr 2016/03/31
 */
public class DiscussionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion);

        AppBarLayout appBar = (AppBarLayout) findViewById(R.id.appbar);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        toolbar.setNavigationIcon(R.mipmap.icon_share);

        toolbar.setLogo(R.mipmap.icon_share);

        collapsingToolbarLayout.setTitle("");

        appBar.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state, float percent) {
                if (state == State.EXPANDED) {
//                    toolbar.setVisibility(View.GONE);
                    //展开状态

                } else if (state == State.COLLAPSED) {
//                    toolbar.setVisibility(View.VISIBLE);
                    //折叠状态

                } else {
//                    toolbar.setVisibility(View.VISIBLE);
                    //中间状态
                }
                toolbar.setAlpha(percent);
                for (int i = 0; i < toolbar.getChildCount(); i++) {
                    toolbar.getChildAt(i).setAlpha(percent);
                }


            }
        });

    }
}

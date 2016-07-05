package xyz.ibat.sloth.main.activities;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import xyz.ibat.sloth.R;
/**
 * create by DongJr 2016/04/29
 */
public class MessageActivity extends AppCompatActivity {

    @Bind(R.id.backdrop)
    ImageView backdrop;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @Bind(R.id.appbar)
    AppBarLayout appbar;
    @Bind(R.id.main_content)
    CoordinatorLayout mainContent;

//    使用CollapsingToolbarLayout实现折叠效果，需要注意3点
//    1. AppBarLayout的高度固定
//    2. CollapsingToolbarLayout的子视图设置layout_collapseMode属性
//    3. 关联悬浮视图设置app:layout_anchor，app:layout_anchorGravity属性
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Sloth");
        toolbar.setNavigationIcon(R.mipmap.icon_arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }



}

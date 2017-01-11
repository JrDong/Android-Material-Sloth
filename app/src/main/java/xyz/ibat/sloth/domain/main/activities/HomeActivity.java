package xyz.ibat.sloth.domain.main.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.Bind;
import butterknife.ButterKnife;
import xyz.ibat.sloth.R;
import xyz.ibat.sloth.utils.SlothUtil;
/**
 * create by DongJr 2016/04/29
 */
public class HomeActivity extends AppCompatActivity {

    @Bind(R.id.main_toolbar)
    Toolbar mainToolbar;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.tabs)
    TabLayout tabs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setSupportActionBar(mainToolbar);
        mainToolbar.setTitle("Sloth");
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view,"FAB",Snackbar.LENGTH_LONG)
                        .setAction("cancel", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //这里的单击事件代表点击消除Action后的响应事件
                                SlothUtil.showToast("cancle");
                            }
                        })
                        .show();
            }
        });
        mainToolbar.setNavigationIcon(R.mipmap.icon_arrow_back);
        mainToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tabs.addTab(tabs.newTab().setText("ONE"));
        tabs.addTab(tabs.newTab().setText("TWO"));
    }
}

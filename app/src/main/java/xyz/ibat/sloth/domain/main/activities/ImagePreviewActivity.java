package xyz.ibat.sloth.domain.main.activities;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.pm.ProviderInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Window;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import xyz.ibat.sloth.R;
import xyz.ibat.sloth.base.BaseActivity;
import xyz.ibat.sloth.network.ImageLoader;

public class ImagePreviewActivity extends BaseActivity {

    @Bind(R.id.image)
    ImageView image;

    public static final String URL_TAG = "URL_TAG";
    private String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_image_preview);
        ButterKnife.bind(this);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Transition explode = TransitionInflater.from(this).inflateTransition(R.transition.explode);
//            //退出时使用
//            getWindow().setExitTransition(explode);
//            //第一次进入时使用
//            getWindow().setEnterTransition(explode);
//            //再次进入时使用
//            getWindow().setReenterTransition(explode);
//        }

        Intent intent = getIntent();
        url = intent.getStringExtra(URL_TAG);

        ImageLoader.load(url,image);

    }
}

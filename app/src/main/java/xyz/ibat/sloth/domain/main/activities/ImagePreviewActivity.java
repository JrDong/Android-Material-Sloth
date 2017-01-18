package xyz.ibat.sloth.domain.main.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Window;

import butterknife.Bind;
import butterknife.ButterKnife;
import xyz.ibat.sloth.R;
import xyz.ibat.sloth.base.BaseActivity;
import xyz.ibat.sloth.network.ImageLoader;
import xyz.ibat.sloth.view.widget.ZoomImageView;

public class ImagePreviewActivity extends BaseActivity {

    @Bind(R.id.image)
    ZoomImageView image;

    public static final String URL_TAG = "URL_TAG";

    public static final String SCALETYPE_TAG = "SCALETYPE_TAG";

    public static final String SCALETYPE_CENTERCROP = "CENTER_CROP";

    public static final String SCALETYPE_FITXY = "FIT_XY";

    private String SCALETYPE = SCALETYPE_FITXY;

    private String url;

    public static void startActivity(Context context, String url) {
        startActivity(context, url, SCALETYPE_FITXY);
    }

    public static void startActivity(Context context, String url, String scropType) {
        Intent intent = new Intent();
        intent.setClass(context, ImagePreviewActivity.class);
        intent.putExtra(URL_TAG, url);
        intent.putExtra(SCALETYPE_TAG, scropType);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_preview);
        ButterKnife.bind(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Transition explode = TransitionInflater.from(this).inflateTransition(R.transition.fade);
            //退出时使用
            getWindow().setExitTransition(explode);
            //第一次进入时使用
            getWindow().setEnterTransition(explode);
            //再次进入时使用
            getWindow().setReenterTransition(explode);
        }

        Intent intent = getIntent();
        url = intent.getStringExtra(URL_TAG);
        SCALETYPE = intent.getStringExtra(SCALETYPE_TAG);

        if (SCALETYPE_FITXY.equals(SCALETYPE)) {
//            image.setScaleType(ImageView.ScaleType.FIT_XY);
        } else if (SCALETYPE_CENTERCROP.equals(SCALETYPE)) {
//            image.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else {
//            image.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }

        ImageLoader.load(url, image);

    }
}

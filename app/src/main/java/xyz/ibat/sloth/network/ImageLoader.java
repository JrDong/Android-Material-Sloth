package xyz.ibat.sloth.network;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;

import xyz.ibat.sloth.SlothApplication;
import xyz.ibat.sloth.view.picasso.CircleTransform;

/**
 * Created by jerry on 2017/1/14.
 */

public class ImageLoader {


    private ImageLoader() {

    }

    /**
     * ALPHA_8：每个像素占用1byte内存
     * ARGB_4444:每个像素占用2byte内存
     * ARGB_8888:每个像素占用4byte内存
     * RGB_565:每个像素占用2byte内存
     */
    public static void load(String url, ImageView view) {
        Picasso.with(SlothApplication.getContext())
                .load(url)
                .config(Bitmap.Config.RGB_565)
                .into(view);
    }

    public static void load(int drawableRes, ImageView view) {
        Picasso.with(SlothApplication.getContext())
                .load(drawableRes)
                .config(Bitmap.Config.RGB_565)
                .into(view);
    }

    public static void load(File file, ImageView view) {
        Picasso.with(SlothApplication.getContext())
                .load(file)
                .config(Bitmap.Config.RGB_565)
                .into(view);
    }

    public static void loadCircle(String url, ImageView view) {
        Picasso.with(SlothApplication.getContext())
                .load(url)
                .transform(new CircleTransform())
                .config(Bitmap.Config.RGB_565)
                .into(view);
    }


}


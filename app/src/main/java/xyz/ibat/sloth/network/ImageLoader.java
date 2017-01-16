package xyz.ibat.sloth.network;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import xyz.ibat.sloth.SlothApplication;

/**
 * Created by jerry on 2017/1/14.
 */

public class ImageLoader {


    private ImageLoader(){

    }

    public static void load(String url , ImageView view){

        Picasso.with(SlothApplication.getContext())
                .load(url)
                .into(view);

    }


}


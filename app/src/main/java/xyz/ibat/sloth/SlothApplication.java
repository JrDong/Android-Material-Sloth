package xyz.ibat.sloth;

import android.app.Application;
import android.content.Context;

/**
 * Created by DongJr on 2016/4/1.
 */
public class SlothApplication extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();

    }

    public static Context getContext() {
        return mContext;
    }
}

package xyz.ibat.sloth.utils;

import android.widget.Toast;

import xyz.ibat.sloth.SlothApplication;

/**
 * Created by DongJr on 2016/4/1.
 */
public class SlothUtil {

    public static void showToast(String msg) {
        Toast.makeText(SlothApplication.getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}

package xyz.ibat.sloth.utils;

import android.widget.Toast;

import xyz.ibat.sloth.SlothApplication;

/**
 * Created by DongJr on 2017/1/13.
 */

public class T {

    private static Toast toast;

    private T() {

    }

    public static void show(int msg) {
        if (toast == null) {
            toast = Toast.makeText(SlothApplication.getContext(), msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }

    public static void show(String msg) {
        if (toast == null) {
            toast = Toast.makeText(SlothApplication.getContext(), msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }


}

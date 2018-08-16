package me.bvaleo.deepnoise.util;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

public class Util {

    public static void requestPermission(Activity activity, String permission) {
        if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(activity, permission)) {
            ActivityCompat.requestPermissions(activity, new String[]{permission}, 0);
        }
    }
}

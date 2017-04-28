package com.hjb.example;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;

/**
 * 作者 ： hjb
 * 时间 ： 2017/4/28.
 */

public class PermissionUtil {
    private String[] galleryPermissions = {
            "android.permission.WRITE_EXTERNAL_STORAGE",
            "android.permission.READ_EXTERNAL_STORAGE"
    };

    private String[] cameraPermissions = {
            "android.permission.CAMERA",
            "android.permission.WRITE_EXTERNAL_STORAGE",
            "android.permission.READ_EXTERNAL_STORAGE"
    };

    public String[] getGalleryPermissions(){
        return galleryPermissions;
    }

    public String[] getCameraPermissions() {
        return cameraPermissions;
    }

    public boolean verifyPermissions(int[] grantResults) {
        if(grantResults.length < 1){
            return false;
        }

        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    public boolean verifyPermissions(Context context, String[] grantResults) {
        for (String result : grantResults) {
            if (ActivityCompat.checkSelfPermission(context, result) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    public boolean checkMarshMellowPermission(){
        return(Build.VERSION.SDK_INT> Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    public boolean checkJellyBean(){
        return(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN);
    }
}

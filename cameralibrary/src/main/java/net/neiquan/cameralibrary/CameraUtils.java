package net.neiquan.cameralibrary;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 作者 ： hjb
 * 时间 ： 2017/4/28.
 */

public class CameraUtils {
    public static final int TAKE_PICTURE = 101;
    public static String photoPath;
    public static final int LOCAL_PICTURE = 102;

    public static void takePhoto(Activity ac, String appID) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File photoFile = null;
        if (takePictureIntent.resolveActivity(ac.getPackageManager()) != null) {
            try {
                photoFile = createImageFile(ac);
            } catch (IOException ex) {
            }
            if (photoFile != null) {

                Uri photoURI;
                if (Build.VERSION.SDK_INT >= 24) {
                    photoURI = FileProvider.getUriForFile(ac,
                            appID + ".provider", photoFile);
                } else {
                    photoURI = Uri.fromFile(photoFile);
                }
                takePictureIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                ac.startActivityForResult(takePictureIntent, TAKE_PICTURE);
            }
        }
        // Save a file: path for use with ACTION_VIEW intents
        if (Build.VERSION.SDK_INT >= 24) {
            photoPath = String.valueOf(FileProvider.getUriForFile(ac,
                    appID + ".provider", photoFile));
        } else {
            photoPath = String.valueOf(Uri.fromFile(photoFile));
        }
    }

    private static File createImageFile(Activity ac) throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";

        File storageDir = ac.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        return image;
    }
}

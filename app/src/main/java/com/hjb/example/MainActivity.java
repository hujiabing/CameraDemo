package com.hjb.example;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import net.neiquan.cameralibrary.CameraUtils;

import java.io.File;


public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private PermissionUtil permissionUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.iv_image);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (permissionUtil.checkMarshMellowPermission()) {
                    if (permissionUtil.verifyPermissions(MainActivity.this, permissionUtil.getCameraPermissions())) {
                         CameraUtils.takePhoto(MainActivity.this, BuildConfig.APPLICATION_ID);
                    } else{
                        ActivityCompat.requestPermissions(MainActivity.this, permissionUtil.getCameraPermissions(), CameraUtils.TAKE_PICTURE);
                    }
                } else
                     CameraUtils.takePhoto(MainActivity.this, BuildConfig.APPLICATION_ID);
            }
        });
        permissionUtil = new PermissionUtil();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == CameraUtils.TAKE_PICTURE && resultCode == Activity.RESULT_OK) {
                ImageUtil.LoadPicture(CameraUtils.photoPath, imageView);
            }
        }
    }

}

package com.belajar.flashlight.flashlight;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private CameraManager camera;
    private String cameraId;
    private ToggleButton tgl;
    private Boolean isTorchOn;
    int angka = 0;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.senternya);

        tgl = findViewById(R.id.togl);
        isTorchOn = false;

        // Buat ngecek ada fitur Flash nya atau ngga

        Boolean isFlashAvailable = getApplicationContext() .getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

        if(!isFlashAvailable) {
            AlertDialog alert = new AlertDialog.Builder(this).create();
            alert.setTitle("Can't Run");
            alert.setMessage("Your device doesn't support flashlight");
            alert.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                          finish();
                          System.exit(0);

                        }
                    });
                    alert.show();
                    // Buat dapetin akses FLASLIGHT
            }
            camera = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN_MR1) {
            try {
                cameraId=camera.getCameraIdList()[0];
            } catch (Exception e) {
                e.printStackTrace();
            }

        } tgl.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onClick(View v) {
            try {
                if (isTorchOn) {
                    turnOffFlashLihgt();
                    isTorchOn= false;
                } else {
                    turnOnFlashLight();
                    isTorchOn= true;
            }
            
        }catch (Exception e) {
                e.printStackTrace();
            }
            
    }
            @TargetApi(Build.VERSION_CODES.M)
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            private void turnOffFlashLihgt() {
            try {
                camera.setTorchMode(cameraId,false);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
            }

            @TargetApi(Build.VERSION_CODES.M)
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            private void turnOnFlashLight() {
                try {
                    camera.setTorchMode(cameraId,true);
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            }

        });
}}
package com.cloudlandfx.rek.flashlight;

import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private ImageButton mDroidButton;
    private CameraManager mCameraManager;
    private  String mCameraId;
    private boolean isTorchOn;
    private final String TORCH_STATE = "torch_state";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDroidButton = findViewById(R.id.ivDroidButton);
        isTorchOn = false;

        mCameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        checkForCamera();
        checkForTorch();

        if ( savedInstanceState != null ) {
            isTorchOn = savedInstanceState.getBoolean(TORCH_STATE);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putBoolean(TORCH_STATE, isTorchOn);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if ( isTorchOn ) {
            turnTorchOff();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if ( isTorchOn ) {
            turnTorchOff();
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if ( isTorchOn ) {
            turnTorchOff();
        }
    }

    /**
     * Check if device has a camera flash
     * @return
     */
    private boolean flashAvailable() {
        return getPackageManager().hasSystemFeature( PackageManager.FEATURE_CAMERA_FLASH );
    }

    /**
     * Check if device has an accessable camera
     */
    private void checkForCamera() {
        try {
            mCameraId = mCameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * Alert if torch not available
     */
    private void checkForTorch() {
        if ( !flashAvailable() ) {
            AlertDialog alerto = new AlertDialog.Builder(this)
                    .setTitle("Alert!")
                    .setMessage("Flashlight Not Avaliable")
                    .setPositiveButton("Bummer", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .show();
        }
    }

    /**
     * Toggle the state of the device torch
     * @param view  Button which called this function
     */
    public void toggleFlashlight(View view) {
        if (isTorchOn) {
            turnTorchOff();
        } else {
            turnTorchOn();
        }
    }

    /**
     * Turn the camera torch on and change button image
     */
    private void turnTorchOn() {
        try {
            mCameraManager.setTorchMode(mCameraId, true);
            mDroidButton.setImageResource(R.drawable.ic_android_orange_24dp);
            isTorchOn = true;
            } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * Turn the camera torch off and change button image
     */
    private void turnTorchOff() {
        try {
            mCameraManager.setTorchMode(mCameraId, false);
            mDroidButton.setImageResource(R.drawable.ic_android_black_24dp);
            isTorchOn = false;
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

}

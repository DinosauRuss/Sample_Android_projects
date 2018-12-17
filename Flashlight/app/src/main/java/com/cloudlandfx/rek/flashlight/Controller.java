package com.cloudlandfx.rek.flashlight;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.support.v7.app.AlertDialog;

public class Controller {

    private Context mContext;
    private CameraManager mCameraManager;
    private  String mCameraId;
    public boolean torchState;


    /**
     * Constructor for Controller class
     * @param context   Context of activity controlled by this class
     */
    public Controller(Context context) {
        this.mContext = context;

        mCameraManager = (CameraManager) mContext.getSystemService(Context.CAMERA_SERVICE);

        checkForCamera();
        checkForTorch();

        // Start with torch in off state
        turnTorchOff();
    }

    /**
     * Check if device has a camera flash
     * @return
     */
    private boolean flashAvailable() {
        return mContext.getPackageManager().hasSystemFeature( PackageManager.FEATURE_CAMERA_FLASH );
    }

    /**
     * Alert if torch not available
     */
    private void checkForTorch() {
        if ( !flashAvailable() ) {
            AlertDialog alerto = new AlertDialog.Builder(mContext)
                    .setTitle("Alert!")
                    .setMessage("Flashlight Not Avaliable")
                    .setPositiveButton("Bummer", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Close activity
                            Activity activo = (Activity) mContext;
                            activo.finish();
                        }
                    }).create();

            alerto.show();
        }
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
     * Turn the camera torch on and change button image
     */
    public void turnTorchOn() {
        try {
            mCameraManager.setTorchMode(mCameraId, true);
            torchState = true;
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * Turn the camera torch off
     */
    public void turnTorchOff() {
        try {
            mCameraManager.setTorchMode(mCameraId, false);
            torchState = false;
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

}

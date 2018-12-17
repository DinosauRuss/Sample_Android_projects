package com.cloudlandfx.rek.flashlight;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    // For Log.d
    private final String TAG = "MainActivity_tag";

    private ImageButton mDroidButton;
    private final String TORCH_STATE = "torch_state";

    private Controller mController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDroidButton = findViewById(R.id.ivDroidButton);
        mController = new Controller(this);

        if ( savedInstanceState != null ) {
            mController.torchState = savedInstanceState.getBoolean(TORCH_STATE);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putBoolean(TORCH_STATE, mController.torchState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if ( savedInstanceState != null ) {
            mController.torchState = savedInstanceState.getBoolean(TORCH_STATE);
        }
    }

    /**
     * Toggle the state of the device torch
     * @param view  Button which called this function
     */
    public void toggleFlashlight(View view) {
        if (mController.torchState) {
            torchOffWithImage();
        } else {
            torchOnWithImage();
        }
    }

    /**
     * Turn off torch and change button image to match
     */
    private void torchOffWithImage() {
        mController.turnTorchOff();
        mDroidButton.setImageResource(R.drawable.ic_android_black_24dp);
    }

    /**
     * Turn on torch and change button image to match
     */
    private void torchOnWithImage() {
        mController.turnTorchOn();
        mDroidButton.setImageResource(R.drawable.ic_android_orange_24dp);
    }

}

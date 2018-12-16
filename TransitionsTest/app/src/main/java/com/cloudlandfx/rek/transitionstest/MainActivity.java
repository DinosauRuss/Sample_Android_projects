package com.cloudlandfx.rek.transitionstest;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Methods {

    private ImageView mRedCircle;
    private ImageView mBlueLine;
    private ImageView mGreenDroid;
    private ImageView mYellowCircle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRedCircle = findViewById(R.id.ivRedCircle);
        mBlueLine = findViewById(R.id.ivBlueLine);
        mGreenDroid = findViewById(R.id.ivGreenDroid);
        mYellowCircle = findViewById(R.id.ivYellowSquare);

        recreateExplode(this, mRedCircle);
        recreateFade(this, mBlueLine);
        rotateView(mYellowCircle);
        startSharedElementActivity(this, mGreenDroid, mGreenDroid);
    }

}

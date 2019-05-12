package com.example.rek.customview.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.graphics.drawable.Animatable2Compat;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.rek.customview.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Avd2Fragment extends Fragment {

    private boolean circleDirty = false;
    private boolean circleAnimRunning = false;
    private boolean fingerprintDirty = false;
    private boolean fingerAnimRunning = false;

    public Avd2Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_avd2, container, false);

        ImageView imgCircle = v.findViewById(R.id.imgCircleAnim);
        imgCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animate((ImageView) v);
            }
        });


        ImageView imgFingerprint = v.findViewById(R.id.imgFingerAnim);
        imgFingerprint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animate((ImageView) v);
            }
        });
        return v;
    }


    private void animate(ImageView v) {

        switch (v.getId()) {
            case R.id.imgCircleAnim:
                if (!circleAnimRunning) {
                    resetCircleVector(v);
                }

                break;
            case R.id.imgFingerAnim:
                if (!fingerAnimRunning) {
                    resetFingerprintVector(v);
                }
                break;
        }
    }

    private void resetCircleVector(final ImageView v) {

        if (circleDirty) {
            AnimatedVectorDrawableCompat d = AnimatedVectorDrawableCompat.create(
                    v.getContext(), R.drawable.avd_circle_anim);
            v.setImageDrawable(d);
        } else {
            AnimatedVectorDrawableCompat avdc = (AnimatedVectorDrawableCompat) v.getDrawable() ;
            avdc.start();
            circleAnimRunning = true;

            avdc.registerAnimationCallback(new Animatable2Compat.AnimationCallback() {
                @Override
                public void onAnimationEnd(Drawable drawable) {
                    v.setImageDrawable(ResourcesCompat.getDrawable(
                            v.getResources(), R.drawable.ic_reset, null));
                    circleAnimRunning = false;
                }
            });
        }

        circleDirty = !circleDirty;
    }

    private void resetFingerprintVector(final ImageView v) {

        if (fingerprintDirty) {
            AnimatedVectorDrawableCompat d = AnimatedVectorDrawableCompat.create(
                    v.getContext(), R.drawable.avd_fingerprint_anim);
            v.setImageDrawable(d);
        } else {
            AnimatedVectorDrawableCompat avdc = (AnimatedVectorDrawableCompat) v.getDrawable();
            avdc.start();
            fingerAnimRunning = true;

            avdc.registerAnimationCallback(new Animatable2Compat.AnimationCallback() {
                @Override
                public void onAnimationEnd(Drawable drawable) {
                    v.setImageDrawable(ResourcesCompat.getDrawable(
                            getResources(), R.drawable.ic_reset, null));
                    fingerAnimRunning = false;
                }
            });
        }

        fingerprintDirty = !fingerprintDirty;
    }





}

package com.example.rek.customview.fragments;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.example.rek.customview.R;
import com.example.rek.customview.custom_views.MyDonutDrawable;

public class DonutFragment extends Fragment implements View.OnClickListener{

    private MyDonutDrawable donut;
    private ObjectAnimator animato;
    private boolean currentlyAnimatingFlag = false;

    public DonutFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_donut, container, false);

        donut = new MyDonutDrawable(80);

        ImageView donutView = v.findViewById(R.id.donutView);
        donutView.setImageDrawable(donut);

        SeekBar seekBar = v.findViewById(R.id.seekBar);
        donut.setScale((float) seekBar.getProgress() / seekBar.getMax());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                donut.setScale( (float) progress / seekBar.getMax() );
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        Button btnBaseColor = v.findViewById(R.id.btnBaseColor);
        btnBaseColor.setOnClickListener(this);
        Button btnIcingColor = v.findViewById(R.id.btnIcingColor);
        btnIcingColor.setOnClickListener(this);
        Button btnAnimate = v.findViewById(R.id.btnAnimate);
        btnAnimate.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBaseColor:
                donut.toggleBasePaintColor();
                break;
            case R.id.btnIcingColor:
                donut.toggleIcingPaintColor();
                break;
            case R.id.btnAnimate:
                if (currentlyAnimatingFlag) {
                    animato.cancel();
                    animato = null;
                    currentlyAnimatingFlag = false;
                } else {
                    animato = ObjectAnimator.ofFloat(
                            donut,
                            "sprinkleRotation",
                            0f,
                            1f);
                    animato.setRepeatMode(ValueAnimator.RESTART);
                    animato.setRepeatCount(ValueAnimator.INFINITE);
                    animato.setInterpolator(new LinearInterpolator());
                    animato.setDuration(3000);
                    animato.start();
                    currentlyAnimatingFlag = true;
                }
                break;
            default:
                break;
        }
    }

}

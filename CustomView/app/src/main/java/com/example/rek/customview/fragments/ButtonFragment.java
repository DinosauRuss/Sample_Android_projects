package com.example.rek.customview.fragments;


import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;

import com.example.rek.customview.R;
import com.example.rek.customview.custom_views.GreenView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ButtonFragment extends Fragment{

    GreenView gv;

    public ButtonFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_button, container, false);

        gv = v.findViewById(R.id.greenView);

        return v;
    }

}

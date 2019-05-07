package com.example.rek.customview.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.example.rek.customview.R;
import com.example.rek.customview.custom_views.MyDonutDrawable;

public class DonutFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public DonutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_donut, container, false);

        final MyDonutDrawable donut = new MyDonutDrawable(80);

        ImageView donutPlate = v.findViewById(R.id.platter);
        donutPlate.setImageDrawable(donut);

        SeekBar seekBar = v.findViewById(R.id.seekBar);
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

        return v;
    }

    public void onButtonPressed(Button b) {
        if (mListener != null) {
            mListener.onFragmentInteraction(b);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}

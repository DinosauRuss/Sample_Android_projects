package com.example.rek.customview.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.rek.customview.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectorFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public SelectorFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_selector, container, false);

        Button btnCounter = v.findViewById(R.id.btnCounter);
        btnCounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonPressed((Button) view);
            }
        });
        Button btnDonut = v.findViewById(R.id.btnDonut);
        btnDonut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonPressed((Button) view);
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

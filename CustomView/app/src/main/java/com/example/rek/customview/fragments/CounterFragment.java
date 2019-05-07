package com.example.rek.customview.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.rek.customview.custom_views.MyCounterView;
import com.example.rek.customview.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class CounterFragment extends Fragment {

    public CounterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_counter, container, false);

        final MyCounterView theCount = v.findViewById(R.id.theCount);

        Button add = v.findViewById(R.id.btnAdd);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                theCount.incrementCount();
            }
        });
        Button reset = v.findViewById(R.id.btnReset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                theCount.resetCount();
            }
        });
        Button decrement = v.findViewById(R.id.btnDecrement);
        decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                theCount.decrementCount();
            }
        });

        return v;
    }

}



package com.example.rek.customview.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.rek.customview.R;
import com.example.rek.customview.custom_views.DigitScroller;


public class ScrollCounterFragment extends Fragment {

    public ScrollCounterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_scroll_counter, container, false);

        final DigitScroller digitScroller = v.findViewById(R.id.digitScroller);

        Button btnIncrement = v.findViewById(R.id.btnIncrement);
        btnIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                digitScroller.increment();
            }
        });

        return v;
    }

}

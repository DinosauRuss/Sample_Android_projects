package com.example.rek.customview.fragments;

import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.rek.customview.R;


public class AvdFragment extends Fragment {

    private boolean sortedFlag = false;

    public AvdFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_avd, container, false);

        ImageView imgSort = v.findViewById(R.id.imgSort);
        imgSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animate((ImageView) view);
            }
        });

        return v;
    }

    private void animate(ImageView view) {
        Drawable d;
        if (sortedFlag) {
            d = getActivity().getDrawable(R.drawable.unsort_animator);
        } else {
            d = getActivity().getDrawable(R.drawable.sort_animator);
        }

        sortedFlag = !sortedFlag;
        view.setImageDrawable(d);;
        if (d instanceof AnimatedVectorDrawable) {
            ((AnimatedVectorDrawable) d).start();
        }
    }

}

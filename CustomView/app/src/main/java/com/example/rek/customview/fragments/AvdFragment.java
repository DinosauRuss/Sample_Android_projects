package com.example.rek.customview.fragments;

import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
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

        ImageView imgSort2 = v.findViewById(R.id.imgSort2);
        imgSort2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animate((ImageView) v);
            }
        });

        return v;
    }

    private void animate(ImageView view) {
        Drawable d = null;
        switch (view.getId()) {
            case R.id.imgSort:
                d = redrawFirstImageView();
                view.setImageDrawable(d);
                break;
            case R.id.imgSort2:
                d = animateSecondImageView();
                view.setImageDrawable(d);
                break;
        }
        sortedFlag = !sortedFlag;
        if (d instanceof AnimatedVectorDrawable) {
            ((AnimatedVectorDrawable) d).start();
        }
    }

    private Drawable redrawFirstImageView() {
        if (sortedFlag) {
            return ResourcesCompat.getDrawable(getResources(), R.drawable.avd_icon_unsort, null);
        } else {
            return ResourcesCompat.getDrawable(getResources(), R.drawable.avd_icon_sort, null);
        }
    }

    private Drawable animateSecondImageView() {
        if (sortedFlag) {
            return ResourcesCompat.getDrawable(getResources(), R.drawable.avd_anim_sort_to_black, null);
        } else {
            return ResourcesCompat.getDrawable(getResources(), R.drawable.avd_anim_sort_to_yellow, null);
        }
    }

}

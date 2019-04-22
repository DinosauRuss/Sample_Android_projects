package com.example.rek.fragmentrecyclerview;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


public class WelcomeFragment extends Fragment {

    public WelcomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_welcome, container, false);
        TextView tv = v.findViewById(R.id.tvWelcome);

        CharacterViewModel cvm = ViewModelProviders.of(getActivity()).get(CharacterViewModel.class);
        Character c = cvm.getCharacter();

        String welcomeText;
        if (c == null) {
            welcomeText = "Welcome nobody!";
        } else {
            String name = c.getName();
            welcomeText = getString(R.string.welcome) + name + "!";
        }
        tv.setText(welcomeText);

        return v;
    }

}

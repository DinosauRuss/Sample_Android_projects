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


public class WelcomeFragment extends Fragment {

    private TextView tvName;
    private Character mCharacter;
    CharacterViewModel mCharacterViewModel;

    public WelcomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_welcome, container, false);
        tvName = v.findViewById(R.id.tvWelcome);

        mCharacterViewModel = ViewModelProviders.of(getActivity()).get(CharacterViewModel.class);
        mCharacterViewModel.getCharacter().observe(this, new Observer<Character>() {
            @Override
            public void onChanged(@Nullable Character character) {
                mCharacter = character;
                String welcomeText;
                String name = mCharacter.getName();
                welcomeText = getString(R.string.welcome) + name + "!";
                tvName.setText(welcomeText);
            }
        });
        return v;
    }

}

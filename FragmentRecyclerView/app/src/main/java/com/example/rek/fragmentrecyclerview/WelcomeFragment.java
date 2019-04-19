package com.example.rek.fragmentrecyclerview;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class WelcomeFragment extends Fragment {

    public WelcomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_welcome, container, false);

        CharacterViewModel cvm = ViewModelProviders.of(getActivity()).get(CharacterViewModel.class);
        String name = cvm.getCharacter().getName();
        String welcomeText = "Welcome " + name + "!";
        TextView tv = v.findViewById(R.id.tvWelcome);
        tv.setText(welcomeText);

        // Return to RecyclerView fragment
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .popBackStack();
            }
        });

        return v;
    }

}

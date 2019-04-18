package com.example.rek.fragmentrecyclerview;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecyclerFragment extends Fragment {

    private CharacterViewModel mViewModel;

    public RecyclerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_recycler, container, false);

        RecyclerView recyclo = v.findViewById(R.id.recyclerView);
        recyclo.setLayoutManager(new LinearLayoutManager(getActivity()));
        final RecyclerAdapter adapto = new RecyclerAdapter( getActivity() );
        recyclo.setAdapter(adapto);

        mViewModel = ViewModelProviders.of(getActivity()).get(CharacterViewModel.class);
        mViewModel.getAllCharacters().observe(getActivity(), new Observer<List<Character>>() {
            @Override
            public void onChanged(@Nullable List<Character> characters) {
                // Update recyclerview
                adapto.setData(characters);
            }
        });

        FloatingActionButton fab = v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frag_container, new AddCharacterFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        return v;
    }

}

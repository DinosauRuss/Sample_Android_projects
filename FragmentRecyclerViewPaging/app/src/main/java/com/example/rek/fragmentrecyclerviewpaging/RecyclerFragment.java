package com.example.rek.fragmentrecyclerviewpaging;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;


public class RecyclerFragment extends Fragment {

    private CharacterViewModel mCharacterViewModel;
    private RecyclerAdapter adapto;

    public RecyclerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recycler, container, false);

        createRecyclerView(view);

        mCharacterViewModel = ViewModelProviders.of(getActivity()).get(CharacterViewModel.class);
        mCharacterViewModel.getAllCharacters().observe(getActivity(),
                new Observer<PagedList<Character>>() {
                    @Override
                    public void onChanged(@Nullable PagedList<Character> characters) {
                        adapto.submitList(characters);
                        Log.d("something", "onChanged");
                    }
                });

        return view;
    }

    /**
     * Creates the RecyclerView, adapter, and attaches ItemTouchHelper
     *
     * @param v The inflated view of the fragment
     */
    private void createRecyclerView(View v) {
        RecyclerView recyclo = v.findViewById(R.id.recyclerView);
        recyclo.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapto = new RecyclerAdapter(getActivity(), (RecyclerAdapter.ImageClickListener) getActivity());
        recyclo.setAdapter(adapto);

        ItemTouchHelper.SimpleCallback touchCallback = new ItemTouchHelper.SimpleCallback(
                0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                // Remove item
                int position = viewHolder.getAdapterPosition();
                Character c = adapto.getCharacterAtPosition(position);
                mCharacterViewModel.deleteCharacter(c);

                Toast.makeText(getActivity(), getString(R.string.character_deleted), Toast.LENGTH_SHORT).show();
            }
        };
        ItemTouchHelper helpo = new ItemTouchHelper(touchCallback);
        helpo.attachToRecyclerView(recyclo);
    }

}

package com.example.rek.fragmentrecyclerview;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;


public class RecyclerFragment extends Fragment implements RecyclerAdapter.ImageClickListener{

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
        mCharacterViewModel.getAllCharacters().observe(getActivity(), new Observer<List<Character>>() {
            @Override
            public void onChanged(@Nullable List<Character> characters) {
                // Update recyclerview
                adapto.submitList(characters);
            }
        });

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitle("Some Title");
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Needed to include menu in toolbar inside fragment
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_new, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuNew:
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frag_container, new AddCharacterFragment())
                        .addToBackStack(null)
                        .commit();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    /**
     * Creates the RecyclerView, adapter, and attaches ItemTouchHelper
     * @param v The inflated view of the fragment
     */
    private void createRecyclerView(View v) {
        RecyclerView recyclo = v.findViewById(R.id.recyclerView);
        recyclo.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapto = new RecyclerAdapter( getActivity(), this);
        recyclo.setAdapter(adapto);

        ItemTouchHelper.SimpleCallback touchCallback = new ItemTouchHelper.SimpleCallback(
                0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                // Remove item
                int position = viewHolder.getAdapterPosition();
                Character c = adapto.getCharacterAtPosition(position);
                mCharacterViewModel.deleteCharacter(c);

                Toast.makeText(getActivity(), "Character Deleted", Toast.LENGTH_SHORT).show();
            }
        };
        ItemTouchHelper helpo = new ItemTouchHelper(touchCallback);
        helpo.attachToRecyclerView(recyclo);
    }

    /**
     * Respond to clicks on welcome ImageView of each list_item
     * @param c The character from the list_item
     */
    @Override
    public void respondWelcomeImageclick(Character c) {
        mCharacterViewModel.setCharacter(c);

        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frag_container, new WelcomeFragment())
                .addToBackStack(null)
                .commit();
    }

    /**
     * Respond to clicks on edit ImageView of each list_item
     * @param c The character from the list_item
     */
    @Override
    public void respondEditImageclick(Character c) {
        mCharacterViewModel.setCharacter(c);

        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frag_container, new EditCharacterFragment())
                .addToBackStack(null)
                .commit();
    }
}

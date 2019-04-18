package com.example.rek.fragmentrecyclerview;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddCharacterFragment extends Fragment {

    CharacterViewModel cvm;

    public AddCharacterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_character, container, false);

        cvm = ViewModelProviders.of(getActivity()).get(CharacterViewModel.class);

        Button btnCancel = v.findViewById(R.id.btnAddCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Character not added", Toast.LENGTH_SHORT).show();
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        final EditText edtName = v.findViewById(R.id.edtNewName);
        final EditText edtColor = v.findViewById(R.id.edtNewColor);

        Button btnSave = v.findViewById(R.id.btnAddSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                String color = edtColor.getText().toString();
                if ( name.trim().length() > 0  &&  color.trim().length()> 0 ) {
                    // Add new character to room db
                    Character c = new Character(name, color);
                    cvm.insertCharacter(c);

                    // Return to recycler fragment
                    getActivity().getSupportFragmentManager().popBackStack();
                    Toast.makeText(getActivity(), "Character saved", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return v;
    }

}

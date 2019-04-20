package com.example.rek.fragmentrecyclerview;

import android.app.AlertDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AddCharacterFragment extends Fragment {

    CharacterViewModel characterViewModel;
    EditText edtName;
    EditText edtColor;

    public AddCharacterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_character, container, false);

        characterViewModel = ViewModelProviders.of(getActivity()).get(CharacterViewModel.class);

        edtName = v.findViewById(R.id.edtNewName);
        edtColor = v.findViewById(R.id.edtNewColor);

        Button btnCancel = v.findViewById(R.id.btnAddCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonCancel();
            }
        });

        Button btnSave = v.findViewById(R.id.btnAddSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSave();
            }
        });
        return v;
    }

    private void buttonSave() {
        String name = edtName.getText().toString();
        String color = edtColor.getText().toString();
        if ( name.trim().length() > 0  &&  color.trim().length()> 0 ) {
            // Add new character to room db
            Character c = new Character(name, color);
            characterViewModel.insertCharacter(c);

            // Show 'saving dialog'
            new saveAsyncTask(2000).execute();

            // Return to recycler fragment
            getActivity().getSupportFragmentManager().popBackStack();
        } else {
            buttonCancel();
        }
    }

    private void buttonCancel() {
        Toast.makeText(getActivity(), getString(R.string.character_not_added), Toast.LENGTH_SHORT).show();
        getActivity().getSupportFragmentManager().popBackStack();
    }

    private class saveAsyncTask extends AsyncTask<Void, Void, Void> {

        AlertDialog alertDialog;
        final int delayMillis;

        private saveAsyncTask(int delayMillis) {
            this.delayMillis = delayMillis;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            AlertDialog.Builder buildo = new AlertDialog.Builder(getActivity())
                    .setView(R.layout.dialog_saving);
            alertDialog = buildo.create();
            alertDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            long start = System.currentTimeMillis();
            while (System.currentTimeMillis() - start < delayMillis) {}
            alertDialog.dismiss();

            return null;
        }
    }

}

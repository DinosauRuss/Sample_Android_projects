package com.example.rek.fragmentrecyclerview;


import android.app.AlertDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class EditCharacterFragment extends Fragment {

    private CharacterViewModel mCharacterViewModel;
    private Character mCharacter;
    private EditText mEdtName;
    private EditText mEdtColor;
    private String mStartName;
    private String mStartColor;

    public EditCharacterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_edit_character, container, false);

        mCharacterViewModel = ViewModelProviders.of(getActivity()).get(CharacterViewModel.class);
        mCharacter = mCharacterViewModel.getCharacter();

        mEdtName = v.findViewById(R.id.edtEditName);
        mStartName = mCharacter.getName();
        mEdtName.setText(mStartName);
        mEdtColor = v.findViewById(R.id.edtEditColor);
        mStartColor = mCharacter.getColor();
        mEdtColor.setText(mStartColor);

        Button btnSave = v.findViewById(R.id.btnEditSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSave();
            }
        });

        Button btnCancel = v.findViewById(R.id.btnEditCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonCancel();
            }
        });

        return v;
    }

    private void buttonSave() {
        String currentName = mEdtName.getText().toString();
        String currentColor = mEdtColor.getText().toString();

        // If at least 1 field has changed and are both not empty
        if ( (!mStartName.equals(currentName)) || (!mStartColor.equals(currentColor)) ) {
            if (currentName.trim().length() > 0 && currentColor.trim().length() > 0) {
                mCharacter.setName(currentName);
                mCharacter.setColor(currentColor);
                mCharacterViewModel.updateCharacter(mCharacter);

                new saveAsyncTask(2000).execute();
                getActivity().getSupportFragmentManager().popBackStack();
            }
        } else {
            buttonCancel();
        }
    }

    private void buttonCancel() {
        Toast.makeText(getActivity(), getString(R.string.character_not_changed), Toast.LENGTH_SHORT).show();
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
            while ( (System.currentTimeMillis()-start) < delayMillis) {
                try {
                    wait(delayMillis);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            alertDialog.dismiss();

            return null;
        }
    }

}

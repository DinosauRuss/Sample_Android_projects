package com.example.rek.persistanceexample1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

public class NoteEditActivity extends AppCompatActivity {

    public static final String NOTE_UPDATED_TEXT = "note_updated";
    public static final String NOTE_UPDATED_ID = "note_updated_id";

    private EditText mEdtUpdateNote;
    private UpdateNoteViewModel mUpdateViewModel;
    private String mCurrentNoteid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);

        mEdtUpdateNote = findViewById(R.id.edtUpdateNote);
        mUpdateViewModel = ViewModelProviders.of(this).get(UpdateNoteViewModel.class);

        if ( getIntent().hasExtra(NoteListAdapter.ID) ) {
            String id = getIntent().getStringExtra(NoteListAdapter.ID);
            LiveData<Note> oldNote = mUpdateViewModel.getNote(id);
            oldNote.observe(this, new Observer<Note>() {
                @Override
                public void onChanged(Note note) {
                    mEdtUpdateNote.setText(note.getNoteText());
                    mCurrentNoteid = note.getId();
                }
            });
        }
    }

    /**
     * Callback from update button
     */
    public void updateNote(View view) {
        if ( TextUtils.isEmpty(mEdtUpdateNote.getText()) ) {
            setResult(RESULT_CANCELED);
        }
        else {
            String updatedNoteText = mEdtUpdateNote.getText().toString();

            Intent intento = new Intent();
            intento.putExtra(NOTE_UPDATED_TEXT, updatedNoteText);
            intento.putExtra(NOTE_UPDATED_ID, mCurrentNoteid);

            setResult(RESULT_OK, intento);
        }
        finish();
    }

    /**
     * Callback from cancel button
     */
    public void cancelUpdate(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }
}

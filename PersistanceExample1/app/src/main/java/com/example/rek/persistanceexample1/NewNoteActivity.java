package com.example.rek.persistanceexample1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewNoteActivity extends AppCompatActivity {

    public static final String NOTE_ADDED = "note_added";

    private EditText mEdtNewNote;
    private Button mBtnSaveNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        mEdtNewNote = findViewById(R.id.edtNewNote);
        mBtnSaveNote = findViewById(R.id.btnNewNote);
        mBtnSaveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento = new Intent();

                if ( TextUtils.isEmpty(mEdtNewNote.getText()) ) {
                    setResult(RESULT_CANCELED);
                }
                else {
                    String note = mEdtNewNote.getText().toString();
                    intento.putExtra(NOTE_ADDED, note);
                    setResult(RESULT_OK, intento);
                }
                finish();
            }
        });
    }

}

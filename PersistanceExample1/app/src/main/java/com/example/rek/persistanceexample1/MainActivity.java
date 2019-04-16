package com.example.rek.persistanceexample1;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;
import java.util.UUID;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class MainActivity extends AppCompatActivity implements NoteListAdapter.OnDeleteClickListener{

    private final String TAG = this.getClass().getSimpleName();
    private NoteViewModel mNoteViewModel;
    private static final int NEW_NOTE_ACTIVITY_RESULT_CODE = 1;
    public static final int UPDATE_NOTE_ACTIVITY_RESULT_CODE = 2;
    NoteListAdapter adapto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initializeRecyclerView();
        initializeViewModel();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(MainActivity.this, NewNoteActivity.class);
                startActivityForResult(intento, NEW_NOTE_ACTIVITY_RESULT_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == NEW_NOTE_ACTIVITY_RESULT_CODE) {
                // Insert new note
                final String newNoteId = UUID.randomUUID().toString();
                Note newNote = new Note(newNoteId, data.getStringExtra(NewNoteActivity.NOTE_ADDED));
                mNoteViewModel.insertNote(newNote);

                Toast.makeText(getApplicationContext(),
                        getString(R.string.toast_note_saved), Toast.LENGTH_SHORT).show();

            } else if (requestCode == UPDATE_NOTE_ACTIVITY_RESULT_CODE) {
                // Update note
                String id = data.getStringExtra(NoteEditActivity.NOTE_UPDATED_ID);
                String newText = data.getStringExtra(NoteEditActivity.NOTE_UPDATED_TEXT);

                Note note = new Note(id, newText);
                mNoteViewModel.updateNote(note);

                Toast.makeText(this, getString(R.string.toast_note_updated), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.toast_note_cancelled), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initializeRecyclerView() {
        RecyclerView recyclo = findViewById(R.id.recyclerView);
        adapto = new NoteListAdapter(this, this);
        recyclo.setAdapter(adapto);
        recyclo.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initializeViewModel() {
        mNoteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        mNoteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                adapto.setNotes(notes);
            }
        });
    }

    @Override
    public void onDeleteClicked(Note note) {
        mNoteViewModel.deleteNote(note);
    }
}

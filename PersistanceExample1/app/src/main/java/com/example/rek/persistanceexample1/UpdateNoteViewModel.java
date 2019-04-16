package com.example.rek.persistanceexample1;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class UpdateNoteViewModel extends AndroidViewModel {

    NoteRoomDatabase mNoteDb;
    NoteDAO mDao;

    public UpdateNoteViewModel(@NonNull Application application) {
        super(application);

        mNoteDb = NoteRoomDatabase.getInstance(application);
        mDao = mNoteDb.noteDAO();
    }

    public LiveData<Note> getNote(String id) {
        return mDao.getNote(id);
    }

}

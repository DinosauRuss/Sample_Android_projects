package com.example.rek.persistanceexample1;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


public class NoteViewModel extends AndroidViewModel {

    private String TAG = this.getClass().getSimpleName();
    private NoteDAO mNoteDao;
    private NoteRoomDatabase mNoteDb;
    private LiveData<List<Note>> mAllNotes;

    public NoteViewModel(@NonNull Application application) {
        super(application);

        mNoteDb = NoteRoomDatabase.getInstance(application);
        mNoteDao = mNoteDb.noteDAO();
        mAllNotes = mNoteDao.getAllNotes();
    }

    /**
     * Wrapper function for adding to database
     * @param note  Note to be added to database
     */
    public void insertNote(Note note) {
        new InsertAsyncTask(mNoteDao).execute(note);
    }

    /**
     * Wrapper function to retrieve contents from database
     * @return contents of database
     */
    LiveData<List<Note>> getAllNotes() {
        return mAllNotes;
    }

    void deleteNote(Note note) {
        new DeleteAsyncTask(mNoteDao).execute(note);
    }

    void updateNote(Note note) {
        new UpdateNoteAsyncTask(mNoteDao).execute(note);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.i(TAG, "ViewModel cleared");
    }


    private static class InsertAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDAO mDao;

        private InsertAsyncTask(NoteDAO dao) {
            this.mDao = dao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            mDao.addNote(notes[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDAO mNoteDao;

        private DeleteAsyncTask(NoteDAO dao) {
            mNoteDao = dao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            mNoteDao.deleteNote(notes[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDAO mNoteDao;

        private UpdateNoteAsyncTask(NoteDAO dao) {
            mNoteDao = dao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            mNoteDao.updateNote(notes[0]);

            return null;
        }
    }

}

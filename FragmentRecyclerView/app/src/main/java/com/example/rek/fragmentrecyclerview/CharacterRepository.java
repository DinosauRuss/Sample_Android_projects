package com.example.rek.fragmentrecyclerview;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class CharacterRepository {

    private CharacterDao mDao;
    private LiveData<List<Character>> mData;

    public CharacterRepository(Application application) {
        CharacterRoomDatabase db = CharacterRoomDatabase.newInstance(application);
        mDao = db.characterDao();
        mData = mDao.getAllCharacters();
    }

    public void insertCharacter(Character character) {
        new InsertCharacterAsyncTask(mDao).execute(character);
    }

    public void deleteCharacter(Character character) {
        new DeleteCharacterAsyncTask(mDao).execute(character);
    }

    public void updateCharacter(Character character) {
        new UpdateCharacterAsyncTask(mDao).execute(character);
    }

    public LiveData<List<Character>> getAllCharacters() {
        return mData;
    }


    private static class InsertCharacterAsyncTask extends AsyncTask<Character, Void, Void> {

        private CharacterDao mDao;

        InsertCharacterAsyncTask(CharacterDao dao) {
            this.mDao = dao;
        }

        @Override
        protected Void doInBackground(Character... characters) {
            mDao.insertCharacter(characters[0]);
            return null;
        }
    }

    private static class DeleteCharacterAsyncTask extends AsyncTask<Character, Void, Void> {

        private CharacterDao mDao;

        DeleteCharacterAsyncTask(CharacterDao dao) {
            this.mDao = dao;
        }

        @Override
        protected Void doInBackground(Character... characters) {
            mDao.deleteCharacter(characters[0]);
            return null;
        }
    }

    private static class UpdateCharacterAsyncTask extends AsyncTask<Character, Void, Void> {

        private CharacterDao mDao;

        UpdateCharacterAsyncTask(CharacterDao dao) {
            this.mDao = dao;
        }

        @Override
        protected Void doInBackground(Character... characters) {
            mDao.updateCharacter(characters[0]);
            return null;
        }
    }

}

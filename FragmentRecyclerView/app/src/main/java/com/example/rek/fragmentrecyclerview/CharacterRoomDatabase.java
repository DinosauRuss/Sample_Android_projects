package com.example.rek.fragmentrecyclerview;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;


@Database(entities = {Character.class}, version = 1)
public abstract class CharacterRoomDatabase extends RoomDatabase {

    public abstract CharacterDao characterDao();

    private static volatile CharacterRoomDatabase INSTANCE;

    public static synchronized CharacterRoomDatabase newInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    CharacterRoomDatabase.class, "character_db")
                    .fallbackToDestructiveMigration()
                    .addCallback(sCharacterDbCallback)
                    .build();
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sCharacterDbCallback= new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            new PopulateDbAsyncTask(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private CharacterDao dao;

        String[][] characters = {
                {"Rizzo", "brown"},
                {"Dinger", "green"},
                {"Kermit", "green"},
                {"Fozzie", "orange"},
                {"Piggy", "pink"},
                {"Gonzo", "grey"},
                {"Penelope", "red"},
                {"Scooter", "yellow"},
                {"Rowlf", "brown"},
                {"Grimace", "purple"},
                {"Beauregard", "pink"},
                {"Billy Bob", "pink"},
                {"Camila", "white"},
                {"Petunia", "white"},
                {"Ratso", "brown"},
                {"Beaker", "yellow"},
                {"Bunsen", "green"},
                {"Waldorf", "pink"},
                {"Bobo", "brown"},
                {"Sam", "blue"},
                {"Constantine", "brown"}
        };

        PopulateDbAsyncTask(CharacterRoomDatabase db) {
            this.dao = db.characterDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            for (String[] muppet : this.characters) {
                dao.insertCharacter( new Character(muppet[0], muppet[1]) );
            }
            return null;
        }
    }

}

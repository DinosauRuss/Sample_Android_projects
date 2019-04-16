package com.example.rek.persistanceexample1;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Note.class, version = 1)
public abstract class NoteRoomDatabase extends RoomDatabase {

    public abstract NoteDAO noteDAO();
    private static volatile NoteRoomDatabase INSTANCE;

    static NoteRoomDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (NoteRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, NoteRoomDatabase.class, "note_db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }


}

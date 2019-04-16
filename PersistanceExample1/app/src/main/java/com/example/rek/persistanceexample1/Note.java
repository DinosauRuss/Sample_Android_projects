package com.example.rek.persistanceexample1;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class Note {

    @PrimaryKey
    @NonNull
    private String mId;

    @NonNull
    @ColumnInfo(name = "note_text")
    private String mNoteText;

    public Note(String id, String noteText) {
        this.mId = id;
        this.mNoteText = noteText;
    }

    public String getId() {
        return mId;
    }

    public String getNoteText() {
        return mNoteText;
    }
}

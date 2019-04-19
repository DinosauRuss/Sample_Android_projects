package com.example.rek.fragmentrecyclerview;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.widget.LinearLayout;

import java.util.List;


@Dao
public interface CharacterDao {

    @Insert
    public void insertCharacter(Character character);

    @Delete
    public void deleteCharacter(Character character);

    @Update
    public void updateCharacter(Character character);

    @Query("SELECT * FROM character_table")
    LiveData<List<Character>> getAllCharacters();

    @Query("SELECT * FROM character_table WHERE id=:id")
    public LiveData<Character> getCharacterById(int id);

}

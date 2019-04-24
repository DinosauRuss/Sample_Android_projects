package com.example.rek.fragmentrecyclerviewpaging;

import android.arch.lifecycle.LiveData;
import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
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
//    LiveData<List<Character>> getAllCharacters();
    DataSource.Factory<Integer, Character> getAllCharacters();

}
